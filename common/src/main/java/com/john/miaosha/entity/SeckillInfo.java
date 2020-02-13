package com.john.miaosha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillInfo {

    private Long id;

    private Long productId;

    private Long shopId;

    private Integer state;

    private String productTitle;

    private String productName;

    private Long seckillNum;

    private Double productPrice;

    private Double seckillPrice;

    private Long seckillInventory;

    private Long version;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime approveTime;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;

    public SeckillInfo(Long id){
        this.id = id;
    }

}
