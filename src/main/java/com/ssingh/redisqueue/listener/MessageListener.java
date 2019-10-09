package com.ssingh.redisqueue.listener;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
	
	@Autowired
	private JedisConnectionFactory jedisConnectionFactory;
	
	
	@ServiceActivator(inputChannel = "studentInputChannel", outputChannel = "redisChannel")
	public Message<?> receiveFromService(Message<?> message){
		System.out.println("Received from Service");
		return message;
	}
	
	
	@ServiceActivator(inputChannel = "redisChannel")
	public void sendMessageToQueue(Message<?> message){
		RedisQueueOutboundChannelAdapter adapter = new RedisQueueOutboundChannelAdapter("Student-processing-Queue", jedisConnectionFactory);
		
		adapter.handleMessage(message);
		
	}
	
	
	@ServiceActivator(inputChannel = "redisReceiverChannel")
	public void receiveFromQueue(Message<?> message) throws InterruptedException {
		TimeUnit.SECONDS.sleep(5);
		System.out.println("received from redis queue" + message);
		TimeUnit.SECONDS.sleep(2);
		System.out.println("Request has been processed, and updated DB table");
	}
}
