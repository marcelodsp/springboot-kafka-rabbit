package br.com.marcelodsp.rabbitteste.kafka.consumer;

import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import br.com.marcelodsp.rabbitteste.entity.Empresa;
import br.com.marcelodsp.rabbitteste.entity.Funcionario;
import br.com.marcelodsp.rabbitteste.service.EmpresaService;
import br.com.marcelodsp.rabbitteste.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
@KafkaListener(topics = "#{myConfig.getKafka().getTopicName()}") // (topics = "${my-config.kafka.topic-name}")
public class KafkaConsumerListener {

	private final FuncionarioService funcionarioService;
	private final EmpresaService empresaService;

	@KafkaHandler
	private void listenFuncionario(@Payload Funcionario func, Acknowledgment ack) {

		log.info("KAFKA -> FUNCIONARIO: {}", func);
		try {
			funcionarioService.save(func, false);
			ack.acknowledge();

		} catch (Exception ex) {
			log.error("Erro ao persistir funcionário: {}", ex.getMessage());
		}

	}

	@KafkaHandler
	private void listenEmpresa(@Payload Empresa emp, Acknowledgment ack) {

		log.info("KAFKA -> EMPRESA: {}", emp);
		try {
			empresaService.save(emp, false);
			ack.acknowledge();

		} catch (Exception ex) {
			log.error("Erro ao persistir empresa: {}", ex.getMessage());
		}
	}

	@KafkaHandler(isDefault = true)
	private void listenGeral(@Payload Object objeto) {
		log.error("KAFKA -> Objeto não identificado: {}", objeto);
	}
}
