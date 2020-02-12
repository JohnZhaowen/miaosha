package com.john.miaosha.producttype.controller;


import com.john.miaosha.entity.ProductTypeInfo;
import com.john.miaosha.producttype.service.ProductTypeService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/productType")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @GetMapping("/toAddProductTypeInfo")
    public String toAddProductTypeInfo(){
        return "toAddProductTypeInfo";
    }


    @PostMapping(value = "/addProductTypeInfo")
    public String addProductTypeInfo(ProductTypeInfo productTypeInfo, Model model){

        if(StringUtils.isBlank(productTypeInfo.getProductTypeName())){
            model.addAttribute("error", "商品类别名称不能为空");
            return "toAddProductTypeInfo";
        }

        productTypeService.saveProductType(productTypeInfo);

        return "toAddProductTypeInfo";
    }

}
