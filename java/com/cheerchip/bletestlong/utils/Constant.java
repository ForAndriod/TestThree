package com.cheerchip.bletestlong.utils;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class Constant {

    // 由Service中的Handler发送的消息类型
    public static final int MSG_READ = 2;
    public static final int REQUEST_ENABLE_BT = 3;
    public static final int MSG_DEVICE_NAME = 4;
    public static final int MESSAGE_FRESH = 5;
    public static final int MESSAGE_DISMISS_DIALOG = 6;

    /////////////////////////
    // 是否更新
    public final static boolean isUpdate = true;
    // 是否从文件读取数据
    public final static boolean isFromFile = true;// 从文件读取
    // 是否打印日志
    public final static boolean DEBUG = true;
    // 是否向 Testin 发送数据
    public final static boolean isUploadTestinData = true;
    // 是否保存崩溃日志到文件
    public final static boolean isSaveCrashInfo2File = true;

    // 更新文件下载链接
    public static final String UPDATE_INFO_URL = "http://cheerchip.w233.mc-test.com/upd/AlaDing/updata_json.txt";

    public static final String SP_DOWNLOADUPDATE_KEY = "com.cheerchip.lovelife.DOWNLOADUPDATE";
    public static final String SP_TEMP_MODE_KEY = "com.cheerchip.lovelife.TEMPMODE";
    public static final String SP_SHAKE_MODE_KEY = "com.cheerchip.lovelife.SHAKEMODE";
    public static final String SP_IS_UPDATE_DEVICE_KEY = "com.cheerchip.lovelife.SP_IS_UPDATE_DEVICE_KEY";
    public static final String SP_SLID_TIME_KEY = "com.cheerchip.lovelife.SP_SLID_TIME_KEY";

    public static final String SP_ANIM_COUNT_KEY = "com.cheerchip.lovelife.ANIM_COUNT_KEY";

    public static final String SP_APP_VERSION_KEY = "com.cheerchip.lovelife.SP_IS_PREPARE_DATABASE_KEY";


    //只在过滤设备的时候用到
    public static final String DEVICE_NAME = "";


    public static final String ACTION_UPDATA_READY = "com.cheerchip.lovelife.action.UPDATA_READY";
    public static final String ACTION_GET_UPDATA_ORDER = "com.cheerchip.lovelife.action.UPDATA_ORDER";
    public static final String EXTRA_GET_UPDATA_ORDER = "com.cheerchip.lovelife.extra.UPDATA_ORDER";

    public static final String ACTION_CONNECT_STATE_CHANGED = "com.cheerchip.lovelife.bluetooth.action.CONNECT_STATE_CHANGED";
    public static final int BT_CONNECT_STATE_CONNECT_SUCCESSFUL = 0;
    public static final int BT_CONNECT_STATE_CONNECT_FAILED = 1;
    public static final int BT_CONNECT_STATE_DISCONNECTING = 2;
    public static final int BT_CONNECT_STATE_CONNECTED = 3;
    public static final int BT_CONNECT_STATE_CONNECTING = 4;
    public static final int BT_CONNECT_STATE_DISCONNECTED = 5;
    public static final int BT_CONNECT_STATE_NONE = 6;
    public static final String BT_CONNECT_STATE = "com.cheerchip.lovelife.bluetooth.extra.CONNECT_STATE";
    public static final String BT_CONNECT_EXTRA_DATA = "com.cheerchip.lovelife.bluetooth.extra.CONNECT_EXTRA_DATA";

    public static final String BT_GET_DEVICE_VERSION = "com.cheerchip.lovelife.GET_DEVICE_VERSION";

    public static final String ACTION_LIGHT_ACTIVITY_LEVEL = "com.cheerchip.lovelife.activity.ColorActivity.LIGHTLEVEL";
    public static final String EXTRA_LIGHT_FRAGMENT_LEVEL = "com.cheerchip.lovelife.activity.ColorActivity.LIGHTLEVEL.EXTRA";
    public static final String ACTION_ALARMACTIVITY_DATA_COME = "com.cheerchip.lovelife.activity.AlarmActivity.DATACOME";
    public static final String EXTRA_ALARMACTIVITY_DATA_COME = "com.cheerchip.lovelife.activity.AlarmActivity.DATACOME.EXTRA";

    public static final String SP_KEY_MESSAGE="com.cheerchip.lovelife.WarmActivity.spkey.MESSAGE";

    public static final String ACTION_SET_BOXMODE = "com.cheerchip.lovelife.action.SET_BOXMODE";
    public static final String EXTRA_SET_BOXMODE = "com.cheerchip.lovelife.extra.SET_BOXMODE";
    public static final String ACTION_GET_LIGHTCOLOR = "com.cheerchip.lovelife.action.GET_LIGHTCOLOR";
    public static final String EXTRA_GET_LIGHTCOLOR = "com.cheerchip.lovelife.action.extra_LIGHTCOLOR";
    public static final String ACTION_GET_LIGHTLEVEL = "com.cheerchip.lovelife.action.GET_LIGHTLEVEL";
    public static final String EXTRA_GET_LIGHTLEVEL = "com.cheerchip.lovelife.extra.GET_LIGHTLEVEL";
    public static final String ACTION_GET_LIGHTMODE = "com.cheerchip.lovelife.action.GET_LIGHTMODE";
    public static final String EXTRA_GET_LIGHTMODE = "com.cheerchip.lovelife.extra.GET_LIGHTMODE";
    public static final String ACTION_GET_CLOSETIME = "com.cheerchip.lovelife.action.GET_CLOSETIME";
    public static final String EXTRA_GET_CLOSETIME = "com.cheerchip.lovelife.extra.GET_CLOSETIME";
    public static final String ACTION_SET_CLOSETIME = "com.cheerchip.lovelife.action.SET_CLOSETIME";
    public static final String EXTRA_SET_CLOSETIME = "com.cheerchip.lovelife.extra.SET_CLOSETIME";
    public static final String ACTION_SET_NIGHTLIGHT = "com.cheerchip.lovelife.extra.SET_NIGHTLIGHT";
    public static final String ACTION_GET_ALARM = "com.cheerchip.lovelife.action.GET_ALARM";
    public static final String EXTRA_GET_ALARM = "com.cheerchip.lovelife.extra.GET_ALARM";
    public static final String ACTION_SET_ALARM = "com.cheerchip.lovelife.action.SET_ALARM";
    public static final String EXTRA_SET_ALARM = "com.cheerchip.lovelife.extra.SET_ALARM";



    public static final String ACTION_GET_MUSICLIST = "com.cheerchip.lovelife.action.GET_MUSICLIST";
    public static final String EXTRA_GET_MUSICLIST = "com.cheerchip.lovelife.extra.GET_MUSICLIST";
    public static final String ACTION_GET_MUSICNAME = "com.cheerchip.lovelife.action.GET_MUSICNAME";
    public static final String EXTRA_GET_MUSICNAME = "com.cheerchip.lovelife.extra.GET_MUSICNAME";
    public static final String ACTION_SET_RECORDSTATE = "com.cheerchip.lovelife.action.SET_RECORDSTATE";
    public static final String EXTRA_SET_RECORDSTATE = "com.cheerchip.lovelife.extra.SET_RECORDSTATE";

    public static final String ACTION_GET_DEVICESTATUS = "com.cheerchip.lovelife.action.GET_DEVICESTATUS";
    public static final String EXTRA_GET_DEVICESTATUS = "com.cheerchip.lovelife.extra.GET_DEVICESTATUS";
    public static final String ACTION_BTR_TFCHANGE = "com.cheerchip.lovelife.action.BTR_TFCHANGE";
    public static final String EXTRA_BTR_TFCHANGE = "com.cheerchip.lovelife.extra.GET_TFCHANGE";
    public static final String ACTION_GET_WORKMODE = "com.cheerchip.lovelife.action.GET_WORKMODE";
    public static final String EXTRA_GET_WORKMODE = "com.cheerchip.lovelife.extra.GET_WORKMODE";

    public static final String ACTION_GET_TEMPERATURE = "com.cheerchip.lovelife.action.GET_TEMPERATURE";
    public static final String EXTRA_GET_TEMPERATURE = "com.cheerchip.lovelife.extra.GET_TEMPERATURE";

    public static final String ACTION_GET_ALARM_RING_TYPE="com.cheerchip.AlarmClockActivity.GET_ALARM_RING_TYPE";

    public static final String EXTRA_ACTION_GET_ALARM_RING_TYPE="com.cheerchip.AlarmClockActivity.EXTRA_ACTION_GET_ALARM_RING_TYPE";

    public static final String ACTION_GET_VOL = "com.cheerchip.lovelife.action.GET_VOL";
    public static final String EXTRA_GET_VOL = "com.cheerchip.lovelife.extra.GET_VOL";
    public static final String ACTION_GET_PLAYSTATUS = "com.cheerchip.lovelife.action.GET_PLAYSTATUS";
    public static final String EXTRA_GET_PLAYSTATUS = "com.cheerchip.lovelife.extra.GET_PLAYSTATUS";
    public static final String EXTRA_GET_PLAYSTATUS_TIME = "com.cheerchip.lovelife.extra.GET_PLAYSTATUS_TIME";
    public static final String ACTION_GET_MUSICID = "com.cheerchip.lovelife.action.GET_MUSICID";
    public static final String EXTRA_GET_MUSICID = "com.cheerchip.lovelife.extra.GET_MUSICID";
    public static final String ACTION_GET_PLAYMODE = "com.cheerchip.lovelife.action.GET_PLAYMODE";
    public static final String EXTRA_GET_PLAYMODE = "com.cheerchip.lovelife.extra.GET_PLAYMODE";
    public static final String ACTION_BTR_LISTOVER = "com.cheerchip.lovelife.action.BTR_LISTOVER";

    public static final String ACTION_BTR_RECORDSTATA = "com.cheerchip.lovelife.action.GET.RECORDSTATA";
    public static final String EXTRA_BTR_RECORDSTATA = "com.cheerchip.lovelife.extra.GET.RECORDSTATA";

    //点阵界面用于广播传递的intent
    public static final String ACTION_GET_BOX_SPEED = "com.cheerchip.lovelife.action.GET_BOX_SPEED";
    public static final String EXTRA_GET_BOX_SPEED = "com.cheerchip.lovelife.extra.GET_BOX_SPEED";
    public static final String ACTION_GET_BOX_SPECTRUM = "com.cheerchip.lovelife.action.GET_BOX_SPECTRUM";
    public static final String EXTRA_GET_BOX_SPECTRUM = "com.cheerchip.lovelife.extra.GET_BOX_SPECTRUM";
    public static final String ACTION_SET_BOX_DATA = "com.cheerchip.lovelife.action.SET_BOX_DATA";

    public static final String ACTION_GET_SLEEP_DATA = "com.cheerchip.lovelife.action.GET_SLEEP_DATA";
    public static final String EXTRA_GET_SLEEP_DATA = "com.cheerchip.lovelife.extra.GET_SLEEP_DATA";

    //用于存放全局变量的map-key;
    public static final String MAP_CURRENT_WORKMODE = "com.cheerchip.lovelife.map.CURRENT_WORKMODE";
    public static final String MAP_TF_STATUS = "com.cheerchip.lovelife.map.TF_STATUS";
    public static final String MAP_GET_TF_LIST = "com.cheerchip.lovelife.mpa.GET_TF_LIST";
    public static final String MAP_KEY_LIST_IS_OVER ="com.cheerchip.lovelife.list_is_over";

    //Activity中数据传递的Map-key,取完要删除
    public static final String MAP_KEY_TRANFER_MEP_BOXDATA ="com.cheerchip.lovelife.TRANFER_MEP_BOXDATA";

    public static final String ACTION_ALARMCLOCKACTIVITY_DATA_COME = "com.elfstton101.activities.AlarmClockActivity.DATACOME";
    public static final String EXTRA_ALARMCLOCKACTIVITY_DATA_COME ="com.elfstton101.activities.AlarmClockActivity.DATACOME.EXTRA" ;

    //SpTools中常量
    public static final String SPDATA ="Config" ;
    public static final String ALARMCLOCKTIME ="12:00 AM" ;
    public static final String ALARMONOROFF ="0" ;//默认是关的
    public  static  final  String ALARMRING="0";
    public  static  final  String MUSICLIST="0";


    //闹钟试听

    public static final String ACTION_GET_AUDITIONTYPE="com.cheerchip.elfstton101.AlarmClockActivity.ACTION_GET_AUDITIONTYPE";
    public static final String EXTRA_GET_AUDITIONTYPE ="com.cheerchip.elfstton101.AlarmClockActivity.EXTRA_GET_AUDITIONTYPE" ;
    //对箱
    public static final String ACTION_GET_AWSTYPE_STATE ="com.cheerchip.elfstton101.BoxSettingActivity.ACTION_GET_AWSTYPE_STATE";
    public static final String EXTRA_GET_AWSTYPE_STATE ="com.cheerchip.elfstton101.BoxSettingActivity.EXTRA_GET_AWSTYPE_STATE" ;

    public static final String ACTION_GET_AWS_AWSLR ="com.cheerchip.elfstton101.BoxSettingActivity.ACTION_GET_AWS_AWSLR" ;
    public static final String EXTRA_GET_AWS_AWSLR ="com.cheerchip.elfstton101.BoxSettingActivity.EXTRA_GET_AWS_AWSLR" ;

    public static final String ACTION_GET_AWS_AWSEQ ="com.cheerchip.elfstton101.BoxSettingActivity.ACTION_GET_AWS_AWSEQ" ;
    public static final String EXTRA_GET_AWS_AWSEQ ="com.cheerchip.elfstton101.BoxSettingActivity.EXTRA_GET_AWS_AWSEQ" ;


    /** 通知的名称标识 */
    public enum WARNMESSAGE{
        INCOMING_CALL("com.android.phone_test",0,(byte)0x0C),
        MISSED_CALL("com.android.phone",1,(byte)0x0B),
        SMS("com.android.mms",2,(byte)0x13),
        QQ("com.tencent.mobileqq",3,(byte)0x10),
        WECHAT("com.tencent.mm",4,(byte)0x11),
        SKYPE("com.skype.rover",5,(byte)0x19),
        TWITTER("com.twitter.android",6,(byte)0x20),
        FACEBOOK("com.facebook.katana",7,(byte)0x21),
        WHATSAPP("com.whatsapp",8,(byte)0x22),
        LINE("jp.samurai_interational.LineNow",9,(byte)0x23),
        FACEBOOK_NEW("com.facebook.orca",10,(byte)0x24),
        CONTACTS_NEW("com.android.contacts",11,(byte)0x25)
        ;

        String key;
        int index;
        private byte cmd_code;
        private WARNMESSAGE(String key,int index,byte cmd_code){
            this.key = key;
            this.index = index;
            this.cmd_code = cmd_code;
        }

        public static String getPackageName(int index){
            for(WARNMESSAGE w : WARNMESSAGE.values()){
                if(w.getIndex() == index){
                    return w.getKey();
                }
            }
            return null;
        }

        public byte getCmd_code() {
            return cmd_code;
        }

        public String getPackageName(){
            return key;
        }

        private String getKey(){
            return key;
        }

        private int getIndex(){
            return index;
        }
    }
}

