package concurrnet.thread;

import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("�������߳�1"+Thread.currentThread().getName() + "-->" + System.currentTimeMillis());
            }
        }, 2000, 1000);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("�������߳�2"+Thread.currentThread().getName() + "-->" + System.currentTimeMillis());
            }
        }, 2000, 1000);
    }
}
