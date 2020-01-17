package com.john.miaosha.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class ShopInfo implements Serializable {

    private static final long serialVersionUID = 6739215422942456996L;

    private Long id;
    private Long merchantId;
    private String shopName;
    private String shopDesc;
    private String shopBizScope;
    private String province;
    private String city;
    private String bizLicense;
    private LocalDate createTime;


}
