package easytouch.demooverlay2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;


import easytouch.demooverlay2.adapter.CustomPagerAdapter;
import easytouch.demooverlay2.adapter.MainPagerAdapter;
import easytouch.slidinguplibrary.SlidingUpPanelLayout;

/**
 * Created by VuDuc on 8/14/2017.
 */

public class MyService extends Service {

    WindowManager.LayoutParams params;
    WindowManager.LayoutParams bottomParams;
    SlidingUpPanelLayout slidingUpPanelLayout;
    ViewPager mViewPager = null;
    MainPagerAdapter mainPagerAdapter = null;

    Context mContext;
    private WindowManager windowManager;
    private View overlayView;
    private View overlayBottom;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        addOverlayView();
    }

    private void addOverlayView() {
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 0.95f);
        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.CENTER | Gravity.BOTTOM;
        params.x = 0;
        params.y = 0;

        params.windowAnimations = android.R.style.Animation_InputMethod;

        overlayView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view, null);
        //Phần dưới màn hình
        bottomParams = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        bottomParams.gravity = Gravity.BOTTOM;
        overlayBottom = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.floating_view2, null);

        windowManager.addView(overlayBottom, bottomParams);
        windowManager.addView(overlayView, params);
        addControls(overlayView);

        overlayBottom.setOnTouchListener(new View.OnTouchListener() {

            private float starty;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        starty = motionEvent.getY();
                        break;
                    case MotionEvent.ACTION_UP: {
                        float endY = motionEvent.getY();
                        if (endY < starty) {
                            //Move up
                            overlayView.setVisibility(View.VISIBLE);
                            overlayBottom.setVisibility(View.GONE);

                            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                            windowManager.updateViewLayout(overlayView, params);
                        }
                    }
                }
                return false;
            }
        });
    }

    private void addControls(View view) {
        slidingUpPanelLayout = (SlidingUpPanelLayout) view.findViewById(R.id.sliding_layout);
        slidingUpPanelLayout.setPanelHeight(1);
        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if(newState.name().toString().equalsIgnoreCase("Collapsed")){
                    //action when collapsed
//                    slidingUpPanelLayout.getLayoutParams().height=200;
                    //slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
//                    windowManager.updateViewLayout(overlayView, params);
                    overlayView.setVisibility(View.GONE);
                    overlayBottom.setVisibility(View.VISIBLE);
                }else if(newState.name().equalsIgnoreCase("Expanded")){
                    //action when expanded
                }
            }
        });

        mViewPager = view.findViewById(R.id.controlViewPager);
        mViewPager.setAdapter(new MainPagerAdapter(this));


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (overlayView != null) {
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.removeView(overlayBottom);
            wm.removeView(overlayView);
        }
    }

    public IBinder onBind(Intent intent) {
        return null;
    }
}
