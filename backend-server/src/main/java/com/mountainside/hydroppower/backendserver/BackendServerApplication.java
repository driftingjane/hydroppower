package com.mountainside.hydroppower.backendserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@Configuration
@ImportResource({"classpath:application-context.xml"})
public class BackendServerApplication {


    public static void main(String[] args) {
        SpringApplication.run(BackendServerApplication.class, args);
    }

}
