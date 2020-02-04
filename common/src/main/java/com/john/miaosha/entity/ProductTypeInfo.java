package com.john.miaosha.entity;

import lombok.Data;

@Data
public class ProductTypeInfo {

    private Long id;

    private String productTypeName;

    private String productTypeDesc;

    private Long parentId;

    private Long grade;

}
