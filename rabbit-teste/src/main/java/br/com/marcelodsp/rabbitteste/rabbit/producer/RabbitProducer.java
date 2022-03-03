package br.com.marcelodsp.rabbitteste.rabbit.producer;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import br.com.marcelodsp.rabbitteste.config.MyConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitProducer {

	private final RabbitTemplate template;
	private final MyConfig config;

	public void sendToRabbit(Object object) {

		try {

			template.convertAndSend(config.getRabbit().getExchangeName(), null, object);

			log.info("RABBIT -> Objeto enviado: {}", object);

		} catch (AmqpException e) {
			log.error("RABBIT -> Erro ao enviar o objeto: {}", object);
			log.error("RABBIT -> Erro: {}", e.getMessage());
			throw e;
		}

	}

}
