package com.liangjun.servicehi;

import brave.sampler.Sampler;
import com.liangjun.servicehi.event.MyApplicationStartingEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceHiApplication.class);
        application.addListeners(new MyApplicationStartingEventListener());
        application.run(args);
        System.out.println("spring-boot-event-listener-chapter32启动!");
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
