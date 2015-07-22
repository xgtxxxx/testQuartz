package com.quartz.listeners;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class TestListener extends AbstractListener{
	private Logger log = Logger.getLogger(getClass());
	public TestListener(String name) {
		super(name);
	}

	@Override
	public void beforeJob(JobExecutionContext ct) {
		String name = ct.getTrigger().getJobKey().getName();
		log.info("开始任务："+name);
	}

	@Override
	public void afterJob(JobExecutionContext ct, JobExecutionException arg1) {
		String name = ct.getTrigger().getJobKey().getName();
		log.info("结束任务："+name);
	}

}
