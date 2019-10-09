package com.ssingh.redisqueue.intfc;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {
	
	@Gateway(requestChannel = "studentInputChannel")
	public <S> void sendMessage(S request);
}
