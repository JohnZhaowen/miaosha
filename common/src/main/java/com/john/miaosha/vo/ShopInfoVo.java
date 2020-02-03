package com.john.miaosha.vo;

import com.john.miaosha.form.ShopSearchForm;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class ShopInfoVo {

    private ShopInfoCondition shopInfoCondition;

    public ShopInfoVo(ShopSearchForm shopSearchForm){
        ShopInfoCondition shopInfoCondition = new ShopInfoCondition();
        shopInfoCondition.setShopName(shopSearchForm.getShopName());
        if(StringUtils.isNotBlank(shopSearchForm.getState())){
            shopInfoCondition.setState(Integer.valueOf(shopSearchForm.getState()));
        }

        this.shopInfoCondition = shopInfoCondition;
    }
}
