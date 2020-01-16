package com.john.miaosha.merchant.service;

import com.john.miaosha.entity.MerchantInfo;
import com.john.miaosha.entity.UserInfo;
import com.john.miaosha.form.MerchantCheckInForm;
import com.john.miaosha.form.UserRegisterForm;

public interface MerchantService {

    MerchantInfo findUserById(int id);

    void saveMerchantInfo(MerchantCheckInForm merchantCheckInForm);

    MerchantInfo findMerchantByAccount(String account);

    boolean verifyPassword(String account, String password);
}
