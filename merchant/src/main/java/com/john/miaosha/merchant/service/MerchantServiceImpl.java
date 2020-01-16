package com.john.miaosha.merchant.service;

import com.john.miaosha.entity.MerchantInfo;
import com.john.miaosha.form.MerchantCheckInForm;
import com.john.miaosha.merchant.mapper.MerchantMapper;
import com.john.miaosha.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantMapper merchantMapper;

    @Override
    public MerchantInfo findUserById(int userId) {
        return merchantMapper.findMerchantById(userId);
    }

    @Override
    public void saveMerchantInfo(MerchantCheckInForm merchantCheckInForm) {

        MerchantInfo merchantInfo = new MerchantInfo();
        merchantInfo.setAccount(merchantCheckInForm.getAccount());
        merchantInfo.setEncryptionPassword(Md5Util.md5(merchantCheckInForm.getPassword(), Md5Util.MD5_KEY));
        merchantInfo.setOriginalPassword(merchantCheckInForm.getPassword());
        merchantInfo.setTelphone(merchantCheckInForm.getTelphone());
        merchantInfo.setAddress(merchantCheckInForm.getAddress());
        merchantInfo.setCity(merchantCheckInForm.getCity());
        merchantInfo.setProvince(merchantCheckInForm.getProvince());
        merchantInfo.setName(merchantCheckInForm.getName());

        merchantMapper.saveMerchantInfo(merchantInfo);
    }

    @Override
    public MerchantInfo findMerchantByAccount(String account) {
        return merchantMapper.findMerchantByAccount(account);
    }

    @Override
    public boolean verifyPassword(String account, String password) {
        MerchantInfo merchantInfo = findMerchantByAccount(account);
        String encryptionPassword = merchantInfo == null ? null : merchantInfo.getEncryptionPassword();
        return Md5Util.verify(password, Md5Util.MD5_KEY, encryptionPassword);
    }
}
