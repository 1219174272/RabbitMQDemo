package com.rabbitmq.service;

/**
 * 发送
 */
public interface SendMessageService {

    /**
     * Work Queues
     * 如果先启动生产者, 那么要先与RabbitMQ进行过通讯之后, 才会在RabbitMQ Management处看到Exchange与Queue. 如果是先启动消费者, 那么@RabbitListener会自动监听队列,
     * 所以可以直接在RabbitMQ Management处看到我们所定义的组件
     * 
     * @param message
     */
    String sendWorkQueues(String message);

    /**
     * Fanout (路由器模式)
	 * Fanout 模式类似于广播  不用设置路由  你只需要简单的将队列绑定到交换机上  一个消息发送到交换机就会被转发到与该交换机绑定的所有队列上
     *
     * @param message
     */
    String sendFanout(String message);

    /**
     * Direct (指定模式，RabbitMQ的默认模式)
	 * Direct 模式就是需要指定路由（Routing）  需要将一个队列绑定到交换机上  要求该消息与一个特定的路由键完全匹配  这是一个完整的匹配
     *
     * @param message
     */
    String sendDirect(String message);

    /**
     * Topics (主题)
	 * Topic 模式是根据 Config 中设置的 RoutineKey 还有发送消息时候的 topic 来判断是否会传输 生产端发送请求  其实就是指定Exchange与Route Key
     *
     * @param message
     */
    String sendTopics(String message);

    /**
     * Headers
	 * Headers 是一个键值对 可以定义成 Hashtable。
     * 发送者在发送的时候定义一些键值对  接收者也可以再绑定时候传入一些键值对  两者匹配的话  则对应的队列就可以收到消息  匹配有两种方式all(必须所有的键值对匹配)和any(只要有一个键值对匹配即可)
     *
     * @param message
     */
    String sendHeaders(String message);
}
