package com.member.common.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * 时间类操作，一律使用此文件
 * @author Administrator
 * 
 */
public class DateUtil {

	/**yyyy-MM-dd HH:mm:ss */
	public static String defaultDateTimeF = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd */
	public static String defaultDateTimeD = "yyyy-MM-dd";
	/** yyyy/MM/dd */
	public static String dateTimeD = "yyyy/MM/dd";
	/**yyyy-MM  */
	public static String defaultDateTimeM = "yyyy-MM";
	/** yyyy */
	public static String defaultDateTimeY = "yyyy";
	/** yyyyMMdd */
	public static String dateStringD = "yyyyMMdd";
	/** yyyyMMddHHmmss */
	public static String dateTimeStringF = "yyyyMMddHHmmss";
	/** yyyyMMddHHmmsss */
	public static String dateTimeStringF2 = "yyyyMMddHHmmsss";
	/** yyyyMMddHH */
	public static String dateTimeString = "yyyyMMddHH";
	/** HHmmss*/
	public static String timeString = "HHmmss";

	public static String getString(String source, String patternBy, String patternTo) {
		try {
			String format = new SimpleDateFormat(patternTo).format(new SimpleDateFormat(patternBy).parse(source));
			return format;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 将yyyy-MM-dd HH:MM:SS类型的字符串转换为Date类型 如果字符串日期不合法
	 * @param str 需要转换的日期字符串
	 * @return 转换后的日期类型
	 */
	public static Date parseStringToDateTime(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			return dateFormat.parse(str);
		} catch (Exception ex) {
			//log.debug("DateUtils.java : " + ex.getMessage());
			return null;
		}
	}

	public static String getNowDateTime() {
		GregorianCalendar gcNow = new GregorianCalendar();
		Date dNow = gcNow.getTime();
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, Locale.SIMPLIFIED_CHINESE);
		return df.format(dNow);
	}

	/**
	 * 功能：将Timestamp型转换为String型
	 * @param date  需要转换的日期
	 * @return 返回DateTimeStame类型的当前时间
	 * @throws ParseException
	 * 
	 */
	public static String getString(Timestamp ts) {
		String str = "";
		DateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		try {
			//方法一   
			str = sdf.format(ts);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static Date getDateToTimestamp(Timestamp ts) {
		try {
			DateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
			return sdf.parse(sdf.format(ts));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getStringToPattern(Timestamp ts, String pattern) {
		String str = null;
		DateFormat sdf = new SimpleDateFormat(pattern);
		try {
			str = sdf.format(ts);
		} catch (Exception e) {
			/* e.printStackTrace(); */
		}
		return str;
	}

	/**
	 * 格式化日期
	 * @param strDate	日期字符串
	 * @param pattern	日期格式
	 * @return
	 */
	public static Date parseDataToString(String strDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 检验字符串是否是符合格式的日期
	 * @param strDate	字符串格式的日期
	 * @param pattern	日期格式
	 * @return
	 */
	public static boolean isValidDate(String strDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			sdf.parse(strDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static Timestamp parseStringToTimestamp2(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeF);
		Timestamp currentTime = null;
		try {
			Date d = dateFormat.parse(str);
			currentTime = new Timestamp((new SimpleDateFormat(defaultDateTimeF)).parse(dateFormat.format(d)).getTime());
		} catch (Exception ex) {
			// System.out.println("DateUtils.java : " + ex.getMessage());
			return null;
		}
		return currentTime;
	}

	public static Timestamp parseStringToTimestamp(String str) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeF);
		Timestamp currentTime = null;
		try {
			Date d = dateFormat.parse(str);
			currentTime = new Timestamp((new SimpleDateFormat(defaultDateTimeF)).parse(dateFormat.format(d)).getTime());
		} catch (Exception ex) {
			// System.out.println("DateUtils.java : " + ex.getMessage());
			return null;
		}
		return currentTime;
	}

	public static String getCurrentMin5(Timestamp date1) {
		String str = "";
		DateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		long msec = date1.getTime() + 5000 * 60;
		str = sdf.format(msec);
		return str;
	}

	/**
	 * 加分钟
	 * @param date
	 * @param min
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentMinAdd(Date date, int min) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimeF);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, min);
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 加月
	 * @param date
	 * @param month
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentMonthAdd(Date date, int month) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimeF);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 加周
	 * @param date
	 * @param week
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentWeekAdd(Date date, int week) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimeF);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.WEEK_OF_YEAR, week);
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 加年
	 * @param date
	 * @param year
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String getCurrentYearAdd(Date date, int year) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(defaultDateTimeF);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, year);
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 针对有间隔符的日期/时间进行比较，看dateTime是否处于startTime和endTime之间
	 * 如果startTime为null则表示对起始时间无限制；如果endTime为Null则表示对结束时间无限制
	 * @return -1表示dateTime在时间域之前，1表示在时间域之后,-2表示出现异常
	 */
	public static int checkBetween(String dateTime, String startTime, String endTime) {
		String divit = "[^0-9]+";
		String[] date = dateTime.split(divit);
		String[] start = null;
		String[] end = null;
		boolean beforeStart = startTime != null, afterEnd = endTime != null;
		if (beforeStart) {
			start = startTime.split(divit);
		}
		if (afterEnd) {
			end = endTime.split(divit);
		}
		for (int i = 0; i < date.length; i++) {
			if (beforeStart && (i > start.length || "".equals(start[i]))) {
				beforeStart = false;
			}
			if (beforeStart) {
				if (Integer.parseInt(start[i]) < Integer.parseInt(date[i])) {
					beforeStart = false;
				} else if (Integer.parseInt(start[i]) > Integer.parseInt(date[i])) {
					return -1;
				}
			}
			if (afterEnd && (i > end.length || "".equals(end[i]))) {
				afterEnd = false;
			}
			if (afterEnd) {
				if (Integer.parseInt(end[i]) > Integer.parseInt(date[i])) {
					afterEnd = false;
				} else if (Integer.parseInt(end[i]) < Integer.parseInt(date[i])) {
					return 1;
				}
			}
			if ((!beforeStart && !afterEnd) || i == date.length - 1) {
				return 0;
			}
		}
		return -2;
	}

	/**
	 * 返回一个当前的完整时间，并按格式转换为字符串
	 * 例：2018-01-01
	 */
	public static String getCurrentDateString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(new Date());
	}

	/**
	 * 返回一个当前的完整时间，并按格式转换为字符串
	 * 例：yyyyMMddHHmmsss
	 */
	public static String getCurrentFullDateTimeString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeStringF2);
		return dateFormat.format(new Date());
	}

	/**
	 * 获取当前日期
	 * @param pattern
	 * @return
	 */
	public static String getCurrentDateTime(String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}

	public static String getCurrenYearString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		return dateFormat.format(new Date());
	}

	public static String getCurrenMonthString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return dateFormat.format(new Date());
	}

	public static String getCurrenDayString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
		return dateFormat.format(new Date());
	}
	public static String getCurrenDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeD);
		return dateFormat.format(new Date());
	}

	public static String getMonthString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM");
		return dateFormat.format(date);
	}

	public static Date parseDataToString(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateStringD);
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Date();
		}
	}
	public static String getYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(defaultDateTimeD).format(cal.getTime());
		return yesterday;
	}
	public static String getYesterdayF() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat(defaultDateTimeF).format(cal.getTime());
		return yesterday;

	}
	public static String formatDateTime(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	/**
	 * 返回日期和时间，并按格式转换为字符串
	 * 例：2004-4-30 17:27:03
	 */
	public static String formatDateTime(Date date) {
		if (date == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		return sdf.format(date);
	}
	public static String formatDateTime(Timestamp timestamp) {
		if (timestamp == null)
			return "";
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		return sdf.format(timestamp);
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Date getNowDate() {
		return new Date();
	}
	public static String addMon(int mon) {
		String str = "";
		Format f = new SimpleDateFormat(defaultDateTimeF);
		Calendar thisDay = Calendar.getInstance();
		thisDay.add(Calendar.DAY_OF_MONTH, mon * 30);
		str = f.format(thisDay.getTime());
		return str;
	}
	/**
	 * 返回当前时间yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDateTimeF() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeF);
		return dateFormat.format(new Date());
	}
	/**
	 * 返回当前时间14位yyyyMMddHHmmss
	 * @return
	 */
	public static String getNowDateTimeS() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeStringF);
		return dateFormat.format(new Date());
	}
	/**
	 * @return
	 */
	public static Date parseDateTimeS(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeStringF);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
	}

	public static Date parseStringToDate(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(defaultDateTimeD);
		try {
			return dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Date parseDateTime() {
		SimpleDateFormat format = new SimpleDateFormat(defaultDateTimeF);
		String str = format.format(new Date());
		try {
			return format.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	* 功能：将Date型转换为DateTimeStame型
	* @param date  需要转换的日期
	* @return 返回DateTimeStame类型的当前时间
	* @throws ParseException
	*/
	public static Timestamp getCurrentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		Date date = new Date();
		Timestamp currentTime = null;
		try {
			currentTime = new Timestamp((new SimpleDateFormat(defaultDateTimeF)).parse(sdf.format(date)).getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return currentTime;
	}
	public static Timestamp getLateMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Timestamp currentTime = null;
		try {
			currentTime = new Timestamp((new SimpleDateFormat(defaultDateTimeF)).parse(sdf.format(cal.getTime())).getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return currentTime;
	}

	public static Timestamp getTimestampByDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeF);
		Timestamp currentTime = null;
		try {
			currentTime = new Timestamp((new SimpleDateFormat(defaultDateTimeF)).parse(sdf.format(date)).getTime());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return currentTime;
	}

	public static Timestamp parseStringToTimestamp(String str, String dateFormat) {
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);
		try {
			Date date = format.parse(str);
			Timestamp timestamp = new Timestamp(date.getTime());
			return timestamp;
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 返回当前时间10位yyyyMMddHH
	 * @return
	 */
	public static String getNowDateDay() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateTimeString);
		return dateFormat.format(new Date());
	}
	
	

	public static Date addDays(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}
	
	public static Date addMonths(Date date, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, day);
		return calendar.getTime();
	}
	
}
