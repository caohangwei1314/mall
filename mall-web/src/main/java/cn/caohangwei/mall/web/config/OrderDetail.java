package cn.caohangwei.mall.web.config;

import cn.caohangwei.mall.shop.dao.model.ShopGoods;
import cn.caohangwei.mall.shop.dao.model.ShopOrderInfo;

public class OrderDetail {

    private ShopGoods shopGoods;
    private ShopOrderInfo shopOrderInfo;

    public ShopGoods getShopGoods() {
        return shopGoods;
    }

    public void setShopGoods(ShopGoods shopGoods) {
        this.shopGoods = shopGoods;
    }

    public ShopOrderInfo getShopOrderInfo() {
        return shopOrderInfo;
    }

    public void setShopOrderInfo(ShopOrderInfo shopOrderInfo) {
        this.shopOrderInfo = shopOrderInfo;
    }
}
