package com.cheerchip.bletestlong.ble;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.cheerchip.bletestlong.App;
import com.cheerchip.bletestlong.utils.BluetoothController;
import com.cheerchip.bletestlong.utils.Constant;
import com.cheerchip.bletestlong.utils.SongNameItem;
import com.cheerchip.bletestlong.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.cheerchip.bletestlong.App.app;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class SppDecodeHolder {

    public static final String TAG = "Log.SppDecodeHolder";
    public static String fileName;
    public static long nowOrder;
    private static int version;
    static String otherSong="";

    private static Map<Integer,Object> deviceData = new HashMap<>();

    public final static int SAME_FLAG = 10000;

    /** 如里是同样的类型,可以在类型后面加上10000,如同一个获取指令,A0,A1,第一位0代表睡眠铃声类型 ,第一位1代表闹钟铃声类型 */
    static {
        deviceData.put(SppProc.CMD_TYPE.BTR_GET_ALARM_TIP_TYPE.value()+SAME_FLAG, new byte[]{0,0});
        deviceData.put(SppProc.CMD_TYPE.BTR_GET_ALARM_TIP_TYPE.value(), new byte[]{0,0});
    }

    public static byte[] getDeviceData(int type){
        return (byte[]) deviceData.get(type);
    }


    private static Application app;
    public static void setApp(Application a) {
        app = a;
    }

    //回应包  暂时没用到

    /**
     *
     * @param cmd_byte 指令类型
     * @param check
     * @param cmd_data
     * @return
     */
    private static byte[] notUseEncodeCheckCmd(SppProc.CMD_TYPE cmd_byte, byte check, byte[] cmd_data){
        //1.确定包长度,并把固定的数据加上
        byte[] cmd;
        if(cmd_data == null){
            cmd = new byte[9];
        }else{
            cmd = new byte[7 + cmd_data.length];
            for(int i=0; i<cmd_data.length; i++){
                cmd[i + 6] = cmd_data[i];//从第7位把数据放进去  索引第6个数也就是第七位。
            }
        }
        cmd[0] = SppProc.PACK_PREFIX;// 包头
        cmd[1] = (byte) ((cmd.length - 4) & 0xFF);// 包长度数据低位
        cmd[2] = (byte) (((cmd.length - 4) >>> 8) & 0xFF);// 包长度数据高位
        cmd[3] = (byte) 0x04; //
        cmd[4] = (byte) (cmd_byte.value() & 0xFF);// 命令类型
        cmd[5] = check;// 包确认标识
        cmd[cmd.length - 1] = SppProc.PACK_SUFFIX;// 包尾
        // 2.计算校验和
        int CheckSum = 0;
        for (int i = 1; i < cmd.length - 3; i++) {// 这里不确定是-3还是减2
            CheckSum += (cmd[i] & 0xFF);
            CheckSum &= 0xFFFF;
        }
        cmd[cmd.length - 3] = (byte) (CheckSum & 0xFF);
        cmd[cmd.length - 2] = (byte) ((CheckSum >>> 8) & 0xFF);

        //3.转义
        byte[] sendBuf = new byte[cmd.length * 2 - 2];
        int count = 0;
        sendBuf[count++] = SppProc.PACK_PREFIX;//包头
        for (int i = 1; i < cmd.length - 1; i++) {
            if (cmd[i] == SppProc.PACK_PREFIX) {
                sendBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                sendBuf[count++] = SppProc.PACK_REPLACE_A;
            } else if (cmd[i] == SppProc.PACK_SUFFIX) {
                sendBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                sendBuf[count++] = SppProc.PACK_REPLACE_B;
            } else if (cmd[i] == SppProc.PACK_REPLACE_PREFFIX) {
                sendBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                sendBuf[count++] = SppProc.PACK_REPLACE_C;
            } else {
                sendBuf[count++] = cmd[i];
            }
        }
        sendBuf[count++] = SppProc.PACK_SUFFIX;//包尾

        // 4.截去多余的空byte,得到实际发送的数据
        byte[] sendCmd = new byte[count];
        for (int index = 0; index < count; index++) {
            sendCmd[index] = sendBuf[index];
        }
        Log.i(TAG , "所发送的回应包是----- : " + StringUtils.getHex(sendCmd));
        return sendCmd;
    }


    /**
     * 发送包用
     * @param cmd_byte 指令类型
     * @param data     具体指令
     * @return
     */
    public static byte[] encodeCmd(SppProc.CMD_TYPE cmd_byte, byte[] data) {

        Log.e("Lin.SppDecode","发包类型为:"+StringUtils.byte2Hex(new byte[]{(byte) (cmd_byte.value())})+"  发送数据为:"+StringUtils.byteArray2Hex(data));

        // 1.确定包长度,并 +加头+加尾+包长度+命令类型
        byte[] cmd;
        if (data == null) {
            cmd = new byte[7];
        } else {
            cmd = new byte[7 + data.length];
            for (int i = 0; i < data.length; i++) {
                cmd[i + 4] = data[i];// 从第五位开始把数据放进去
            }
        }
        cmd[0] = SppProc.PACK_PREFIX;// 包头
        cmd[1] = (byte) ((cmd.length - 4) & 0xFF);// 包长度数据低位
        cmd[2] = (byte) (((cmd.length - 4) >>> 8) & 0xFF);// 包长度数据高位
        cmd[3] = (byte) (cmd_byte.value() & 0xFF);// 命令类型
        cmd[cmd.length - 1] = SppProc.PACK_SUFFIX;// 包尾

        // 2.计算校验和
        int CheckSum = 0;
        for (int i = 1; i < cmd.length - 3; i++) {// 这里不确定是减3还是减2
            CheckSum += (cmd[i] & 0xFF);
            CheckSum &= 0xFFFF;
        }
        cmd[cmd.length - 3] = (byte) (CheckSum & 0xFF);
        cmd[cmd.length - 2] = (byte) ((CheckSum >>> 8) & 0xFF);

        // 转义之前的数据
        // Log.i(Tag , "转义之前的数据 : " + StringUtils.getHex(cmd));

        // 3.转义 :
        // 除包头包尾外,其它数据中出现0X01，则需经过转义变为两个字节：0X03和0x04；如果出现0X02，则须转义为0X03和0X05；如果数据中出现0X03，将需转义为0X03
        // 和0X06


        byte[] sendBuf = new byte[cmd.length * 2 - 2];//有疑问？

        int count = 0;
        sendBuf[count++] = SppProc.PACK_PREFIX;
        for (int i = 1; i < cmd.length - 1; i++) {
            if (cmd[i] == SppProc.PACK_PREFIX) {
                sendBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                sendBuf[count++] = SppProc.PACK_REPLACE_A;
            } else if (cmd[i] == SppProc.PACK_SUFFIX) {
                sendBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                sendBuf[count++] = SppProc.PACK_REPLACE_B;
            } else if (cmd[i] == SppProc.PACK_REPLACE_PREFFIX) {
                sendBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                sendBuf[count++] = SppProc.PACK_REPLACE_C;
            } else {
                sendBuf[count++] = cmd[i];
            }
        }
        sendBuf[count++] = SppProc.PACK_SUFFIX;
        // 转义之前的数据
        // Log.i(Tag , "转义之后->的数据 : " + StringUtils.getHex(sendBuf));

        // 4.截去多余的空byte,得到实际发送的数据
        byte[] sendCmd = new byte[count];
        for (int index = 0; index < count; index++) {
            sendCmd[index] = sendBuf[index];
        }


//		Intent intent = new Intent();
//		intent.setAction("Text.EQ");
//		intent.putExtra("Extra.Text.EQ", sendCmd);

        Log.i(TAG , "实际发送->的数据 : " + StringUtils.getHex(sendCmd));

        return sendCmd;
    }

    /**
     * app接收数据
     * 音箱返回数据
     * @param readDates
     * @param length
     */
    public static void decodeData(byte[] readDates, int length) {

        if (readDates == null || length < 9) {
            return;
        }

        byte[] readBufk = new byte[length];
        for (int i = 0; i < length; i++) {
            readBufk[i] = readDates[i];
        }



        // 1.拿到整包数据
        int flag_prefix = -1;
        byte[] packet = null;
        for (int i = 0; i < readBufk.length; i++) {
            if (readBufk[i] == SppProc.PACK_PREFIX) {
                flag_prefix = i;
            } else if (readBufk[i] == SppProc.PACK_SUFFIX) {
                if (flag_prefix != -1) {
                    packet = new byte[i - flag_prefix + 1];
                    for (int j = 0; j < packet.length; j++) {
                        packet[j] = readBufk[flag_prefix++];
                    }
                }
            }
        }
        if (packet == null || packet.length < 9) {// 如果查找不到包头和包尾则为空
            return;
        }

        // 2.去除转义字符
        readBufk = escapeReadCmd(packet);

        if (readBufk == null || readBufk.length < 9) {// 转义有错为空
            return;
        }

        // 3. 计算校验和
        int CheckSum = 0;
        for (int i = 1; i < readBufk.length - 3; i++) {// 这里不确定是-3还是减2
            CheckSum += (readBufk[i] & 0xFF);
            CheckSum &= 0xFFFF;
        }
        int cmdCheckSum = getIntInByteArray(readBufk, readBufk.length - 3);

        if (CheckSum != cmdCheckSum) {
            Log.e(TAG , "校验错误 : " + "CheckSum==" + CheckSum + "---cmdCheckSum==" + cmdCheckSum);
            return;
        }

        Log.i(TAG , "实际解码后的数据为 : " + StringUtils.getHex(readBufk));

        // 4.获取命令类型.
        int Cmd = getByteInByteArray(readBufk, 3);// 命令回应确认包 0x04
        int cmd_type = getByteInByteArray(readBufk, 4);// 命令类型
        boolean isSuccess = (getByteInByteArray(readBufk, 5) == SppProc.PACK_ACCEPT);// 包确认标识



        if (Cmd == SppProc.CMD_TYPE.BTR_COMMAND_CHECK.value() && isSuccess) {
            Intent intent = new Intent();
            Log.i("Lin.SppDecode","接收类型为:"+StringUtils.byte2Hex(new byte[]{(byte) cmd_type})+"  接收数据为:"+StringUtils.byteArray2Hex(getReadPackageData(readBufk)));
            switch (cmd_type) {
                case 0x05:
                case 0x06:
                   /* int workMode = getByteInByteArray(readBufk, 6);
                    App.getInstance().putAppMapElement(Constant.MAP_CURRENT_WORKMODE, workMode);
                    intent.setAction(Constant.ACTION_GET_WORKMODE);
                    intent.putExtra(Constant.EXTRA_GET_WORKMODE, workMode);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG,"工作模式为-->"+workMode);
                    break;*/

                case 0x07://歌曲名
                case 0x08:
                    int songFlag = getByteInByteArray(readBufk, 6);
                    BluetoothController.getInstance().write(encodeCmd(SppProc.CMD_TYPE.BTR_COMMAND_CHECK, new byte[]{(byte)cmd_type,(byte)0x55}));
                    decodeSongPackage(readBufk, songFlag, cmd_type);
                    Log.i(TAG,"获取歌曲名为-->"+songFlag);

                    break;

                case 0x09://音乐列表
                    int listFlag = getByteInByteArray(readBufk, 6);
                    Log.i(TAG,"包标识为-->"+listFlag);
                    BluetoothController.getInstance().write(encodeCmd(SppProc.CMD_TYPE.BTR_COMMAND_CHECK, new byte[]{(byte)cmd_type,(byte)0x55}));//必须发回应包
                    decodeSongPackage(readBufk, listFlag, cmd_type);
                    break;

//			case 0x0B://设置音量如果连设置的时候也监听,那么进度条会卡
                case 0x0C://获取音量
                    int vol = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_VOL);
                    intent.putExtra(Constant.EXTRA_GET_VOL, vol);
                    App.getInstance().sendBroadcast(intent);
                    break;



                case 0xE:
                case 0xD:
                    int setStatus = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_PLAYSTATUS);
                    intent.putExtra(Constant.EXTRA_GET_PLAYSTATUS, setStatus);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "设置录音播放状态 : "+setStatus);
                    break;

                case 0x13:
                case 0x14://播放模式
                    int playMode = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_PLAYMODE);
                    intent.putExtra(Constant.EXTRA_GET_PLAYMODE, playMode);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "播放模式 : " +playMode);
                    break;

                case 0x17:
                    int musicID = readBufk[6] + readBufk[7]*256;
                    intent.setAction(Constant.ACTION_GET_MUSICID);
                    intent.putExtra(Constant.EXTRA_GET_MUSICID,musicID);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "当前ID : " +musicID);
                    break;

                case 0x19://列表发完
                   /* intent.setAction(Constant.ACTION_BTR_LISTOVER);
                    App.getInstance().sendBroadcast(intent);
                    App.getInstance().putAppMapElement(Constant.MAP_KEY_LIST_IS_OVER, true);
                    Log.i(TAG , "列表发完 : ");*/
                    break;

                case 0x1A:
                  /*  intent.setAction(Constant.ACTION_BTR_TFCHANGE);
                    int tf_status = getByteInByteArray(readBufk, 6);
                    if(tf_status == 0x01){
                        App.getInstance().putAppMapElement(Constant.MAP_TF_STATUS, true);
                    }else{
                        App.getInstance().putAppMapElement(Constant.MAP_TF_STATUS, false);
                    }
                    intent.putExtra(Constant.EXTRA_BTR_TFCHANGE, tf_status);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "TF卡有插拔动作: "+tf_status );*/
                    break;

                case 0x1B:
                    byte deviceStatus =readBufk[6];
                    if((deviceStatus >> 1 & 0x1) == 0x1){
                        App.getInstance().putAppMapElement(Constant.MAP_TF_STATUS, true);
                    }else{
                        App.getInstance().putAppMapElement(Constant.MAP_TF_STATUS, false);
                    }
                    intent.setAction(Constant.ACTION_GET_DEVICESTATUS);
                    intent.putExtra(Constant.EXTRA_GET_DEVICESTATUS, deviceStatus);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "得到外设状态 : " +deviceStatus);
                    break;

                case 0x6F:
                    intent.setAction(Constant.BT_GET_DEVICE_VERSION);
                    App.getInstance().sendBroadcast(intent);
                    String version = readBufk[7]+"."+readBufk[6];
                    Log.i(TAG,"得到设备版本号后发设置设备时间==version=="+version);
                    break;

                case 0x2B:
                    byte[] alrm_setData = new byte[5];
                    for (int i = 0; i < alrm_setData.length; i++) {
                        alrm_setData[i] = readBufk[6 + i];
                    }
                    intent.setAction(Constant.ACTION_SET_ALARM);
                    intent.putExtra(Constant.EXTRA_SET_ALARM, alrm_setData);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "设置音乐闹钟信息 : " + StringUtils.getHex(alrm_setData));
                    break;
                case 0x2c:
                    byte[] alrm_data = new byte[5];
                    for (int i = 0; i < alrm_data.length; i++) {
                        alrm_data[i] = readBufk[6 + i];
                    }
                    intent.setAction(Constant.ACTION_GET_ALARM);
                    intent.putExtra(Constant.EXTRA_GET_ALARM, alrm_data);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "获取音乐闹钟信息 : " + StringUtils.getHex(alrm_data));
                    break;

                case 0x2D:
                    int getStatus = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_BTR_RECORDSTATA);
                    intent.putExtra(Constant.EXTRA_BTR_RECORDSTATA, getStatus);
                    if(getStatus == 1){//当录音正在录音状态时返回当前录音时间
                        byte[] recondTime = new byte[readBufk.length-9-1];
                        for(int i=0; i<recondTime.length; i++){
                            recondTime[i] = readBufk[i+7];
                        }
                        intent.putExtra(Constant.EXTRA_GET_PLAYSTATUS_TIME,recondTime);
                        Log.i(TAG,"录音时间");
                    }
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG,"获得录音状态=="+getStatus);
                    break;

                case 0x30:
                case 0x31:
                    Log.i(TAG,"关机时间为=="+readBufk[6]+":分:"+readBufk[7]);
                    byte[] closeTime = new byte[3];
                    closeTime[0] = readBufk[6];
                    closeTime[1] = readBufk[7];
                    closeTime[2] = (byte) cmd_type;
                    intent.setAction(Constant.ACTION_GET_CLOSETIME);
                    intent.putExtra(Constant.EXTRA_GET_CLOSETIME, closeTime);

                    App.getInstance().sendBroadcast(intent);
                    break;

                case 0x36:
                    int recordMode = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_SET_RECORDSTATE);
                    intent.putExtra(Constant.EXTRA_SET_RECORDSTATE, recordMode);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "播放模式 : " +recordMode);
                    break;

                case 0x37:
//			case 0x38:
                    byte[] temperature = getByteInByteArray(readBufk, 6, 7);
                    intent.setAction(Constant.ACTION_GET_TEMPERATURE);
                    intent.putExtra(Constant.EXTRA_GET_TEMPERATURE, temperature);
                    App.getInstance().sendBroadcast(intent);
                    break;

                case 0x61:// 获取灯光模式
                case 0x60:// 设置灯光模式
                    int lightMode = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_LIGHTMODE);
                    intent.putExtra(Constant.EXTRA_GET_LIGHTMODE, lightMode);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "获取灯光模式 : " + StringUtils.getHex(readBufk));
                    break;

                case 0x62:
                    int[] lightcolor = new int[3];
                    lightcolor[0] = getByteInByteArray(readBufk, 6);
                    lightcolor[1] = getByteInByteArray(readBufk, 7);
                    lightcolor[2] = getByteInByteArray(readBufk, 8);
                    intent.setAction(Constant.ACTION_GET_LIGHTCOLOR);
                    intent.putExtra(Constant.EXTRA_GET_LIGHTCOLOR, lightcolor);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "获取灯光颜色 ---------->: " + StringUtils.getHex(readBufk));
                    break;

                case 0x65:
                    int lightLevel = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_LIGHTLEVEL);
                    intent.putExtra(Constant.EXTRA_GET_LIGHTLEVEL, lightLevel);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "获取的灯光亮度 : " + lightLevel);
                    break;

                case 0x67:
                    intent.setAction(Constant.ACTION_SET_NIGHTLIGHT);
                    App.getInstance().sendBroadcast(intent);
                    break;

                case 0x70:
                    int boxMode = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_SET_BOXMODE);
                    intent.putExtra(Constant.EXTRA_SET_BOXMODE, boxMode);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "点阵屏设置显示模式 : " +boxMode);
                    break;

                case 0x72:
                    intent.setAction(Constant.ACTION_SET_BOX_DATA);
                    App.getInstance().sendBroadcast(intent);
                    break;

                case 0x75:
                case 0x76:
                    int boxSpeed = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_BOX_SPEED);
                    intent.putExtra(Constant.EXTRA_GET_BOX_SPEED, boxSpeed);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "点阵屏设置的速度为: " +boxSpeed);
                    break;

                case 0x77:
                case 0x78:
                    int boxSpectrum = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_BOX_SPECTRUM);
                    intent.putExtra(Constant.EXTRA_GET_BOX_SPECTRUM, boxSpectrum);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "点阵屏设置频谱为===: " +boxSpectrum);
                    break;

                //设置闹钟铃声
                case 0xA0:

                  /*  byte[] ring_data= new byte[2];
                    for (int i = 0; i < ring_data.length; i++) {
                        ring_data[i] = readBufk[i+6];
                    }
                    intent.setAction(Constant.ACTION_GET_ALARM_RING_TYPE);
                    intent.putExtra(Constant.EXTRA_ACTION_GET_ALARM_RING_TYPE, ring_data);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "设置音乐闹钟信息 : " + StringUtils.getHex(ring_data));
*/
                    break;

                case 0xA1:
                    byte[] sleepType = getByteInByteArray(readBufk, 6,7);
                    if(sleepType[0] == 0){
                        deviceData.put(SppProc.CMD_TYPE.BTR_GET_ALARM_TIP_TYPE.value()+SAME_FLAG, sleepType);
                    }else{
                        deviceData.put(SppProc.CMD_TYPE.BTR_GET_ALARM_TIP_TYPE.value(), sleepType);
                    }

                    intent.setAction(Constant.ACTION_GET_SLEEP_DATA);
                    intent.putExtra(Constant.EXTRA_GET_SLEEP_DATA, sleepType);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "点阵屏设置频谱为===: " +sleepType);

                    break;

                case 0xA2:
                    byte [] auditionType=getByteInByteArray(readBufk,6,7);
                    if (auditionType[0]==0){
                        //睡眠状态
                    }else{
                        //试听
                    }
                    intent.setAction(Constant.ACTION_GET_AUDITIONTYPE);
                    intent.putExtra(Constant.EXTRA_GET_AUDITIONTYPE,auditionType);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "闹钟试听===: " +auditionType);
                    break;


                //对箱连接状态（0xC0，0xC1） 0断开，1连接 C0是set C1是get




                //set
                case 0xC0:
                    break;
                case 0xC1:
                    int awsType = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_AWSTYPE_STATE);
                    intent.putExtra(Constant.EXTRA_GET_AWSTYPE_STATE, awsType);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "对箱连接状态===: " +awsType);
                    break;


                //对箱的左右声道 0默认 0,1
                case 0xC2:
                    break;
                case 0xC3:
                    int awsLR = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_AWS_AWSLR);
                    intent.putExtra(Constant.EXTRA_GET_AWS_AWSLR, awsLR);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "对箱的左右声道===: " +awsLR);
                    break;


                //设置对箱模式
                case 0x0f:
                    break;
                case 0x10:
                    int awsEQ = getByteInByteArray(readBufk, 6);
                    intent.setAction(Constant.ACTION_GET_AWS_AWSEQ);
                    intent.putExtra(Constant.EXTRA_GET_AWS_AWSEQ, awsEQ);
                    App.getInstance().sendBroadcast(intent);
                    Log.i(TAG , "对箱的EQ模式===: " +awsEQ);
                    break;
            }

        }
        else {
            // 包拒绝
            Log.e(TAG , "包拒绝----------------------------- : " );
        }

    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private static byte[] getReadPackageData(byte[] readBufk){
        if(readBufk.length <= 9){
            return null;
        }else{
            byte[] data = new byte[readBufk.length-9];
            for(int i=0; i<data.length; i++){
                data[i] = readBufk[i+6];
            }
            return data;
        }
    }


    //128,129,130都是规定好的。请知晓。
    private static void decodeSongPackage(byte[] readBufk, int packFlag, int sendFlag) {
        if(readBufk.length < 14){	//有可能刚等于14
            Log.e(TAG,"接收的包有异常-----------歌曲名---------------------");
            return;
        }
        Intent intent;
        if(packFlag==128){	//第一个包(无后续包)
            songData2String(readBufk,true);
            if(sendFlag==0x09){
                SongNameItem.songNames.add(otherSong);
                intent = new Intent(Constant.ACTION_GET_MUSICLIST);
//				intent.putExtra(Constant.EXTRA_GET_MUSICLIST, otherSong);
                App.getInstance().sendBroadcast(intent);
            }
            if(sendFlag==0x07 || sendFlag == 0x08){
                intent = new Intent(Constant.ACTION_GET_MUSICNAME);
                intent.putExtra(Constant.EXTRA_GET_MUSICNAME, otherSong);
                App.getInstance().sendBroadcast(intent);
            }

        }else{
            if(packFlag==0){	//第一个包(有后续包)
                songData2String(readBufk,true);
            }
            else if(packFlag==1){//是后续包
                songData2String(readBufk,false);
            }
            else if(packFlag==130){//是后续包结束
                songData2String(readBufk,false);
                if(sendFlag==0x09){
                    SongNameItem.songNames.add(otherSong);
                    intent = new Intent(Constant.ACTION_GET_MUSICLIST);
//					intent.putExtra(Constant.EXTRA_GET_MUSICLIST, otherSong);
                    App.getInstance().sendBroadcast(intent);
                }
                if(sendFlag==0x07 || sendFlag == 0x08){
                    intent = new Intent(Constant.ACTION_GET_MUSICNAME);
                    intent.putExtra(Constant.EXTRA_GET_MUSICNAME, otherSong);
                    App.getInstance().sendBroadcast(intent);
                }
            }

            else if(packFlag==129){	//是后续包结束
                songData2String(readBufk,false);
                if(sendFlag==0x09){
                    SongNameItem.songNames.add(otherSong);
                    intent = new Intent(Constant.ACTION_GET_MUSICLIST);
//					intent.putExtra(Constant.EXTRA_GET_MUSICLIST, otherSong);
                    App.getInstance().sendBroadcast(intent);
                }
                if(sendFlag==0x07 || sendFlag == 0x08){
                    intent = new Intent(Constant.ACTION_GET_MUSICNAME);
                    intent.putExtra(Constant.EXTRA_GET_MUSICNAME, otherSong);
                    App.getInstance().sendBroadcast(intent);
                }
            }
        }
    }

    /**
     * 收到返回歌曲名称的包,分两种情况,第一个包和后续包用boolean标识isFirst
     */
    private static void songData2String(byte[] readBufk, boolean isFirst){
        if(isFirst){
            byte[] data=new byte[readBufk.length-14];
            for(int i=13, j=2; i<readBufk.length-3; i++,j++){
                data[j]=readBufk[i];
            }
            data[0] = (byte) 255;
            data[1] = (byte) 254;
            try {
                otherSong = new String(data,"unicode");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            byte[] data=new byte[readBufk.length-10];
            for(int i=9, j=2; i<readBufk.length-3; i++,j++){
                data[j]=readBufk[i];
            }
            data[0] = (byte) 255;
            data[1] = (byte) 254;
            try {
                otherSong =otherSong+ new String(data,"unicode");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 也就是再遇到0x03开头的数据转换成原来的数据
     */
    private static byte[] escapeReadCmd(byte[] buffer) {
        byte[] escapeBuf = null;
        escapeBuf = new byte[buffer.length];
        int count = 0;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] == SppProc.PACK_REPLACE_PREFFIX) {
                i++;
                if (buffer[i] == SppProc.PACK_REPLACE_A) {
                    escapeBuf[count++] = SppProc.PACK_PREFIX;
                } else if (buffer[i] == SppProc.PACK_REPLACE_B) {
                    escapeBuf[count++] = SppProc.PACK_SUFFIX;
                } else if (buffer[i] == SppProc.PACK_REPLACE_C) {
                    escapeBuf[count++] = SppProc.PACK_REPLACE_PREFFIX;
                } else {
                    return null;// 所收到的数据没有转义
                }
            } else {
                escapeBuf[count++] = buffer[i];
            }
        }

        byte[] escapeCmd = new byte[count];// 包尾占一个字节
        for (int i = 0; i < escapeCmd.length; i++) {
            escapeCmd[i] = escapeBuf[i];
        }

        return escapeCmd;
    }

    /**
     * 获取 point 及 point + 1 两个字节的数据, point 低位, point + 1 高位
     * @param Data
     * @param point
     * @return
     */
    private static int getIntInByteArray(byte[] Data, int point) {
        // 注意多字节数据小端对齐, 低位在前(左), 高位在后(右)
        int numl = getByteInByteArray(Data, point); // 低位
        int numh = getByteInByteArray(Data, point + 1);// 高位
        if (numl == -1 || numh == -1) {
            return -1;
        }
        return (numh * 256 + numl);
    }

    /**
     * 获取字节数组
     *
     * @param Data
     * @param point
     * @return
     */
    private static int getByteInByteArray(byte[] Data, int point) {
        if (Data == null) {
            return -1;
        }
        if (Data.length <= point) {
            return -1;
        }
        return (Data[point] & 0xFF);
    }

    /**
     * 获取指定位数的数据,从0开始,左闭右闭
     */
    private static byte[] getByteInByteArray(byte[] readBufk,int start, int end){
        if(end >= readBufk.length){
            Log.e(TAG," 获取数据有误");
            return new byte[end + 1 - start];
        }
        byte[] readData = new byte[end + 1 - start];	//如[1,2,3,4] start =2 end = 3;
        for(int i=start; i<=end; i++){
            readData[i-start] = readBufk[i];
        }
        return readData;
    }

    static long last_color_time;
    private static boolean sendDelay(int delayTime) {
        if (System.currentTimeMillis() - last_color_time >= delayTime) {
            last_color_time = System.currentTimeMillis();
            return true;
        }
        return false;
    }
}
