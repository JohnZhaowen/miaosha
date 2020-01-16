package com.john.miaosha.form;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRegisterForm implements Serializable {

    private String account;

    private String password;

    private String repassword;

    private String telphone;

    private String birthday;

    private String qq;

    private String wechat;

}
