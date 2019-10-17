package com.rabbitmq.common;

import com.rabbitmq.entity.UserEntity;
import com.sun.org.apache.xalan.internal.xsltc.runtime.MessageHandler;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

/**
 * 在消息header中 增加一个TypeId,value就是具体的java对象（全类名）,一定是消费者所在系统的java对象全称
 * 但耦合性太高  需此类
 */
public class RabbitMQConfiguration {

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames("com.rabbitmq");

        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageHandler());
        //指定Json转换器
        Jackson2JsonMessageConverter jackson2JsonMessageConverter =new Jackson2JsonMessageConverter();

        //消费端配置映射
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("UserEntity", UserEntity.class);

        DefaultJackson2JavaTypeMapper jackson2JavaTypeMapper = new DefaultJackson2JavaTypeMapper();
        jackson2JavaTypeMapper.setIdClassMapping(idClassMapping);

        System.out.println("在jackson2JsonMessageConverter转换器中指定映射配置");
        jackson2JsonMessageConverter.setJavaTypeMapper(jackson2JavaTypeMapper);
        adapter.setMessageConverter(jackson2JsonMessageConverter);

        //设置处理器的消费消息的默认方法
        // adapter.setDefaultListenerMethod("onMessage");
        container.setMessageListener(adapter);

        return container;
    }
}