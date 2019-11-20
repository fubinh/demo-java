package concurrnet.thread;

import java.util.concurrent.*;

public class FutureTaskDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Task task = new Task();
        Task2 task2 = new Task2();

        //Callable + Future
//        Future<Integer> future = executorService.submit(task);

        //Callable + FutureTask
        FutureTask<Integer> future = new FutureTask<>(task);
        executorService.submit(future);

        FutureTask<?> future2 = new FutureTask<Void>(task2,null);
        executorService.submit(future2);
        executorService.shutdown();

        TimeUnit.SECONDS.sleep(2);

        System.out.println("���߳�"+Thread.currentThread().getName()+"ִ������");
        System.out.println("task���н����"+future.get());

        System.out.println("����ִ�����......");
    }

    static class Task implements Callable<Integer>{
        @Override
        public Integer call() throws InterruptedException {
            System.out.println("���߳�"+Thread.currentThread().getName()+ "���ڼ���");
            TimeUnit.SECONDS.sleep(2);
            return 100;
        }
    }

    static class Task2 implements Runnable{
        @Override
        public void run() {
            System.out.println("runnable ���߳�"+Thread.currentThread().getName()+"ִ�С���������");
        }
    }
}
