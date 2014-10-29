/** 
 * @author LuShuWei E-mail:albertxiaoyu@163.com 
 * @version 创建时间：2014-10-28 下午8:41:35 
 * 类说明 
 */

package com.androidlibrary.baidumap.fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.androidlibrary.lib.R;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowLocationInMapFragment extends Fragment {

	private BaiduMap mBaiduMap;
	private MapView mMapView;

	public static ShowLocationInMapFragment newInstance(JSONArray array) {
		ShowLocationInMapFragment f = new ShowLocationInMapFragment();
		Bundle args = new Bundle();
		args.putString("JSONArray", array.toString());
		f.setArguments(args);

		return f;
	}

	@Override
	public void onCreate(Bundle state) {
		super.onCreate(state);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle state) {
		if (container == null) {// fragment view can not be attached to a parent
								// view,
								// so there is no need to create the fragment
								// view,just return null
			return null;
		}
		Bundle args = getArguments();
		JSONArray array = getJSONArray(args.getString("JSONArray"));
		View layout = inflater
				.inflate(R.layout.lib_showlocation_fragment, null);
		initalBaiduMap(layout);
		try {
			setLocation((JSONObject)array.get(0));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TextView tv = (TextView) layout.findViewById(R.id.tv_result);
		tv.setText(array.toString());

		return layout;

	}

	@Override
	public void onResume() {
		mMapView.onResume();
		super.onResume();
	}

	@Override
	public void onPause() {
		mMapView.onPause();
		super.onPause();
	}

	@Override
	public void onDestroy() {
		// 关闭定位图层
		mBaiduMap.setMyLocationEnabled(false);
		mMapView.onDestroy();
		mMapView = null;
		super.onDestroy();

	}

	private JSONArray getJSONArray(String strArray) {
		try {
			JSONArray array = new JSONArray(strArray);
			return array;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private void initalBaiduMap(View layout) {
		mMapView = (MapView) layout.findViewById(R.id.bmapView);
		mBaiduMap = mMapView.getMap();

	}

	private void setLocation(JSONObject one) {
		try {

			MyLocationData locationData = generateMyLocationData(one.get("lat")
					+ "", one.get("Lon") + "");

			mBaiduMap.setMyLocationEnabled(true);// 开启图层
			mBaiduMap.setMyLocationData(locationData);

			LatLng ll = new LatLng(Double.valueOf(one.get("lat") + ""),
					Double.valueOf(one.get("Lon") + ""));
			MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
			mBaiduMap.animateMapStatus(u);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据经纬度，生成百度位置
	 */
	private MyLocationData generateMyLocationData(String latitude,
			String longtitude) {
		MyLocationData locData = new MyLocationData.Builder().accuracy(100)
				.direction(100).latitude(Double.valueOf(latitude))
				.longitude(Double.valueOf(longtitude)).build();
		return locData;
	}
}
