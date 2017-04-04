package com.cheerchip.bletestlong.ble;

import android.util.Log;

import java.util.Calendar;
import  com.cheerchip.bletestlong.ble.SppProc.CMD_TYPE;
import com.cheerchip.bletestlong.utils.StringUtils;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class CmdManager {

    public static final String TAG = CmdManager.class.getSimpleName();


    public static byte[] setWorkMode(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SWITCH_MODE,data);
    }
    public static byte[] getWorkMode(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_STDB_MODE,null);
    }

    public static byte[] getTFMode(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SEND_TF_STATUS,null);
    }



    public static byte[] getMusicName(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_MUSIC_NAME,data);
    }
    public static byte[] setMusicName(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SEND_MUSIC_NAME,data);
    }

    public static byte[] getMusicList(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_MUSIC_LIST, data);
    }

    public static byte[] setPlayId(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_PLAY_MUSICID,data);
    }

    public static byte[] setBlueVol(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_VOL,data);
    }
    public static byte[] getBlueVol(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_VOL,null);
    }

    public static byte[] setPlayStatus(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_PLAY_STATUS, data);
    }
    public static byte[] getPlayStatus(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_PLAY_STATUS, null);
    }

    public static byte[] setRecordStatus(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_REC_STATE, data);
    }

    public static byte[] setPlayNextLast(byte[] data){
        //上下曲：0x0 为上一曲；0x1 为下一曲；其它为错误。
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_LAST_NEXT,data);
    }

    public static byte[] getDeviceStatus(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_DEVICE_STATUS,null);
    }

    public static byte[] setPlayMode(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_PLAY_MODE,data);
    }
    public static byte[] getPlayMode(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_PLAY_MODE,null);
    }

    public static byte[] setAlarmCmd(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_ALARM_TIME, data);
    }
    public static byte[] getAlarmCmd(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_ALARM_TIME, null);
    }

    public static byte[] setPowerOffTimeCmd(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_POWEROFF_TIME, data);
    }

    public static byte[] setMusicPlayIDTime(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_MUSIC_PLAYID_TIME, data);
    }
    public static byte[] getMusicAlarmTime(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_MUSIC_PLAYID_TIME, null);
    }

    public static byte[] getLightLevel(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_LIGHT_BRIGHT,null);
    }

    public static byte[] setLightBright(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_LIGHT_BRIGHT, data);
    }

    public static byte[] setAlarmTipType(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_ALARM_TIP_TYPE, data);
    }

    public static byte[] getAlarmTipType(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_ALARM_TIP_TYPE, data);
    }

    //试听闹钟铃音
    public static byte[] setAlarmAuditionType(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_ALARM_TIP_PALY, data);
    }




    //加一个
    public static byte[] getAlarmTipType1(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_ALARM_TIP_TYPE, null);
    }


    public static byte[] setAlarmTipPlay(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_ALARM_TIP_PALY, data);
    }

    public static byte[] getColorModeCmd(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_LIGHT_MODE, null);
    }

    public static byte[] setLightModeCmd(byte[] data){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_LIGHT_MODE, data);
        return cmd;
    }

    public static byte[] getColorCmd(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_LIGHT_COLOR, null);
    }

    public static byte[] setLightSaveData(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_LIGHT_ALL, data);
    }

    public static byte[] setColorCmd(byte[] data){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_LIGHT_COLOR, data);
        return cmd;
    }

    public static byte[] setBoxModeCmd(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_LCD_SET_MODE, data);
    }

    public static byte[] setBoxAnimCmd(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_LCD_SET_DATA, data);
    }

    public static byte[] setBoxSpeedOnOff(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_LCD_SET_SPEED, data);
    }
    public static byte[] getBoxSpeedStata(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_LCD_GET_SPEED, null);
    }

    public static byte[] setBoxSpectrumStata(byte state){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_LCD_SET_SPECTRUM, new byte[]{state});
    }
    public static byte[] getBoxSpectrumStata(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_LCD_GET_SPECTRUM,null);
    }

    public static byte[] getRecordStata(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_REC_STATE,null);
    }

    public static byte[] getTemperatureMode(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_TEMPERATURE,null);
    }

    public static byte[] setTemperatureMode(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_TEMPERATURE,data);
    }


    public static byte[] getPowerOffTimeCmd() {
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_POWEROFF_TIME, null);
    }



    public static byte[] setSystemTimeCmd(){
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
//		int center = year/100;
//		int year_center = year % 100;
        int month = calendar.get(Calendar.MONTH)+1;
        int day_week = calendar.get(Calendar.DAY_OF_WEEK);
        int day_month = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

//		Logg.i(TAG, "hour : " + hour + " , minute : " + minute + " , second : " + second);

        byte[] data = new byte[8];
        data[0] = (byte) (year & 0xFF);
        data[1] = (byte) (year >> 8 & 0xFF);
        data[2] = (byte) (month & 0xFF);
        data[3] = (byte) (day_month & 0xFF);

        data[4] = (byte) (hour & 0xFF);
        data[5] = (byte) (minute & 0xFF);
        data[6] = (byte) (second & 0xFF);

        data[7] = (byte) (day_week & 0xFF);

        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_SYSTEM_TIME, data);
		Log.i(TAG, "发出的数据 : " + StringUtils.getHex(cmd));
        return cmd;
    }

    public static byte[] getVersionCmd(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_BTR_VERSION, null);
    }

    /**
     * 获取设备的固件版本号
     * @return
     */
    public static byte[] getDeviceVersionCmd(){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_GET_SYS_VIERSION, null);
        return cmd;
    }

    public static byte[] getTextCmd(){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_SYS_UPDATE_READY, null);
        return cmd;
    }

    /**
     * 发送是否更新固件
     * @return
     */
    public static byte[] getIsUpdateCmd(boolean isUpdate){
        byte data[] = new byte[1];
        if(isUpdate){
            data[0] = 0;
        }else{
            data[0] = 1;
        }
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_SYS_UPDATE_FILE_INFO, data);
        return cmd;
    }

    /**
     * 发送升级文件相关信息
     * @return
     */
    public static byte[] getUpdateInfoCmd(byte[] data){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_SYS_DEVICE_UPDATE, data);
        return cmd;
    }


    /**
     * 0x95 的返回 ack
     * @return
     */
    public static byte[] getUpdateDataAckCmd(){
        byte[] data = new byte[]{(byte) 0x95, 0x55};
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_COMMAND_CHECK, data);
        return cmd;
    }

    /**
     * 0x94 发送数据 64 字节
     * @return
     */
    public static byte[] getUpdateDataFileSendCmd(byte[] data){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_SYS_UPDATE_DATA, data);
        return cmd;
    }

    /**
     * 0x91 的返回 ack
     * @return
     */
    public static byte[] getUpdateApplyAckCmd(){
        byte[] data = new byte[]{(byte) 0x91, 0x55};
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_COMMAND_CHECK, data);
        return cmd;
    }

    /**
     * 设备升级命令
     * @return
     */
    public static byte[] getIsDeviceUpdateCmd(boolean isUpdate){
        byte[] data = new byte[1];
        if(isUpdate){
            data[0] = 0;
        }else{
            data[0] = 1;
        }
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_SYS_UPDATE_FILE_INFO, data);
        return cmd;
    }

    /**
     * 0x96 获取更新 Url
     * @return
     */
    public static byte[] getUpdateUrlCmd(){
        byte[] cmd = SppDecodeHolder.encodeCmd(CMD_TYPE.SPP_SYS_GET_UPDATE_ADDR, null);
        return cmd;
    }


    /**
     * 设置对箱状态 0:断开   1:连接
     * @return
     */
    public static byte[] getAwsState(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_AWS_STATUS,null);
    }

    public static byte[] setAwsState(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_AWS_STATUS,data);
    }

    /**
     *  对箱的左右声道状态  LR:0 (默认)
     * @return
     */
    public static byte[] getAwsLR(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_AWS_LR,null);
    }

    public static byte[] setAwsLR(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_AWS_LR,data);
    }

    /**
     *  对箱的EQ模式
     * @return
     */
    public static byte[] getAwsEQ(){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_GET_EQ_MODE,null);
    }

    public static byte[] setAwsEQ(byte[] data){
        return SppDecodeHolder.encodeCmd(CMD_TYPE.BTR_SET_EQ_MODE,data);
    }
}
