package cn.caohangwei.mall.web.base;

import cn.caohangwei.mall.common.util.JSONUtil;
import cn.caohangwei.mall.web.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQSender.class);

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send(Object msg){
        msg = JSONUtil.beanToString(msg);
        LOGGER.info("send message: " + msg);
        amqpTemplate.convertAndSend(RabbitMQConfig.QUEUE, msg);
    }

    public void send(SpikeMessages msg){
        String message = JSONUtil.beanToString(msg);
        LOGGER.info("send message: " + message);
        amqpTemplate.convertAndSend(RabbitMQConfig.SPIKE_QUEUE, message);
    }

    public void sendTopic(Object msg){
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY1,msg + "1");
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, RabbitMQConfig.ROUTING_KEY2,msg + "2");
    }

    public void sendFanout(Object msg){
        amqpTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE,"",msg);
    }

    public void sendHeaders(Object msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("header1","value1");
        messageProperties.setHeader("header2","value2");
        Message message = new Message(JSONUtil.beanToString(msg).getBytes(),messageProperties);
        amqpTemplate.convertAndSend(RabbitMQConfig.HEADER_EXCHANGE,"",message);
    }
}
