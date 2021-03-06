package com.liangjun.servicemiya.dao;

import com.liangjun.servicemiya.DO.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Select("SELECT * FROM t_user WHERE phone = #{phone}")
    User findUserByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO t_user(NAME,PASSWORD,phone) VALUES(#{name},#{password},#{phone})")
    int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone);

    @Insert("INSERT INTO t_user(NAME,PASSWORD,phone) VALUES(#{name},#{password},#{phone})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO t_user(NAME,PASSWORD,phone) VALUES(#{name},#{password},#{phone})")
    int insertByUser(User user);

    /*@Results(id = "1",value = {
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "password",column = "password"),
            @Result(property = "phone",column = "phone")
    })*/
    @Select("select name,password,phone from t_user")
    List<User> findAll();
}
