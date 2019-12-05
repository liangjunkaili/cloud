package com.liangjun.gatewayfirstsight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayFirstSightApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayFirstSightApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder){
        String httpUri = "http://localhost:8763";
        return builder.routes()
                .route(p -> p
                    .path("/info")
                    .filters(f -> f.addRequestHeader("hello","world"))
                    .uri(httpUri))
                .route(p -> p
                    .host("*.hystrix.com")
                    .filters(f -> f
                        .hystrix(config -> config
                            .setName("myCmd")
                        .setFallbackUri("forward:/fallback")))
                    .uri(httpUri))
                .build();
    }
}
