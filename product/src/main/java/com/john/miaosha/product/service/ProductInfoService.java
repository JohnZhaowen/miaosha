package com.john.miaosha.product.service;

import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoService {

    void saveProductInfo(ProductInfo productInfo);

    List<ProductInfo> listProductInfoBy(ProductInfoVo productInfoVo);

    void updateProductInfoBy(ProductInfo productInfo);

    ProductInfo findProductById(Long id);
}
