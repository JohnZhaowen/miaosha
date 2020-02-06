package com.john.miaosha.product.mapper;

import com.john.miaosha.entity.ProductInfo;
import com.john.miaosha.vo.ProductInfoVo;

import java.util.List;

public interface ProductInfoMapper {

    void saveProductInfo(ProductInfo productInfo);

    List<ProductInfo> listProductInfoBy(ProductInfoVo productInfoVo);

    ProductInfo findProductById(Long id);

    void updateProductInfoBy(ProductInfo productInfo);


}
