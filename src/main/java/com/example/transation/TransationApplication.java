package com.example.transation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.example.transation.mapper")
@SpringBootApplication
@EnableScheduling
public class TransationApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransationApplication.class, args);
    }

}
