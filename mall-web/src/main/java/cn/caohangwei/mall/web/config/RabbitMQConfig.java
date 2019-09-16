package cn.caohangwei.mall.web.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE = "queue";
    public static final String SPIKE_QUEUE = "spike.queue";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String TOPIC_EXCHANGE = "topicExchange";
    public static final String ROUTING_KEY1 = "topic.key1";
    public static final String ROUTING_KEY2 = "topic.#";
    public static final String FANOUT_EXCHANGE = "fanoutExchange";
    public static final String HEADER_EXCHANGE = "headersExchange";
    public static final String HEADERS_QUEUE = "headers.queue1";


    @Bean
    public Queue spikeQueue(){
        return new Queue(SPIKE_QUEUE,true);
    }
    /**
     * Direct 模式 交换机Exchange
     */
    @Bean
    public Queue queue(){
        return new Queue(QUEUE,true);
    }

    /**
     * Topic 模式 交换机Exchange
     */
    @Bean
    public Queue topicQueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }

    @Bean
    public Queue topicQueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(topicExchange()).with(ROUTING_KEY1);
    }

    @Bean
    public Binding topicBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(topicExchange()).with(ROUTING_KEY2);
    }

    /**
     * Fanout模式 交换机Exchange模式
     */
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    public Binding FanoutBinding1(){
        return BindingBuilder.bind(topicQueue1()).to(fanoutExchange());
    }

    @Bean
    public Binding FanoutBinding2(){
        return BindingBuilder.bind(topicQueue2()).to(fanoutExchange());
    }

    /**
     * Header 模式 交换机Exchange
     */
    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange(HEADER_EXCHANGE);
    }

    @Bean
    public Queue headersQueue1(){
        return new Queue(HEADERS_QUEUE,true);
    }

    @Bean
    public Binding headersBinding1(){
        HashMap<String,Object> hashMap = new HashMap<String,Object>();
        hashMap.put("header1","value1");
        hashMap.put("header2","value2");
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAll(hashMap).match();
    }
}
