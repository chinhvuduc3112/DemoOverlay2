package easytouch.demooverlay2.model;

import easytouch.demooverlay2.R;

/**
 * Created by VuDuc on 8/15/2017.
 */

public enum ViewObject {

    VIEW1("View1", R.layout.fragment_view1),
    VIEW2("View2", R.layout.fragment_view2);

    private String mTitleResId;
    private int mLayoutResId;

    ViewObject(String titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public String getmTitleResId() {
        return mTitleResId;
    }

    public void setmTitleResId(String mTitleResId) {
        this.mTitleResId = mTitleResId;
    }

    public int getmLayoutResId() {
        return mLayoutResId;
    }

    public void setmLayoutResId(int mLayoutResId) {
        this.mLayoutResId = mLayoutResId;
    }
}
