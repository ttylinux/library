/** 
 * @author LuShuWei E-mail:albertxiaoyu@163.com 
 * @version 鍒涘缓鏃堕棿锛�2014-10-30 涓嬪崍7:55:13 
 * 绫昏鏄� 
 */

package com.androidlibrary.activity;

import java.util.ArrayList;

import com.androidlibrary.lib.R;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;

public abstract class CommonTabActivity extends TabActivity {

	private TabHost _tabHost;
	private View _tab_view;

	@Override
	protected void onCreate(Bundle state) {
		super.onCreate(state);
		setContentView(R.layout.lib_common_tabhost_activity_layout);
		_tabHost = getTabHost();
		prepareDatas();
		addTabSpec();
	}

	private void addTabSpec() {
		
		
	      ArrayList<TabItem> items = getTabItems();
	      for(int i = 0; i < items.size();i++)
	      {
	    	  TabItem one = items.get(i);
	    	  
	    	  View tabView = LayoutInflater.from(this).inflate(R.layout.tab_item_layout,
	  				null);
	    	  ImageView icon = (ImageView)tabView.findViewById(R.id.imageView_tab);
	    	  icon.setImageResource(one.getIcon());
	    	  
	    	  TabHost.TabSpec oneSpec = _tabHost.newTabSpec(one.getTag());
	    	  oneSpec.setIndicator(tabView);
	    	  oneSpec.setContent(one.getContent());
	    	  
	    	  _tabHost.addTab(oneSpec);
	      }
		
	}

  protected abstract void prepareDatas();
  protected abstract ArrayList<TabItem> getTabItems();
	protected class TabItem {

		private int _icon_id;
		private String _tag;
		private Intent _content;

		public TabItem(int icon_id, String tag, Intent content) {
			_icon_id = icon_id;
			_tag = tag;
			_content = content;
		}
		
		public int getIcon()
		{
			return _icon_id;
		}
		
		public String getTag()
		{
			return _tag;
		}
		
		public Intent getContent()
		{
			return _content;
		}

	}

}
