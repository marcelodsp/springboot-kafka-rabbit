package br.com.marcelodsp.kafkateste.service;

import org.springframework.stereotype.Service;

import br.com.marcelodsp.kafkateste.documents.Empresa;
import br.com.marcelodsp.kafkateste.kafaka.producer.KafkaProducer;
import br.com.marcelodsp.kafkateste.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class EmpresaService {

	private final EmpresaRepository repository;
	private final KafkaProducer kafkaService;

	public Flux<Empresa> listEmpresas() {
		return repository.findAll();
	}

	public Mono<Empresa> save(Empresa empresa, boolean sendMessage) {
		return repository.save(empresa)
				.doOnSuccess(emp -> {
					if (sendMessage)
						kafkaService.sendToKafka(emp);
				});
	}

	public Mono<Empresa> findById(String id) {
		return repository.findById(id);
	}

}
