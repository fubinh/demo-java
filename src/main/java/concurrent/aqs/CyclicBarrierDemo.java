package concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4);

        for(int i=0;i< 10;i++){
            new Writer(barrier).start();
        }

        System.out.println("主线程结束......");
    }

    //写入线程
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程->" + Thread.currentThread().getName()+ "正在写入数据......");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"写入完毕.....");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println("回环栏删抛异常");
                e.printStackTrace();
            }
            System.out.println("所有线程写入完毕，继续执行其他任务......");
        }
    }
}
