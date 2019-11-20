package concurrent.thread;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class ThreadPoolMyRejected implements RejectedExecutionHandler {

    public ThreadPoolMyRejected(){
    }


    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("自定义处理..当前被拒绝任务为：" + r.toString());
    }

}

