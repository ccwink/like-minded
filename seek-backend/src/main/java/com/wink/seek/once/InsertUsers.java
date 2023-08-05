package com.wink.seek.once;

import com.wink.seek.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;

@Component
public class InsertUsers {

    @Resource
    private UserMapper userMapper;

    /**
     * 批量插入用户
     */
    //@Scheduled(initialDelay = 5000, fixedRate = Long.MAX_VALUE)
    public void doInsertUsers(){
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();  // 开启记时
/*        final int INSERT_NUM = 100;
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
            userMapper.insert(user);
        }*/
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

}
