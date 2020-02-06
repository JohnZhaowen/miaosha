package com.john.miaosha.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SeckillResult {

    private Long id;

    private Long userId;

    private Long productId;

    private Long seckillId;

    private Integer result;

    private String resultData;

    private LocalDateTime secTime;

}
