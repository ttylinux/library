/*
 *  @author LuShuWei  E-mail:albertxiaoyu@163.com
 *  创建时间 2014-10-14
 */

package com.androidlibrary.lib.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DateUtil {

	public static final int Jan = 1;
	public static final int Dec = 12;
	public static final int WeekLength = 7;
	public static final int PartNumber = 3;
	public static HashMap<Integer, String> seasonQ = new HashMap<Integer, String>();
	public static HashMap<Integer, Integer> monthDay = new HashMap<Integer, Integer>();

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

		seasonQ.put(1, "1");
		seasonQ.put(2, "1");
		seasonQ.put(3, "1");

		seasonQ.put(4, "2");
		seasonQ.put(5, "2");
		seasonQ.put(6, "2");

		seasonQ.put(7, "3");
		seasonQ.put(8, "3");
		seasonQ.put(9, "3");

		seasonQ.put(10, "4");
		seasonQ.put(11, "4");
		seasonQ.put(12, "4");
	}

	/**
	 * Get the start of the week
	 * 
	 * @param endOftheWeek
	 *            the end of this week.dateFormat is yyyy-mmm-dd
	 * @return theStartOftheWeek
	 * @throws Exception
	 */
	public static String getStartOfTheWeek(String endOftheWeek)
			throws Exception {

		String[] end = parseDateToStrArray(endOftheWeek);
		String startYear = "";
		String startMonth = "";
		String startDay = "";

		if (end.length < PartNumber) {
			throw new Exception("param is invalid");
		} else {
			int year = Integer.valueOf(end[0]);
			int month = Integer.valueOf(end[1]);
			int day = Integer.valueOf(end[2]);

			if (Integer.valueOf(end[2]) >= WeekLength) {

				startYear = year+"";
				startMonth = month+"";
				startDay = (day - WeekLength + 1) + "";
			} else {
               //day smaller than WeekLength
				if(month == Jan)
				{
					startYear = (year - 1) + "";
					startMonth = Dec+"";
					startDay = (monthDay.get(Dec) - (WeekLength - day) + 1)
							+ "";
				}else
				{
					startYear = year + "";
					startMonth = (month - 1) + "";
					startDay = (monthDay.get(Integer.valueOf(startMonth))
							- (WeekLength - day) + 1)
							+ "";
				}
			}
		}
		StringBuffer result = new StringBuffer();
		result.append(startYear);
		result.append("-");
		result.append(startMonth);
		result.append("-");
		result.append(startDay);

		return result.toString();
	}

	/**
	 * convert date to strArray
	 * 
	 * @param date
	 *            format"yyyy-mm-dd"
	 * 
	 * @return String[] convert result
	 */
	public static String[] parseDateToStrArray(String date)
			throws ParseException {
		SimpleDateFormat oldFormat = new SimpleDateFormat("yyyy-mm-dd",
				Locale.CHINA);
		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-m-d",
				Locale.CHINA);

		Date end = oldFormat.parse(date);
		String[] endArray = newFormat.format(end).split("-");
		return endArray;
	}

	public static void setFeb(int year) {
		if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
			monthDay.put(2, 29);
		} else {
			monthDay.put(2, 28);
		}
	}

}
