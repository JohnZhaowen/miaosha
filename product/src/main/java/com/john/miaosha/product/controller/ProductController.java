package com.john.miaosha.product.controller;


import com.john.miaosha.entity.ProductDetail;
import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.product.service.ProductDetailService;
import com.john.miaosha.product.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductDetailService productDetailService;

    @GetMapping(value = "/toApplyProduct")
    public String toApplyProduct(){
        return "toApplyProduct";
    }

    @PostMapping(value = "/applyProduct")
    public String applyProduct(ProductInfo productInfo, Model model){

        //TODO字段校验
        productInfo.setState(0);
        productInfoService.saveProductInfo(productInfo);

        return "toApplyProduct";
    }

    @GetMapping(value = "/listProduct")
    public String listProduct(Model model){

        List<ProductInfo> productInfos = productInfoService.listProductInfoBy(null);

        model.addAttribute("list", productInfos);
        return "listProduct";
    }

    @GetMapping(value = "/updateState")
    public String updateState(int state, Long id){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setState(state);
        productInfo.setId(id);
        productInfoService.updateProductInfoBy(productInfo);
        return "listProduct";
    }

    @GetMapping(value = "/toAddProductDetail")
    public String toAddProductDetail(Long id, Model model){
        ProductInfo productInfo = productInfoService.findProductById(id);
        model.addAttribute("productInfo", productInfo);
        return "toAddProductDetail";
    }
    @PostMapping(value = "/addProductDetail")
    public String addProductDetail(ProductDetail productDetail){
        productDetailService.saveProductDetail(productDetail);
        return "redirect:/product/listProduct";
    }


}
