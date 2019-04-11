package com.mrhao.personalutiltest.utils;

import android.content.Context;

public class DownLoadConfig {
    private Context mContext;
    private String feilurl = "";    //下载文件地址
    private String downpath = "";   //下载完成后保存到的路径名
    private String downApkName = "";    //下载完成后保存到手机中的文件名
    private String infoNmae = "";   //显示在通知栏的下载文件名
    private String infoDesc = "";   //通知栏显示的下载文件内容
    private boolean canMediaScanner;//文件能否被设备扫描到

    int AllowedNetworkTypes = 0;//下载网络类型：WiFi和移动网络流量

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    private DownLoadConfig(Context context) {

        this.mContext = context;
    }

    public boolean isCanMediaScanner() {
        return canMediaScanner;
    }

    public void setCanMediaScanner(boolean canMediaScanner) {
        this.canMediaScanner = canMediaScanner;
    }

    public int getAllowedNetworkTypes() {
        return AllowedNetworkTypes;
    }

    public void setAllowedNetworkTypes(int allowedNetworkTypes) {
        AllowedNetworkTypes = allowedNetworkTypes;
    }

    public String getFeilurl() {
        return feilurl;
    }

    public void setFeilurl(String feilurl) {
        this.feilurl = feilurl;
    }

    public String getDownpath() {
        return downpath;
    }

    public void setDownpath(String downpath) {
        this.downpath = downpath;
    }

    public String getDownApkName() {
        return downApkName;
    }

    public void setDownApkName(String downApkName) {
        this.downApkName = downApkName;
    }

    public String getInfoNmae() {
        return infoNmae;
    }

    public void setInfoNmae(String infoNmae) {
        this.infoNmae = infoNmae;
    }

    public String getInfoDesc() {
        return infoDesc;
    }

    public void setInfoDesc(String infoDesc) {
        this.infoDesc = infoDesc;
    }

    public static class Builder {
        DownLoadConfig config;

        public Builder(Context context) {
            config = new DownLoadConfig(context.getApplicationContext());
        }


        /**
         * 设置文件下载地址
         *
         * @param downUrl
         * @return
         */
        public Builder setFeilurl(String downUrl) {
            config.setFeilurl(downUrl);
            return this;
        }


        /**
         * 设置文件下载路径
         *
         * @param downpath
         * @return
         */
        public Builder setDownpath(String downpath) {
            config.setDownpath(downpath);
            return this;
        }


        /**
         * 设置完成后存放到路径中的文件名
         *
         * @param downApkName
         * @return
         */
        public Builder setDownApkName(String downApkName) {
            config.setDownApkName(downApkName);
            return this;
        }


        /**
         * 设置在通知栏中下载文件的名字
         *
         * @param infoNmae
         * @return
         */
        public Builder setInfoNmae(String infoNmae) {
            config.setInfoNmae(infoNmae);
            return this;
        }


        /**
         * 设置在通知栏中对下载文件的描述
         *
         * @param infoDesc
         * @return
         */
        public Builder setInfoDesc(String infoDesc) {
            config.setInfoDesc(infoDesc);
            return this;
        }


        /**
         * 设置文件下载完成后能否被设备扫描到
         *
         * @param canMediaScanner
         * @return
         */
        public Builder setCanMediaScanner(boolean canMediaScanner) {
            config.canMediaScanner = canMediaScanner;
            return this;
        }


        /**
         * 设置下载文件的网络环境:wifi或流量
         *
         * @param type
         * @return
         */
        public Builder setAllowedNetworkTypes(int type) {
            config.AllowedNetworkTypes = type;
            return this;
        }

        public DownLoadConfig build() {
            return config;
        }

    }
}
