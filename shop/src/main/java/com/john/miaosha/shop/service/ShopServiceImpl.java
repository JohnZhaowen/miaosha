package com.john.miaosha.shop.service;

import com.john.miaosha.entity.ShopInfo;
import com.john.miaosha.form.ShopApplyForm;
import com.john.miaosha.form.ShopSearchForm;
import com.john.miaosha.shop.mapper.ShopMapper;
import com.john.miaosha.vo.ShopInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Override
    public void saveShopInfo(ShopApplyForm shopApplyForm) {
        ShopInfo shopInfo = new ShopInfo();
        shopInfo.setMerchantId(shopApplyForm.getMerchantId());
        shopInfo.setShopName(shopApplyForm.getShopName());
        shopInfo.setShopDesc(shopApplyForm.getShopDesc());
        shopInfo.setShopBizScope(shopApplyForm.getShopBizScope());
        shopInfo.setProvince(shopApplyForm.getProvince());
        shopInfo.setCity(shopApplyForm.getCity());
        shopInfo.setBizLicense(shopApplyForm.getBizLicense());
        shopInfo.setState(0);

        shopMapper.saveShopInfo(shopInfo);
    }

    @Override
    public List<ShopInfo> listShopInfoBy(ShopSearchForm shopSearchForm) {
        ShopInfoVo shopInfoVo = new ShopInfoVo(shopSearchForm);
        return shopMapper.listShopInfoBy(shopInfoVo);
    }

    @Override
    public void updateShopInfoBy(ShopInfo shopInfo) {
        shopMapper.updateShopInfoBy(shopInfo);
    }
}
