package com.testproject.registration.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String ACCEPT_REGISTRATION_QUEUE = "qu.registration.accept";
    public static final String ACCEPT_REGISTRATION_EXCHANGE = "ex.registration.accept";


    @Bean
    public ConnectionFactory connectionFactory(){
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public Queue acceptRegistrationQueue(){
        return QueueBuilder.durable(ACCEPT_REGISTRATION_QUEUE).build();
    }

    @Bean
    public TopicExchange acceptRegistrationExchange(){
        return new TopicExchange(ACCEPT_REGISTRATION_EXCHANGE);
    }

    @Bean
    public Binding bindingAcceptRegistrationExchange(Queue acceptRegistrationQueue, TopicExchange acceptRegistrationExchange){
        return BindingBuilder.bind(acceptRegistrationQueue).to(acceptRegistrationExchange).with(ACCEPT_REGISTRATION_QUEUE);
    }



}
