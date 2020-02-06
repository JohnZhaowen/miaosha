package com.john.miaosha.product.controller;

import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.product.service.ProductInfoService;
import com.john.miaosha.vo.ProductInfoCondition;
import com.john.miaosha.vo.ProductInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productFacade")
public class ProductFacadeController {

    @Autowired
    private ProductInfoService productInfoService;

    @GetMapping(value = "/listProductBy/{shopId}")
    public List<ProductInfo> listProductBy(@PathVariable("shopId") Long shopId){
        ProductInfoVo productInfoVo = new ProductInfoVo();
        ProductInfoCondition condition = new ProductInfoCondition();
        condition.setShopId(shopId);
        condition.setState(1);
        productInfoVo.setProductInfoCondition(condition);
        return productInfoService.listProductInfoBy(productInfoVo);
    }


    @GetMapping(value = "/findProductById/{id}")
    public ProductInfo findProductById(@PathVariable("id") Long id){
        return productInfoService.findProductById(id);
    }

}
