/*
 *  @author LuShuWei  E-mail:albertxiaoyu@163.com
 *  创建时间 2014-10-10
 */

package com.android.lib.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;

import android.util.Log;

public class DateUtil {

	public static final String TAG = "DateUtil";
	public static HashMap<Integer, Integer> monthDay = new HashMap<Integer, Integer>();
	public static final int WeekLength = 7;

	public static HashMap<Integer, String> seasonQ = new HashMap<Integer, String>();

	static {

		monthDay.put(1, 31);
		monthDay.put(2, 28);
		monthDay.put(3, 31);
		monthDay.put(4, 30);
		monthDay.put(5, 31);
		monthDay.put(6, 30);
		monthDay.put(7, 31);
		monthDay.put(8, 31);
		monthDay.put(9, 30);
		monthDay.put(10, 31);
		monthDay.put(11, 30);
		monthDay.put(12, 31);

		seasonQ.put(1, "Q1");
		seasonQ.put(2, "Q1");
		seasonQ.put(3, "Q1");

		seasonQ.put(4, "Q2");
		seasonQ.put(5, "Q2");
		seasonQ.put(6, "Q2");

		seasonQ.put(7, "Q3");
		seasonQ.put(8, "Q3");
		seasonQ.put(9, "Q3");

		seasonQ.put(10, "Q4");
		seasonQ.put(11, "Q4");
		seasonQ.put(12, "Q4");
	}

	public static String getStartofTheWeek(String end) {

		try {
			SimpleDateFormat sdOrginal = new SimpleDateFormat("yyyy-mm-dd",
					Locale.CHINA);
			SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-m-d",
					Locale.CHINA);

			String startYear = "";
			String startMonth = "";
			String startDay = "";
			StringBuffer sb = new StringBuffer();
			Date date;
			date = sdOrginal.parse(end);
			String newEnd = newFormat.format(date);
			String[] dateArray = newEnd.split("-");
			// Log.d(TAG, "newEnd:"+newEnd);
			if (dateArray.length == 3) {
				setFeb(Integer.valueOf(dateArray[0]));

				int year = Integer.valueOf(dateArray[0]);
				int month = Integer.valueOf(dateArray[1]);
				int day = Integer.valueOf(dateArray[2]);
				if (day < WeekLength) {
					if (month == 1) {// 1月
						startYear = (year - 1) + "";
						startMonth = "12";
						startDay = (monthDay.get(12) - (WeekLength - day) + 1)
								+ "";

					} else {
						startYear = year + "";
						startMonth = (month - 1) + "";
						startDay = (monthDay.get(Integer.valueOf(startMonth))
								- (WeekLength - day) + 1)
								+ "";
					}

				} else if (Integer.valueOf(dateArray[2]) >= WeekLength) {

					startYear = year + "";
					startMonth = month + "";
					startDay = (day - WeekLength + 1) + "";

				}
				sb.append(startYear + "-" + startMonth + "-" + startDay);
				return sb.toString();
			} else {
				Log.e(TAG, "date format error");
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";

	}

	public static void setFeb(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			monthDay.put(2, 29);
		} else {
			monthDay.put(2, 28);
		}
	}

	public static String getSeason(String strDate) {
		SimpleDateFormat sdOrginal = new SimpleDateFormat("yyyy-mm-dd",
				Locale.CHINA);
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-m-d",
				Locale.CHINA);

		try {
			Date date;
			date = sdOrginal.parse(strDate);
			String newStrDate = newFormat.format(date);
			String[] dateArray = newStrDate.split("-");

			return seasonQ.get(Integer.valueOf(dateArray[1]));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	

}
