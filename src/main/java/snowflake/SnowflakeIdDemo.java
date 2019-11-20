package snowflake;

/**
 * Created by fubin on 2019-11-17.
 */
public class SnowflakeIdDemo {
    /** 测试 */

    public  static  void  main(String[]  args)  {

        long  begin  =  System.currentTimeMillis();

        SnowflakeIdWorker  idWorker  =  new  SnowflakeIdWorker(0,  0);

        for  (int  i  =  0; i <  4000000; i++)  {

            long  id  =  idWorker.nextId();

//          System.out.println(Long.toBinaryString(id));

//          System.out.println(id);

        }

        long  end  =  System.currentTimeMillis();



        System.out.println("用时 "+  (end- begin));



    }
}
