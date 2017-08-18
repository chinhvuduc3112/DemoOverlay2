package easytouch.demooverlay2.model;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.admin.SystemUpdateInfo;
import android.content.Context;
import android.content.Intent;
import android.provider.Settings;

import java.io.IOException;

import easytouch.demooverlay2.MainActivity;
import easytouch.demooverlay2.MyApplication;

/**
 * Created by VuDuc on 8/18/2017.
 */

public class MyExceptionHandler implements Thread.UncaughtExceptionHandler {
    private Activity myActivity;

    public MyExceptionHandler(Activity myActivity) {
        this.myActivity = myActivity;
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        Intent intent = new Intent(myActivity, MainActivity.class);
        intent.putExtra("crash", true);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent =PendingIntent.getActivity(MyApplication.getInstance().getBaseContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);

        AlarmManager mgr = (AlarmManager) MyApplication.getInstance().getBaseContext().getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis()+2000, pendingIntent);

        myActivity.finish();

        System.exit(2);
    }
}
