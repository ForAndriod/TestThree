package com.cheerchip.bletestlong.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cheerchip.bletestlong.App;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * Created by YangJingLin on 2017/3/8.
 */
public class BluetoothController {

    private static final String TAG = "BluetoothController";
    private String deviceAddress;
    private String deviceName;

    private BluetoothAdapter bleAdapter;
    private Handler serviceHandler;// 服务句柄

    static BluetoothGatt bleGatt;// 连接
    static BluetoothGattCharacteristic bleGattCharacteristic;

    /**
     * 单例模式
     */
    private static BluetoothController instance = null;
	/*private UUID[] ser={
			"49535343-1E4D-4BD9-BA61-23C647249616"
	};*/


    private BluetoothController() {
    }

    public static BluetoothController getInstance() {
        if (instance == null)
            instance = new BluetoothController();
        return instance;
    }

    /**
     * 初始化蓝牙
     *
     * @return
     */
    public boolean initBLE() {
        // 检查当前手机是否支持ble 蓝牙,如果不支持退出程序
        // App.app可能会报错，清单文件中不要忘了配置application
        if (!App.app.getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_BLUETOOTH_LE)) {
            return false;
        }
        // 初始化 Bluetooth adapter, 通过蓝牙管理器得到一个参考蓝牙适配器(API必须在以上android4.3或以上版本)
        final BluetoothManager bluetoothManager = (BluetoothManager) App.app
                .getSystemService(Context.BLUETOOTH_SERVICE);
        bleAdapter = bluetoothManager.getAdapter();
        // 检查设备上是否支持蓝牙
        if (bleAdapter == null)
            return false;
        else
            return true;
    }

    /**
     * 设置服务事件接收者
     *
     * @return
     */
    public void setServiceHandler(Handler handler) {
        // handler初始化在service中，用于逻辑和界面的沟通
        serviceHandler = handler;
    }

    /**
     * 搜索蓝牙回调
     */
    BluetoothAdapter.LeScanCallback bleScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int arg1, byte[] arg2) {
            // device就是搜索到的设备
            String name = device.getName();
            if (name == null)
                return;
            if (BluetoothController.this.serviceHandler != null
                    && !name.isEmpty()) {
                Message msg = new Message();
                msg.what = ConstantUtils.WM_UPDATE_BLE_LIST;
                msg.obj = device;
                BluetoothController.this.serviceHandler.sendMessage(msg);
            }
        }
    };

    /**
     * 开始扫描蓝牙
     */
    public void startScanBLE() {
        bleAdapter.startLeScan(bleScanCallback);
        //bleAdapter.startLeScan(ser,bleScanCallback);
        if (serviceHandler != null)
            serviceHandler.sendEmptyMessageDelayed(
                    ConstantUtils.WM_STOP_SCAN_BLE, 5000);
    }

    /**
     * 停止扫描蓝牙设备
     */
    public void stopScanBLE() {
        bleAdapter.stopLeScan(bleScanCallback);
    }

    /**
     * 是否蓝牙打开
     *
     * @return
     */
    public boolean isBleOpen() {
        return bleAdapter.isEnabled();
    }

    /**
     * 连接蓝牙设备
     *
     * @param device
     *            待连接的设备
     */
    public void connect(EntityDevice device) {
        deviceAddress = device.getAddress();
        deviceName = device.getName();
        BluetoothDevice localBluetoothDevice = bleAdapter
                .getRemoteDevice(device.getAddress());
        if (bleGatt != null) {

            bleGatt.disconnect();
            bleGatt.close();
            bleGatt = null;
        }
        bleGatt = localBluetoothDevice.connectGatt(App.app, false,
                bleGattCallback);
    }

    /**
     * 与蓝牙通信回调
     */
    public BluetoothGattCallback bleGattCallback = new BluetoothGattCallback() {
        /**
         * 收到消息
         */
        public void onCharacteristicChanged(
                BluetoothGatt paramAnonymousBluetoothGatt,
                BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic) {

            /*byte[] arrayOfByte = paramAnonymousBluetoothGattCharacteristic
                    .getValue();*/

            byte[] arrayOfByte=paramAnonymousBluetoothGattCharacteristic.getValue();

            if (BluetoothController.this.serviceHandler != null) {
                Message msg = new Message();
                //msg.what = ConstantUtils.WM_RECEIVE_MSG_FROM_BLE;
                msg.what=ConstantUtils.WM_RECEIVE_MSG_FROM_BLE;
                // byte数组转换为十六进制字符串
				/*msg.obj = ConvertUtils.getInstance().bytesToHexString(
						arrayOfByte);*/
                msg.obj = arrayOfByte;
               // msg.obj=StringUtils.getHex(arrayOfByte);
                BluetoothController.this.serviceHandler.sendMessage(msg);
                Log.d(TAG,"ConvertUtils.getInstance().bytesToHexString(arrayOfByte))"+StringUtils.bytes2Int(arrayOfByte));
                Log.d(TAG,"测试："+StringUtils.getHex(new byte[]{1}));
            }
            // 也可以先打印出来看看
           /* Log.d(TAG,"ConvertUtils.getInstance().bytesToHexString(arrayOfByte))"+
                    ConvertUtils.getInstance().bytesToHexString(arrayOfByte));*/


        }

        public void onCharacteristicRead(
                BluetoothGatt paramAnonymousBluetoothGatt,
                BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic,
                int paramAnonymousInt) {
        }

        public void onCharacteristicWrite(
                BluetoothGatt paramAnonymousBluetoothGatt,
                BluetoothGattCharacteristic paramAnonymousBluetoothGattCharacteristic,
                int paramAnonymousInt) {
        }

        /**
         * 连接状态改变
         */
        public void onConnectionStateChange(
                BluetoothGatt paramAnonymousBluetoothGatt, int oldStatus,
                int newStatus) {
            if (newStatus == 2)// 已连接状态，表明连接成功
            {
                Message msg = new Message();
                msg.what = ConstantUtils.WM_BLE_CONNECTED_STATE_CHANGE;
                Bundle bundle = new Bundle();
                bundle.putString("address", deviceAddress);
                bundle.putString("name", deviceName);
                msg.obj = bundle;
                serviceHandler.sendMessage(msg);
                paramAnonymousBluetoothGatt.discoverServices();
                // 连接到蓝牙后查找可以读写的服务，蓝牙有很多服务
                return;
            }
            if (newStatus == 0)// 断开连接或未连接成功
            {
                serviceHandler.sendEmptyMessage(ConstantUtils.WM_STOP_CONNECT);
                return;
            }
            paramAnonymousBluetoothGatt.disconnect();
            paramAnonymousBluetoothGatt.close();
            return;
        }

        public void onDescriptorRead(BluetoothGatt paramAnonymousBluetoothGatt,
                                     BluetoothGattDescriptor paramAnonymousBluetoothGattDescriptor,
                                     int paramAnonymousInt) {
        }

        public void onDescriptorWrite(
                BluetoothGatt paramAnonymousBluetoothGatt,
                BluetoothGattDescriptor paramAnonymousBluetoothGattDescriptor,
                int paramAnonymousInt) {

        }

        public void onReadRemoteRssi(BluetoothGatt paramAnonymousBluetoothGatt,
                                     int paramAnonymousInt1, int paramAnonymousInt2) {
        }

        public void onReliableWriteCompleted(
                BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt) {
        }

        public void onServicesDiscovered(
                BluetoothGatt paramAnonymousBluetoothGatt, int paramAnonymousInt) {


            BluetoothController.this.findService(paramAnonymousBluetoothGatt
                    .getServices());
        }

    };

    /**
     * 传输数据
     *
     * @param byteArray
     * @return
     */
    public boolean write(byte byteArray[]) {
        Log.d(TAG,"write data："+ Arrays.toString(byteArray));
        if (bleGattCharacteristic == null)
            return false;
        if (bleGatt == null)
            return false;
        bleGattCharacteristic.setValue(byteArray);
        bleGattCharacteristic.setWriteType(BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT);
        return bleGatt.writeCharacteristic(bleGattCharacteristic);
    }

    /**
     * 传输数据
     *
     * @param
     * @return
     */
    public boolean write(String str) {
        if (bleGattCharacteristic == null)
            return false;
        if (bleGatt == null)
            return false;
        bleGattCharacteristic.setValue(str);
        Log.d(TAG,"write data====："+str );
        return bleGatt.writeCharacteristic(bleGattCharacteristic);
    }

    /**
     * 搜索服务
     *
     * @param paramList
     */
    public void findService(List<BluetoothGattService> paramList) {

        Iterator localIterator1 = paramList.iterator();
        Log.d(TAG,""+localIterator1.hasNext());
        while (localIterator1.hasNext()) {
            BluetoothGattService localBluetoothGattService = (BluetoothGattService) localIterator1
                    .next();

            Log.d(TAG,"uuidtest0"+localBluetoothGattService.getUuid().toString());

            if (localBluetoothGattService.getUuid().toString()
                    .equalsIgnoreCase(ConstantUtils.UUID_SERVER)) {
                List localList = localBluetoothGattService.getCharacteristics();
                Iterator localIterator2 = localList.iterator();

                while (localIterator2.hasNext()) {
                    BluetoothGattCharacteristic localBluetoothGattCharacteristic = (BluetoothGattCharacteristic) localIterator2
                            .next();
                    Log.d(TAG,"uuidtest0"+localBluetoothGattCharacteristic.getUuid().toString());

                    if (localBluetoothGattCharacteristic.getUuid().toString()
                            .equalsIgnoreCase(ConstantUtils.UUID_NOTIFY)) {
                        Log.d(TAG,"uuidtest1"+localBluetoothGattService.getUuid().toString());
                        bleGattCharacteristic = localBluetoothGattCharacteristic;
                        bleGatt.setCharacteristicNotification(bleGattCharacteristic, true);
                        break;
                    }

                 /*   if (localBluetoothGattCharacteristic.getUuid().toString().equalsIgnoreCase(ConstantUtils.UUID_WRITE)) {
                        Log.d(TAG,"uuidtest2"+localBluetoothGattService.getUuid().toString());
                        bleGattCharacteristic = localBluetoothGattCharacteristic;
                        BluetoothGattDescriptor descriptor = bleGattCharacteristic.getDescriptor(
                                UUID.fromString(ConstantUtils.UUID_WRITE));
                        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                        bleGatt.writeDescriptor(descriptor);
                        bleGatt.setCharacteristicNotification(bleGattCharacteristic, true);
                        break;
                    }
                   */

                    }
                }
            }
        }


    }





