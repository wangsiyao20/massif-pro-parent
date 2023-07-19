package com.massif.rabbitmq.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectRabbitMqUtil {

    public static Channel getChannel() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("172.23.140.124");
        connectionFactory.setUsername("admin");
        connectionFactory.setPassword("admin");
        // 创建连接
        Connection connection = connectionFactory.newConnection();
        // 创建通信信道
        return connection.createChannel();
    }

}
