package com.liangjun.servicehi.controller;

import com.liangjun.servicehi.ServiceHiApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @ClassName RpcController
 * @Description TODO
 * @Author junliang
 * @Date 2019/12/4 4:04 PM
 * @Version 1.0
 **/
@RestController
public class RpcController {

    private static final Logger LOG = Logger.getLogger(ServiceHiApplication.class.getName());

    @Value("${server.port}")
    private String port;
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/hi")
    public String home(@RequestParam(value = "name",defaultValue = "liangJun") String name){
        return "hi "+name+", i am from port:"+port;
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
}
