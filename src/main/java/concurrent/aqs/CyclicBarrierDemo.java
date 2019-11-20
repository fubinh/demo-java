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

        System.out.println("���߳̽���......");
    }

    //д���߳�
    static class Writer extends Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("�߳�->" + Thread.currentThread().getName()+ "����д������......");
            try {
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName()+"д�����.....");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println("�ػ���ɾ���쳣");
                e.printStackTrace();
            }
            System.out.println("�����߳�д����ϣ�����ִ����������......");
        }
    }
}