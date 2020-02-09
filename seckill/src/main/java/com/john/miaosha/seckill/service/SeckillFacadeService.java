package com.john.miaosha.seckill.service;

import com.john.miaosha.entity.ProductDetail;
import com.john.miaosha.entity.ProductInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "product")
public interface SeckillFacadeService {

    @GetMapping(value = "/productFacade/listProductBy/{shopId}")
    List<ProductInfo> listProductBy(@PathVariable("shopId") Long shopId);

    @GetMapping(value = "/productFacade/findProductById/{id}")
    ProductInfo findProductById(@PathVariable("id") Long id);

    @GetMapping(value = "/productFacade/findProductDetailById/{id}")
    ProductDetail findProductDetailById(@PathVariable("id") Long id);
}
