package com.john.miaosha.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderAckEvent extends Event {

    private Long id;

    private Long orderId;

    private Long userId;

    private Long merchantId;

    private Long sckillResultId;

}
