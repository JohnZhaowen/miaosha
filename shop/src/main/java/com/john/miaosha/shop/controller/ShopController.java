package com.john.miaosha.shop.controller;


import com.john.miaosha.entity.ShopInfo;
import com.john.miaosha.form.ShopApplyForm;
import com.john.miaosha.form.ShopSearchForm;
import com.john.miaosha.shop.service.ShopService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;


    @GetMapping(value = "/toApplyShop")
    public String toApplyShop(){
        return "toApplyShop";
    }

    @PostMapping(value = "/applyShop")
    public String applyShop(ShopApplyForm shopApplyForm, Model model){

        if(StringUtils.isBlank(shopApplyForm.getShopName())){
            model.addAttribute("error", "店铺名称不能为空");
            return "toApplyShop";
        }

        if(StringUtils.isBlank(shopApplyForm.getShopBizScope())){
            model.addAttribute("error", "经营范围不能为空");
            return "toApplyShop";
        }

        if(StringUtils.isBlank(shopApplyForm.getBizLicense())){
            model.addAttribute("error", "营业执照不能为空");
            return "toApplyShop";
        }

        shopService.saveShopInfo(shopApplyForm);

        return "toApplyShop";
    }


    @GetMapping(value = "/toSearchShop")
    public String toSearchShop(){
        return "toSearchShop";
    }



    @PostMapping(value = "/searchShop")
    public String searchShop(ShopSearchForm shopSearchForm, Model model){

        List<ShopInfo> shopInfos = shopService.listShopInfoBy(shopSearchForm);
        model.addAttribute("listShop", shopInfos);
        return "listShop";
    }


    @GetMapping(value = "/updateState")
    public String updateState(int state, Long id){

        shopService.updateShopInfoBy(new ShopInfo(state, id));
        return "listShop";
    }
}
