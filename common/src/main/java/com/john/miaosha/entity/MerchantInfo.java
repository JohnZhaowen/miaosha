package com.john.miaosha.entity;

import lombok.Data;

import java.util.Date;

@Data
public class MerchantInfo {

    private Long id;

    private String account;

    private String encryptionPassword;

    private String originalPassword;

    private String name;

    private String province;

    private String city;

    private String address;

    private String telphone;

    private Date createTime;

}
