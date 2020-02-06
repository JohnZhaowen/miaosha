package com.john.miaosha.product.service;

import com.john.miaosha.entity.ProductDetail;
import com.john.miaosha.product.mapper.ProductDetailMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductDetailServiceImpl implements ProductDetailService {


    @Autowired
    private ProductDetailMapper productDetailMapper;

    @Override
    public void saveProductDetail(ProductDetail productDetail) {
        productDetailMapper.saveProductDetail(productDetail);
    }
}
