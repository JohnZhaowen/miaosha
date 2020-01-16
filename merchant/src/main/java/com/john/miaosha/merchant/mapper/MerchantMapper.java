package com.john.miaosha.merchant.mapper;

import com.john.miaosha.entity.MerchantInfo;
import com.john.miaosha.entity.UserInfo;
import org.apache.ibatis.annotations.Param;

public interface MerchantMapper {

    MerchantInfo findMerchantById(@Param("id") int id);

    void saveMerchantInfo(@Param("merchantInfo") MerchantInfo merchantInfo);

    MerchantInfo findMerchantByAccount(@Param("account") String account);
}
