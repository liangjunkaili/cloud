package com.liangjun.serviceredis.util;

import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

public class RedissionUtil {
    public static void getRedission(){
        Config config = new Config();
        config.setThreads(8);
        RedissonClient client = Redisson.create(config);
        RLock lock = client.getLock("");
        lock.lock();
        client.getAtomicLong("");
        lock.unlock();
        client.shutdown();
    }
    public static void getRedissionSentinel(){
        Config config = new Config();
        config.useSentinelServers()
                .setMasterName("")
                .addSentinelAddress("");
        RedissonClient client = Redisson.create(config);
        client.getAtomicLong("");
        client.shutdown();
    }
    public static void getRedissionCluster(){
        Config config = new Config();
        config.useClusterServers()
                .addNodeAddress("")
                .setPassword("");
        RedissonClient client = Redisson.create(config);
        RAtomicLong atomicLong = client.getAtomicLong("");
        atomicLong.get();
        client.shutdown();
    }
}
