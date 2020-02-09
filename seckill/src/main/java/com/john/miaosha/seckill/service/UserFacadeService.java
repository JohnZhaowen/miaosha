package com.john.miaosha.seckill.service;

import com.john.miaosha.form.UserRegisterForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(value = "user")
public interface UserFacadeService {

    @PostMapping(value = "/user/out/login")
    public Map<String, String> login(@RequestBody UserRegisterForm userRegisterForm);

}
