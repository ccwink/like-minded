package com.wink.seek.service;

import com.wink.seek.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
public class InsertUserTest {

    @Resource
    private UserService userService;

    /**
     * 批量插入用户
     */
    @Test
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  // 开启记时
        final int INSERT_NUM = 10;
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < INSERT_NUM; i++){
            User user = new User();
            user.setUsername("假用户");
            user.setUserAccount("fakeuser");
            user.setAvatarUrl("https://img.zcool.cn/community/047e4558194755a84a148cd0d7924b.jpg");
            user.setGender(0);
            user.setProfile("美男子");
            user.setUserPassword("12345678");
            user.setPhone("10086");
            user.setEmail("123456@qq.com");
            user.setTags("[]");
            user.setUserStatus(0);
            user.setUserRole(0);
            userList.add(user);
        }
        userService.saveBatch(userList,2);
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    /**
     * 并发批量插入用户
     */
    @Test
    public void doConcurrencyInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  // 开启记时
        final int INSERT_NUM = 100000;
        // 分十组
        int j = 0;
        int bachSize = 5000;
        List<CompletableFuture<Void>> futrueList = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            List<User> userList = new ArrayList<>();
            while (true){
                j++;
                User user = new User();
                user.setUsername("假用户");
                user.setUserAccount("fakeuser");
                user.setAvatarUrl("https://img.zcool.cn/community/047e4558194755a84a148cd0d7924b.jpg");
                user.setGender(0);
                user.setProfile("美男子");
                user.setUserPassword("12345678");
                user.setPhone("10086");
                user.setEmail("123456@qq.com");
                user.setTags("[]");
                user.setUserStatus(0);
                user.setUserRole(0);
                userList.add(user);
                if (j % bachSize == 0){
                    break;
                }
            }
            // 异步执行
            CompletableFuture<Void> future = CompletableFuture.runAsync(() ->{
                System.out.println("threadName:" + Thread.currentThread().getName());
                userService.saveBatch(userList,bachSize);
            });
            futrueList.add(future);
        }
        CompletableFuture.allOf(futrueList.toArray(new CompletableFuture[]{})).join();

        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
