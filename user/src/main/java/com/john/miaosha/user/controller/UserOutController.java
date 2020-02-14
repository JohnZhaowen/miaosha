package com.john.miaosha.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.john.miaosha.entity.UserInfo;
import com.john.miaosha.form.UserRegisterForm;
import com.john.miaosha.user.facade.UserFacadeService;
import com.john.miaosha.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserOutController implements UserFacadeService {

    @Autowired
    private UserService userService;

    public Map<String, String> login(@RequestBody UserRegisterForm userRegisterForm){
        Map<String, String> result = new HashMap<>();
        result.put("result", "fail");
        result.put("message", "登录失败");

        if(StringUtils.isBlank(userRegisterForm.getAccount())){
            result.put("message", "用户名不能为空");
            return result;
        }

        if(StringUtils.isBlank(userRegisterForm.getPassword())){
            result.put("message", "密码不能为空");
            return result;
        }
        boolean verifyPassword = userService.verifyPassword(userRegisterForm.getAccount(), userRegisterForm.getPassword());
        if(!verifyPassword){
            result.put("message", "用户名或者密码错误，请重新输入");
            return result;
        } else {
            UserInfo userInfo = userService.findUserByAccount(userRegisterForm.getAccount());
            result.put("result", "success");
            result.put("message", "登录成功");
            result.put("userInfo", JSONObject.toJSONString(userInfo));

            return result;
        }
    }
}
