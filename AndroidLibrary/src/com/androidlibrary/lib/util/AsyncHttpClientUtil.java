/*
*  @author LuShuWei  E-mail:albertxiaoyu@163.com
*  创建时间 2014-10-20
*/

package com.androidlibrary.lib.util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncHttpClientUtil {
	
	
	private static AsyncHttpClient client = new AsyncHttpClient();
	
	public static AsyncHttpClient getClient()
	{
		return client;
	}
	
	public static void get(String url, RequestParams param, AsyncHttpResponseHandler responseHandler)
	{
		client.get(url, param, responseHandler);
	}
	
	public static void post(String url, RequestParams param, AsyncHttpResponseHandler responseHandler)
	{
		client.post(url, param, responseHandler);
	}

	
}

