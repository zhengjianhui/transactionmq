package main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhengjianhui on 17/5/10.
 */
public class ConsumerTest {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath*:/spring/spring-dao.xml");

        applicationContext.start();
    }
}
