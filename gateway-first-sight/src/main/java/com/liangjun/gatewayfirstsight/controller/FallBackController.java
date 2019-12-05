package com.liangjun.gatewayfirstsight.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @ClassName FallBackController
 * @Description TODO
 * @Author junliang
 * @Date 2019/12/5 3:53 PM
 * @Version 1.0
 **/
@RestController
public class FallBackController {
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
