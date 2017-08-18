package easytouch.demooverlay2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import easytouch.demooverlay2.model.MyExceptionHandler;

public class MainActivity extends AppCompatActivity {
    public final static int Overlay_REQUEST_CODE = 251;
    private Activity mActivity;
    Button startService, stopService, crashApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;
//        //Khai báo xử lý exception
//        Thread.setDefaultUncaughtExceptionHandler(new MyExceptionHandler(mActivity));
//
//        if(getIntent().getBooleanExtra("crash", false)){
//            Toast.makeText(this, "Ung dung duoc khoi dong lai", Toast.LENGTH_LONG).show();
//        }

        startService=(Button)findViewById(R.id.startService);
        stopService=(Button)findViewById(R.id.stopService);
        crashApp= (Button) findViewById(R.id.crash_app);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDrawOverlayPermission();
            }
        });

        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService(new Intent(getApplication(), MyService.class));
            }
        });

        crashApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                throw new NullPointerException();
            }
        });
    }

    public void checkDrawOverlayPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(mActivity)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, Overlay_REQUEST_CODE);
            } else {
                openFloatingWindow();
            }
        } else {
            openFloatingWindow();
        }
    }

    private void openFloatingWindow() {
        Intent intent = new Intent(mActivity, MyService.class);
        mActivity.stopService(intent);
        mActivity.startService(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case Overlay_REQUEST_CODE: {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (Settings.canDrawOverlays(mActivity)) {
                        openFloatingWindow();
                    }
                } else {
                    openFloatingWindow();
                }
                break;
            }
        }
    }
}
