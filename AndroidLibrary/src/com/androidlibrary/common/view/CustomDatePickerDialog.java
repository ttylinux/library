package com.androidlibrary.common.view;

import java.util.Calendar;
import java.util.HashMap;

import com.androidlibrary.lib.R;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class CustomDatePickerDialog {


  private HashMap<Integer, String> dayOfWeekStr = new HashMap<Integer, String>();
  {

    dayOfWeekStr.put(Calendar.SUNDAY, "����");
    dayOfWeekStr.put(Calendar.MONDAY, "��һ");
    dayOfWeekStr.put(Calendar.TUESDAY, "�ܶ�");
    dayOfWeekStr.put(Calendar.WEDNESDAY, "����");
    dayOfWeekStr.put(Calendar.THURSDAY, "����");
    dayOfWeekStr.put(Calendar.FRIDAY, "����");
    dayOfWeekStr.put(Calendar.SATURDAY, "����");


  }



  private DatePickerListener mListener;
  private Context mContext;
  private View mCustomView;
  private AlertDialog mDialog;
  private DatePicker mDatePicker;
  private TextView m_tv_selectdate;
  private DatePicker.OnDateChangedListener mOnDateChangedListener =
      new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker arg0, int year, int monthOfYear, int dayOfMonth) {
          // TODO Auto-generated method stub
          Calendar cal = Calendar.getInstance();
          cal.set(Calendar.YEAR, year);
          cal.set(Calendar.MONTH, monthOfYear);
          cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

          int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);

          m_tv_selectdate.setText(year + "-" + monthOfYear + "-" + dayOfMonth
              + dayOfWeekStr.get(dayOfWeek));
        }
      };


  public CustomDatePickerDialog(Context context, DatePickerListener listener) {
    mListener = listener;
    mContext = context;
  }

  public void init() {

    initalView();
    displayDialog();

  }

  private void displayDialog() {
    mDialog.show();
  }

  private void initalView() {
    mCustomView = LayoutInflater.from(mContext).inflate(R.layout.lib_custom_datepicker, null);
    mDatePicker = (DatePicker) mCustomView.findViewById(R.id.datepicker);
    m_tv_selectdate = (TextView) mCustomView.findViewById(R.id.tv_selectdate);

    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
    builder.setView(mCustomView);
    mDialog = builder.create();

    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH);
    int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
    m_tv_selectdate.setText(year + "-" + month + "-" + dayOfMonth
        + dayOfWeekStr.get(cal.get(Calendar.DAY_OF_WEEK)));
    mDatePicker.init(year, month, dayOfMonth, mOnDateChangedListener);



    Button okBtn = (Button) mCustomView.findViewById(R.id.btn_ok);
    Button cancleBtn = (Button) mCustomView.findViewById(R.id.btn_cancle);

    okBtn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View arg0) {
        // TODO Auto-generated method stub
        mListener.onDateChangeListener(mDatePicker.getYear(), mDatePicker.getMonth() + 1,
            mDatePicker.getDayOfMonth());
        mDialog.dismiss();
      }
    });
    cancleBtn.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        // TODO Auto-generated method stub
        mDialog.dismiss();
      }
    });

  }

}
