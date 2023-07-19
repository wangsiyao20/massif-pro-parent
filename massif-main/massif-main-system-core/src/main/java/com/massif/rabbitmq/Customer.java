package com.massif.rabbitmq;


import com.massif.rabbitmq.constant.AmqpConstant;
import com.massif.rabbitmq.utils.ConnectRabbitMqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.Delivery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消费者
 */
public class Customer {

    /** Logger available to subclasses. */
    protected static final Log logger = LogFactory.getLog(Customer.class);

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = ConnectRabbitMqUtil.getChannel();


        /**
         * 消费者消费消息
         * 参数：
         *      1、要取的消息的队列名
         *      2、消费成功后是否要自动应答（true自动应答/false手动应答）
         *      3、消费未成功消费的回调
         *      4、消费者取消消费的回调
         */
        channel.basicConsume(AmqpConstant.QUEUE_NAME,
                true,
                (consumerTag, message) -> logger.info(new String(message.getBody())),
                consumerTag -> logger.info("取出消息被中断...")
                );

    }

}
