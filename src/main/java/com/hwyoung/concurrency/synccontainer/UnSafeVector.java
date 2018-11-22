package com.hwyoung.concurrency.synccontainer;


import com.hwyoung.concurrency.annotation.UnThreadSafe;

import java.util.Vector;

/**
 * 同步容器类（线程安全）：Vector类
 * 并发安全问题,多线程操作
 *
 */
@UnThreadSafe
public class UnSafeVector {

	private static Vector<Integer> vector = new Vector<>();

	public static void main(String[] args) {
		while (true){
			for (int i = 0; i < 10; i++) {
				vector.add(i);
			}

			Thread thread1 = new Thread(){
				public void run(){
					for (int i = 0; i < vector.size(); i++) {
						vector.remove(i);
					}
				}
			};

			Thread thread2 = new Thread(){
				public void run(){
					for (int i = 0; i < vector.size(); i++) {
						vector.get(i);
					}
				}
			};

			thread1.start();
			thread2.start();
		}
	}
}
