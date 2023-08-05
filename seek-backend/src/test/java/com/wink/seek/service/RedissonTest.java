package com.wink.seek.service;

import org.junit.jupiter.api.Test;
import org.redisson.api.RList;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class RedissonTest {

    @Resource
    private RedissonClient redissonClient;

    @Test
    void test() {
        // list
        // 数据存在本地JVM内存中
        List<String> list = new ArrayList<>();
        list.add("dogdog");
        System.out.println("list:" + list.get(0));
        //list.remove(0);

        // 数据存在 redis 的内存中
        RList<String> rList = redissonClient.getList("test-list");
        rList.add("dogdog");
        System.out.println("rList:" + rList.get(0));
        //rList.remove(0);

        // map
        Map<String, Integer> map = new HashMap<>();
        map.put("dogdog", 6666);
        System.out.println("map:" + map.get("dogdog"));

        RMap<Object, Integer> rMap = redissonClient.getMap("test-map");
        rMap.put("dogdog", 6666);
        System.out.println("rMap:" + map.get("dogdog"));

        // set

        // stack

    }
}
