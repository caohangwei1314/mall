package cn.caohangwei.mall.shop.dao.mapper;

import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.ShopGoods;
import cn.caohangwei.mall.shop.dao.model.ShopGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface ShopGoodsMapper {
    long countByExample(ShopGoodsExample example);

    int deleteByExample(ShopGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopGoods record);

    int insertSelective(ShopGoods record);

    List<ShopGoods> selectByExampleWithBLOBs(ShopGoodsExample example);

    List<ShopGoods> selectByExample(ShopGoodsExample example);

    ShopGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByExample(@Param("record") ShopGoods record, @Param("example") ShopGoodsExample example);

    int updateByPrimaryKeySelective(ShopGoods record);

    int updateByPrimaryKeyWithBLOBs(ShopGoods record);

    int updateByPrimaryKey(ShopGoods record);

    @Select("select a.*,b.spike_price,b.spike_stock,b.spike_start_time,b.spike_end_time from shop_goods as a inner join shop_spike_goods as b on a.id = b.goods_id")
    @Results({
            @Result(property = "spikePrice",column = "spike_price"),
            @Result(property = "spikeStock",column = "spike_stock"),
            @Result(property = "spikeStartTime",column = "spike_start_time"),
            @Result(property = "spikeEndTime",column = "spike_end_time")
    })
    List<ShopSpikeGoodsDetail> getShopSpikeGoodsDetailList();

    @Select("select a.*,b.spike_price,b.spike_stock,b.spike_start_time,b.spike_end_time from shop_goods as a inner join shop_spike_goods as b on a.id = b.goods_id where a.id = #{id}")
    @Results({
            @Result(property = "spikePrice",column = "spike_price"),
            @Result(property = "spikeStock",column = "spike_stock"),
            @Result(property = "spikeStartTime",column = "spike_start_time"),
            @Result(property = "spikeEndTime",column = "spike_end_time")
    })
    ShopSpikeGoodsDetail getGoodsDetailByPrimaryKey(@Param("id") Long id);
}