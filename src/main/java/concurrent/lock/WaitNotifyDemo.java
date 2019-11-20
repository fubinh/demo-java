package concurrent.lock;

/**
 * Object��wait��notify����ģ��
 */
public class WaitNotifyDemo {

    // �������Ķ���
    public static Object object = new Object();

    public static void main(String[] args) {
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        t1.start();
        t2.start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                try {
                    //ֹͣ��ǰ�̣߳��ͷ���
                    object.wait();
                    System.out.println(Thread.currentThread().getName()+"�����ִ��ҵ���߼�");
                }catch (InterruptedException e){

                }
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                try {
                    //���ѵ�ǰ�̣߳������
                    object.notify();
                    System.out.println("�߳�"+Thread.currentThread().getName()+"������object��notify����");
                }finally {
                    System.out.println("�߳�" + Thread.currentThread().getName() + " �ͷ�����");
                }
            }
        }
    }
}
