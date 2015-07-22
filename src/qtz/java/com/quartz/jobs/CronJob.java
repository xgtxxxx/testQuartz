package com.quartz.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;

import com.quartz.listeners.AbstractListener;
import com.quartz.utils.JobRuntime;

public abstract class CronJob implements Job{
	protected Logger log = Logger.getLogger(getClass());
	
	private String runtime = JobRuntime.DEFAULT_RUNTIME;
	
	private AbstractListener listener;
	
	private String name;

	public String getRuntime() {
		return runtime;
	}
	/**
	 * cron类型time
	 * @param runtime
	 */
	public void setRuntime(String runtime) {
		this.runtime = runtime;
	}
	public AbstractListener getListener() {
		return listener;
	}
	/**
	 * 添加监听事件
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
