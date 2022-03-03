package br.com.marcelodsp.kafkateste.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "my-config")
public class MyConfig {

	private Kafka kafka = new Kafka();
	private Rabbit rabbit = new Rabbit();

	@Data
	public static class Kafka {
		private String topicName;
	}

	@Data
	public static class Rabbit {
		private String exchangeName;
		private String queueName;
		//private String routingKey;
	}

}
