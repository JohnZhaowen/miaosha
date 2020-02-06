package com.john.miaosha.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeckillInfo {

    private Long id;

    private Long productId;

    private Long shopId;

    private Integer state;

    private String productTile;

    private String productName;

    private Long seckillNum;

    private Double productPrice;

    private Double seckillPrice;

    private Long seckillInventory;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime approveTime;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

}
