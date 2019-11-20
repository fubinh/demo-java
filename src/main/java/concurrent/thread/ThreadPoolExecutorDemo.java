package concurrent.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * 在使用有界队列时，若有新的任务需要执行，如果线程池实际线程数小于corePoolSize，则优先创建线程，
         * 若大于corePoolSize，则会将任务加入队列，
         * 若队列已满，则在总线程数不大于maximumPoolSize的前提下，创建新的线程，
         * 若线程数大于maximumPoolSize，则执行拒绝策略。或其他自定义方式。
         *
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 				//coreSize
                2, 				//MaxSize
                60, 			//60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)			//指定一种队列 （有界队列）
                //new LinkedBlockingQueue<Runnable>()
                , new ThreadPoolMyRejected()
                //, new DiscardOldestPolicy()
        );

        ThreadPoolMyTask mt1 = new ThreadPoolMyTask(1, "任务1");
        ThreadPoolMyTask mt2 = new ThreadPoolMyTask(2, "任务2");
        ThreadPoolMyTask mt3 = new ThreadPoolMyTask(3, "任务3");
        ThreadPoolMyTask mt4 = new ThreadPoolMyTask(4, "任务4");
        ThreadPoolMyTask mt5 = new ThreadPoolMyTask(5, "任务5");
        ThreadPoolMyTask mt6 = new ThreadPoolMyTask(6, "任务6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        pool.shutdown();
    }
}
