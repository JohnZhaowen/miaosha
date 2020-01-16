package com.john.miaosha.merchant.controller;


import com.john.miaosha.form.MerchantCheckInForm;
import com.john.miaosha.form.UserRegisterForm;
import com.john.miaosha.merchant.service.MerchantService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/merchant")
public class MerchantController {

    @Autowired
    private MerchantService merchantService;

    @GetMapping(value = "/findMerchantById")
    public String findMerchantById(int id, Model model){
        model.addAttribute("merchantInfo", merchantService.findUserById(id));
        return "viewMerchant";
    }

    @GetMapping(value = "/toCheckIn")
    public String toCheckIn(){
        return "toCheckIn";
    }

    @PostMapping(value = "/checkIn")
    public String checkIn(MerchantCheckInForm merchantCheckInForm, Model model){

        if(StringUtils.isBlank(merchantCheckInForm.getAccount())){
            model.addAttribute("error", "用户名不能为空");
            return "toCheckIn";
        }

        if(StringUtils.isBlank(merchantCheckInForm.getPassword())){
            model.addAttribute("error", "密码不能为空");
            return "toCheckIn";
        }

        if(StringUtils.isBlank(merchantCheckInForm.getRepassword())){
            model.addAttribute("error", "重复密码不能为空");
            return "toCheckIn";
        }

        if(!StringUtils.equals(merchantCheckInForm.getRepassword(), merchantCheckInForm.getRepassword())){
            model.addAttribute("error", "两次输入的密码不一致");
            return "toCheckIn";
        }
        merchantService.saveMerchantInfo(merchantCheckInForm);

        return "toCheckIn";
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
        boolean verifyPassword = merchantService.verifyPassword(userRegisterForm.getAccount(), userRegisterForm.getPassword());
        if(!verifyPassword){
            model.addAttribute("error", "用户名或者密码错误，请重新输入");
            return "toLogin";
        }

        return "toLogin";
    }
}
