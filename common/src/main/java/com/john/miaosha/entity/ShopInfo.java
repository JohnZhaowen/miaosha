package com.john.miaosha.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
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
    /**
     * 0-申请中
     * 1-申请通过
     * 2-驳回
     * 3-商铺下架
     */
    private int state;

    private LocalDate createTime;

    public ShopInfo(int state, Long id){
        this.state = state;
        this.id = id;
    }


}
