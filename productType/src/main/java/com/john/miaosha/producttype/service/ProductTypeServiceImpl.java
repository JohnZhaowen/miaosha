package com.john.miaosha.producttype.service;

import com.john.miaosha.entity.ProductTypeInfo;
import com.john.miaosha.producttype.mapper.ProductTypeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductTypeServiceImpl implements ProductTypeService {
    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Override
    public void saveProductType(ProductTypeInfo productTypeInfo) {
        productTypeMapper.saveProductType(productTypeInfo);
    }
}
