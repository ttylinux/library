/** 
 * @author LuShuWei E-mail:albertxiaoyu@163.com 
 * @version 创建时间：2014-10-28 下午8:41:35 
 * 类说明 
 */

package com.androidlibrary.baidumap.fragment;

import org.json.JSONArray;
import org.json.JSONException;

import com.androidlibrary.lib.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ShowLocationInMapFragment extends Fragment {

	public static ShowLocationInMapFragment newInstance(JSONArray array) {
		ShowLocationInMapFragment f = new ShowLocationInMapFragment();
		Bundle args = new Bundle();
		args.putString("JSONArray", array.toString());
		f.setArguments(args);

		return f;
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
		View layout = inflater.inflate(R.layout.lib_showlocation_fragment,
				null);
		TextView tv = (TextView)layout.findViewById(R.id.tv_result);
		tv.setText(array.toString());

		return layout;

	}
	
	private JSONArray getJSONArray(String strArray)
	{
		try {
			JSONArray array = new JSONArray(strArray);
			return array;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
