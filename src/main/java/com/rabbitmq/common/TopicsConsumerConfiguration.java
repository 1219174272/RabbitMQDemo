package com.rabbitmq.common;

import java.util.UUID;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topics 消费者配置
 */
@Configuration
public class TopicsConsumerConfiguration {

    public static final String DEFAULT_TOPIC_EXCHANGE = "prontera.topic";
    public static final String TOPIC_QUEUE = "topic-" + UUID.randomUUID();

    /**
     * 符号 # 匹配一个或多个词，符号 * 匹配不多不少一个词
     */
    // public static final String TOPIC_ROUTE_KEY = "#.#";
    public static final String TOPIC_ROUTE_KEY = "topic.#";
    public static final String TOPIC_ROUTE_KEY_TEMP = "*.topic";

    @Bean
    public MessageConverter messageConverter() {

        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public TopicExchange topicExchange() {

        return new TopicExchange(DEFAULT_TOPIC_EXCHANGE, true, true);
    }

    @Bean
    public Queue randomTopicsQueue() {

        return new Queue(TOPIC_QUEUE, true, false, true);
    }

    @Bean
    public Queue randomTopicsQueueTemp() {

        return new Queue(TOPIC_QUEUE, true, false, true);
    }

    @Bean
    public Binding topicBinding() {

        return BindingBuilder.bind(randomTopicsQueue()).to(topicExchange()).with(TOPIC_ROUTE_KEY);
    }

    @Bean
    public Binding topicBindingTemp() {

        return BindingBuilder.bind(randomTopicsQueueTemp()).to(topicExchange()).with(TOPIC_ROUTE_KEY_TEMP);
    }
}
