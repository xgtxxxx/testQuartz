package com.quartz.key;

import org.quartz.JobKey;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class Key {
	
	public Key(JobKey jobKey, TriggerKey triggerKey) {
		this.jobKey = jobKey;
		this.triggerKey = triggerKey;
	}
	private JobKey jobKey;
	private TriggerKey triggerKey;
	public JobKey getJobKey() {
		return jobKey;
	}
	public void setJobKey(JobKey jobKey) {
		this.jobKey = jobKey;
	}
	public TriggerKey getTriggerKey() {
		return triggerKey;
	}
	public void setTriggerKey(TriggerKey triggerKey) {
		this.triggerKey = triggerKey;
	}
}
