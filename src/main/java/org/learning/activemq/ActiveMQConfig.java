package org.learning.activemq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ActiveMQConfig {

	public static final String SEND_SMS = "sendSms.queue";
	
	@Bean
	public Queue sendSmsQueue(){
		return new ActiveMQQueue(SEND_SMS);
	}
}
