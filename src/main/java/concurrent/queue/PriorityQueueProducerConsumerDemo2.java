package concurrent.queue;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PriorityQueueProducerConsumerDemo2 {
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<Integer>(queueSize);
    private static Lock lock = new ReentrantLock();
    private static Condition queueFull = lock.newCondition();
    private static Condition queueEmpty = lock.newCondition();

    public static void main(String[] args) {
        PriorityQueueProducerConsumerDemo2 test = new PriorityQueueProducerConsumerDemo2();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("���пգ��ȴ�����");
                            queueEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queueFull.signal();
                        }
                    }
                    queue.poll();          //ÿ�����߶���Ԫ��
                    queueFull.signal();
                    System.out.println("�Ӷ���ȡ��һ��Ԫ�أ�����ʣ��" + queue.size() + "��Ԫ��");
                } finally {
                    lock.unlock();
                }

            }

        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("���������ȴ��п���ռ�");
                            queueFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queueEmpty.signal();
                        }
                    }
                    queue.offer(1);        //ÿ�β���һ��Ԫ��
                    queueEmpty.signal();
                    System.out.println("�����ȡ�в���һ��Ԫ�أ�����ʣ��ռ䣺" + (queueSize - queue.size()));
                } finally {
                    lock.unlock();
                }

            }
        }
    }
}
