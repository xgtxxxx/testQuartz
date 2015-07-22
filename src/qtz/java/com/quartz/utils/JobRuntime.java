package com.quartz.utils;

import java.util.Date;

/**
 * 任务执行时间
 * @author xgt
 *  秒 是 0-59 , - * /
 *	分 是 0-59 , - * /
 *	时 是 0-23 , - * / 
 *	日 是 1-31 , - * ? / L W C 
 *	月 是 1-12 或 JAN-DEC , - * / 
 *	周 是 1-7 或 SUN-SAT , - * ? / L C # 
 *	年 否 空 或 1970-2099 , - * / 
 *  Cron表达式的时间字段除允许设置数值外，还可使用一些特殊的字符，提供列表、范围、通配符等功能，细说如下：
 * ●星号(*)：可用在所有字段中，表示对应时间域的每一个时刻，例如，*在分钟字段时，表示“每分钟”；
 * ●问号（?）：该字符只在日期和星期字段中使用，它通常指定为“无意义的值”，相当于点位符；
 * ●减号(-)：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12；
 * ●逗号(,)：表达一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期五；
 * ●斜杠(/)：x/y表达一个等步长序列，x为起始值，y为增量步长值。如在分钟字段中使用0/15，则表示为0,15,30和45秒，而5/15在分钟字段中表示5,20,35,50，你也可以使用* /y，它等同于0/y
 * 用法 表达式 
 * 在每个周一,二, 三和周四的 10:15 AM 0 15 10 ? * MON-FRI 
 * 每月15号的 10:15 AM 0 15 10 15 * ? 
 * 每月最后一天的 10:15 AM 0 15 10 L * ? 
 * 每月最后一个周五的 10:15 AM 0 15 10 ? * 6L 
 * 在 2002, 2003, 2004, 和 2005 年中的每月最后一个周五的 10:15 AM 0 15 10 ? * 6L 2002-2005 
 * 每月第三个周五的 10:15 AM 0 15 10 ? * 6#3 
 * 每月从第一天算起每五天的 12:00 PM (中午) 0 0 12 1/5 * ? 
 * 每一个 11 月 11 号的 11:11 AM 0 11 11 11 11 ? 
 * 三月份每个周三的 2:10 PM 和 2:44 PM 0 10,44 14 ? 3 WED 
 */
public class JobRuntime {
	
	/**
	 * 每天0时
	 */
	public static final String DEFAULT_RUNTIME = "0 0 0 * * ?";
	
	/**
	 * 将字符串日期转换层cron字符
	 * @param date yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String orderRuntime(String runTime){
		Date date = DateUtil.parseDateTime(runTime);
		int s = DateUtil.getSecond(date);
		int m = DateUtil.getMinute(date);
		int h = DateUtil.getHour(date);
		int d = DateUtil.getDay(date);
		int mm = DateUtil.getMonth(date);
		int y = DateUtil.getYear(date);
		
		StringBuffer sb = new StringBuffer();
		sb.append(s).append(" ");
		sb.append(m).append(" ");
		sb.append(h).append(" ");
		sb.append(d).append(" ");
		sb.append(mm).append(" ");
		sb.append(y);
		return sb.toString();
	}
}
