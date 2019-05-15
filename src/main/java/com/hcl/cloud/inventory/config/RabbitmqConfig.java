package com.hcl.cloud.inventory.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class RabbitmqConfig {
	public static final String EXCHANGE_NAME = "pocMqTest";
	
	public static final String ROUTING_KEY = "mqpox";
	
	public static final String QUEUE_SPECIFIC_NAME = "appSpecificQueue";

/*
	public String EXCHANGE_NAME = "";
	
	public String ROUTING_KEY = "";
	
	public String QUEUE_SPECIFIC_NAME = "";*/

	@Bean
	public TopicExchange mqExchange() {
		return new TopicExchange(EXCHANGE_NAME);
	}

	@Bean
	public Queue appQueueSpecific() {
		return new Queue(QUEUE_SPECIFIC_NAME);
	}

	@Bean
	public Binding declareBindingSpecific() {
		return BindingBuilder.bind(appQueueSpecific()).to(mqExchange()).with(ROUTING_KEY);
	}

	 @Bean
	    public SimpleRabbitListenerContainerFactory jsaFactory(ConnectionFactory connectionFactory,
	            SimpleRabbitListenerContainerFactoryConfigurer configurer) {
	        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
	        configurer.configure(factory, connectionFactory);
	        factory.setMessageConverter(producerJackson2MessageConverter());
	        return factory;
	    }

	@Bean
	public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
		Jackson2JsonMessageConverter converter=new Jackson2JsonMessageConverter();
		return converter;
	}
	public static class ImplicitJsonMessageConverter extends Jackson2JsonMessageConverter {    
        public ImplicitJsonMessageConverter( ) {
            super();
        }    
        @Override
        public Object fromMessage(Message message) throws MessageConversionException {
            message.getMessageProperties().setContentType("application/json");
            return super.fromMessage(message);
        }
    }
}
