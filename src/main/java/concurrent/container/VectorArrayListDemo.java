package concurrent.container;

import java.util.ArrayList;
import java.util.Vector;

public class VectorArrayListDemo {

    private static ArrayList<Integer> list = new ArrayList<Integer>();
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread1 = new Thread(){
                public void run(){
                    long start = System.currentTimeMillis();
                    for(int i=0;i<100000;i++)
                        list.add(i);
                    long end = System.currentTimeMillis();
                    System.out.println("ArrayList进行100000次插入操作耗时："+(end-start)+"ms");
                };
            };
            thread1.start();
        }

        for(int i=0;i<10;i++){
            Thread thread2 = new Thread(){
                public void run(){
                    long start = System.currentTimeMillis();
                    for(int i=0;i<100000;i++)
                        vector.add(i);
                    long end = System.currentTimeMillis();
                    System.out.println("Vector进行100000次插入操作耗时："+(end-start)+"ms");
                };
            };
            thread2.start();
        }
    }
}
