package com.sh.lang.retry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable, Delayed {
	
	private String name;
	private long waitTime;
	private long submitTime;
	private CountDownLatch countDownLatch;
	
	public Student(String name, long waitTime, CountDownLatch countDownLatch) {
		this.name = name;
		this.waitTime = waitTime;
		this.countDownLatch = countDownLatch;
		System.out.println(name+" join test");
				
	}

	@Override
	public int compareTo(Delayed o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getDelay(TimeUnit unit) {
		
		return unit.convert(this.submitTime-System.nanoTime(), TimeUnit.NANOSECONDS);
	}

	@Override
	public void run() {
		try {
			Thread.sleep(this.getWaitTime());
			this.setSubmitTime(System.currentTimeMillis());
			countDownLatch.countDown();
			System.out.println(name+" submit test");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(long waitTime) {
		this.waitTime = waitTime;
	}

	public long getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(long submitTime) {
		this.submitTime = submitTime;
	}

	
}
