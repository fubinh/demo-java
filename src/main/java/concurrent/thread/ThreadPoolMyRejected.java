package concurrent.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMyRejected implements RejectedExecutionHandler {

    public ThreadPoolMyRejected(){
    }


    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("�Զ��崦��..��ǰ���ܾ�����Ϊ��" + r.toString());
    }

}

