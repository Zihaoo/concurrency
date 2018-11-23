package com.hwyoung.concurrency.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 * J.U.C之AQS-CyclicBarrier
 * 循环栅栏
 *
 */
@Slf4j
public class AqsCyclicBarrier {

	//当线程到达屏障的时候，优先执行屏障里的方法。
	private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5,() -> {
		log.info("callback is running");
	});

	public static void main(String[] args) throws Exception{

		//创建线程池
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 20; i++) {
			final int threadNum = i;
			Thread.sleep(1000);
			executorService.execute(() -> {
				try {
					race(threadNum);
				} catch (Exception e) {
					log.error("exception: {}",e);
				}
			});
		}

		executorService.shutdown();
	}

	private static void race(int threadNum) throws Exception {
		Thread.sleep(1000);
		log.info("{} is ready",threadNum);
		try {
			//该线程准备好了，但如果等待超过2000ms，
			cyclicBarrier.await(2000, TimeUnit.MICROSECONDS);
		} catch (InterruptedException | TimeoutException | BrokenBarrierException e) {
			log.error("exception: {}",e);
		}
		log.info("{} is continue",threadNum);
	}
}
