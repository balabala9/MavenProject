package org.redis.redis_pub_sub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Publisher {
    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public static void main(String[] args) {
        String redisIp = "127.0.0.1";
        int reidsPort = 6379;
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), redisIp, reidsPort);
        Jedis jedis = jedisPool.getResource();
        String key="#hello#";

        jedis.set(key,"");
        jedis.expire(key,8);


    }



}
