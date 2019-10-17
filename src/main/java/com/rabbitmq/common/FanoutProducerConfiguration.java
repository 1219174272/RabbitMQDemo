package com.rabbitmq.common;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout 生产端配置 路由器模式
 */
@Configuration
public class FanoutProducerConfiguration {

    public static final String DEFAULT_FANOUT_EXCHANGE = "prontera.fanout";

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public FanoutExchange fanoutExchange() {

        return new FanoutExchange(DEFAULT_FANOUT_EXCHANGE, true, true);
    }
}
