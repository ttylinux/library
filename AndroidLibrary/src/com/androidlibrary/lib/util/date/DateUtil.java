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
	public static final int PartCount = 3;
	public static HashMap<Integer, String> _seasonQ = new HashMap<Integer, String>();
	public static HashMap<Integer, Integer> _monthDay = new HashMap<Integer, Integer>();

	static {

		_monthDay.put(1, 31);
		_monthDay.put(2, 28);
		_monthDay.put(3, 31);
		_monthDay.put(4, 30);
		_monthDay.put(5, 31);
		_monthDay.put(6, 30);
		_monthDay.put(7, 31);
		_monthDay.put(8, 31);
		_monthDay.put(9, 30);
		_monthDay.put(10, 31);
		_monthDay.put(11, 30);
		_monthDay.put(12, 31);

		_seasonQ.put(1, "1");
		_seasonQ.put(2, "1");
		_seasonQ.put(3, "1");

		_seasonQ.put(4, "2");
		_seasonQ.put(5, "2");
		_seasonQ.put(6, "2");

		_seasonQ.put(7, "3");
		_seasonQ.put(8, "3");
		_seasonQ.put(9, "3");

		_seasonQ.put(10, "4");
		_seasonQ.put(11, "4");
		_seasonQ.put(12, "4");
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

		if (end.length < PartCount) {
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
					startDay = (_monthDay.get(Dec) - (WeekLength - day) + 1)
							+ "";
				}else
				{
					startYear = year + "";
					startMonth = (month - 1) + "";
					startDay = (_monthDay.get(Integer.valueOf(startMonth))
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
	 * convert date to strArray,use a new format "yyyy-m-d"
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
			_monthDay.put(2, 29);
		} else {
			_monthDay.put(2, 28);
		}
	}
	/**
	 *determine the month belongs to which quarter.
	 *@param  month 
	 * 
	 * @return  quarter 
	 */
	public static String determineWhichQuarter(int month)
	{
		return _seasonQ.get(month);
	}
	

}
