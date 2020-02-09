package com.john.miaosha.user.controller;


import com.alibaba.fastjson.JSONObject;
import com.john.miaosha.entity.UserInfo;
import com.john.miaosha.form.UserRegisterForm;
import com.john.miaosha.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user/out")
public class UserOutController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestBody UserRegisterForm userRegisterForm){
        Map<String, String> reuslt = new HashMap<>();
        reuslt.put("result", "fail");
        reuslt.put("message", "登录失败");

        if(StringUtils.isBlank(userRegisterForm.getAccount())){
            reuslt.put("message", "用户名不能为空");
            return reuslt;
        }

        if(StringUtils.isBlank(userRegisterForm.getPassword())){
            reuslt.put("message", "密码不能为空");
            return reuslt;
        }
        boolean verifyPassword = userService.verifyPassword(userRegisterForm.getAccount(), userRegisterForm.getPassword());
        if(!verifyPassword){
            reuslt.put("message", "用户名或者密码错误，请重新输入");
            return reuslt;
        } else {
            UserInfo userInfo = userService.findUserByAccount(userRegisterForm.getAccount());
            reuslt.put("result", "success");
            reuslt.put("message", "登录成功");
            reuslt.put("userInfo", JSONObject.toJSONString(userInfo));

            return reuslt;
        }


    }
}
