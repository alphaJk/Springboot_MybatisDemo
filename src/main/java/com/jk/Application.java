package com.jk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.annotation.PreDestroy;


/**
 * Created with IntelliJ IDEA.
 * User: jk
 * Date: 2020-03-24
 * Time: 22:40
 * To change this template use File | Settings | File Templates.
 * Description:启动文件
 */
@EnableScheduling
@SpringBootApplication
@Configuration
public class Application {

    private  static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        Application.context = SpringApplication.run(Application.class,args);
    }

    @PreDestroy
    public void close(){
        Application.context.close();
    }
}
