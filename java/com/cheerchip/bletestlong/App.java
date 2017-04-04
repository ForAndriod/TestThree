package com.cheerchip.bletestlong;

import android.app.Application;

import com.cheerchip.bletestlong.ble.SppDecodeHolder;
import com.cheerchip.bletestlong.utils.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YangJingLin on 2017/3/8.
 */

public class App  extends Application {

    /** 用于存放全局变量的集合,如音箱TF卡的当前状态,音箱当前工作模式 */
    private Map<String, Object> appMap;


    public static App app;

    @Override
    public void onCreate() {
        app=this;
        super.onCreate();
        // 初始化全局变量
        initAppVariable();
        SppDecodeHolder.setApp(this);


    }


    public static App getInstance() {
        return app;
    }

    private void initAppVariable() {
        appMap = new HashMap<String, Object>();
        putAppMapElement(Constant.MAP_KEY_LIST_IS_OVER, false);
        putAppMapElement(Constant.MAP_CURRENT_WORKMODE, 1);
        putAppMapElement(Constant.MAP_TF_STATUS, true);
        putAppMapElement(Constant.MAP_KEY_LIST_IS_OVER, false);

    }

    public Object getAppMapElement(String key) {
        return appMap.get(key);
    }

    public void putAppMapElement(String key, Object object) {
        appMap.put(key, object);
    }



}
