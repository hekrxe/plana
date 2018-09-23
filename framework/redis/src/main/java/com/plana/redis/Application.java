package com.plana.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * User: tanhuayou
 * Date: 2018/9/5
 */

@RestController
@SpringBootApplication
public class Application {

    @Resource(name = "numberRedisTemplate")
    private RedisTemplate<String, Long> redisTemplate;

    @RequestMapping("/")
    Object home(HttpServletRequest request) {
        redisTemplate.opsForValue().set("111", 1212L);
        return redisTemplate.opsForValue().get("111");
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
