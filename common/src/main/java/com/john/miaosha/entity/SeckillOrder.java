package com.john.miaosha.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeckillOrder {

    private Long id;

    private Long seckillProductId;

    private Long productId;

    private Double payAmount;

    private Long userId;

    private Long merchantId;

    private LocalDateTime payTime;

    private Integer payStatus;

    private Integer payType;

    private String receiveAddress;

    private String receivePhone;

    private String receiveName;

    private String tradeSerialNum;

    private Integer num;

    private Integer orderFlag;

    private LocalDateTime createTime;

}
