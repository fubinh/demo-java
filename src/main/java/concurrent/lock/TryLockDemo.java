package concurrent.lock;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TryLockDemo {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    private Lock lock = new ReentrantLock();    //ע������ط�
    public static void main(String[] args)  {
        final TryLockDemo test = new TryLockDemo();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();

        new Thread(){
            public void run() {
                test.insert(Thread.currentThread());
            };
        }.start();
    }

    public void insert(Thread thread) {
        if(lock.tryLock()) {
            try {
                System.out.println(thread.getName()+"�õ�����");
                for(int i=0;i<5;i++) {
                    arrayList.add(i);
                }
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                // TODO: handle exception
            }finally {
                System.out.println(thread.getName()+"�ͷ�����" + "->" + arrayList);
                lock.unlock();
            }
        } else {
            System.out.println(thread.getName()+"��ȡ��ʧ��");
        }
    }
}