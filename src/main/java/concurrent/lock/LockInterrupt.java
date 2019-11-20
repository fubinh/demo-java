package concurrent.lock;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockInterrupt {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //ע������ط�

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
        lock.lockInterruptibly();   //ע�⣬�����Ҫ��ȷ�жϵȴ������̣߳����뽫��ȡ���������棬Ȼ��InterruptedException�׳�
        try {
            System.out.println(thread.getName()+"�õ�����");
            TimeUnit.SECONDS.sleep(5);
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"ִ��finally");
            lock.unlock();
            System.out.println(thread.getName()+"�ͷ�����");
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
            System.out.println(Thread.currentThread().getName()+"���ж�");
        }
    }
}