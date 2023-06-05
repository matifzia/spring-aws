package com.spring.aws.spring_aws;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAWSApplication {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    public static void main(String[] args) {
        SpringApplication.run(SpringAWSApplication.class, args);
    }
}