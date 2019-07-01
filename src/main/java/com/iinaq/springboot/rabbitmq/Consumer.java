package com.iinaq.springboot.rabbitmq;

import com.iinaq.springboot.config.RabbitmqConfig;
import com.iinaq.springboot.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;

import java.awt.print.Book;
import java.io.IOException;

@Component
public class Consumer {

    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    @RabbitListener(queues = {RabbitmqConfig.DEFAULT_USER_QUEUE})
    public void listenerAutoAck(User user, Message message, Channel channel){
// TODO 如果手动ACK,消息会被监听消费,但是消息在队列中依旧存在,如果 未配置 acknowledge-mode 默认是会在消费完毕后自动ACK掉
        final long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            log.info("[listenerAutoAck 监听的消息] - [{}]", user.toString());
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, false);
        } catch (IOException e) {
            try {
                // TODO 处理失败,重新压入MQ
                channel.basicRecover();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    @RabbitListener(queues = {RabbitmqConfig.MANUAL_USER_QUEUE})
    public void listenerManualAck(User user, Message message, Channel channel) {
        log.info("[listenerManualAck 监听的消息] - [{}]", user.toString());
        try {
            // TODO 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            // TODO 如果报错了,那么我们可以进行容错处理,比如转移当前消息进入其它队列
        }
    }
}
