/*
 *  @author LuShuWei  E-mail:albertxiaoyu@163.com
 *  ����ʱ�� 2014-10-17
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
	 *@param url Ҫ���ص��ļ���URL��ַ
	 *@param savePath �����ļ��ı���λ�ã��Ǳ������ⲿ�洢����
	 *
	 *@return ���ر�ʶ�������ص�һλID
	 */
	public long startDownload(String url)
	{
		DownloadManager.Request  request = new DownloadManager.Request(Uri.parse(url));
	//	request.setDestinationUri(filePath);
		return _downloadManager.enqueue(request);
	}
	
	
	
	/**
	 *����һ��HashMap,��������������ǣ������ļ��Ĵ�С����ǰ�ļ��Ѿ����صĴ�С����ǰ�ļ�������״̬��
	 *ʹ�õ���Key���ֱ���DownloadManager.COLUMN_TOTAL_SIZE_BYTES,DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR
	 *DownloadManager.COLUMN_STATUS
	 *
	 *�����ع����У�ÿ�ε��ø÷��������ص����ݶ����ǲ�ͬ�ġ�
	 *������Խ��ContentObserver��ʹ�ã�ContentObserver���ϲ�ѯDownloadManager����Ӧ�����ݿ�
	 * 
	 * @param  downloadId  ��ʶĳ�����ص�ΨһID
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
