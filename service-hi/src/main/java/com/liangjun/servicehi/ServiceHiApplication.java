package com.liangjun.servicehi;

import brave.sampler.Sampler;
import com.liangjun.servicehi.event.MyApplicationStartingEventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
@EnableEurekaClient
@RestController
@EnableAsync
public class ServiceHiApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServiceHiApplication.class);
        application.addListeners(new MyApplicationStartingEventListener());
        application.run(args);
        System.out.println("spring-boot-event-listener-chapter32启动!");
    }

    @Value("${server.port}")
    String port;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name",defaultValue = "liangJun") String name){
        return "hi "+name+", i am from port:"+port;
    }
    private static final Logger LOG = Logger.getLogger(ServiceHiApplication.class.getName());


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping("/hello")
    public String callHome(){
        LOG.log(Level.INFO, "calling trace service-hi  ");
        return restTemplate.getForObject("http://localhost:8769/miya", String.class);
    }
    @RequestMapping("/info")
    public String info(HttpServletRequest request){
        LOG.log(Level.INFO, "calling trace service-hi ");
        Enumeration<String> headers =  request.getHeaderNames();
        while (headers.hasMoreElements()){
            LOG.log(Level.INFO, headers.nextElement());
        }
        LOG.log(Level.INFO, request.getRemoteAddr());
        return "i'm service-hi";

    }

    @Bean
    public Sampler defaultSampler() {
        return Sampler.ALWAYS_SAMPLE;
    }

}
