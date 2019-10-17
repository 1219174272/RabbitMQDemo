package com.rabbitmq.common;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct 消费者配置
 */
@Configuration
public class DirectConsumerConfiguration {

    public static final String DEFAULT_DIRECT_EXCHANGE = "prontera.direct";

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DEFAULT_DIRECT_EXCHANGE, true, true);
    }
}