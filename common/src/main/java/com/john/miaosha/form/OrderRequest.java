package com.john.miaosha.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private Long id;

    private Long userId;

    private Long merchantId;
}
