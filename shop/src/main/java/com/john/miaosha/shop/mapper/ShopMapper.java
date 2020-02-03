package com.john.miaosha.shop.mapper;

import com.john.miaosha.entity.ShopInfo;
import com.john.miaosha.vo.ShopInfoVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopMapper {

    void saveShopInfo(@Param("shopInfo")ShopInfo shopInfo);

    List<ShopInfo> listShopInfoBy(ShopInfoVo shopInfoVo);

    void updateShopInfoBy(ShopInfo shopInfo);
}
