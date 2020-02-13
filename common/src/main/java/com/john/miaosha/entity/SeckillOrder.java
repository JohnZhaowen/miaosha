package com.john.miaosha.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SeckillOrder {

    private Long id;

    private Long seckillResultId;

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

    public SeckillOrder(Long id, Integer orderFlag){
        this.id = id;
        this.orderFlag = orderFlag;
    }

}
