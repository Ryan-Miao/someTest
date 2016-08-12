
package com.test.java;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Stream;

public class TestDate {

	Logger logger = LoggerFactory.getLogger(TestDate.class);

	public void year_mon_day(){
		Calendar now = Calendar.getInstance();
		System.out.println(now.get(Calendar.YEAR)+"年");
		System.out.println(now.get(Calendar.MONTH)+1+"月");
		System.out.println(now.get(Calendar.DATE)+"日");
		System.out.println(now.get(Calendar.DAY_OF_MONTH));
		System.out.println(now.get(Calendar.DAY_OF_WEEK));
		System.out.println(now.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println(now.get(Calendar.HOUR)+"/12小时");
		System.out.println(now.get(Calendar.HOUR_OF_DAY)+"/24小时");
		System.out.println(now.get(Calendar.MINUTE)+"分");
		System.out.println(now.get(Calendar.SECOND)+"秒");
		System.out.println(now.get(Calendar.MILLISECOND));
		System.out.println(now.get(Calendar.MONDAY));
	}
	
	public void dateFormat(String pattern){
		SimpleDateFormat sdf= new SimpleDateFormat(pattern);
		System.out.println(sdf.format(System.currentTimeMillis()));
		
	}

	@Test
	public void testCalend() throws ParseException {
		Calendar calendar = Calendar.getInstance();

		String date = "2016-06-13";
		calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		calendar.add(Calendar.DAY_OF_MONTH,7);
		System.out.println(calendar.before(Calendar.getInstance()));
	}

	/*********************************************************************************
	 *	test DateFormat
	 *********************************************************************************/

	@Test
	public void testSimpleDateFormat() throws  Exception{
		dateFormat("yyy-MM-dd hh:mm:ss");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
		Date parse = simpleDateFormat.parse("2016-06-21");
		System.out.println(parse);
	}

	@Test
	public void testDateFormat() throws Exception{
		Date date = new Date();
		DateFormat format = DateFormat.getDateInstance();
		String result = format.format(date);
		logger.debug("dateFormat default: {} ",result);//2016-06-11

		dateFormat(date,0);//2016年6月11日 星期六
		dateFormat(date,1);//2016年6月11日
		dateFormat(date,2);//2016-06-11
		dateFormat(date,3);//16-6-11
//		dateFormat(date,4);//wrong
//		dateFormat(date,-1);

	}

	/**
	 * test DateFormat.getDateTimeInstance();
     */
	@Test
	public  void testTimeFormat() {
		Date date = new Date();
		DateFormat timeFormat = DateFormat.getDateTimeInstance();
		String timeResult = timeFormat.format(date);//2016-6-11 22:23:04
		logger.debug("time format default: {}", timeResult);

		timeFormat(date,2,0);//2016-6-11 下午10时23分04秒 CST
		timeFormat(date,2,1);//2016-6-11 下午10时23分04秒
		timeFormat(date,2,2);//2016-6-11 22:23:04
		timeFormat(date,2,3);//2016-6-11 下午10:23
	}

	@Test
	public void testTimeZone(){
		Date date = new Date();
		System.out.println(date);

	}

	private String timeFormat(Date date, int dateStyle,
							  int timeStyle) {
		DateFormat timeFormat;
		timeFormat = DateFormat.getDateTimeInstance(dateStyle,timeStyle);
		String timeResult = timeFormat.format(date);
		logger.debug("time format {}: {}",timeFormat, timeResult);

		return timeResult;
	}

	private void dateFormat(Date date, int type) {
		DateFormat format = DateFormat.getDateInstance(type);
		String result = format.format(date);
		logger.debug("format  {}:{}",type,result);
	}

	public static void main(String[] args) {
		TestDate td = new TestDate();
		td.dateFormat("yyyy-MM-dd");
	}

}
