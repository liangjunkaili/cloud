package com.liangjun.serviceredis.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class LettuceUtil {

    /**
     * redis://password@host:port/1
     * rediss://password@host:port/1
     * redis-sentinel://password@host:port,host2:port2/databaseNumber#sentinelMasterId
     * redis-socket:///path/to/socket
     * @return
     */
    public static RedisURI getRedisURI(){
//        return RedisURI.create("redis://localhost:6379/");
//        return new RedisURI("localhost", 6379, 60, TimeUnit.SECONDS);
        return RedisURI.builder()
                .withClientName("lettuceClient")
                .withHost("localhost")
                .withPort(6379)
                .withSsl(true)
                .withPassword("123456")
                .withDatabase(0)
                .build();
    }
    public static StatefulRedisConnection<String, String> getStatefulRedisConnection(){
        RedisClient client = RedisClient.create(getRedisURI());
        StatefulRedisConnection<String, String> connection = client.connect();
        return connection;
    }
    public static String sync(){
        RedisCommands commands = getStatefulRedisConnection().sync();
        String res = commands.set("hello","world");
        return res;
    }
    public static String async(){
        RedisAsyncCommands asyncCommands = getStatefulRedisConnection().async();
        RedisFuture<String> future = asyncCommands.set("hello","world");
        try {
            future.thenAccept(new Consumer<String>() {
                @Override
                public void accept(String s) {
                    System.out.println(s);
                }
            });
            CompletionStage<Void> stage = future.thenAccept(System.out::println);
            future.thenAcceptAsync(System.out::println);
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "";
    }
    public static void reactive(){
        RedisReactiveCommands commands = getStatefulRedisConnection().reactive();
        Mono<String> mono = commands.set("hello","world");
        mono.subscribe(v -> System.out.println(v));
    }
    public void close(){

    }
    public static void getLettuce() throws ExecutionException, InterruptedException {
        RedisClient client = RedisClient.create(RedisURI.create("localhost",6379));
        StatefulRedisConnection<String,String> connection = client.connect();
        RedisAsyncCommands asyncCommands = connection.async();
        connection.sync();
        connection.reactive();
        asyncCommands.auth("");
        RedisFuture future = asyncCommands.get("");
        future.get();
        connection.closeAsync();
        client.shutdown();
    }

    public static void testFuture(){
        CompletableFuture<String> future = new CompletableFuture();
        future.complete("future");
        CompletableFuture future1 = future.thenApply(s -> {
            return s+"0000";
        }).thenAccept(System.out::println);
        future1.thenAccept(System.out::println)
                .thenRun(() ->{
            System.out.println("thenRun complete");
        });
    }

    public static void main(String[] args) {
//        testFuture();
        Flux.just("111","222","333")
                .doOnNext(s -> System.out.println(s))
                .doOnComplete(() -> System.out.println("complete"))
                .subscribe();

    }
}
