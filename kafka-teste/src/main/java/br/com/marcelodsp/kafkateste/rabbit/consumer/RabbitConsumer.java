package br.com.marcelodsp.kafkateste.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.marcelodsp.kafkateste.config.RabbitConfig;
import br.com.marcelodsp.kafkateste.documents.Empresa;
import br.com.marcelodsp.kafkateste.documents.Funcionario;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitConsumer {

	// private final FuncionarioService funcionarioService;
	// private final EmpresaService empresaService;

	@RabbitListener(queues = RabbitConfig.FUNCIONARIO_QUEUE)
	private void listenFunc(@Payload Funcionario obj) {
		log.info("RABBIT -> Chegou um {}", obj);
	}

	@RabbitListener(queues = RabbitConfig.EMPRESA_QUEUE)
	private void listenEmp(@Payload Empresa obj) {
		log.info("RABBIT -> Chegou uma {}", obj);
	}

}
