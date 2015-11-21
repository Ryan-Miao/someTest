
package com.test.java;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TestDate {
	

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
	
	public void dateFormat(){
		SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(sdf.format(System.currentTimeMillis()));
		
	}
	
	public static void main(String[] args) {
		TestDate td = new TestDate();
		td.dateFormat();
	}

}
