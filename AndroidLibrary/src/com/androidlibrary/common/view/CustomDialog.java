/**
 * @author LuShuWei E-mail:albertxiaoyu@163.com
 * @version 创建时间：2014-10-27 下午7:54:51 类说明
 */

package com.androidlibrary.common.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;

public class CustomDialog extends Dialog {

  private int layoutResId;

  public CustomDialog(Context context) {
    super(context);
    // TODO Auto-generated constructor stub
  }

  public CustomDialog(Context context, int layoutId) {
    super(context);
    layoutResId = layoutId;
  }


  @Override
  protected void onCreate(Bundle state) {
    super.onCreate(state);
    this.requestWindowFeature(Window.FEATURE_NO_TITLE);
    this.setContentView(layoutResId);
  }

}
