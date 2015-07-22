package com.quartz.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;

import com.quartz.listeners.AbstractListener;
/**
 * 简单时间驱动任务
 * @author xgt
 *
 */
public abstract class SimpleJob implements Job{
	protected Logger log = Logger.getLogger(getClass());
	/**
	 * 永久循环
	 */
	public static final int FOREVER_LOOP = -1;
	
	private String runtime;
	
	private int loopCount = 1;
	
	private int interval = 0;
	
	private String name;
	
	private AbstractListener listener;
	
	public String getRuntime() {
		return runtime;
	}
	/**
	 * 设置执行时间yyyy-MM-dd HH:mm:ss
	 * @param runtime
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public int getLoopCount() {
		return loopCount;
	}
	/**
	 * 设置循环次数
	 * @param loopCount
	 */
	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}
	public int getInterval() {
		return interval;
	}
	/**
	 * 设置循环间隔时间：秒
	 * @param interval
	 */
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public AbstractListener getListener() {
		return listener;
	}
	/**
	 * 添加监听时间
	 * @param listener
	 */
	public void setListener(AbstractListener listener) {
		this.listener = listener;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
