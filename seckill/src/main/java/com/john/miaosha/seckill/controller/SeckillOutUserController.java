package com.john.miaosha.seckill.controller;


import com.alibaba.fastjson.JSONObject;
import com.john.miaosha.entity.UserInfo;
import com.john.miaosha.form.UserRegisterForm;
import com.john.miaosha.seckill.service.UserFacadeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillOutUserController {

    @Autowired
    private UserFacadeService userFacadeService;

    @GetMapping(value = "/toLogin")
    public String toLogin(){
       return "toLogin";
    }

    @PostMapping(value = "/login")
    public String login(UserRegisterForm userRegisterForm, Model model, HttpServletRequest req){

        Map<String, String> loginResult = userFacadeService.login(userRegisterForm);
        if("fail".equals(loginResult.get("reuslt"))){
            model.addAttribute("error", loginResult.get("message"));
            return "toLogin";
        } else {
            UserInfo userInfo = JSONObject.parseObject(loginResult.get("userInfo"), UserInfo.class);
            model.addAttribute("userInfo", userInfo);
            req.getSession().setAttribute("userInfo", userInfo);
            log.info("登录成功，登录用户信息[{}]", userInfo);
        }
        return "redirect:/seckill/listSeckillProductByState";
    }
}
