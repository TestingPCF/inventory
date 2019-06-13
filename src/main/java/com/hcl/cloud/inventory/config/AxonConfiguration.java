package com.hcl.cloud.inventory.config;

import org.axonframework.amqp.eventhandling.DefaultAMQPMessageConverter;
import org.axonframework.amqp.eventhandling.spring.SpringAMQPMessageSource;
import org.axonframework.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import com.rabbitmq.client.Channel;

@Configuration
public class AxonConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(AxonConfiguration.class);

    @Bean
    public SpringAMQPMessageSource complaintEventsMethod(Serializer serializer) {
        return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {

            @RabbitListener(queues = "${axon.amqp.exchange}")
            @Transactional
            @Override
            public void onMessage(Message message, Channel channel){
                LOG.info("Event Received: {}", message.getBody().toString());
                LOG.info("Event Received: {}", message.toString());
                super.onMessage(message, channel);
            }
        };
    }
}
