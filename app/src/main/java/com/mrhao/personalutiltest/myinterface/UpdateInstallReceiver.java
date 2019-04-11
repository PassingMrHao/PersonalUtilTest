package com.mrhao.personalutiltest.myinterface;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.mrhao.personalutiltest.utils.PublicDateValue;

/**
 * 监听downloadManager下载文件的进度和状态
 */
public class UpdateInstallReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        //DownloadManager.ACTION_DOWNLOAD_COMPLETE是downloadManager给出的下载状态
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())) {
            //文件下载完成后的操作：核对downloadId,一致的话进行安装
            long downLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            long localsaveId = PublicDateValue.localDownLoadId; //取出保存在本地的downID

            if (downLoadId == localsaveId) {
                installApk(context, downLoadId);

            }
            Toast.makeText(context, "文件下载完成", Toast.LENGTH_SHORT).show();

        } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(intent.getAction())) {
            //处理：如果还未完成下载，用户点击通知栏下载标题时的可控操作

            //进入下载管理界面
            Intent viewDownloadIntent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
            viewDownloadIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(viewDownloadIntent);
        }
    }


    /**
     * DownloadManager下载完成后进行安装apk的操作
     *
     * @param context
     * @param downloadApkId
     */
    private void installApk(Context context, long downloadApkId) {
        DownloadManager dmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        if (dmanager == null) {
            return;
        }

        Uri downLoadFileUri = dmanager.getUriForDownloadedFile(downloadApkId);
        if (downLoadFileUri != null) {
            //打开Apk进入下载界面
            Intent install = new Intent(Intent.ACTION_VIEW);
            install.setDataAndType(downLoadFileUri, "application/vnd.android.package-archive");
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            context.startActivity(install);
        }
    }


}
