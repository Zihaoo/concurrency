package com.hwyoung.concurrency.threadLocal;

import lombok.extern.slf4j.Slf4j;

/**
 * threadLocal 的实际应用
 */
@Slf4j
public class RequestHolder {
    //线程id存入threadLocal
    private final static ThreadLocal<Long> requestHolder = new ThreadLocal<>();

    //（给fitler用）是map实现 key 为线程名，value 为维护的对象
    public static void add(Long id){
        requestHolder.set(id);
    }

    public static Long getId(){
        return requestHolder.get();
    }

    //移除操作（给interceptor用），不做这个操作，会造成线程泄漏
    public static void remove(){
        requestHolder.remove();
    }
}
