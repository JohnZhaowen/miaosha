package com.john.miaosha.quartz.controller;

import com.john.miaosha.quartz.entity.GoodInfo;
import com.john.miaosha.quartz.service.GoodInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodController {

    @Autowired
    private GoodInfoService goodInfoService;

    @PostMapping(value = "/save")
    public Long save(@RequestBody GoodInfo goodInfo){
        return goodInfoService.saveGood(goodInfo);
    }
}
