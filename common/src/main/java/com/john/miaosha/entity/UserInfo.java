package com.john.miaosha.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {

    private Integer id;

    private String account;

    private String encryptionPassword;

    private String originalPassword;

    private Integer sex;

    private String telphone;

    private String birthday;

    private String qq;

    private String wechat;

    private String idCard;

    private String name;

    private String age;

}
