package com.john.miaosha.user.service;

import com.john.miaosha.entity.UserInfo;
import com.john.miaosha.form.UserRegisterForm;

public interface UserService {

    UserInfo findUserById(int userId);

    void saveUserInfo(UserRegisterForm userRegisterForm);

    UserInfo findUserByAccount(String account);

    boolean verifyPassword(String account, String password);
}
