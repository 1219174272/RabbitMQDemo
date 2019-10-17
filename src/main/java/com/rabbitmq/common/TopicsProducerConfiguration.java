package com.rabbitmq.common;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topics 生产端配置  主题
 */
@Configuration
public class TopicsProducerConfiguration {

    public static final String DEFAULT_TOPIC_EXCHANGE = "prontera.topic";

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange() {

        return new TopicExchange(DEFAULT_TOPIC_EXCHANGE, true, true);
    }

}
