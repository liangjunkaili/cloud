package com.liangjun.servicelogin.dao;

import com.github.pagehelper.Page;
import com.liangjun.servicelogin.dto.User;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.Map;

//@Mapper
public interface UserMapper {

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "qqreader_name"),
            @Result(property = "password",column = "pwd"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "deleted",column = "is_deleted"),
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "score",column = "score")
    })
    @Select("SELECT * FROM qqreader_user WHERE phone = #{phone}")
    User findUserByPhone(@Param("phone") String phone);

    @Insert("INSERT INTO qqreader_user(qqreader_name,pwd,phone,is_deleted,gmt_modified,score) " +
            "VALUES(#{name},#{password},#{phone},#{deleted},#{gmtModified},#{score})")
    int insert(@Param("name") String name, @Param("password") String password, @Param("phone") String phone,
               @Param("deleted") Boolean deleted, @Param("gmtModified") String gmtModified, @Param("score") BigDecimal score);

    @Insert("INSERT INTO qqreader_user(qqreader_name,pwd,phone) VALUES(#{name},#{password},#{phone})")
    int insertByMap(Map<String, Object> map);

    @Insert("INSERT INTO qqreader_user(qqreader_name,pwd,phone,is_deleted,gmt_modified,score)" +
            " VALUES(#{name},#{password},#{phone},#{deleted},#{gmtModified},#{score})")
    int insertByUser(User user);

    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "qqreader_name"),
            @Result(property = "password",column = "pwd"),
            @Result(property = "phone",column = "phone"),
            @Result(property = "deleted",column = "is_deleted"),
            @Result(property = "gmtCreate",column = "gmt_create"),
            @Result(property = "gmtModified",column = "gmt_modified"),
            @Result(property = "score",column = "score")
    })
    @Select("select qqreader_name,pwd,phone,is_deleted,gmt_create,gmt_modified,score from qqreader_user")
    Page<User> findAll();

    @Delete("DELETE FROM qqreader_user where phone = #{phone}")
    int deleteUserByPhone(@Param("phone") String phone);
}
