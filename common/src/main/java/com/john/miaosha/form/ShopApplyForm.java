package com.john.miaosha.form;

import lombok.Data;

@Data
public class ShopApplyForm {

    private Long merchantId;

    private String shopName;

    private String shopDesc;

    private String shopBizScope;

    private String province;

    private String city;

    private String bizLicense;

}
