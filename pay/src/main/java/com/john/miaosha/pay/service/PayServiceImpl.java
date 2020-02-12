package com.john.miaosha.pay.service;

import com.john.miaosha.form.PayForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayServiceImpl implements PayService {

    @Override
    public void payWithZhiFuBao(PayForm payForm) {
        log.info("支付宝支付");
    }

    @Override
    public void payWithYinLian(PayForm payForm) {
        log.info("银联支付");
    }

    @Override
    public void payWithWeiXin(PayForm payForm) {
        log.info("微信支付");
    }


}
