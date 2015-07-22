package com.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob1 extends CronJob{

	@Override
	public void execute(JobExecutionContext context)
			throws JobExecutionException {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		log.info("==>正在执行任务："+this.getName());
	}

}
