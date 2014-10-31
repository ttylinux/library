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
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

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
		for (int i = 0; i < items.size(); i++) {
			TabItem one = items.get(i);

			View tabView = LayoutInflater.from(this).inflate(
					R.layout.tab_item_layout, null);
		    TextView tab = (TextView)tabView.findViewById(R.id.tv_tab);
		    setTabView(tab,i);

			TabHost.TabSpec oneSpec = _tabHost.newTabSpec(one.getTag());
			oneSpec.setIndicator(tabView);
			if (one.getRecreateState()) {
				one.getContent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			}
			oneSpec.setContent(one.getContent());
			_tabHost.addTab(oneSpec);
		}

	}
	


	protected abstract void setTabView(TextView tabView,int position);
	protected abstract void prepareDatas();
    
	protected abstract ArrayList<TabItem> getTabItems();

	protected class TabItem {

		private String _tag;
		private Intent _content;
		private String _tabName;
		private int _tabBg;
		private boolean _isRecreate;

		public TabItem(String tag, Intent content, String tabName) {
			_tag = tag;
			_content = content;
			_tabName = tabName;
		}

		public void setTabBg(int resdId)
		{
			_tabBg = resdId;
		}
		
		public int getTabBg() {
			return _tabBg;
		}

		public String getTabName() {
			return _tabName;
		}

		public String getTag() {
			return _tag;
		}

		public Intent getContent() {
			return _content;
		}

		public void setRecreateState(boolean state) {
			_isRecreate = state;
		}

		public boolean getRecreateState() {
			return _isRecreate;
		}
	}

}
