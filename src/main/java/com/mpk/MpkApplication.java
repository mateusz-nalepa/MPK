package com.mpk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableAutoConfiguration
public class MpkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpkApplication.class, args);
    }

}
