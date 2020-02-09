package com.john.miaosha.form;

import lombok.Data;

@Data
public class SeckillForm {

    private Long productId;

    private Long shopId;

    private Integer state;

    private String productTitle;

    private String productName;

    private Double productPrice;

    private Double seckillPrice;

    private String startTime;

    private String endTime;


}
