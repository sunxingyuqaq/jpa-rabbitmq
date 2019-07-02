package com.study.boot.jpa.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Xingyu Sun
 * @date 2019/1/14 13:45
 */
@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private int port;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private boolean publisherConfirms;
    @Value("${spring.rabbitmq.publisher-returns}")
    private boolean publisherReturns;

    @Bean
    public Queue queueTest() {
        return new Queue(RabbitConstants.Queue.TEST_QUEUE,true);
    }

    @Bean
    public Queue queueYu() {
        return new Queue(RabbitConstants.Queue.QUEUE,true);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitConstants.Exchange.TEST_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(RabbitConstants.Exchange.EXCHANGE);
    }

    @Bean
    public Binding bindingTest(@Qualifier("queueTest") Queue queueTest,
                               @Qualifier("fanoutExchange") FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueTest).to(fanoutExchange);
    }

    @Bean
    public Binding bindingYu(@Qualifier("queueYu") Queue queueYu,
                             @Qualifier("topicExchange") TopicExchange topicExchange) {
        return BindingBuilder.bind(queueYu).to(topicExchange).with(RabbitConstants.RoutingKey.KEY_1);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate(factory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        template.setMandatory(true);
        return template;
    }
}
