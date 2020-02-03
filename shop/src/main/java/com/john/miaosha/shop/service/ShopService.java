package com.john.miaosha.shop.service;

import com.john.miaosha.entity.ShopInfo;
import com.john.miaosha.form.ShopApplyForm;
import com.john.miaosha.form.ShopSearchForm;

import java.util.List;

public interface ShopService {

    void saveShopInfo(ShopApplyForm shopApplyForm);
    List<ShopInfo> listShopInfoBy(ShopSearchForm shopSearchForm);
    void updateShopInfoBy(ShopInfo shopInfo);
}
