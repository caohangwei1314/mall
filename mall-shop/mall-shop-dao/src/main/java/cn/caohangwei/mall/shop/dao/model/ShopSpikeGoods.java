package cn.caohangwei.mall.shop.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopSpikeGoods implements Serializable {
    /**
     * 秒杀商品表
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Long id;

    /**
     * 商品ID
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Long goodsId;

    /**
     * 秒杀价
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private BigDecimal spikePrice;

    /**
     * 库存数量
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Integer spikeStock;

    /**
     * 秒杀开始i时间
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Date spikeStartTime;

    /**
     * 秒杀结束时间
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Date spikeEndTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getSpikePrice() {
        return spikePrice;
    }

    public void setSpikePrice(BigDecimal spikePrice) {
        this.spikePrice = spikePrice;
    }

    public Integer getSpikeStock() {
        return spikeStock;
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
        sb.append(", id=").append(id);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", spikePrice=").append(spikePrice);
        sb.append(", spikeStock=").append(spikeStock);
        sb.append(", spikeStartTime=").append(spikeStartTime);
        sb.append(", spikeEndTime=").append(spikeEndTime);
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
        ShopSpikeGoods other = (ShopSpikeGoods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getSpikePrice() == null ? other.getSpikePrice() == null : this.getSpikePrice().equals(other.getSpikePrice()))
            && (this.getSpikeStock() == null ? other.getSpikeStock() == null : this.getSpikeStock().equals(other.getSpikeStock()))
            && (this.getSpikeStartTime() == null ? other.getSpikeStartTime() == null : this.getSpikeStartTime().equals(other.getSpikeStartTime()))
            && (this.getSpikeEndTime() == null ? other.getSpikeEndTime() == null : this.getSpikeEndTime().equals(other.getSpikeEndTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getSpikePrice() == null) ? 0 : getSpikePrice().hashCode());
        result = prime * result + ((getSpikeStock() == null) ? 0 : getSpikeStock().hashCode());
        result = prime * result + ((getSpikeStartTime() == null) ? 0 : getSpikeStartTime().hashCode());
        result = prime * result + ((getSpikeEndTime() == null) ? 0 : getSpikeEndTime().hashCode());
        return result;
    }
}