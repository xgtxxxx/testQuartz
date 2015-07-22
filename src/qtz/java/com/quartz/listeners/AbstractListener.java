package com.quartz.listeners;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public abstract class AbstractListener implements JobListener{
	
	public AbstractListener(String name){
		this.name = name;
	}
	
	private String name;
	
	@Override
	public void jobToBeExecuted(JobExecutionContext arg0) {
		this.beforeJob(arg0);
	}
	/**
	 * 在job执行之前
	 * @param arg0
	 */
	public abstract void beforeJob(JobExecutionContext arg0);

	@Override
	public void jobWasExecuted(JobExecutionContext arg0,
			JobExecutionException arg1) {
		this.afterJob(arg0,arg1);
	}
	/**
	 * 在JOB执行之后
	 * @param arg0
	 * @param arg1
	 */
	public abstract void afterJob(JobExecutionContext arg0, JobExecutionException arg1);

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
		//do nothing
	}
	
	

}
