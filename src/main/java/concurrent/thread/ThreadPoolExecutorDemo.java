package concurrent.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {
    public static void main(String[] args) {
        /**
         * ��ʹ���н����ʱ�������µ�������Ҫִ�У�����̳߳�ʵ���߳���С��corePoolSize�������ȴ����̣߳�
         * ������corePoolSize����Ὣ���������У�
         * �������������������߳���������maximumPoolSize��ǰ���£������µ��̣߳�
         * ���߳�������maximumPoolSize����ִ�оܾ����ԡ��������Զ��巽ʽ��
         *
         */
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                1, 				//coreSize
                2, 				//MaxSize
                60, 			//60
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(3)			//ָ��һ�ֶ��� ���н���У�
                //new LinkedBlockingQueue<Runnable>()
                , new ThreadPoolMyRejected()
                //, new DiscardOldestPolicy()
        );

        ThreadPoolMyTask mt1 = new ThreadPoolMyTask(1, "����1");
        ThreadPoolMyTask mt2 = new ThreadPoolMyTask(2, "����2");
        ThreadPoolMyTask mt3 = new ThreadPoolMyTask(3, "����3");
        ThreadPoolMyTask mt4 = new ThreadPoolMyTask(4, "����4");
        ThreadPoolMyTask mt5 = new ThreadPoolMyTask(5, "����5");
        ThreadPoolMyTask mt6 = new ThreadPoolMyTask(6, "����6");

        pool.execute(mt1);
        pool.execute(mt2);
        pool.execute(mt3);
        pool.execute(mt4);
        pool.execute(mt5);
        pool.execute(mt6);

        pool.shutdown();
    }
}
