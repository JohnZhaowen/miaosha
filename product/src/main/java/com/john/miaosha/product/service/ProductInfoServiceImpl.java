package com.john.miaosha.product.service;

import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.product.mapper.ProductInfoMapper;
import com.john.miaosha.vo.ProductInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@CacheConfig(cacheNames = "prductCache")
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
    @CachePut(value = "prductCache", key="#productInfo.id")
    public void updateProductInfoBy(ProductInfo productInfo) {
        log.info("更新商品信息开始");
        productInfoMapper.updateProductInfoBy(productInfo);
    }

    @Override
    @Cacheable(value = "prductCache", key="#id")
    public ProductInfo findProductById(Long id) {
        log.info("根据ID查询商品信息开始");
        return productInfoMapper.findProductById(id);
    }
}
