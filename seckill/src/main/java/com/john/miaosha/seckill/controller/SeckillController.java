package com.john.miaosha.seckill.controller;


import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.entity.SeckillInfo;
import com.john.miaosha.form.SeckillForm;
import com.john.miaosha.seckill.service.SeckillFacadeService;
import com.john.miaosha.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillFacadeService seckillFacadeService;

    @Autowired
    private SeckillService seckillService;

    @GetMapping(value = "/listShopProduct/{shopId}")
    public String listShopProduct(@PathVariable("shopId") Long shopId, Model model){
        List<ProductInfo> productInfos = seckillFacadeService.listProductBy(shopId);
        model.addAttribute("list", productInfos);
        return "listProduct";
    }

    @GetMapping(value = "/preSaveSeckillProduct")
    public String saveSeckillProduct(Long id, Model model){
        ProductInfo productInfo = seckillFacadeService.findProductById(id);
        model.addAttribute("productInfo", productInfo);
        return "preSaveSeckill";
    }

    @PostMapping(value = "/saveSeckillProduct")
    public String saveSeckillProduct(SeckillForm seckillForm){
        seckillService.saveSeckill(seckillForm);
        return "seckillSaveSuccess";
    }

    @GetMapping(value = "/listSeckillProductAll/{shopId}")
    public String listSeckillProductAll(@PathVariable("shopId") long shopId, Model model){
        SeckillForm seckillForm = new SeckillForm();
        seckillForm.setShopId(shopId);
        List<SeckillInfo> seckillInfos = seckillService.listSeckillInfo(seckillForm);

        model.addAttribute("list", seckillInfos);
        return "listSeckillInfo";
    }

    @GetMapping(value = "/updateState")
    public String updateState(int state, Long id){
        SeckillInfo seckillInfo = new SeckillInfo();
        seckillInfo.setState(state);
        seckillInfo.setId(id);
        seckillService.updateSeckillInfoBy(seckillInfo);
        return "seckillUpdateStateSuccess";
    }


    @GetMapping(value = "/listSeckillProductByState")
    public String listSeckillProductByState(Model model){
        SeckillForm seckillForm = new SeckillForm();
        seckillForm.setState(3);
        List<SeckillInfo> seckillInfos = seckillService.listSeckillInfo(seckillForm);

        model.addAttribute("list", seckillInfos);
        return "seckillPortal";
    }
}
