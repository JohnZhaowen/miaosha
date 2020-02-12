package com.john.miaosha.pay.controller;


import com.john.miaosha.form.PayForm;
import com.john.miaosha.pay.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay/out")
public class PayOutController {

    @Autowired
    private PayService payService;

    @PostMapping(value = "/payWith")
    public void payWith(@RequestBody PayForm payForm) {
        Integer payType = payForm.getPayType();

        if(0 == payType){
            payService.payWithWeiXin(payForm);
        } else if(1 == payType){
            payService.payWithZhiFuBao(payForm);
        } else if(2 == payType){
            payService.payWithYinLian(payForm);
        }
    }
}
