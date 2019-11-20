package concurrent.aqs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/***
 * ͬ������CountDownLatch
 */
public class CountDownLatchDemo {

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(new Runnable() {
            public void run() {
                System.out.println("���߳�1"+Thread.currentThread().getName()+"����ִ��");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("���߳�1"+Thread.currentThread().getName()+"ִ�����");
                latch.countDown();
            }
        },"���߳�1").start();

        new Thread(new Runnable() {
            public void run() {
                System.out.println("���߳�2"+Thread.currentThread().getName()+"����ִ��");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("���߳�2"+Thread.currentThread().getName()+"ִ�����");
                latch.countDown();
            }
        },"���߳�2").start();


        System.out.println("�ȴ������߳�ִ�����");
        latch.await();
        System.out.println("�����߳�ִ�����");
        System.out.println("�������߳�");

    }
}
