package com.john.miaosha.entity;

import lombok.Data;

@Data
public class ProductDetail {

    private Long id;

    private Long productId;

    private String productPlace;

    private String productBrand;

    private String productDesc;

    private String productWeight;

    private String productDetailPictureUrl;

    private String specificationAndPack;

}
