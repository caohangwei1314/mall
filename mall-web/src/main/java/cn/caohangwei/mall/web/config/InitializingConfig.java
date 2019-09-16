package cn.caohangwei.mall.web.config;

import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.shop.common.base.ShopGoodsPrefix;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class InitializingConfig implements InitializingBean{

    private static final Logger LOGGER = LoggerFactory.getLogger(InitializingConfig.class);

    public static ConcurrentHashMap<Long,Boolean> map;

    @Autowired
    private ShopGoodsService shopGoodsService;

    public void afterPropertiesSet() throws Exception {
        LOGGER.info(">>>>>>>>> InitializingConfig Start <<<<<<<<<<");
        List<ShopSpikeGoodsDetail> shopSpikeGoodsDetailList = shopGoodsService.getGoodsDetailList();
        if(null == shopSpikeGoodsDetailList && shopSpikeGoodsDetailList.size() < 1){
            return;
        }
        map = new ConcurrentHashMap<Long, Boolean>(shopSpikeGoodsDetailList.size());
        for(ShopSpikeGoodsDetail goodsDetail : shopSpikeGoodsDetailList){
            RedisUtil.set(ShopGoodsPrefix.SPIKE_GOODS_STOCK,"" + goodsDetail.getId(),goodsDetail.getSpikeStock());
            map.put(goodsDetail.getId(),false);
        }
    }
}
