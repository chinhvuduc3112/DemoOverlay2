package easytouch.demooverlay2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by VuDuc on 8/18/2017.
 */

public class MyRestarterBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(MyRestarterBroadcastReceiver.class.getSimpleName(), "aciotn:"+intent.getAction());

        context.startService(new Intent(context, MyService.class));
    }
}
