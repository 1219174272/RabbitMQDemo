package com.rabbitmq.common;

import java.util.UUID;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout 消费者配置
 */
@Configuration
public class FanoutConsumerConfiguration {

    public static final String DEFAULT_FANOUT_EXCHANGE = "prontera.fanout";
    public static final String FANOUT_QUEUE = "fanout-" + UUID.randomUUID();

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public FanoutExchange fanoutExchange() {

        return new FanoutExchange(DEFAULT_FANOUT_EXCHANGE, true, true);
    }

    /**
     * 持久性 自动删除 惰性 排他性
     */
    @Bean
    public Queue randomFanoutQueue() {

        return new Queue(FANOUT_QUEUE, true, false, true);
    }

    /**
     * 匿名 不耐用 独占 自动删除的队列
     */
    @Bean
    public Queue anonymousFanoutQueue() {

        return new AnonymousQueue();
    }

    @Bean
    public Binding fanoutBinding(Queue randomFanoutQueue) {

        return BindingBuilder.bind(randomFanoutQueue).to(fanoutExchange());
    }

    @Bean
    public Binding fanoutBinding1() {

        return BindingBuilder.bind(anonymousFanoutQueue()).to(fanoutExchange());
    }
}
