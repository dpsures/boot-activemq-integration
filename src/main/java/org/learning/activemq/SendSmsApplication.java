package org.learning.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath*:/spring/si-config.xml")
public class SendSmsApplication {

	public static void main(String args[]) throws JMSException, InterruptedException{
		ConfigurableApplicationContext context = SpringApplication.run(SendSmsApplication.class, args);
		ConnectionFactory connectionFactory = (ConnectionFactory) context.getBean("jmsConnectionFactory");
		
		String queueName = ActiveMQConfig.SEND_SMS;
		
		Connection connection = connectionFactory.createConnection();
		connection.start();
		
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue(queueName);
		MessageProducer messageProducer = session.createProducer(destination);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		
		TextMessage text = session.createTextMessage();
		for(int i = 0; i <= 100; i++){
			text.setText("Suresh Kumar Devaraj - Technology Architect - "+i);
			messageProducer.send(text);
		}
		
		connection.stop();
	}
}
