package com.john.miaosha.product.service;

import com.john.miaosha.entity.ProductDetail;

public interface ProductDetailService {

    void saveProductDetail(ProductDetail productDetail);

    ProductDetail findProductDetailById(Long id);
}
