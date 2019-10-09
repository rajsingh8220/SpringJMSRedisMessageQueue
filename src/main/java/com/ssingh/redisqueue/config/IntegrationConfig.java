package com.ssingh.redisqueue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;

@Configuration
@EnableIntegration
@IntegrationComponentScan("com.ssingh.redisqueue")
public class IntegrationConfig {
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		//factory.setHostName(hostName);
		return factory;
	}
	
	
	@Bean
	public DirectChannel receiverChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public RedisQueueMessageDrivenEndpoint consumerEndpoint() {
		RedisQueueMessageDrivenEndpoint endPoint = new RedisQueueMessageDrivenEndpoint("Student-processing-Queue", jedisConnectionFactory());
		
		endPoint.setOutputChannelName("redisReceiverChannel");
		return endPoint;
	}
}
