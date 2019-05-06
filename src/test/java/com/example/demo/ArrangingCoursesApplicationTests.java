package com.example.demo;

import com.hut.kwk.ArrangingCoursesApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ArrangingCoursesApplication.class)
public class ArrangingCoursesApplicationTests {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test(){
        stringRedisTemplate.opsForValue().set("111","222");
        System.out.println(stringRedisTemplate.opsForValue().get("111"));
    }


    public void getRedis(String key){

        //return stringRedisTemplate.opsForValue().get(key);
    }


    public void setRedis(String key,String value){
        stringRedisTemplate.opsForValue().set(key,value);
       // return "SUCCESS";
    }

}
