package concurrent.aqs;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo {

    private static final Integer sCount = 5;

    private static final Semaphore semaphore = new Semaphore(sCount);//�������5���߳�һ������

    public static void main(String[] args) {
        for(int i = 0 ; i < 8 ; i++){
            new Worker(i,semaphore).start();
        }

    }

    static class Worker extends Thread{
        private int num;
        private Semaphore semaphore;

        public Worker(int num, Semaphore semaphore) {
            this.num = num;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("ռ�õ�"+num + "̨����>>>>>>>>>>>>>>>>>>>>>>>" );
                TimeUnit.SECONDS.sleep(2);
                System.out.println("�ͷŵ�" + num +"̨����");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
