package com.john.miaosha.seckill.controller;


import com.john.miaosha.entity.*;
import com.john.miaosha.form.SeckillForm;
import com.john.miaosha.seckill.eventModel.CentralEventProcessor;
import com.john.miaosha.seckill.eventModel.SeckillEvent;
import com.john.miaosha.seckill.eventModel.SeckillState;
import com.john.miaosha.seckill.service.MessageFacadeService;
import com.john.miaosha.seckill.service.SeckillFacadeService;
import com.john.miaosha.seckill.service.SeckillResultService;
import com.john.miaosha.seckill.service.SeckillService;
import com.john.miaosha.seckill.strategy.DistributedLockAndFutureSeckill;
import com.john.miaosha.seckill.strategy.ProgramLockSeckill;
import com.john.miaosha.seckill.strategy.SeckillOperator;
import com.john.miaosha.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/seckill")
@Slf4j
public class SeckillController {

    private SeckillOperator seckillOperator;

    @Autowired
    private SeckillFacadeService seckillFacadeService;

    @Autowired
    private DistributedLockAndFutureSeckill distributedLockAndFutureSeckill;

    @Autowired
    private ProgramLockSeckill programLockSeckill;

    @Autowired
    private SeckillService seckillService;
    @Autowired
    private SeckillResultService seckillResultService;

    private CentralEventProcessor centralEventProcessor;

    @Autowired
    private MessageFacadeService messageFacadeService;

    @PostConstruct
    public void set(){
        String strategy = null;
        try {
            strategy = RedisUtil.get("seckillStrategy");
        } catch (Exception e) {
            log.error("redis获取策略失败...");
        }
        centralEventProcessor = new CentralEventProcessor();
        if("ProgramLockSeckill".equals(strategy)){
            seckillOperator = programLockSeckill;
        } else if("DistributedLockAndFutureSeckill".equals(strategy)){
            seckillOperator = distributedLockAndFutureSeckill;
        } else {
            seckillOperator = programLockSeckill;
        }
    }

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

    @PostMapping(value = "/seckill")
    public String seckill(Long id, Long merchantId, HttpServletRequest req){
        UserInfo userInfo = (UserInfo) req.getSession().getAttribute("userInfo");
        if(userInfo == null){
            return "toLogin";
        } else {
            centralEventProcessor.process(new SeckillEvent("new", SeckillState.NEW, seckillOperator,
                    id, userInfo.getId(), merchantId, messageFacadeService));
            return "redirect:/seckill/listSeckillResult";
        }

    }

    @GetMapping(value = "/toSetseckillStrategy")
    public String toSetseckillStrategy(){
        return "toSetseckillStrategy";
    }

    @PostMapping(value = "/setseckillStrategy")
    public String setseckillStrategy(String strategy){

        if("ProgramLockSeckill".equals(strategy)){
            seckillOperator = programLockSeckill;
        } else if("DistributedLockAndFutureSeckill".equals(strategy)){
            seckillOperator = distributedLockAndFutureSeckill;
        } else {
            seckillOperator = programLockSeckill;
        }

        RedisUtil.set("seckillStrategy", strategy);
        return "seckillPortal";
    }


    @GetMapping(value = "/toSeckillProductDetail")
    public String toSeckillProductDetail(Long id, Model model){

        SeckillInfo seckillInfo = seckillService.findSeckillInfoById(id);
        ProductDetail productDetail = seckillFacadeService.findProductDetailById(seckillInfo.getProductId());
        ProductInfo productInfo = seckillFacadeService.findProductById(seckillInfo.getProductId());


        model.addAttribute("seckillInfo", seckillInfo);
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("merchantId", productInfo.getMerchantId());
        model.addAttribute("remainNum", seckillInfo.getSeckillInventory() - seckillInfo.getSeckillNum());
        return "seckillProductDetail";
    }

    @GetMapping(value = "/listSeckillResult")
    public String listSeckillResult(Model model, HttpServletRequest req){

        UserInfo userInfo = (UserInfo) req.getSession().getAttribute("userInfo");
        if(userInfo == null){
            return "toLogin";
        } else {
            List<SeckillResult> seckillResults = seckillResultService.findSeckillResultByUserId(userInfo.getId());
            model.addAttribute("list", seckillResults);
            return "listSeckillResult";
        }
    }


}
