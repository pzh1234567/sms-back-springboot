package com.example.smsbackspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.smsbackspringboot.demos.mapper")
public class SmsBackSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmsBackSpringbootApplication.class, args);
    }

}
