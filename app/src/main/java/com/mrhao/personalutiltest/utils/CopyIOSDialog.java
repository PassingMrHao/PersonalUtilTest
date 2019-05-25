package com.mrhao.personalutiltest.utils;


import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.ActionMode;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myinterface.FinishActIne;

public class CopyIOSDialog {

    private static iosDialog dialog = null;

    public static class iosDialog extends Dialog {

        public iosDialog(@NonNull final Context context,final FinishActIne ine) {
            super(context, R.style.BaseDialogStyle);
            View vv = getLayoutInflater().inflate(R.layout.goback_dialog, null, false);
            setContentView(vv);
            //设置dialog占屏幕大小及位置
            Window w = getWindow();
            WindowManager.LayoutParams p = w.getAttributes();
            p.gravity = Gravity.CENTER;
            w.setAttributes(p);

            TextView ok=vv.findViewById(R.id.ok);//确认退出
            TextView cancle=vv.findViewById(R.id.cancel);//取消

            cancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    closeDialog();
                    ine.finishAct();//接口回调，在调用的地方重写需要的方法
                }
            });

        }

    }


    public static iosDialog showDialog(Context context,FinishActIne ine) {

        dialog = new iosDialog(context,ine);
        dialog.setCanceledOnTouchOutside(true);  //点击界外是否可以关闭dialog，true表示可以；false表示不可以
        dialog.show(); //显示dialog
        return dialog;

    }

    public static void closeDialog(){

        dialog.dismiss(); // 关闭dialog

    }

}
