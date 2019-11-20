package concurrent.lock;

/**
 * Object的wait，notify机制模拟
 */
public class WaitNotifyDemo {

    // 被锁定的对象
    public static Object object = new Object();

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                try {
                    //停止当前线程，释放锁
                    object.wait();
                    System.out.println(Thread.currentThread().getName()+"获得锁执行业务逻辑");
                }catch (InterruptedException e){

                }
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                try {
                    //唤醒当前线程，获得锁
                    object.notify();
                    System.out.println("线程"+Thread.currentThread().getName()+"调用了object的notify方法");
                }finally {
                    System.out.println("线程" + Thread.currentThread().getName() + " 释放了锁");
                }
            }
        }
    }
}
