package concurrent.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierRepeatDemo {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(10, () ->
                System.out.println("���е��̶߳��Ѿ�ִ�����......����ִ��������������"));

        for(int i=0;i< 10;i++){
            if(i<9){
                new Writer(barrier).start();
            }else{
                TimeUnit.SECONDS.sleep(5);
                new Writer(barrier).start();
            }
        }

        System.out.println("--------------------------------------");

        TimeUnit.SECONDS.sleep(5);

        System.out.println("CyclicBarrier����");

        for(int i=0;i< 10;i++){
            new Writer(barrier).start();
        }

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
                TimeUnit.SECONDS.sleep(2);
                System.out.println(Thread.currentThread().getName()+"д�����.....");
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                System.out.println("�ػ���ɾ���쳣");
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "����ִ����������......");
        }
    }
}
