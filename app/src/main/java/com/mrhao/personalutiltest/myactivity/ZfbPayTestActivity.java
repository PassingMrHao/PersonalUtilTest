package com.mrhao.personalutiltest.myactivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.mrhao.personalutiltest.R;
import com.mrhao.personalutiltest.myclass.BaseActivity;
import com.mrhao.personalutiltest.widget.AuthResult;
import com.mrhao.personalutiltest.widget.OrderInfoUtil2_0;
import com.mrhao.personalutiltest.widget.PayResult;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZfbPayTestActivity extends BaseActivity {

    @BindView(R.id.tv_zfbpay)
    TextView tvZfbpay;

    private static final int SDK_PAY_FLAG = 1; //支付金额
    private static final int SDK_AUTH_FLAG = 2; //支付宝授权


    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    //演多多应用APPID： 2019091667406731
    //沙箱应用APPID：  2016091400507853
    public static final String APPID = "2016091400507853";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCFEkujjndBzf4/oNWzlCpBme1RkO1HCPZOFQLsc6Qzl0gG5rw79gcad7IhCkNzjZtb7QqA3TmQubjx3aXsCQSr8cXaOeYuA1tkoDuUjDcTaPFn+92SVTkyHdzcshrbs/i/DlRKLb3S5mNF8ts7ig3qEeiif46ziZkLKNlzPmTHGkhbnYHqCF89vcQdDIT5euLtfabUD2YvVY2cY7sWXCqcBv6mrLigV8YLVENayAtVuti9olosG863sb6V46FjTp2jeCtl5hT8NtKzjEBuGpuHFklXlQHVbsnxHouyDbROXsrUk48/bBFsm65SdepDb15Bpl70UKqPvKefFSJw4w3lAgMBAAECggEASg23aCqlNUqkfdZMdqYyqdU2W1jyosBbCXLp8JoeO9OpcqW/BQNSSqlnyLlEdi7M0WhlmvcBzQd946N8bZFLN0DR4TKtZNfbaXE+Ftm84KFbDon8cS3aKYiy22KGTdOmzvPzdmBkLdSmVEvlMwoN2j/jHWMtD764G5y8/kQvoxire/21fCE5/FfUQW+BGZwn5z/afgl6LbG8PQmzr4AJh7q8yKafoSfwZxWFVA/j60r3roTUHRGci+OSpmogGsSJfMd6gBNDN0TgEDhmnBVBHNVsKI22/oQAoZSRgxO7FmrftU8ucjT3f9OR14iyHkcJn8VN2cr2RvefwhaYIX9iAQKBgQDnvxyjS/cXlD1nDVDBsae1T/k34TJQZZ2n0HEVQQCi2rwILY9XKEVgEpwfie68ISWDA6Jbl3BC+sluZ+TI4MFdpavyjGvpUz40iZ9Ts9OkS8n7tdfn4B5zIfCGZ3VHErnNCrZUCVpfz3X0qvlHsbWFhO3DsX4v8ZKsEps1xOMXgQKBgQCS/4JIvjX2PAmAfi71m6Pp7r7/PNjUdVcaMKzEDMw3sBRl+oTvI/990qlMWuU6bx/5Bs7uqzQzJrGzD29lbDR0T5ReI5htuMOOiccHYipKZwRybVAGaG/eYCBcSnB1MRoQok3oC0puw4OlPASZ0P8idHjjXo6A+lBRGggR/+7IZQKBgQDcTbxOrB94Bc8dUPYtO9J/NUhoS6dF8GOgLTHjD4koR6GqcI0sQLc6rlZGURxnMWd0lZbZVSw+MCQe1ZsTLz4C5USBbeYDx8OBtDaHHpMaQ4X+yA44esDXmDMLwfwmFXIVrpJHbyC/pfuvHH2fHxu9kvFoMCoSL6bL92AfDE0JgQKBgHcWIhEWb5b1GmmLUDXPiiJJNViHjc3I5fCkwEHvcD4pGs1m/+zQh/oCAP+Hn68QOaHAH0KBVj+eOFWsyn0rBO+5TKRbR/CTv3GpMqFLqKEwBg1Be5RFZWONEopSD/sQ9WHGa0DybLGBGRRyo/OMujTKkOj6QI5Cu3CIm0MhyOt1AoGAT26sn6dCeGbqZa0MxeqEps0jEPFTaEUGrOLt0HtOq1f814mscDnnbKUY5lrXYIie+nUDcA2AdL++WIaztNAUWQz2/KQG23/UKmD7VhEb+qBsbfXbzdzWZpJJaF2OpaRlkvcQKIbNYv5+CeYthW5kJUvXHxokwJdWvFRUJsiCjks=";


    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showAlert(ZfbPayTestActivity.this, "支付成功！");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(ZfbPayTestActivity.this, "支付失败！"+ payResult.getMemo());
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(ZfbPayTestActivity.this, "授权成功！" + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(ZfbPayTestActivity.this, "授权失败！" + authResult);
                    }
                    break;
                }

                default:
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);//打开支付宝沙箱
        setContentView(R.layout.activity_zfb_pay_test);
        ButterKnife.bind(this);
        setBaseEvent();
    }

    private void setBaseEvent() {
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String sign = OrderInfoUtil2_0.getSign(params, RSA2_PRIVATE, rsa2);
        final String orderInfo = orderParam + "&" + sign;


        tvZfbpay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Runnable payRunnable = new Runnable() {
                    @Override
                    public void run() {
                        PayTask alipay = new PayTask(ZfbPayTestActivity.this);
                        Map<String, String> result = alipay.payV2(orderInfo, true);
                        Log.i("msp", result.toString());

                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = result;
                        mHandler.sendMessage(msg);
                    }
                };

                // 必须异步调用
                Thread payThread = new Thread(payRunnable);
                payThread.start();
            }
        });

    }



    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    @SuppressLint("NewApi")
    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton("确定", null)
                .setOnDismissListener(onDismiss)
                .show();
    }


}
