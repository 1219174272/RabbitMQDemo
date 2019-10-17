package com.rabbitmq.service.impl;

import com.rabbitmq.entity.UserEntity;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.common.WorkQueuesConfiguration;
import com.rabbitmq.service.ReceviceMessageService;

import java.util.List;
import java.util.Map;

@Component
public class ReceviceMessageServiceImpl implements ReceviceMessageService {

    @Override
    @RabbitListener(queues = {WorkQueuesConfiguration.TRADE_QUEUE})
    public void receviceWorkQueues(String message) {

        System.out.println("WorkQueues:------" + message);
    }

    @Override
    @RabbitListener(queues = "#{randomFanoutQueue.name}")
    public void receviceFanoutRandom(String message) {

        System.out.println("Fanout_Randomt:------" + message);
    }

    @Override
    @RabbitListener(queues = "#{anonymousFanoutQueue.name}")
    public void receviceFanoutAnonymous(String message) {

        System.out.println("Fanout_Anonymous:------" + message);
    }

    @Override
    @RabbitListener(queues = "#{randomDirectQueue.name}")
    public void receviceDirect(String message) {

        System.out.println("Direct:------" + message);

    }

    @Override
    @RabbitListener(queues = "#{randomTopicsQueue.name}")
    public void receviceTopics(String message) {

        System.out.println("Topics:------" + message);
    }

    @Override
    @RabbitListener(queues = "#{randomHeadersQueue.name}")
    public void receviceHeaders(String message) {

        System.out.println("Headers:------" + message);

    }

    @Override
    @RabbitListener(queues = "#{randomHeadersQueueParams.name}")
    public void receviceHeadersParams(String message) {

        System.out.println("Headers_Params:------" + message);
    }

    @Override
    @RabbitListener(queues = "#{randomHeadersQueueUserEntity.name}")
    public void receviceHeadersUserEntity(UserEntity userEntity) {

        System.out.println("Headers_UserEntity:------"+ userEntity.getU_id()+"--"+userEntity.getU_name()+"--"+userEntity.getU_name_long());
    }

    @Override
    @RabbitListener(queues = "#{randomHeadersQueueListUserEntity.name}")
    public void receviceHeadersListUserEntity(List<UserEntity> list) {

        System.out.println("Headers_ListUserEntity:------"+ list);
    }

    @Override
    @RabbitListener(queues = "#{randomHeadersQueueMapUserEntity.name}")
    public void receviceHeadersListUserEntity(Map<String,UserEntity> map) {

        System.out.println("Headers_MapUserEntity:------"+ map);
    }
}