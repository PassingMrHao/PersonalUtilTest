package com.mrhao.personalutiltest.myactivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.utils.MyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioButtonActivity extends BaseActivity {

    @BindView(R.id.rb_youji)
    RadioButton rbYouji;
    @BindView(R.id.rb_qupiao)
    RadioButton rbQupiao;
    @BindView(R.id.rb_yanpiao)
    RadioButton rbYanpiao;
    @BindView(R.id.group_choose)
    RadioGroup groupChoose;
    @BindView(R.id.rb_youji2)
    RadioButton rbYouji2;
    @BindView(R.id.rb_qupiao2)
    RadioButton rbQupiao2;
    @BindView(R.id.rb_yanpiao2)
    RadioButton rbYanpiao2;
    @BindView(R.id.group_choose2)
    RadioGroup groupChoose2;
    @BindView(R.id.group_choose3)
    RadioGroup groupChoose3;
    @BindView(R.id.add_groupbutton)
    TextView addGroupbutton;


    String[] rbname = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};
    @BindView(R.id.daojishi_method1)
    TextView daojishiMethod1;//倒计时方法1
    @BindView(R.id.daojishi_method2)
    TextView daojishiMethod2;//倒计时方法2
    @BindView(R.id.tv_daojishi)
    TextView tvDaojishi;//倒计时显示
    int shuNum = 0;
    int shuNum2 = 0;

    boolean runThead = true;
    boolean runThead2 = true;

    final Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    shuNum = shuNum + 1;
                    tvDaojishi.setText(MyUtils.formatDuring2(1571475343));
                    Toast.makeText(RadioButtonActivity.this, shuNum + "S", Toast.LENGTH_SHORT).show();
                    break;
            }
            super.handleMessage(msg);
        }
    };



    @BindView(R.id.tv_daojishi2)
    TextView tvDaojishi2;//倒计时显示2
    Handler handler2 = new Handler();

    Runnable ru=new Runnable() {
        @Override
        public void run() {
            if(runThead2==true){
                shuNum2=shuNum2+1;
                tvDaojishi2.setText(shuNum2+" S");
                handler2.postDelayed(this,1000);
                Toast.makeText(RadioButtonActivity.this, shuNum + "S", Toast.LENGTH_SHORT).show();
            }

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button);
        ButterKnife.bind(this);
        setBaseEvent();
        rbQupiao2.setBackgroundResource(R.drawable.btn_huiyuanline);
        rbYanpiao2.setBackgroundResource(R.drawable.btn_huiyuanline);
        rbYouji2.setBackgroundResource(R.drawable.btn_huiyuanline);
        SetDaoJiShiMethod();

    }

    //两种倒计时方法
    private void SetDaoJiShiMethod() {

        daojishiMethod1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new MyThread()).start();
            }
        });


        daojishiMethod2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handler2.postDelayed(ru, 1000);
            }
        });


    }


    public class MyThread implements Runnable {// thread

        @Override
        public void run() {
            while (runThead == true) {
                try {
                    Thread.sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    handler1.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }

    }


    private void setBaseEvent() {

        groupChoose.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                GetChooseResult(checkedId);
            }
        });
        groupChoose2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                GetChooseResult2(checkedId);
            }
        });


        //横向动态添加子选项item，重要参考： https://blog.csdn.net/shihuiyun/article/details/52935392
        addGroupbutton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View v) {
                for (int i = 0; i < rbname.length; i++) {
                    RadioButton button = new RadioButton(RadioButtonActivity.this);
                    String nnnn = rbname[i];
                    button.setText(rbname[i]);
                    button.setButtonDrawable(null);
                    button.setBackgroundResource(R.drawable.checkback);
                    button.setTextColor(getResources().getColorStateList(R.drawable.colorchoose));
                    groupChoose3.addView(button);
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button
                            .getLayoutParams();
                    layoutParams.setMargins(0, 0, 60, 0);//4个参数按顺序分别是左上右下
                    button.setLayoutParams(layoutParams);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RadioButtonActivity.this, nnnn, Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });

        groupChoose3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {

            }
        });


    }

    private void GetChooseResult(int i) {
        switch (i) {
            case R.id.rb_youji://邮寄
                rbYouji.setBackgroundResource(R.drawable.btn_blueback);
                rbYouji.setTextColor(getResources().getColor(R.color.white));

                rbQupiao.setBackgroundResource(R.drawable.btn_blackline);
                rbQupiao.setTextColor(getResources().getColor(R.color.text_black));
                rbYanpiao.setBackgroundResource(R.drawable.btn_blackline);
                rbYanpiao.setTextColor(getResources().getColor(R.color.text_black));
                break;

            case R.id.rb_qupiao://现场取票
                rbQupiao.setBackgroundResource(R.drawable.btn_blueback);
                rbQupiao.setTextColor(getResources().getColor(R.color.white));

                rbYouji.setBackgroundResource(R.drawable.btn_blackline);
                rbYouji.setTextColor(getResources().getColor(R.color.text_black));
                rbYanpiao.setBackgroundResource(R.drawable.btn_blackline);
                rbYanpiao.setTextColor(getResources().getColor(R.color.text_black));
                break;
            case R.id.rb_yanpiao://电子验票
                Toast.makeText(RadioButtonActivity.this, "该方式还在测试中，请选择其他配送方式", Toast.LENGTH_SHORT).show();

                break;
            default:
                break;

        }

    }

    private void GetChooseResult2(int i) {
        switch (i) {
            case R.id.rb_youji2://邮寄
                rbQupiao2.setBackgroundResource(R.drawable.btn_huiyuanline);
                rbYanpiao2.setBackgroundResource(R.drawable.btn_huiyuanline);
                rbYouji2.setBackgroundResource(R.drawable.btn_huiyuan);
                break;

            case R.id.rb_qupiao2://现场取票
                rbQupiao2.setBackgroundResource(R.drawable.btn_huiyuan);
                rbYanpiao2.setBackgroundResource(R.drawable.btn_huiyuanline);
                rbYouji2.setBackgroundResource(R.drawable.btn_huiyuanline);
                break;
            case R.id.rb_yanpiao2://电子验票
                rbQupiao2.setBackgroundResource(R.drawable.btn_huiyuanline);
                rbYanpiao2.setBackgroundResource(R.drawable.btn_huiyuan);
                rbYouji2.setBackgroundResource(R.drawable.btn_huiyuanline);
                break;
            default:
                break;

        }

    }


    @SuppressLint("ResourceType")
    private void setRaidBtnAttribute(final RadioButton codeBtn, String btnContent, int id) {
        if (null == codeBtn) {
            return;
        }
//        codeBtn.setBackgroundResource(R.drawable.radio_group_selector);
//        codeBtn.setTextColor(this.getResources().getColorStateList(R.drawable.color_radiobutton));
//        codeBtn.setButtonDrawable(new ColorDrawable(Color.TRANSPARENT));
//        //codeBtn.setTextSize( ( textSize > 16 )?textSize:24 );
//        codeBtn.setId( id );
//        codeBtn.setText( btnContent );
//        //codeBtn.setPadding(2, 0, 2, 0);
//
//        codeBtn.setGravity( Gravity.CENTER );
//        codeBtn.setOnClickListener( new View.OnClickListener( ) {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(RadioButtonActivity.this, codeBtn.getText().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//        //DensityUtilHelps.Dp2Px(this,40)
//        LinearLayout.LayoutParams rlp = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.WRAP_CONTENT , DensityUtilHelps.Dp2Px(this,25) );
//
//        codeBtn.setLayoutParams( rlp );
    }


    @Override
    protected void onDestroy() {
//        handler1.removeCallbacks(aa);
        runThead = false;
        runThead2 = false;
        super.onDestroy();

    }
}
