package concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition的await和signal方法
 */
public class ConditionDemo {
    public static Object object = new Object();
    private static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }

    static class Thread1 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.await();
                System.out.println(Thread.currentThread().getName() + "获得锁执行业务逻辑");
            } catch (InterruptedException e) {

            }
            lock.unlock();
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                condition.signal();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了condition的signal方法");
            } finally {
                System.out.println("线程" + Thread.currentThread().getName() + " 释放了锁");
            }
            lock.unlock();
        }
    }
}


