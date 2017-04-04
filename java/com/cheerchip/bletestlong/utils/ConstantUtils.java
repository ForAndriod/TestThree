package com.cheerchip.bletestlong.utils;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class ConstantUtils {
    //消息类型
    public final static int WM_STOP_SCAN_BLE=1;
    public final static int WM_UPDATE_BLE_LIST=2;
    //蓝牙连接状态改变
    public final static int  WM_BLE_CONNECTED_STATE_CHANGE=3;
    //接受到蓝牙发来的消息
    public final static int WM_RECEIVE_MSG_FROM_BLE=4;
    //断开连接或未连接成功
    public final static int WM_STOP_CONNECT=5;

    //intent的action们
    public final static String ACTION_UPDATE_DEVICE_LIST="action.update.device.list";//更新设备列表
    public final static String  ACTION_CONNECTED_ONE_DEVICE="action.connected.one.device";//连接上某个设备时发的广播
    public final static String ACTION_RECEIVE_MESSAGE_FROM_DEVICE="action.receive.message";
    public final static String ACTION_STOP_CONNECT="action.stop.connect";


    //UUID	49535343-1E4D-4BD9-BA61-23C647249616
    //      49535343-fe7d-4ae5-8fa9-9fafd205e455

    //0000ffe0-0000-1000-8000-00805f9b34fb
    //0000ffe1-0000-1000-8000-00805f9b34fb
    //0000
    //00001800-0000-1000-8000-00805f9b34fb
    //0000180a-0000-1000-8000-00805f9b34fb
    //simple

		/*public final static  String UUID_SERVER="0000fff0-0000-1000-8000-00805f9b34fb";
		public final static  String UUID_NOTIFY="0000fff6-0000-1000-8000-00805f9b34fb";*/
//bt

    //00001800-0000-1000-8000-00805f9b34fb
    //49535343-fe7d-4ae5-8fa9-9fafd205e455
    public final static  String UUID_SERVER="49535343-fe7d-4ae5-8fa9-9fafd205e455";
    public final static  String UUID_NOTIFY="49535343-1E4D-4BD9-BA61-23C647249616";
//6e400003-b5a3-f393-e0a9-e50e24dcca9e

  /*  public final static  String UUID_SERVER="6e400001-b5a3-f393-e0a9-e50e24dcca9e";
    public final static  String UUID_NOTIFY="6e400003-b5a3-f393-e0a9-e50e24dcca9e";
    public  final  static String UUID_WRITE="6e400002-b5a3-f393-e0a9-e50e24dcca9e";
*/


    //49535343-8841-43f4-a8d4-ecbe34729bb3

}
