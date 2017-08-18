package easytouch.demooverlay2;

import android.app.Application;
import android.content.Context;

/**
 * Created by VuDuc on 8/18/2017.
 */

public class MyApplication extends Application {
    public static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
