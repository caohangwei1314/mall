package cn.caohangwei.mall.shop.dao.mapper;

import cn.caohangwei.mall.shop.dao.model.ShopOrderInfo;
import cn.caohangwei.mall.shop.dao.model.ShopOrderInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopOrderInfoMapper {
    long countByExample(ShopOrderInfoExample example);

    int deleteByExample(ShopOrderInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ShopOrderInfo record);

    int insertSelective(ShopOrderInfo record);

    List<ShopOrderInfo> selectByExample(ShopOrderInfoExample example);

    ShopOrderInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ShopOrderInfo record, @Param("example") ShopOrderInfoExample example);

    int updateByExample(@Param("record") ShopOrderInfo record, @Param("example") ShopOrderInfoExample example);

    int updateByPrimaryKeySelective(ShopOrderInfo record);

    int updateByPrimaryKey(ShopOrderInfo record);
}