package com.john.miaosha.product.service;

import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.product.mapper.ProductInfoMapper;
import com.john.miaosha.vo.ProductInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoMapper productInfoMapper;

    @Override
    public void saveProductInfo(ProductInfo productInfo) {
        productInfoMapper.saveProductInfo(productInfo);
    }

    @Override
    public List<ProductInfo> listProductInfoBy(ProductInfoVo productInfoVo) {
        if(productInfoVo == null){
            productInfoVo = new ProductInfoVo();
        }
        return productInfoMapper.listProductInfoBy(productInfoVo);
    }

    @Override
    public void updateProductInfoBy(ProductInfo productInfo) {
        productInfoMapper.updateProductInfoBy(productInfo);
    }

    @Override
    public ProductInfo findProductById(Long id) {
        return productInfoMapper.findProductById(id);
    }
}
