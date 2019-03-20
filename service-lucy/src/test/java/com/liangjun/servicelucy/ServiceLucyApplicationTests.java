package com.liangjun.servicelucy;

import com.liangjun.servicelucy.DO.User;
import com.liangjun.servicelucy.dao.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceLucyApplicationTests {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void contextLoads() {
//        userMapper.insert("liangjun","123456","15600806167");
        User u = userMapper.findUserByPhone("15600806167");
        Assert.assertEquals("liangjun",u.getName());
    }

}
