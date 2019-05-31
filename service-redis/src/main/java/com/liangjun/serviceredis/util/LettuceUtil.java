package com.liangjun.serviceredis.util;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;

import java.net.URI;
import java.util.concurrent.ExecutionException;

public class LettuceUtil {
    public static void getLettuce() throws ExecutionException, InterruptedException {
        RedisClient client = RedisClient.create(RedisURI.create("",6379));
        StatefulRedisConnection<String,String> connection = client.connect();
        RedisAsyncCommands asyncCommands = connection.async();
        connection.sync();
        connection.reactive();
        asyncCommands.auth("");
        RedisFuture future = asyncCommands.get("");
        future.get();
        connection.closeAsync();
        asyncCommands.shutdown(true);
    }
}
