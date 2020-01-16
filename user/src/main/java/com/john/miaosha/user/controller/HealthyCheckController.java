package com.john.miaosha.user.controller;

import com.john.miaosha.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthyCheckController {

    @Autowired
    private UserService userService;

    @GetMapping({"/helloWorld", "/"})
    public String sayHelloWorld(){

        return "success";
    }



    @GetMapping({"/getUser/{userId}"})
    public String getUserById(@PathVariable("userId") Integer userId){

        return userService.findUserById(userId) + "";
    }
}
