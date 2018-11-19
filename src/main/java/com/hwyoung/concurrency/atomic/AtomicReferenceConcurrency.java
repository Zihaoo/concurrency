package com.hwyoung.concurrency.atomic;

import com.hwyoung.concurrency.annotation.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 原子性：AtomicReference并发测试类
 *
 */
@ThreadSafe
@Slf4j
public class AtomicReferenceConcurrency {

	private static AtomicReference<Integer> count = new AtomicReference<Integer>(0);

	public static void main(String[] args){
		count.compareAndSet(0, 2);	// 执行得2
		count.compareAndSet(2, 4); // 执行得4
		count.compareAndSet(3, 5); //  不执行
		log.info("count:{}",count.get());
	}
}
