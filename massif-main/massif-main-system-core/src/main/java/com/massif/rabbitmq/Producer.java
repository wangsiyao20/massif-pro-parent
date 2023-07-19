package com.massif.rabbitmq;

import com.massif.rabbitmq.constant.AmqpConstant;
import com.massif.rabbitmq.utils.ConnectRabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
public class Producer {

    /** Logger available to subclasses. */
    protected static final Log logger = LogFactory.getLog(Producer.class);

    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = ConnectRabbitMqUtil.getChannel();

        /**
         * 生成一个队列
         * 参数：
         *      1、队列名称
         *      2、队列里的消息是否持久化（到磁盘） 默认情况下消息存储在内存中
         *      3、该队列是否只供一个消费者进行消费，是否进行消息共享，true表示只能一个消费者，false表示可以多个消费者
         *      4、是否自动删除，最后一个消费者断开连接后，该队列是否自动删除
         *      5、其他参数
         */
        channel.queueDeclare(AmqpConstant.QUEUE_NAME,
                false,
                false,
                false,
                null
                );

        /**
         * 发送一个消息
         * 参数：
         *      1、发送到哪个交换机（交换机名）
         *      2、路由键，即本次队列名称
         *      3、其他参数信息（比如消息持久化 MessageProperties.PERSISTENT_TEXT_PLAIN）
         *      4、发送的消息（转换为字节）
         */
        channel.basicPublish("", AmqpConstant.QUEUE_NAME, null, "我是要发送的消息".getBytes());
        logger.info("消息发送成功...");

    }

}
