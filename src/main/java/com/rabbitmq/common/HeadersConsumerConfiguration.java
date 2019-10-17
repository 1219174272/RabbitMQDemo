package com.rabbitmq.common;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Headers 消费者配置
 */
@Configuration
public class HeadersConsumerConfiguration {

    public static final String DEFAULT_HEADERS_EXCHANGE = "prontera.headers";
    public static final String FANOUT_QUEUE = "headers-" + UUID.randomUUID();

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public HeadersExchange headersExchange() {

        return new HeadersExchange(DEFAULT_HEADERS_EXCHANGE, true, true);
    }

    /**
     * Arguments 单参数
     * @return
     */
    @Bean
    public Queue randomHeadersQueue() {

        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    /**
     * Arguments 多参数
     * @return
     */
    @Bean
    public Queue randomHeadersQueueParams() {

        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    /**
     * Arguments 单参数 + Entity
     * @return
     */
    @Bean
    public Queue randomHeadersQueueUserEntity() {

        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    /**
     * Arguments 单参数 + List
     * @return
     */
    @Bean
    public Queue randomHeadersQueueListUserEntity() {

        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    /**
     * Arguments 单参数 + Map
     *
     * @return
     */
    @Bean
    public Queue randomHeadersQueueMapUserEntity() {

        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    @Bean
    public Binding headersBinding() {

        return BindingBuilder.bind(randomHeadersQueue()).to(headersExchange()).where("key").matches("value");
    }

    @Bean
    public Binding headersBindingParams() {

        Map<String,Object> map = new HashMap<>();
        map.put("key","value");
        map.put("key1","value1");

        // 匹配有两种方式all(必须所有的键值对匹配)和any(只要有一个键值对匹配即可)
        return BindingBuilder.bind(randomHeadersQueueParams()).to(headersExchange()).whereAll(map).match();
    }

    @Bean
    public Binding headersBindingUserEntity() {

        return BindingBuilder.bind(randomHeadersQueueUserEntity()).to(headersExchange()).where("keyU").matches("valueU");
    }

    @Bean
    public Binding headersBindingListUserEntity() {

        return BindingBuilder.bind(randomHeadersQueueListUserEntity()).to(headersExchange()).where("keyL").matches("valueL");
    }

    @Bean
    public Binding headersBindingMapUserEntity() {

        return BindingBuilder.bind(randomHeadersQueueMapUserEntity()).to(headersExchange()).where("keyM").matches("valueM");
    }
}