package cn.caohangwei.mall.web.base;

import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;

import java.io.Serializable;

public class SpikeMessages implements Serializable {

    private UcenterUser ucenterUser;

    private Long goodsId;

    private static final long serialVersionUID = 1L;

    public UcenterUser getUcenterUser() {
        return ucenterUser;
    }

    public void setUcenterUser(UcenterUser ucenterUser) {
        this.ucenterUser = ucenterUser;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
