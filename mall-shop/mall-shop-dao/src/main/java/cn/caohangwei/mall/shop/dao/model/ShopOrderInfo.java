package cn.caohangwei.mall.shop.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ShopOrderInfo implements Serializable {
    private Long id;

    /**
     * 用户ID
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Long userId;

    /**
     * 商品ID
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Long goodsId;

    /**
     * 收获地址ID
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Long deliveryAddressId;

    /**
     * 冗余过来的商品名称
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private String goodsName;

    /**
     * 商品数量
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Integer goodsCount;

    /**
     * 商品单价
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private BigDecimal goodsPrice;

    /**
     * 1pc,2android,3ios
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Byte orderChannel;

    /**
     * 订单状态：0新建未支付，1已支付，2已发货，3已收货，4已退款，5已完成
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Byte status;

    /**
     * 订单创建时间
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Date createDate;

    /**
     * 支付时间
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Date payDate;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getDeliveryAddressId() {
        return deliveryAddressId;
    }

    public void setDeliveryAddressId(Long deliveryAddressId) {
        this.deliveryAddressId = deliveryAddressId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Byte getOrderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(Byte orderChannel) {
        this.orderChannel = orderChannel;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", goodsId=").append(goodsId);
        sb.append(", deliveryAddressId=").append(deliveryAddressId);
        sb.append(", goodsName=").append(goodsName);
        sb.append(", goodsCount=").append(goodsCount);
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", orderChannel=").append(orderChannel);
        sb.append(", status=").append(status);
        sb.append(", createDate=").append(createDate);
        sb.append(", payDate=").append(payDate);
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
        ShopOrderInfo other = (ShopOrderInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getGoodsId() == null ? other.getGoodsId() == null : this.getGoodsId().equals(other.getGoodsId()))
            && (this.getDeliveryAddressId() == null ? other.getDeliveryAddressId() == null : this.getDeliveryAddressId().equals(other.getDeliveryAddressId()))
            && (this.getGoodsName() == null ? other.getGoodsName() == null : this.getGoodsName().equals(other.getGoodsName()))
            && (this.getGoodsCount() == null ? other.getGoodsCount() == null : this.getGoodsCount().equals(other.getGoodsCount()))
            && (this.getGoodsPrice() == null ? other.getGoodsPrice() == null : this.getGoodsPrice().equals(other.getGoodsPrice()))
            && (this.getOrderChannel() == null ? other.getOrderChannel() == null : this.getOrderChannel().equals(other.getOrderChannel()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateDate() == null ? other.getCreateDate() == null : this.getCreateDate().equals(other.getCreateDate()))
            && (this.getPayDate() == null ? other.getPayDate() == null : this.getPayDate().equals(other.getPayDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getGoodsId() == null) ? 0 : getGoodsId().hashCode());
        result = prime * result + ((getDeliveryAddressId() == null) ? 0 : getDeliveryAddressId().hashCode());
        result = prime * result + ((getGoodsName() == null) ? 0 : getGoodsName().hashCode());
        result = prime * result + ((getGoodsCount() == null) ? 0 : getGoodsCount().hashCode());
        result = prime * result + ((getGoodsPrice() == null) ? 0 : getGoodsPrice().hashCode());
        result = prime * result + ((getOrderChannel() == null) ? 0 : getOrderChannel().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateDate() == null) ? 0 : getCreateDate().hashCode());
        result = prime * result + ((getPayDate() == null) ? 0 : getPayDate().hashCode());
        return result;
    }
}