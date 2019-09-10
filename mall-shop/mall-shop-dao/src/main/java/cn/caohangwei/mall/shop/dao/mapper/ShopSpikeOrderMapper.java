package cn.caohangwei.mall.shop.dao.mapper;

import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrder;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopSpikeOrderMapper {
    long countByExample(ShopSpikeOrderExample example);

    int deleteByExample(ShopSpikeOrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopSpikeOrder record);

    int insertSelective(ShopSpikeOrder record);

    List<ShopSpikeOrder> selectByExample(ShopSpikeOrderExample example);

    ShopSpikeOrder selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopSpikeOrder record, @Param("example") ShopSpikeOrderExample example);

    int updateByExample(@Param("record") ShopSpikeOrder record, @Param("example") ShopSpikeOrderExample example);

    int updateByPrimaryKeySelective(ShopSpikeOrder record);

    int updateByPrimaryKey(ShopSpikeOrder record);
}