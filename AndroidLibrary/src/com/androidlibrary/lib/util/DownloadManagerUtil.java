/*
 * @author LuShuWei E-mail:albertxiaoyu@163.com 2014-10-17
 */

package com.androidlibrary.lib.util;

import java.util.HashMap;

import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;

public class DownloadManagerUtil {

  private DownloadManager downloadManager;
  private HashMap<String, Object> curInfo = new HashMap<String, Object>();



  public DownloadManagerUtil(DownloadManager downloadManager) {
    this.downloadManager = downloadManager;
  }


  public long startDownload(String url, Uri filePath) {
    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
    request.setDestinationUri(filePath);
    return downloadManager.enqueue(request);
  }



  public HashMap<String, Object> getCurDownloadInfo(long downloadId) {
    DownloadManager.Query query = new DownloadManager.Query();
    query.setFilterById(downloadId);

    Cursor result = downloadManager.query(query);
    if (result != null && result.moveToFirst()) {
      try {

        int max =
            result.getInt(result.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
        int cur =
            result.getInt(result
                .getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
        int status = result.getInt(result.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));

        String filePath =
            result.getString(result.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_FILENAME));

        curInfo.put(DownloadManager.COLUMN_TOTAL_SIZE_BYTES, max);
        curInfo.put(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR, cur);
        curInfo.put(DownloadManager.COLUMN_STATUS, status);
        curInfo.put(DownloadManager.COLUMN_LOCAL_FILENAME, filePath);


        return curInfo;

      } catch (Exception e) {
        e.printStackTrace();
      } finally {

        if (result != null)
          result.close();
      }
    }

    return null;
  }

}
