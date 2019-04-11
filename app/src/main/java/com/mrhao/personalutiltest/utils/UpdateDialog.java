package com.mrhao.personalutiltest.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myinterface.UpDateIne;

public class UpdateDialog {

    private static updialog ud = null;
    private static TextView version;
    private static TextView desc;
    private static TextView update;

    private static class updialog extends Dialog {

        public updialog(Context context) {
            super(context, R.style.BaseDialogStyle);
            View v = getLayoutInflater().inflate(R.layout.mydialog_test, null);
            setContentView(v);
            Window w = getWindow();
            WindowManager.LayoutParams p = w.getAttributes();
            p.gravity = Gravity.CENTER;
            w.setAttributes(p);
            ImageView img = v.findViewById(R.id.up_close);
            TextView ignore = v.findViewById(R.id.up_ignore);
            version = v.findViewById(R.id.up_version);
            desc = v.findViewById(R.id.up_contnet);
            update = v.findViewById(R.id.up_update);

            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ud.dismiss();
                }
            });


            ignore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ud.dismiss();
                }
            });


        }
    }

    public static updialog showUpDate(Context content, UpDateIne ine) {
        ud = new updialog(content);
        ud.show();
        ine.BaseEvent(version,desc,update);
        return ud;
    }

    public static void closeDialog(){
        ud.dismiss();

    }
}
