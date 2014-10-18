/*
 *  @author LuShuWei  E-mail:albertxiaoyu@163.com
 *  创建时间 2014-10-17
 */

package com.androidlibrary.lib.util;

import java.util.HashMap;



import android.app.DownloadManager;
import android.database.Cursor;
import android.net.Uri;

public class DownloadManagerUtil {

	private DownloadManager _downloadManager;
	private HashMap<String, Integer> _curInfo = new HashMap<String, Integer>();
    
	
	
	public DownloadManagerUtil(DownloadManager downloadManager) {
		_downloadManager = downloadManager;
	}

	/**
	 * 
	 *@param url 要下载的文件的URL地址
	 *@param savePath 下载文件的保存位置，是保存在外部存储器的
	 *
	 *@return 返回标识本次下载的一位ID
	 */
	public long startDownload(String url)
	{
		DownloadManager.Request  request = new DownloadManager.Request(Uri.parse(url));
	//	request.setDestinationUri(filePath);
		return _downloadManager.enqueue(request);
	}
	
	
	
	/**
	 *返回一个HashMap,里面包含的数据是，下载文件的大小，当前文件已经下载的大小，当前文件的下载状态。
	 *使用到的Key，分别是DownloadManager.COLUMN_TOTAL_SIZE_BYTES,DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR
	 *DownloadManager.COLUMN_STATUS
	 *
	 *在下载过程中，每次调用该方法，返回的数据都会是不同的。
	 *这个可以结合ContentObserver来使用，ContentObserver不断查询DownloadManager所对应的数据库
	 * 
	 * @param  downloadId  标识某次下载的唯一ID
	 * 
	 * @return HashMap<String,Integer>
	 */
	public HashMap<String, Integer> getCurDownloadInfo(long downloadId) {
		DownloadManager.Query query = new DownloadManager.Query();
		query.setFilterById(downloadId);

		Cursor result = _downloadManager.query(query);
		if (result != null && result.moveToFirst()) {
			try {

				int max = result
						.getInt(result
								.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
				int cur = result
						.getInt(result
								.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
				int status = result.getInt(result
						.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS));

				_curInfo.put(DownloadManager.COLUMN_TOTAL_SIZE_BYTES, max);
				_curInfo.put(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR,
						cur);
				_curInfo.put(DownloadManager.COLUMN_STATUS, status);

				return _curInfo;

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
