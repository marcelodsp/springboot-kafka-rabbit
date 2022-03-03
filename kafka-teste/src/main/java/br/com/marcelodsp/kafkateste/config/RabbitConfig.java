package br.com.marcelodsp.kafkateste.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class RabbitConfig {

	public final static String FUNCIONARIO_QUEUE = "funcionario.queue";
	public final static String EMPRESA_QUEUE = "empresa.queue";
	private final static String TIPE_ID_HEADER = "__TypeId__";

	private final MyConfig config;
	private final CachingConnectionFactory cachingConnectionFactory;

	@Bean
	RabbitTemplate rabbitTemplate() {
		RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
		template.setMessageConverter(messageConverter());
		return template;
	}

	@Bean
	Jackson2JsonMessageConverter messageConverter() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return new Jackson2JsonMessageConverter(objectMapper);
	}

	@Bean
	HeadersExchange headerExchange() {
		return new HeadersExchange(config.getRabbit().getExchangeName());
	}

	@Bean
	Queue funcionarioQueue() {
		return new Queue(FUNCIONARIO_QUEUE, false);
	}

	@Bean
	Queue empresaQueue() {
		return new Queue(EMPRESA_QUEUE, false);
	}

	@Bean
	Binding funcionarioBinding(Queue funcionarioQueue, HeadersExchange exchange) {
		return BindingBuilder
				.bind(funcionarioQueue)
				.to(exchange)
				.where(TIPE_ID_HEADER)
				.matches("br.com.marcelodsp.rabbitteste.entity.Funcionario");
	}

	@Bean
	Binding empresaBinding(Queue empresaQueue, HeadersExchange exchange) {
		return BindingBuilder
				.bind(empresaQueue)
				.to(exchange)
				.where(TIPE_ID_HEADER)
				.matches("br.com.marcelodsp.rabbitteste.entity.Empresa");
	}

//	@Bean
//	public TopicExchange topicExchange() {
//		return new TopicExchange(config.getRabbit().getExchangeName());
//	}
//
//	@Bean
//	public DirectExchange directExchange() {
//		return new DirectExchange(config.getRabbit().getExchangeName());
//	}

//	@Bean
//	MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory ) {
//
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//		container.setConnectionFactory(connectionFactory);
//		container.setMessageListener(new RabbitConsumer());
//		container.setQueueNames(config.getRabbit().getQueueName());
//		return container;
//
//	}

}
