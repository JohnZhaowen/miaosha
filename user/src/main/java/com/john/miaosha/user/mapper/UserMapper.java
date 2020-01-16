package com.john.miaosha.user.mapper;

import com.john.miaosha.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    UserInfo findUserById(@Param("userId") int userId);

    void saveUserInfo(@Param("userInfo")UserInfo userInfo);

    UserInfo findUserByAccount(@Param("account") String account);
}
