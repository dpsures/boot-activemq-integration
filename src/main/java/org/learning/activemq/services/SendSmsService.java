package org.learning.activemq.services;

import org.springframework.stereotype.Service;

@Service
public class SendSmsService {

	public void sendMessage(String msg){
		System.out.println(msg+ " - this is send");
	}
}
