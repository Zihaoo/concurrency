package com.hwyoung.concurrency.atomic;

import com.hwyoung.concurrency.annotation.ThreadSafe;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子性：AtomicIntegerFieldUpdater并发测试类
 *
 */
@ThreadSafe
@Slf4j
public class AtomicIntegerFieldUpdaterConcurrency {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterConcurrency> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterConcurrency.class, "count");

    @Getter
    public volatile int count = 100;

    public static void main(String[] args){
        AtomicIntegerFieldUpdaterConcurrency atomicReferenceConcurrency = new AtomicIntegerFieldUpdaterConcurrency();
        if(updater.compareAndSet(atomicReferenceConcurrency, 100, 120)){
            log.info("count:{}", atomicReferenceConcurrency.getCount());
        }
    }
}