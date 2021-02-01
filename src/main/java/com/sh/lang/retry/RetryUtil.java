package com.sh.lang.retry;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;


/**
 * 重试计时器 
 * @param <E>
 */
public class RetryUtil implements Runnable{
	private DelayQueue<Student> queue = new DelayQueue<Student>();
	private CountDownLatch countDownLatch;
	
	public RetryUtil(CountDownLatch countDownLatch){
		this.countDownLatch = countDownLatch;
	}
	
	public void add(Student student){
		queue.add(student);
	}
	

	public static void main(String[] args) {
		
		try {
			CountDownLatch countDownLatch = new CountDownLatch(50);
			RetryUtil retryUtil = new RetryUtil(countDownLatch);
			for(int i=0; i<50; i++){
				Student student = new Student("st"+i, (1+(int)Math.random()*10)*60*1000, countDownLatch);
				retryUtil.add(student);
			}
			retryUtil.run();
			countDownLatch.await();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void run() {
		System.out.print("test start");
		try {
			while(!Thread.interrupted()){
				queue.take().run();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.print("test end");
		
	}

}
