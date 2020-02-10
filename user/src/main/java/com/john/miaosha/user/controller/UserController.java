package com.john.miaosha.user.controller;


import com.john.miaosha.form.UserRegisterForm;
import com.john.miaosha.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/findUserById")
    public String findUserById(int userId, Model model){
        model.addAttribute("userInfo", userService.findUserById(userId));
        return "viewUser";
    }

    @GetMapping(value = "/toRegisterUser")
    public String toRegisterUser(){
        return "toRegisterUser";
    }

    @PostMapping(value = "/registerUser")
    public String registerUser(UserRegisterForm userRegisterForm, Model model){

        if(StringUtils.isBlank(userRegisterForm.getAccount())){
            model.addAttribute("error", "用户名不能为空");
            return "toRegisterUser";
        }

        if(StringUtils.isBlank(userRegisterForm.getPassword())){
            model.addAttribute("error", "密码不能为空");
            return "toRegisterUser";
        }

        if(StringUtils.isBlank(userRegisterForm.getRepassword())){
            model.addAttribute("error", "重复密码不能为空");
            return "toRegisterUser";
        }

        if(!StringUtils.equals(userRegisterForm.getRepassword(), userRegisterForm.getRepassword())){
            model.addAttribute("error", "两次输入的密码不一致");
            return "toRegisterUser";
        }
        userService.saveUserInfo(userRegisterForm);

        return "toRegisterUser";
    }

    @GetMapping(value = "/toLogin")
    public String toLogin(){
        return "toLogin";
    }

    @PostMapping(value = "/login")
    public String login(UserRegisterForm userRegisterForm, Model model){

        if(StringUtils.isBlank(userRegisterForm.getAccount())){
            model.addAttribute("error", "用户名不能为空");
            return "toLogin";
        }

        if(StringUtils.isBlank(userRegisterForm.getPassword())){
            model.addAttribute("error", "密码不能为空");
            return "toLogin";
        }
        boolean verifyPassword = userService.verifyPassword(userRegisterForm.getAccount(), userRegisterForm.getPassword());
        if(!verifyPassword){
            model.addAttribute("error", "用户名或者密码错误，请重新输入");
            return "toLogin";
        }

        return "toLogin";
    }
}
