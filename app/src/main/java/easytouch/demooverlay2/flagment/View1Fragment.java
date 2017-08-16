package easytouch.demooverlay2.flagment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import easytouch.demooverlay2.R;

/**
 * Created by VuDuc on 8/15/2017.
 */

public class View1Fragment extends RelativeLayout {

    ViewGroup view1Layout;
    Button btn_nut1;
    public View1Fragment(Context context) {
        super(context);
        initialize();
    }

    public View1Fragment(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public View1Fragment(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {
        ViewGroup viewGroup = (ViewGroup) inflate(getContext(), R.layout.fragment_view1, this);
        view1Layout = (ViewGroup) viewGroup.getChildAt(0);

        btn_nut1 = view1Layout.findViewById(R.id.btn_nut1);

        btn_nut1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("chinh", "chinh da bam nut");
            }
        });
    }
}
