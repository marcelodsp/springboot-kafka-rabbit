package br.com.marcelodsp.rabbitteste.config;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import br.com.marcelodsp.rabbitteste.entity.Empresa;
import br.com.marcelodsp.rabbitteste.entity.Funcionario;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class KafkaConsumerConfig {

	private final KafkaProperties kafkaProperties;

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Funcionario> funcionarioListenerContainerFactory() {
		var consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
				new StringDeserializer(), new JsonDeserializer<>(Funcionario.class));
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Funcionario>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Empresa> empresaListenerContainerFactory() {
		var consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties(),
				new StringDeserializer(), new JsonDeserializer<>(Empresa.class));
		var factory = new ConcurrentKafkaListenerContainerFactory<String, Empresa>();
		factory.setConsumerFactory(consumerFactory);
		return factory;
	}

}
