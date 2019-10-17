package com.rabbitmq.common;

import java.util.UUID;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Direct 生产者配置 指定模式
 */
@Configuration
public class DirectProducerConfiguration {

    public static final String DEFAULT_DIRECT_EXCHANGE = "prontera.direct";
    public static final String DIRECT_QUEUE = "direct-" + UUID.randomUUID();

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {

        return new DirectExchange(DEFAULT_DIRECT_EXCHANGE, true, true);
    }

    @Bean
    public Queue randomDirectQueue() {

        return new Queue(DIRECT_QUEUE, true, false, true);
    }

    @Bean
    public Queue randomDirectQueueRouting() {

        return new Queue(DIRECT_QUEUE, true, false, true);
    }

    @Bean
    public Binding directBinding() {

        return BindingBuilder.bind(randomDirectQueue()).to(directExchange()).with("direct");
    }

    /**
     * 当randomDirectQueueRouting() 方法 使用注解@Bean 会自动赋予参数
     */
    @Bean
    public Binding directBindingRouting(Queue randomDirectQueueRouting) {

        return BindingBuilder.bind(randomDirectQueueRouting).to(directExchange()).with("routing");
    }
}