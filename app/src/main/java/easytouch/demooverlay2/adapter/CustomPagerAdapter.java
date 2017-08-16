package easytouch.demooverlay2.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import easytouch.demooverlay2.model.ViewObject;

/**
 * Created by VuDuc on 8/15/2017.
 */

public class CustomPagerAdapter extends PagerAdapter {

    private Context mContext;

    public CustomPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        ViewObject viewObject = ViewObject.values()[position];
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewGroup layout = (ViewGroup) inflater.inflate(viewObject.getmLayoutResId(), collection, false);
        collection.addView(layout);
        return layout;
    }





    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return ViewObject.values().length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        ViewObject customPagerEnum = ViewObject.values()[position];
        return customPagerEnum.getmTitleResId();
    }

}
