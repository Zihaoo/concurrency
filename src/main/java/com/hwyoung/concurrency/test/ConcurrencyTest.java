package com.hwyoung.concurrency.test;

import com.hwyoung.concurrency.annotation.UnTreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

/**
 *  并发测试类
 *  5000个线程给count加1
 *  结论：不做任何线程安全操作，计数远小于5000
 */
@UnTreadSafe
@Slf4j
public class ConcurrencyTest {

    /**
     * 请求总数
     */
    private static int clientTotal = 5000;

    /**
     * 线程并发数
     */
    private static int threadToal = 20;

    //计数
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadToal);
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();

            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}",count);
    }

    private static void add() {
        count++;
    }
}
