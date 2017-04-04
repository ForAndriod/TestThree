package com.cheerchip.bletestlong.ble;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class SppProc {
    public static final int PACK_PREFIX = 0x01;
    public static final int PACK_SUFFIX = 0x02;
    public static final int PACK_REPLACE_PREFFIX = 0x03;
    public static final int PACK_REPLACE_A = 0x04;
    public static final int PACK_REPLACE_B = 0x05;
    public static final int PACK_REPLACE_C = 0x06;

    public static final int PACK_ACCEPT = 0x55;
    public static final int PACK_ERROR = 0xAA;

//	public static final int PACK_PREFIX = 0x06;
//	public static final int PACK_SUFFIX = 0x07;
//	public static final int PACK_REPLACE_PREFFIX = 0x08;
//	public static final int PACK_REPLACE_A = 0x14;
//	public static final int PACK_REPLACE_B = 0x15;
//	public static final int PACK_REPLACE_C = 0x16;
//
//	public static final int PACK_ACCEPT = 0x55;
//	public static final int PACK_ERROR = 0xAA;

    public enum CMD{
        SPP_COMMAND;
    }

    /**
     * 命令类型
     */
    public enum CMD_TYPE{
        BTR_COMMAND_CHECK (0x04),//命令回应
        BTR_SWITCH_MODE (0x05),//切换系统工作模式
        BTR_GET_STDB_MODE (0x06),//得到系统工作模式
        BTR_GET_MUSIC_NAME (0x07),//得到当前播放歌曲名
        BTR_SEND_MUSIC_NAME (0x08),//发送指定序号的播放歌曲名
        BTR_GET_MUSIC_LIST (0x09),//得到指定序列的播放列表
        BTR_GET_MUSIC_ID(0x0a),//得到当前播放歌曲ID
        BTR_SET_VOL (0x0b),//设置播放音量
        BTR_GET_VOL (0x0c),//得到当前音量
        BTR_SET_PLAY_STATUS (0x0d),//设置当前播放状态
        BTR_GET_PLAY_STATUS (0x0e),//得到当前播放状态
        BTR_SET_EQ_MODE (0x0f),//设置当前的EQ 模式
        BTR_GET_EQ_MODE (0x10),//得到当前EQ 模式
        BTR_SET_EQ_USER_DEFINE (0x11),//得到当前EQ 模式
        BTR_GET_EQ_USER_DEFINE (0x12),//得到用户自定义EQ
        BTR_SET_PLAY_MODE (0x13),//设置当前播放模式
        BTR_GET_PLAY_MODE (0x14),//设置当前播放模式
        BTR_GET_VOLTAGE (0x15),//得到设备电压
        BTR_SEND_VOLTAGE (0x16),//发送设备电压
        BTR_SET_PLAY_MUSICID (0x17),//设置当前播放的歌曲
        BTR_SET_LAST_NEXT (0x18),//设置上下曲
        BTR_SEND_LIST_OVER (0x19),//设置上下曲
        BTR_SEND_TF_STATUS (0x1a),//通知T 卡有插拔动作
        BTR_GET_DEVICE_STATUS (0x1b),//获取外设状态
        BTR_SET_PLAY_BACK_FORWARD_START (0x1c),//设置音乐播放快进/快退开始
        BTR_SET_PLAY_BACK_FORWARD_STOP (0x1d),//设置音乐播放快进/快退结束
        BTR_GET_PLAY_BACK_FORWARD_STATE (0x1e),//得到音乐播放快进/快退状态
        BTR_GET_BT_SIGNAL (0x1f),//获取蓝牙信号强度
        BTR_SET_BT_SIGNAL (0x20),
        BTR_GET_BT_CONNECT_STATE (0x21),//获取蓝牙连接状态
        BTR_SET_SYSTEM_TIME (0x22),//设置系统时钟
        BTR_GET_SYSTEM_TIME (0x23),//得到系统时间
        BTR_GET_ALARM_TIME (0x24),//得到系统闹钟
        BTR_SET_ALARM_TIME (0x25),//设置系统闹钟
        BTR_GET_PRODUCE_NAME (0x27),//获取产品名称
        BTR_GET_SOFTWARE_VERSION (0x28),//获取软件版本
        BTR_GET_PRODUCER_SERIALID (0x29),//获取产品序列号
        BTR_GET_REC_LIST (0x2a),//获取录音列表
        BTR_SET_MUSIC_PLAYID_TIME (0x2b),//设置播放 TF 指定 id 音乐时间
        BTR_GET_MUSIC_PLAYID_TIME (0x2c),//获取播放 TF 指定 id 音乐时间
        BTR_GET_REC_STATE (0x2d),//获取录音状态
        BTR_SET_POWER_STATE (0x2e),//设置机器设备状态
        BTR_GET_POWER_STATE (0x2f),//获取机器设备状态
        BTR_SET_POWEROFF_TIME (0x30),//设置设备自动关机时间（单位：分钟）
        BTR_GET_POWEROFF_TIME (0x31),//获取设备自动关机时间（单位：分钟）
        BTR_SET_REC_STATE (0x36),//设置录音状态
        BTR_GET_TEMPERATURE (0x37),//获取音箱温度
        BTR_SET_TEMPERATURE (0x38),//设置音箱温度
        BTR_GET_FM_LIST (0x50), //得到FM 的频道列表
        BTR_GET_FM_PLAY_ID (0x51),//得到FM 当前频道
        BTR_SET_FM_PLAY_ID (0x52),//设置FM 的播放频道
        BTR_SET_LIGHT_MODE (0x60),//设置灯光模式
        BTR_GET_LIGHT_MODE (0x61),//获取灯光模式
        BTR_GET_LIGHT_COLOR (0x62),//获取灯光颜色
        BTR_SET_LIGHT_COLOR (0x63),//设置灯光颜色
        BTR_SET_LIGHT_BRIGHT (0x64),//设置灯光亮度
        BTR_GET_LIGHT_BRIGHT (0x65),//获取灯光亮度
        BTR_GET_LIGHT_ALL (0x66),//获取灯光模式亮度和颜色
        BTR_SET_LIGHT_ALL (0x67),//设置灯光模式亮度和颜色
        BTR_RESET_LIGHT (0x68),//灯光复位：还原成设备默认的模式、亮度、颜色。
        BTR_GET_BTR_VERSION (0x6f),//获取BTR 版本
        BTR_LCD_SET_MODE (0x70),//设置模式
        BTR_LCD_GET_MODE (0x71),//获取模式
        BTR_LCD_SET_DATA (0x72),//设置动画显示数据
        BTR_LCD_GET_DATA (0x73),//设置数据
        BTR_LCD_SET_PREVIEW (0x74),//设置预览
        BTR_LCD_SET_SPEED (0x75), //设置动画速度（0 - 255）
        BTR_LCD_GET_SPEED (0x76),	//获取速度
        BTR_LCD_SET_SPECTRUM (0x77),	//设置频谱开关开：0x1 关：0x0
        BTR_LCD_GET_SPECTRUM (0x78),	//获取频谱开关

        SPP_GET_SYS_VIERSION(0x90),//获取系统版本号信息2字节
        SPP_SYS_UPDATE_READY(0x91),//设备已经完成升级文件的接收，APP可以发送升级命令
        SPP_SYS_UPDATE_FILE_INFO(0x92),//设备升级  带一字体数据0:升级;1:取消升级
        SPP_SYS_DEVICE_UPDATE(0x93),//升级文件的相关信息
        SPP_SYS_UPDATE_DATA(0x94),//升级文件传输，4字节包序号+每次64字节
        SPP_SYS_CONTINUE_UPDATE_DATA(0x95),//升级启动与暂停命令 1字节(1:继续发送升级文件；0:暂停发送) +2字节版本号+2字节序号
        SPP_SYS_GET_UPDATE_ADDR(0x96),//获取更新 url

        BTR_SET_ALARM_TIP_TYPE (0xA0),	//设置闹钟提示类型
        BTR_GET_ALARM_TIP_TYPE (0xA1),	//获取闹钟提示类型

        BTR_SET_ALARM_TIP_PALY(0xA2),	//试听闹钟提示音,

        BTR_SET_AWS_LR (0xC2),       // 设置对箱的左右声道状态  LR:0 (默认)  RL:1
        BTR_GET_AWS_LR (0xC3),       // 获取对箱的左右声道状态



        BTR_SET_AWS_STATUS (0XC0),   // 设置对箱状态 0:断开
        BTR_GET_AWS_STATUS (0XC1);//设置对箱状态 1:连接



        private int value;
        private CMD_TYPE(int value){
            this.value = value;
        }
        public int value(){
            return value;
        }

    }


    public enum LIGHT_MODE{
        CLOCK(new byte[]{0x0}),
        TEMPRETURE(new byte[]{0x1}),
        COLOR_LIGHT(new byte[]{0x2}),
        SOUND_LIGHT(new byte[]{0x3}),
        FOREST(new byte[]{0x4}),
        MARQUEEN(new byte[]{0x5}),
        COLOR_PIECE(new byte[]{0x6}),
        COUNT_DOWN(new byte[]{0x7});


        private byte[] cmd_data;
        private LIGHT_MODE(byte[] data){
            this.cmd_data = data;
        }
        public byte[] getCmd_data() {
            return cmd_data;
        }
    }

    public enum BOX_COLOR{
        COLOR1((byte)0x1),
        COLOR2((byte)0x2),
        COLOR3((byte)0x4),
        COLOR4((byte)0x3),
        COLOR5((byte)0x6),
        COLOR6((byte)0x5),
        COLOR7((byte)0x7),
        COLOR8((byte)0x0);

        private byte color_byte;

        private BOX_COLOR(byte color_byte){
            this.color_byte = color_byte;
        }

        public byte getByte(){
            return color_byte;
        }
    }
}
