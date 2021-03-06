package com.hwyoung.concurrency.singleton;

import com.hwyoung.concurrency.annotation.UnRecommend;
import com.hwyoung.concurrency.annotation.UnThreadSafe;
import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式
 * 懒汉模式:单例实例在第一次使用时创建
 * 多线程环境下线程不安全：（线安需要syn同步）
 */
@UnThreadSafe
@UnRecommend
@Slf4j
public class SingletonExample1 {

	/**
	 * 私有构造方法
	 */
	private SingletonExample1(){

	}

	/**
	 * 单例对象
	 */
	private static SingletonExample1 instance = null;

	/**
	 * 静态工厂方法
	 * @return
	 */
	public static SingletonExample1 getInstance(){
		if(instance == null) {
			instance = new SingletonExample1();
		}
		return instance;
	}

	public static void main(String[] args) {
		SingletonExample1 instance1 = getInstance();
		SingletonExample1 instance2 = getInstance();

		log.info("instance1: {}",instance1.hashCode());
		log.info("instance1: {}",instance2.hashCode());
	}
}
