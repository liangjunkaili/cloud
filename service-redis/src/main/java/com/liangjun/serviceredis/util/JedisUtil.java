package com.liangjun.serviceredis.util;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

public class JedisUtil {

    public static void getJedis(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        jedis.auth("password");
        jedis.get("");
        jedis.close();
    }

    public static void getJedisPool(){
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        JedisPool jedisPool = new JedisPool(config,"",6379,2000,"password");
        Jedis jedis = jedisPool.getResource();
        jedis.get("");
        jedis.close();
        jedisPool.close();
    }

    public static void getJedisSentinelPool(){
        Set<String> sentinels = new HashSet<>();
        sentinels.add("127.0.0.1:6379");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("master",sentinels,config,"pwd");
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.get("");
        jedis.close();
        jedisSentinelPool.close();
    }

    public static void getCluster(){
        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        HostAndPort hp1 = new HostAndPort("172.19.59.51", 6379);
        jedisClusterNode.add(hp1);
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(8);
        JedisCluster cluster = new JedisCluster(jedisClusterNode,config);
        cluster.close();
    }
}
