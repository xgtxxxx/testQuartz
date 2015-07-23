package com.quartz;

import com.quartz.jobs.TestJob1;
import com.quartz.key.Key;
import com.quartz.listeners.AbstractListener;
import com.quartz.listeners.TestListener;

public class TestQtz {
	public static void main(String[] args) {
		try {
			QuartzManager qm = QuartzManager.getInstance();
			qm.start();
			
//			TestJob job1 = new TestJob();
//			job1.setName("oncejob");
//			AbstractListener listerer = new TestListener(job1.getName());
//			job1.setListener(listerer);
//			job1.setRuntime("2014-09-05 05:00:00");
//			
//			qm.addJob(job1);
//			
//			TestJob job2 = new TestJob();
//			job2.setName("everyday5");
//			AbstractListener listerer2 = new TestListener(job2.getName());
//			job2.setListener(listerer2);
//			job2.setInterval(60);
//			job2.setLoopCount(5);
//			job2.setRuntime("2014-09-04 20:00:00");
//			qm.addJob(job2);
//			
//			TestJob job3 = new TestJob();
//			job3.setName("everyhour");
//			AbstractListener listerer3 = new TestListener(job3.getName());
//			job3.setListener(listerer3);
//			job3.setRuntime("2014-09-04 18:10:00");
//			qm.addJob(job3);
			
			
			
			TestJob1 job22 = new TestJob1();
			job22.setName("_everyday5");
			AbstractListener listerer22 = new TestListener(job22.getName());
			job22.setListener(listerer22);
			job22.setRuntime("*/5 * * * * ?");
			Key key = qm.addJob(job22);
			
			Thread.sleep(60000);
			
			System.out.println("===Remove job===");
			qm.removeJob(key);;
//			
//			TestJob1 job33 = new TestJob1();
//			job33.setName("_everyhour");
//			AbstractListener listerer33 = new TestListener(job33.getName());
//			job33.setListener(listerer33);
//			job33.setRuntime("0 10 18 * * ?");
//			qm.addJob(job33);
			
//			for(int i=0; i<100; i++){
//				SimpleJob job = new TestJob();
//				job.setRuntime("2014-09-03 18:06:30");
//				qm.addJob(job);
//			}
			
//			SimpleJob job = new TestJob();
//			job.setRuntime("2014-09-03 16:20:00");
//			job.setInterval(1);
//			job.setLoopCount(1);
//			
//			AbstractListener listerer = new TestListener("mylistener");
//			job.setListener(listerer);
//			Key key = qm.addJob(job);
//			Thread.sleep(1000);
////			qm.removeJob(key);
//			qm.addJob(job);
//			
////			CronJob cj = new TestJob1();
////			cj.setRuntime("* * * * * ?");
////			qm.addJob(cj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
