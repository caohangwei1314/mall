package cn.caohangwei.mall.shop.dao.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopSpikeGoodsExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ShopSpikeGoodsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNull() {
            addCriterion("goods_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIsNotNull() {
            addCriterion("goods_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodsIdEqualTo(Long value) {
            addCriterion("goods_id =", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotEqualTo(Long value) {
            addCriterion("goods_id <>", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThan(Long value) {
            addCriterion("goods_id >", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdGreaterThanOrEqualTo(Long value) {
            addCriterion("goods_id >=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThan(Long value) {
            addCriterion("goods_id <", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdLessThanOrEqualTo(Long value) {
            addCriterion("goods_id <=", value, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdIn(List<Long> values) {
            addCriterion("goods_id in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotIn(List<Long> values) {
            addCriterion("goods_id not in", values, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdBetween(Long value1, Long value2) {
            addCriterion("goods_id between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andGoodsIdNotBetween(Long value1, Long value2) {
            addCriterion("goods_id not between", value1, value2, "goodsId");
            return (Criteria) this;
        }

        public Criteria andSpikePriceIsNull() {
            addCriterion("spike_price is null");
            return (Criteria) this;
        }

        public Criteria andSpikePriceIsNotNull() {
            addCriterion("spike_price is not null");
            return (Criteria) this;
        }

        public Criteria andSpikePriceEqualTo(BigDecimal value) {
            addCriterion("spike_price =", value, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceNotEqualTo(BigDecimal value) {
            addCriterion("spike_price <>", value, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceGreaterThan(BigDecimal value) {
            addCriterion("spike_price >", value, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("spike_price >=", value, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceLessThan(BigDecimal value) {
            addCriterion("spike_price <", value, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("spike_price <=", value, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceIn(List<BigDecimal> values) {
            addCriterion("spike_price in", values, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceNotIn(List<BigDecimal> values) {
            addCriterion("spike_price not in", values, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spike_price between", value1, value2, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("spike_price not between", value1, value2, "spikePrice");
            return (Criteria) this;
        }

        public Criteria andSpikeStockIsNull() {
            addCriterion("spike_stock is null");
            return (Criteria) this;
        }

        public Criteria andSpikeStockIsNotNull() {
            addCriterion("spike_stock is not null");
            return (Criteria) this;
        }

        public Criteria andSpikeStockEqualTo(Integer value) {
            addCriterion("spike_stock =", value, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockNotEqualTo(Integer value) {
            addCriterion("spike_stock <>", value, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockGreaterThan(Integer value) {
            addCriterion("spike_stock >", value, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockGreaterThanOrEqualTo(Integer value) {
            addCriterion("spike_stock >=", value, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockLessThan(Integer value) {
            addCriterion("spike_stock <", value, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockLessThanOrEqualTo(Integer value) {
            addCriterion("spike_stock <=", value, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockIn(List<Integer> values) {
            addCriterion("spike_stock in", values, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockNotIn(List<Integer> values) {
            addCriterion("spike_stock not in", values, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockBetween(Integer value1, Integer value2) {
            addCriterion("spike_stock between", value1, value2, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStockNotBetween(Integer value1, Integer value2) {
            addCriterion("spike_stock not between", value1, value2, "spikeStock");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeIsNull() {
            addCriterion("spike_start_time is null");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeIsNotNull() {
            addCriterion("spike_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeEqualTo(Date value) {
            addCriterion("spike_start_time =", value, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeNotEqualTo(Date value) {
            addCriterion("spike_start_time <>", value, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeGreaterThan(Date value) {
            addCriterion("spike_start_time >", value, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("spike_start_time >=", value, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeLessThan(Date value) {
            addCriterion("spike_start_time <", value, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("spike_start_time <=", value, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeIn(List<Date> values) {
            addCriterion("spike_start_time in", values, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeNotIn(List<Date> values) {
            addCriterion("spike_start_time not in", values, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeBetween(Date value1, Date value2) {
            addCriterion("spike_start_time between", value1, value2, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("spike_start_time not between", value1, value2, "spikeStartTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeIsNull() {
            addCriterion("spike_end_time is null");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeIsNotNull() {
            addCriterion("spike_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeEqualTo(Date value) {
            addCriterion("spike_end_time =", value, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeNotEqualTo(Date value) {
            addCriterion("spike_end_time <>", value, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeGreaterThan(Date value) {
            addCriterion("spike_end_time >", value, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("spike_end_time >=", value, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeLessThan(Date value) {
            addCriterion("spike_end_time <", value, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("spike_end_time <=", value, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeIn(List<Date> values) {
            addCriterion("spike_end_time in", values, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeNotIn(List<Date> values) {
            addCriterion("spike_end_time not in", values, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeBetween(Date value1, Date value2) {
            addCriterion("spike_end_time between", value1, value2, "spikeEndTime");
            return (Criteria) this;
        }

        public Criteria andSpikeEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("spike_end_time not between", value1, value2, "spikeEndTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}