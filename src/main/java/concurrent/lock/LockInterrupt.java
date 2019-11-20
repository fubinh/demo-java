package concurrent.lock;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterrupt {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //注意这个地方

    public static void main(String[] args) throws InterruptedException {
        final LockInterrupt test = new LockInterrupt();

        MyThread t1 = new MyThread(test);
        MyThread t2 = new MyThread(test);
        t1.start();
        t2.start();

        TimeUnit.SECONDS.sleep(2);

        t2.interrupt();
    }

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName()+"得到了锁");
            TimeUnit.SECONDS.sleep(5);
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }
}

class MyThread extends  Thread{

    private LockInterrupt lockInterrupt = null;

    public MyThread(LockInterrupt lockInterrupt){
        this.lockInterrupt = lockInterrupt;
    }

    @Override
    public void run() {
        try {
            lockInterrupt.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }
    }
}