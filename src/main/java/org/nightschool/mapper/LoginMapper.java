package org.nightschool.mapper;

import org.apache.ibatis.annotations.*;
import org.nightschool.model.User;

/**
 * Created by Administrator on 15-2-5.
 */
public interface LoginMapper {
    @Insert("insert into users values(#{username}, #{password}, #{type}, #{email})")
    public int register(User user);

    @Select("select * from users where LOWER(username)=LOWER(#{username})")
    public User checkUserNameExisted(@Param("username") String username);

    @Delete("delete from users where LOWER(username)=LOWER(#{username}) and password=#{password}")
    public int deregister(User user);

    @Select("select * from users where LOWER(username)=LOWER(#{username}) and password=#{password}")
    public User login(User user);
}
