package br.com.marcelodsp.kafkateste.rabbit.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.marcelodsp.kafkateste.config.RabbitConfig;
import br.com.marcelodsp.kafkateste.documents.Empresa;
import br.com.marcelodsp.kafkateste.documents.Funcionario;
import br.com.marcelodsp.kafkateste.service.EmpresaService;
import br.com.marcelodsp.kafkateste.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class RabbitConsumer {

	private final FuncionarioService funcionarioService;
	private final EmpresaService empresaService;

	@RabbitListener(queues = RabbitConfig.FUNCIONARIO_QUEUE)
	private void listenFunc(@Payload Funcionario funcionario) {
		log.info("RABBIT -> Chegou um {}", funcionario);
		funcionarioService.save(funcionario, false);
	}

	@RabbitListener(queues = RabbitConfig.EMPRESA_QUEUE)
	private void listenEmp(@Payload Empresa empresa) {
		log.info("RABBIT -> Chegou uma {}", empresa);
		empresaService.save(empresa, false);
	}

}
