package com.john.miaosha.producttype.mapper;

import com.john.miaosha.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface ProductTypeMapper {

    UserInfo findUserById(@Param("userId") int userId);

    void saveUserInfo(@Param("userInfo") UserInfo userInfo);

    UserInfo findUserByAccount(@Param("account") String account);
}
