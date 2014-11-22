/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 创建时间：2014-10-28 下午7:45:36 类说明
 */

package com.androidlibrary.baidumap.activity;

import com.androidlibrary.lib.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowLocationActivity extends Activity {

  private TextView tv;

  public void onCreate(Bundle state) {
    super.onCreate(state);
    setContentView(R.layout.lib_showlocation_layout);
    inital();

    Intent intent = getIntent();
    tv.setText(intent.getStringExtra("Values"));
  }

  private void inital() {
    tv = (TextView) findViewById(R.id.tv_result);

  }
}
