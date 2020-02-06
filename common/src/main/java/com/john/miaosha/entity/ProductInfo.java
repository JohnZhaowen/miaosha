package com.john.miaosha.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ProductInfo {

    private Long id;

    private Long merchantId;

    private Long productTypeId;

    private Long shopId;

    private Long productInventory;

    private String productTitle;

    private String productName;

    private String productPictureUrl;

    private Double productPrice;

    private Double productDiscounts;

    private Integer state;

    private LocalDateTime approveTime;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
