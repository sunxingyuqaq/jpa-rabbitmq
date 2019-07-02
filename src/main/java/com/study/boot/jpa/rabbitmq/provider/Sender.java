package com.study.boot.jpa.rabbitmq.provider;

import com.alibaba.fastjson.JSONObject;

import com.study.boot.jpa.config.RabbitConstants;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author Xingyu Sun
 * @date 2019/1/14 13:56
 */
@Component
public class Sender {

    private final Logger logger = LoggerFactory.getLogger(Sender.class);

    private final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        logger.info("correlationData is {}", correlationData);
        logger.info("ack is {}", ack);
        if (!ack) {
            logger.info("cause is {}", cause);
        }
    };

    private final RabbitTemplate.ReturnCallback returnCallback = (message, replyCode, replyText, exchange, routingKey) -> {
        logger.info("message is {}", message);
        logger.info("replyCode is {}", replyCode);
        logger.info("replyText is {}", replyText);
        logger.info("exchange is {}", exchange);
        logger.info("routingKey is {}", routingKey);
    };

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg() {
        logger.info("{} send msg start ...", LocalDateTime.now());
        JSONObject object = new JSONObject();
        object.put("name", "sxy");
        object.put("age", 11);
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString());
        rabbitTemplate.setConfirmCallback(this.confirmCallback);
        rabbitTemplate.setReturnCallback(this.returnCallback);
        rabbitTemplate.convertAndSend(RabbitConstants.Exchange.TEST_EXCHANGE, "", object, correlationData);
        logger.info("{} send msg end ...", LocalDateTime.now());
    }

    public Object sendMsgAndReturn() {
        logger.info("{} send msg start ...", LocalDateTime.now());
        JSONObject object = new JSONObject();
        object.put("name", "sxy");
        object.put("age", 11);
        object.put("id", "123456");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(UUID.randomUUID().toString() + "123456");
        rabbitTemplate.setConfirmCallback(this.confirmCallback);
        rabbitTemplate.setReturnCallback(this.returnCallback);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType("application/json");
        Message message = new Message(object.toString().getBytes(),messageProperties);
        Object o = rabbitTemplate.convertSendAndReceive(RabbitConstants.Exchange.EXCHANGE,
                RabbitConstants.RoutingKey.KEY_1, message, correlationData);
        logger.info("{} send msg over ...", LocalDateTime.now());
        logger.info("-----------------------------------------");
        byte[] content = (byte[]) o;
        Assert.assertNotNull("not null",content);
        String result = new String(content);
        logger.info("receive msg 【{}】from sever ...", result);
        logger.info("-----------------------------------------");
        return result;
    }

}
