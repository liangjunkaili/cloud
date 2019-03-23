package com.liangjun.servicemongo.dao;

import com.liangjun.servicemongo.DTO.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName UserDao
 * @Description TODO
 * @Author junliang
 * @Date 2019/3/23 3:41 PM
 * @Version 1.0
 **/
@Component
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;
    public User save(User user){
        return mongoTemplate.save(user);
    }
}
