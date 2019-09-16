package cn.caohangwei.mall.web.base;

import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;

public class GoodsDetail {
    private int status = 0;
    private int remainSeconds = 0;
    private UcenterUser ucenterUser;
    private ShopSpikeGoodsDetail shopSpikeGoodsDetail;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(int remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public UcenterUser getUcenterUser() {
        return ucenterUser;
    }

    public void setUcenterUser(UcenterUser ucenterUser) {
        this.ucenterUser = ucenterUser;
    }

    public ShopSpikeGoodsDetail getShopSpikeGoodsDetail() {
        return shopSpikeGoodsDetail;
    }

    public void setShopSpikeGoodsDetail(ShopSpikeGoodsDetail shopSpikeGoodsDetail) {
        this.shopSpikeGoodsDetail = shopSpikeGoodsDetail;
    }
}
