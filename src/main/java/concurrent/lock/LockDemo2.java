package concurrent.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockDemo2 {
    private ArrayList<Integer> arrayList = new ArrayList<Integer>();
    Lock lock = new ReentrantLock();    //ע������ط�
    public static void main(String[] args)  {
        final LockDemo2 test = new LockDemo2();

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

        lock.lock();
        try {
            System.out.println(thread.getName()+"�õ�����");
            for(int i=0;i<5;i++) {
                arrayList.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"�ͷ�����" + "--->" + arrayList);
            lock.unlock();
        }
    }
}
