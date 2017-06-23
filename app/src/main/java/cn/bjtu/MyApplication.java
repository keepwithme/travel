package cn.bjtu;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by wsg on 2017/6/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //修改成你在Bmob创建的AppId
        Bmob.initialize(this, "6b88e22693607f413ce6d78b8f7abf80");
    }
}
