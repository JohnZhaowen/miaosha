package com.john.miaosha.user.service;

import com.john.miaosha.entity.UserInfo;
import com.john.miaosha.form.UserRegisterForm;
import com.john.miaosha.user.mapper.UserMapper;
import com.john.miaosha.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo findUserById(int userId) {
        return userMapper.findUserById(userId);
    }

    @Override
    public void saveUserInfo(UserRegisterForm userRegisterForm) {

        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(userRegisterForm.getAccount());
        userInfo.setEncryptionPassword(Md5Util.md5(userRegisterForm.getPassword(), Md5Util.MD5_KEY));
        userInfo.setOriginalPassword(userRegisterForm.getPassword());
        userInfo.setTelphone(userRegisterForm.getTelphone());
        userInfo.setBirthday(userRegisterForm.getBirthday());
        userInfo.setQq(userRegisterForm.getQq());
        userInfo.setWechat(userRegisterForm.getWechat());

        userMapper.saveUserInfo(userInfo);
    }

    @Override
    public UserInfo findUserByAccount(String account) {
        return userMapper.findUserByAccount(account);
    }

    @Override
    public boolean verifyPassword(String account, String password) {
        UserInfo userInfo = findUserByAccount(account);
        String encryptionPassword = userInfo == null ? null : userInfo.getEncryptionPassword();
        return Md5Util.verify(password, Md5Util.MD5_KEY, encryptionPassword);
    }
}
