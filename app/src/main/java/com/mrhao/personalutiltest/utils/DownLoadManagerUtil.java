package com.mrhao.personalutiltest.utils;

import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.net.Uri;
import static android.content.Context.DOWNLOAD_SERVICE;

public class DownLoadManagerUtil {

    static DownloadManager md;
    public static void downFile( DownLoadConfig config) {


        md = (DownloadManager) config.getmContext().getSystemService(DOWNLOAD_SERVICE); //启用DownLoadManager控件

        Request re = new Request(Uri.parse(config.getFeilurl()));
        //设置下载的本地路径，如下表示下载到手机上的‘Download’文件夹内，apk名称为‘XXX’：
        re.setDestinationInExternalPublicDir(config.getDownpath(), config.getDownApkName());

        //一些非必要设置---------------------start
        re.setAllowedNetworkTypes(Request.NETWORK_WIFI | Request.NETWORK_MOBILE); //设置下载网络环境：浏览和WiFi都可以

        //表示下载进行中和下载完成的通知栏是否显示。默认只显示下载中通知。VISIBILITY_VISIBLE_NOTIFY_COMPLETED表示下载完成后显示通知栏提示。
        // VISIBILITY_HIDDEN表示不显示任何通知栏提示，这个需要在AndroidMainfest中添加权限android.permission.DOWNLOAD_WITHOUT_NOTIFICATION.
        re.setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        re.setVisibleInDownloadsUi(true);    //是否希望下载的文件可以被系统的Downloads应用扫描到并管理
        re.setTitle(config.getInfoNmae());  //通知栏下载标题
        re.setDescription(config.getInfoDesc());      //下载内容描述

        //一些非必要设置---------------------end

            PublicDateValue.localDownLoadId = md.enqueue(re);   //在全局变量中保存downLoadId，需要创建个存储全局变量的类PublicDateValue

    }



}
