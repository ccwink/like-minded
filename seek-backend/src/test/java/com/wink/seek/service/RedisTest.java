package com.wink.seek.service;

import com.wink.seek.model.domain.User;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import javax.annotation.Resource;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate redisTemplate;

    @Test
    void test() {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        // 增
        valueOperations.set("string", "dog");
        valueOperations.set("int", 1);
        valueOperations.set("double", 2.8);
        User user = new User();
        user.setId(1L);
        user.setUsername("Dog");
        valueOperations.set("user", user);
        // 查
        Object aString = valueOperations.get("string");
        Assertions.assertTrue("dog".equals((String) aString));
        Object anInt = valueOperations.get("int");
        Assertions.assertTrue(1 == ((Integer) anInt));
        Object aDouble = valueOperations.get("double");
        Assertions.assertTrue(2.8 == ((Double) aDouble));
        Object aUser = valueOperations.get("user");
        System.out.println(aUser);
        // 改
        valueOperations.set("string", "dogdogdog");
        // 删
        redisTemplate.delete("string");

    }
}
