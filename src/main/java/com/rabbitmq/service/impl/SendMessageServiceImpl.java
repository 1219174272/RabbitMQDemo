package com.rabbitmq.service.impl;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.common.*;
import com.rabbitmq.entity.UserEntity;
import com.rabbitmq.service.SendMessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    public AmqpTemplate rabbitTemplate;

    /* 生产者(Producer) P
     * 交换机(Exchange) X
     * 消费者(Consumer) C
     */

    @Override
    public String sendWorkQueues(String message) {

        // 无论怎样只要能注入AmqpTemplate就行
        rabbitTemplate.convertAndSend(WorkQueuesConfiguration.DEFAULT_DIRECT_EXCHANGE,
            WorkQueuesConfiguration.TRADE_ROUTE_KEY, message);

        return "200";
    }

    @Override
    public String sendFanout(String message) {

        // s1(Route Key) "fanout"在Fanout Exchange中是会被忽略的 可以选择不填入参数
        // rabbitTemplate.convertAndSend(FanoutConsumerConfiguration.DEFAULT_FANOUT_EXCHANGE, message);
        rabbitTemplate.convertAndSend(FanoutConsumerConfiguration.DEFAULT_FANOUT_EXCHANGE, "fanout", message);

        return "200";
    }

    @Override
    public String sendDirect(String message) {

        rabbitTemplate.convertAndSend(DirectConsumerConfiguration.DEFAULT_DIRECT_EXCHANGE, "key", "key-*-" + message);
        rabbitTemplate.convertAndSend(DirectConsumerConfiguration.DEFAULT_DIRECT_EXCHANGE, "direct",
            "direct-*-" + message);
        rabbitTemplate.convertAndSend(DirectConsumerConfiguration.DEFAULT_DIRECT_EXCHANGE, "routing",
            "routing-*-" + message);

        return "200";
    }

    @Override
    public String sendTopics(String message) {

        rabbitTemplate.convertAndSend(TopicsConsumerConfiguration.DEFAULT_TOPIC_EXCHANGE, "a.a.topic",
            "a.a.topic-*-" + message);

        rabbitTemplate.convertAndSend(TopicsConsumerConfiguration.DEFAULT_TOPIC_EXCHANGE, "topic.a.a",
            "topic.a.a-*-" + message);

        rabbitTemplate.convertAndSend(TopicsConsumerConfiguration.DEFAULT_TOPIC_EXCHANGE, "test.topic",
            "test.topic-*-" + message);

        return "200";
    }

    @Override
    public String sendHeaders(String message) {
        String jsonString = "";
        String jsonUserEntity = "";
        String jsonListUserEntity = "";
        String jsonMapUserEntity = "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            jsonString = objectMapper.writeValueAsString(message);

            UserEntity userEntity = new UserEntity();
            userEntity.setU_id(1);
            userEntity.setU_name("name");
            userEntity.setU_name_long("nameLong");
            jsonUserEntity = objectMapper.writeValueAsString(userEntity);

            List<UserEntity> userEntityList = new ArrayList<>();
            userEntityList.add(userEntity);
            userEntityList.add(new UserEntity().setU_id(2).setU_name("name2").setU_name_long("nameLong2"));
            jsonListUserEntity= objectMapper.writeValueAsString(userEntityList);

            jsonMapUserEntity = objectMapper.writeValueAsString(new HashMap<>().put("user",userEntityList));

        } catch (Exception e) {
            e.printStackTrace();
        }

        MessageProperties messageProperties = new MessageProperties();
        // 解决： org.springframework.amqp.support.converter.Jackson2JsonMessageConverter - Could not convert incoming
        // message with content-type [application/octet-stream], 'json' keyword missing.
        messageProperties.setContentType("application/json");

        // Arguments 单
        /*{
            messageProperties.setHeader("key", "value");
            rabbitTemplate.convertAndSend(HeadersConsumerConfiguration.DEFAULT_HEADERS_EXCHANGE, "",
                new Message(jsonString.getBytes(), messageProperties));
        }*/

        // Arguments 多
        /*{
            messageProperties.setHeader("key", "value");
            messageProperties.setHeader("key1", "value1");
            rabbitTemplate.convertAndSend(HeadersConsumerConfiguration.DEFAULT_HEADERS_EXCHANGE, "",
                new Message(jsonString.getBytes(), messageProperties));
        }*/

        // Arguments Entity
        /*{
            messageProperties.getHeaders().put("__TypeId__","UserEntity");
            messageProperties.setHeader("keyU", "valueU");
            rabbitTemplate.convertAndSend(HeadersConsumerConfiguration.DEFAULT_HEADERS_EXCHANGE, "",
            new Message(jsonUserEntity.getBytes(), messageProperties));
        }*/

        // Arguments List
        /*{
            // TypeId的值可以是java对象全称，也可以是映射的key
            // 发送 List<User> JSON 数据
            // messageProperties.getHeaders().put("__TypeId__","UserEntity");
            messageProperties.setHeader("keyL", "valueL");
            rabbitTemplate.convertAndSend(HeadersConsumerConfiguration.DEFAULT_HEADERS_EXCHANGE, "",
            new Message(jsonListUserEntity.getBytes(), messageProperties));
        }*/

        // Map
        {
            // 发送 Map JSON 数据 __KeyTypeId__，__ContentTypeId__
            messageProperties.getHeaders().put("__KeyTypeId__","java.lang.String");
            messageProperties.getHeaders().put("__ContentTypeId__","UserEntity");
            messageProperties.setHeader("keyM", "valueM");
            rabbitTemplate.convertAndSend(HeadersConsumerConfiguration.DEFAULT_HEADERS_EXCHANGE, "",
                    new Message(jsonMapUserEntity.getBytes(), messageProperties));
        }

        return "200";
    }
}
