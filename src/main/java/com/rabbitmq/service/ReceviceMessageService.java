package com.rabbitmq.service;

import com.rabbitmq.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 接收
 */
public interface ReceviceMessageService {

    /**
     * Queues
     *
     * @param message
     */
    void receviceWorkQueues(String message);

    /**
     * Fanout  自定义队列
     *
     * @param message
     *
     */
    void receviceFanoutRandom(String message);

    /**
     * Fanout  Anonymous队列
     *
     * @param message
     *
     */
    void receviceFanoutAnonymous(String message);

    /**
     * Direct 指定路由模式
     *
     * @param message
     */
    void receviceDirect(String message);

    /**
     * Topics 匹配路由模式
     *
     * @param message
     */
    void receviceTopics(String message);

    /**
     * Arguments 单
     *
     * @param message
     */
    void receviceHeaders(String message);

    /**
     * Arguments 多
     *
     * @param message
     */
    void receviceHeadersParams(String message);

    /**
     * Arguments 实体类
     *
     * @param userEntity
     */
    void receviceHeadersUserEntity(UserEntity userEntity);

    /**
     * Arguments List
     *
     * @param list
     */
    void receviceHeadersListUserEntity(List<UserEntity> list);

    /**
     * Arguments Map
     *
     * @param map
     */
    void receviceHeadersListUserEntity(Map<String,UserEntity> map);
}
