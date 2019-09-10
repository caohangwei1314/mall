package cn.caohangwei.mall.shop.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class ShopGoods implements Serializable {
    /**
     * 商品ID
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Long id;

    /**
     * 商品名称
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private String name;

    /**
     * 商品标题
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private String title;

    /**
     * 商品图片
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private String img;

    /**
     * 商品单价
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private BigDecimal prices;

    /**
     * 商品库存，-1表示没有限制
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private Integer stock;

    /**
     * 商品的详细介绍
     *
     * @mbg.generated Tue Sep 10 10:01:41 CST 2019
     */
    private String detail;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public BigDecimal getPrices() {
        return prices;
    }

    public void setPrices(BigDecimal prices) {
        this.prices = prices;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", img=").append(img);
        sb.append(", prices=").append(prices);
        sb.append(", stock=").append(stock);
        sb.append(", detail=").append(detail);
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
        ShopGoods other = (ShopGoods) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getImg() == null ? other.getImg() == null : this.getImg().equals(other.getImg()))
            && (this.getPrices() == null ? other.getPrices() == null : this.getPrices().equals(other.getPrices()))
            && (this.getStock() == null ? other.getStock() == null : this.getStock().equals(other.getStock()))
            && (this.getDetail() == null ? other.getDetail() == null : this.getDetail().equals(other.getDetail()));
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
        return result;
    }
}