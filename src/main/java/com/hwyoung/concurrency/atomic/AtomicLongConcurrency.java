package com.hwyoung.concurrency.atomic;

import com.hwyoung.concurrency.annotation.ThreadSafe;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 原子性：AtomicLong并发测试类
 *
 */
@ThreadSafe
@Slf4j
public class AtomicLongConcurrency {

	/**
	 * 请求总数
	 */
	private static int clientTotal = 5000;

	/**
	 * 线程并发数
	 */
	private static int threadTotal = 200;

	/**
	 * 计数
	 */
	private static AtomicLong count = new AtomicLong(0);

	public static void main(String[] args) throws Exception{

		ExecutorService executorService = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(threadTotal);
		final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
		for (int i = 0; i < clientTotal; i++){
			executorService.execute(() -> {
				try {
					semaphore.acquire();	//信号量确定是否add可以执行，或是阻塞
					add();
					semaphore.release();
				} catch (InterruptedException e) {
					log.error("exception:",e);
				}
				countDownLatch.countDown();
			});
		}
		countDownLatch.await();
		executorService.shutdown();
		log.info("count:{}",count);
	}

	private static void add(){
		count.incrementAndGet();
//		count.getAndIncrement();
	}
}
