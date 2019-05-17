package com.liangjun.applicationmonitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApplicationMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMonitorApplication.class, args);
    }

    @RequestMapping("getPeople")
    public String getPeople(){
        return "people";
    }

}
