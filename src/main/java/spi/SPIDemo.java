package spi;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * Created by fubin on 2019-11-20.
 *
 * SPI:
 * 1.在 META-INF/services/ 目录中创建以接口全限定名命名的文件，该文件内容为API具体实现类的全限定名
 * 2.使用 ServiceLoader 类动态加载 META-INF 中的实现类
 * 3.如 SPI 的实现类为 Jar 则需要放在主程序 ClassPath 中
 * 4.API 具体实现类必须有一个不带参数的构造方法
 */
public class SPIDemo {
    public static void main(String[] args) {

        ServiceLoader<People> peoples = ServiceLoader.load(People.class);

        Iterator<People> iters = peoples.iterator();
        while (iters.hasNext()) {
            People people = iters.next();
            people.speak();
        }

    }
}
