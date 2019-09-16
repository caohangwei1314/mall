package cn.caohangwei.mall.web.base;

import cn.caohangwei.mall.common.base.BaseResult;
import cn.caohangwei.mall.common.base.BaseResultEnum;
import cn.caohangwei.mall.common.util.JSONUtil;
import cn.caohangwei.mall.common.util.RedisUtil;
import cn.caohangwei.mall.shop.common.base.ShopGoodsPrefix;
import cn.caohangwei.mall.shop.dao.base.ShopSpikeGoodsDetail;
import cn.caohangwei.mall.shop.dao.model.ShopOrderInfo;
import cn.caohangwei.mall.shop.dao.model.ShopSpikeOrder;
import cn.caohangwei.mall.shop.rpc.api.ShopGoodsService;
import cn.caohangwei.mall.shop.rpc.api.ShopOrderInfoService;
import cn.caohangwei.mall.web.config.RabbitMQConfig;
import cn.caohangwei.mall.ucenter.dao.model.UcenterUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RabbitMQReceiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQReceiver.class);

    @Autowired
    private ShopGoodsService shopGoodsService;

    @Autowired
    private ShopOrderInfoService shopOrderInfoService;

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receive(String msg){
        LOGGER.info("receive message: " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.SPIKE_QUEUE)
    public void receiveHeaders(String msg){
        LOGGER.info("receive message: " + msg);
        SpikeMessages messages = JSONUtil.stringToBean(msg,SpikeMessages.class);
        UcenterUser user = messages.getUcenterUser();
        Long goodsId = messages.getGoodsId();
        //判断库存
        ShopSpikeGoodsDetail goods = shopGoodsService.getGoodsDetailByPrimaryKey(goodsId);
        if(goods.getSpikeStock() <= 0){
            return ;
        }
        ShopSpikeOrder shopSpikeOrder = RedisUtil.get(ShopGoodsPrefix.SPIKE_ORDER,user.getId().toString(),ShopSpikeOrder.class);
        if(null != shopSpikeOrder){
            return ;
        }
        shopOrderInfoService.spikeGoods(user.getId(),goods);
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String msg){
        LOGGER.info("topic queue 1 message: " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String msg){
        LOGGER.info("topic queue 2 message: " + msg);
    }

    @RabbitListener(queues = RabbitMQConfig.HEADERS_QUEUE)
    public void receiveHeaders(byte[] msg){LOGGER.info("headers queue messages: " + new String(msg));}
}
