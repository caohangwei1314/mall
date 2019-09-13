package cn.caohangwei.mall.shop.dao.mapper;

import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoods;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeGoodsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopSpikeGoodsMapper {
    long countByExample(ShopSpikeGoodsExample example);

    int deleteByExample(ShopSpikeGoodsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopSpikeGoods record);

    int insertSelective(ShopSpikeGoods record);

    List<ShopSpikeGoods> selectByExample(ShopSpikeGoodsExample example);

    ShopSpikeGoods selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopSpikeGoods record, @Param("example") ShopSpikeGoodsExample example);

    int updateByExample(@Param("record") ShopSpikeGoods record, @Param("example") ShopSpikeGoodsExample example);

    int updateByPrimaryKeySelective(ShopSpikeGoods record);

    int updateByPrimaryKey(ShopSpikeGoods record);

    int incrGoodsStockByGoodsId(@Param("goodsId") Long goodsId);
}