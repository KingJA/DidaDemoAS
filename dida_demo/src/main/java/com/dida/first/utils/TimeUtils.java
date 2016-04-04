/**
 * 
 */
package com.dida.first.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author KingJA
 * @data 2015-8-14 上午11:02:35
 * @use
 * 
 */
public class TimeUtils {

	private static int day;
	private static int hour;
	private static int min;
	private static int sec;

	/**
	 *
	 * @param timeString
	 * @return
	 * @throws ParseException
     */
	public static int[] getDeadTime(String timeString)  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = sdf.parse(timeString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long deadTime = date.getTime();
		long nowTime = System.currentTimeMillis();
		long distanceTime = deadTime - nowTime;
		if (distanceTime < 0) {
			int[] timeArr = { 0, 0, 0 };
			return timeArr;
		}
		day = (int) (distanceTime / (1000 * 60 * 60 * 24));
		hour = (int) (distanceTime % (1000 * 60 * 60 * 24) / (1000 * 60 * 60));
		min = (int) (distanceTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60) / (1000 * 60));
		sec = (int) (distanceTime % (1000 * 60 * 60 * 24) % (1000 * 60 * 60)% (1000 * 60)/1000);
		
		int[] timeArr = { day, hour, min ,sec};
		return timeArr;
	}

	public static String getDayTime(String date){
		return date.substring(date.indexOf("-")+1,date.lastIndexOf(":"));
	}


}
