package com.wink.seek;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // 默认扫描当前包及其子包
@MapperScan("com.wink.seek.mapper")
@EnableScheduling  // 开启定时任务
@Slf4j
public class SeekApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeekApplication.class, args);
        log.info("启动成功···");
    }

}
