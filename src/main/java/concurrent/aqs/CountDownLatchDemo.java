package concurrent.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 * 同步器：CountDownLatch
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(new Runnable() {
            public void run() {
                System.out.println("子线程1"+Thread.currentThread().getName()+"正在执行");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程1"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            }
        },"子线程1").start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("子线程2"+Thread.currentThread().getName()+"正在执行");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("子线程2"+Thread.currentThread().getName()+"执行完毕");
                latch.countDown();
            }
        },"子线程2").start();


        System.out.println("等待两个线程执行完毕");
        latch.await();
        System.out.println("两个线程执行完毕");
        System.out.println("结束主线程");

    }
}
