package com.rabbitmq.common;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Headers 生产者配置  是一个键值对
 */
@Configuration
public class HeadersProducerConfiguration {

    public static final String DEFAULT_HEADERS_EXCHANGE = "prontera.headers";

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public HeadersExchange headersExchange() {

        return new HeadersExchange(DEFAULT_HEADERS_EXCHANGE, true, true);
    }
}