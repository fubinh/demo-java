package concurrent.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        final ReadWriteLockDemo test = new ReadWriteLockDemo();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }

            ;
        }.start();

        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }

            ;
        }.start();

    }

    public  void get(Thread thread) {
        rwl.readLock().lock();
        try{
            for(int i =0 ; i < 100 ; i++){
                System.out.println("i:" + i + "-->" +thread.getName() + "¶Á²Ù×÷Íê±Ï");
            }
        }finally {
            rwl.readLock().unlock();
        }

    }
}
