package queue;

import java.util.concurrent.ArrayBlockingQueue;


/**
 * 阻塞队列实现生产者消费者
 *
 * 1. 生产者线程，消费者线程
 * 2. 线程里创建死循环，获取不到元素时线程阻塞
 */
public class BlockingQueueProducerConsumer {

    private int queueSize = 10;
    private ArrayBlockingQueue queue = new ArrayBlockingQueue(queueSize);

    public static void main(String[] args) {
        BlockingQueueProducerConsumer test = new BlockingQueueProducerConsumer();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();

    }

    class Consumer extends Thread{
        @Override
        public void run() {
            consume();
        }

        private void consume(){
            while(true){
                try {
                    queue.take();
                    System.out.println("从队列中取出一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer extends Thread{
        @Override
        public void run() {
            producer();
        }

        private void producer(){
            while(true){
                try {
                    queue.put(1);
                    System.out.println("向队列插入一个元素:"+ queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}


