package com.study.boot.jpa.rabbitmq.consumer;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.study.boot.jpa.config.RabbitConstants;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author Xingyu Sun
 * @date 2019/1/14 13:56
 */
@Component
public class Receiver {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(queues = {RabbitConstants.Queue.TEST_QUEUE})
    @RabbitHandler
    public void receive(Channel channel, Message message) throws Exception {
        logger.info("*************************START***********************");
        logger.info("{} receive msg start ...", LocalDateTime.now());
        logger.info("channel is {}", channel.toString());
        logger.info("消费端Payload is {}", new String((byte[]) message.getPayload(), StandardCharsets.UTF_8));
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK   批量签收false
        Assert.assertNotNull("", deliveryTag);
        channel.basicAck(deliveryTag, false);
        logger.info("{} receive msg end ...", LocalDateTime.now());
        logger.info("***************************END***********************");
    }

    @RabbitListener(queues = {RabbitConstants.Queue.QUEUE})
    @RabbitHandler
    public String receiveAndSend(Channel channel, Message message) throws Exception {
        logger.info("*************************START***********************");
        logger.info("{} receive msg start ...", LocalDateTime.now());
        logger.info("channel is {}", channel.toString());
        String result = new String((byte[]) message.getPayload(), StandardCharsets.UTF_8);
        logger.info("消费端Payload is {}", result);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK   批量签收false
        Assert.assertNotNull("", deliveryTag);
        Map object = JSON.parseObject(result, Map.class);
        JSONObject jsonObject = new JSONObject();
        if (object != null) {
            Object id = object.get("id");
            String userId = "123456";
            if (userId.equals(id)) {
                logger.info("get user id {}", id);
                jsonObject.put("id", id);
                jsonObject.put("name", "rabbitmq");
                jsonObject.put("sex", "man");
            }
        }
        channel.basicAck(deliveryTag, false);
        logger.info("{} receive msg end ...", LocalDateTime.now());
        logger.info("***************************END***********************");
        return jsonObject.toString();
    }
}
