/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 鍒涘缓鏃堕棿锛�2014-10-30 涓嬪崍7:55:13 绫昏鏄�
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

  private TabHost tabHost;

  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.lib_common_tabhost_activity_layout);
    tabHost = getTabHost();
    prepareDatas();
    addTabSpec();

  }

  private void addTabSpec() {

    ArrayList<TabItem> items = getTabItems();
    for (int i = 0; i < items.size(); i++) {
      TabItem one = items.get(i);

      View tabView = LayoutInflater.from(this).inflate(R.layout.tab_item_layout, null);
      TextView tab = (TextView) tabView.findViewById(R.id.tv_tab);
      setTabView(tab, i);

      TabHost.TabSpec oneSpec = tabHost.newTabSpec(one.getTag());
      oneSpec.setIndicator(tabView);
      if (one.getRecreateState()) {
        one.getContent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
      }
      oneSpec.setContent(one.getContent());
      tabHost.addTab(oneSpec);
    }

  }



  protected abstract void setTabView(TextView tabView, int position);

  protected abstract void prepareDatas();

  protected abstract ArrayList<TabItem> getTabItems();

  protected class TabItem {

    private String tag;
    private Intent content;
    private String tabName;
    private int tabBg;
    private boolean isRecreate;

    public TabItem(String tag, Intent content, String tabName) {
      this.tag = tag;
      this.content = content;
      this.tabName = tabName;
    }

    public void setTabBg(int resdId) {
      tabBg = resdId;
    }

    public int getTabBg() {
      return tabBg;
    }

    public String getTabName() {
      return tabName;
    }

    public String getTag() {
      return tag;
    }

    public Intent getContent() {
      return content;
    }

    public void setRecreateState(boolean state) {
      isRecreate = state;
    }

    public boolean getRecreateState() {
      return isRecreate;
    }
  }

}
