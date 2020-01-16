package com.john.miaosha.form;

import lombok.Data;

import java.util.Date;

@Data
public class MerchantCheckInForm {

    private String account;

    private String password;

    private String repassword;

    private String name;

    private String province;

    private String city;

    private String address;

    private String telphone;

}
