package com.exercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author jianglong.li@hand-china.com
 * @date 2021-01-11 10:49
 **/
@EnableDiscoveryClient
@SpringBootApplication
public class ExerciseApplication {

    public static void main(String[] args) {
        try {
            SpringApplication.run(ExerciseApplication.class, args);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
