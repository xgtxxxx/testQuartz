package com.quartz;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import com.quartz.jobs.CronJob;
import com.quartz.jobs.SimpleJob;
import com.quartz.key.Key;
import com.quartz.utils.DateUtil;

public class QuartzManager {
	private static String JOB_GROUP_NAME = "JOBGROUP_NAME";
	private static String TRIGGER_GROUP_NAME = "TRIGGERGROUP_NAME";
	private static String JOB_TITLE = "job";
	private static String TRIGGER_TITLE = "trigger";
	
	private static SchedulerFactory sf = new StdSchedulerFactory();
	
	private static QuartzManager manager = null;
	
	/**
	 * 单例，在生成对象的同时启动一个定时线程
	 */
	private QuartzManager(){}
	
	public static QuartzManager getInstance(){
		if(manager==null){
			manager = new QuartzManager();
		}
		return manager;
	}
	
	public Key addJob(SimpleJob job) throws Exception{
		Scheduler sched = sf.getScheduler();
		
		String name = job.getName()==null?JOB_TITLE+job.hashCode():job.getName()+"@"+job.hashCode();
		
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(name, JOB_GROUP_NAME).build();
		// 触发器
		TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger().withIdentity(TRIGGER_TITLE+job.hashCode(), TRIGGER_GROUP_NAME);
		builder.startAt(DateUtil.parseDateTime(job.getRuntime()));
		if(job.getLoopCount()==SimpleJob.FOREVER_LOOP){
			builder.withSchedule(simpleSchedule()
					.withIntervalInSeconds(job.getInterval())
					.repeatForever());
		}else{
			builder.withSchedule(simpleSchedule()
					.withIntervalInSeconds(job.getInterval())
					.withRepeatCount(job.getLoopCount()-1));
		}
		Trigger trigger = builder.build();
		JobListener listener = job.getListener();
		if(listener!=null){
			Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			sched.getListenerManager().addJobListener(listener, matcher); 
		}
		sched.scheduleJob(jobDetail, trigger);
		// 启动
		if (!sched.isShutdown()) {
			sched.start();
		}
		return new Key(jobDetail.getKey(),trigger.getKey());
	}
	
	public Key addJob(CronJob job) throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		String name = job.getName()==null?JOB_TITLE+job.hashCode():job.getName()+"@"+job.hashCode();
		JobDetail jobDetail = JobBuilder.newJob(job.getClass()).withIdentity(name, JOB_GROUP_NAME).build();
		// 触发器
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(TRIGGER_TITLE+job.hashCode(), TRIGGER_GROUP_NAME).withSchedule(CronScheduleBuilder.cronSchedule(job.getRuntime())).build();
		JobListener listener = job.getListener();
		if(listener!=null){
			Matcher<JobKey> matcher = KeyMatcher.keyEquals(jobDetail.getKey());
			sched.getListenerManager().addJobListener(listener, matcher); 
		}
		sched.scheduleJob(jobDetail, trigger);
		// 启动
		if (!sched.isShutdown()) {
			sched.start();
		}
		return new Key(jobDetail.getKey(),trigger.getKey());
	}
	

	public void removeJob(Key key) throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(key.getTriggerKey());
		sched.unscheduleJob(key.getTriggerKey());
		sched.deleteJob(key.getJobKey());
	}

	public void shutdown() throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		sched.shutdown();
	}

	public void start() throws SchedulerException {
		Scheduler sched = sf.getScheduler();
		sched.start();
	}
}
