package cn.caohangwei.mall.shop.dao.base;

import cn.caohangwei.mall.shop.dao.model.ShopGoods;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopSpikeGoodsDetail extends ShopGoods implements Serializable {

    /**
     * 秒杀价
     *
     * @mbg.generated Mon Sep 09 17:43:15 CST 2019
     */
    private BigDecimal spikePrice;

    /**
     * 库存数量
     *
     * @mbg.generated Mon Sep 09 17:43:15 CST 2019
     */
    private Integer spikeStock;

    /**
     * 秒杀开始i时间
     *
     * @mbg.generated Mon Sep 09 17:43:15 CST 2019
     */
    private Date spikeStartTime;

    /**
     * 秒杀结束时间
     *
     * @mbg.generated Mon Sep 09 17:43:15 CST 2019
     */
    private Date spikeEndTime;

    public Integer getSpikeStock() {
        return spikeStock;
    }

    public BigDecimal getSpikePrice() {
        return spikePrice;
    }

    public void setSpikePrice(BigDecimal spikePrice) {
        this.spikePrice = spikePrice;
    }

    public void setSpikeStock(Integer spikeStock) {
        this.spikeStock = spikeStock;
    }

    public Date getSpikeStartTime() {
        return spikeStartTime;
    }

    public void setSpikeStartTime(Date spikeStartTime) {
        this.spikeStartTime = spikeStartTime;
    }

    public Date getSpikeEndTime() {
        return spikeEndTime;
    }

    public void setSpikeEndTime(Date spikeEndTime) {
        this.spikeEndTime = spikeEndTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(getId());
        sb.append(", name=").append(getName());
        sb.append(", title=").append(getTitle());
        sb.append(", img=").append(getImg());
        sb.append(", prices=").append(getPrices());
        sb.append(", stock=").append(getStock());
        sb.append(", detail=").append(getDetail());
        sb.append(", spikePrice=").append(getSpikePrice());
        sb.append(", spikeStock=").append(getSpikeStock());
        sb.append(", spikeStartTime=").append(getSpikeStartTime());
        sb.append(", spikeEndTime=").append(getSpikeEndTime());
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        ShopSpikeGoodsDetail other = (ShopSpikeGoodsDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
                && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
                && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
                && (this.getPrices() == null ? other.getPrices() == null : this.getPrices().equals(other.getPrices()))
                && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
                && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()))
                && (this.getSpikePrice() == null ? other.getSpikePrice() == null : this.getSpikePrice().equals(other.getSpikePrice()))
                && (this.getSpikeStock() == null ? other.getSpikeStock() == null : this.getSpikeStock().equals(other.getSpikeStock()))
                && (this.getSpikeStartTime() == null ? other.getSpikeStartTime() == null : this.getSpikeEndTime().equals(other.getSpikeStartTime()))
                && (this.getSpikeEndTime() == null ? other.getSpikeEndTime() == null : this.getSpikeEndTime().equals(other.getSpikeEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getImg() == null) ? 0 : getImg().hashCode());
        result = prime * result + ((getPrices() == null) ? 0 : getPrices().hashCode());
        result = prime * result + ((getStock() == null) ? 0 : getStock().hashCode());
        result = prime * result + ((getDetail() == null) ? 0 : getDetail().hashCode());
        result = prime * result + ((getSpikePrice() == null) ? 0 : getSpikePrice().hashCode());
        result = prime * result + ((getSpikeStock() == null) ? 0 : getSpikeStock().hashCode());
        result = prime * result + ((getSpikeStartTime() == null) ? 0 : getSpikeStartTime().hashCode());
        result = prime * result + ((getSpikeEndTime() == null) ? 0 : getSpikeEndTime().hashCode());
        return result;
    }
}
