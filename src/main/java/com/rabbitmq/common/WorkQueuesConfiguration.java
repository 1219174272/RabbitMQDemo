package com.rabbitmq.common;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Work queues
 *
 * 生产者与消费者的配置一样, 因为监听的是同一个队列, 所以队列名要先约定好
 */
@Configuration
public class WorkQueuesConfiguration {

    public static final String DEFAULT_DIRECT_EXCHANGE = "prontera.direct";
    public static final String TRADE_QUEUE = "funds";
    public static final String TRADE_ROUTE_KEY = "trading";

    @Bean
    public DirectExchange pronteraExchange() {

        return new DirectExchange(DEFAULT_DIRECT_EXCHANGE, true, true);
    }

    @Bean
    public Queue tradeQueue() {

        return new Queue(TRADE_QUEUE, true, false, true);
    }

    @Bean
    public Binding tradeBinding() {

        return BindingBuilder.bind(tradeQueue()).to(pronteraExchange()).with(TRADE_ROUTE_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

}
