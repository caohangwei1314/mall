package cn.caohangwei.mall.ucenter.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:applicationContext-dubbo-customer.xml")
public class UcenterApplication {

    public static void main(String[] args){
        SpringApplication.run(UcenterApplication.class,args);
    }
}
