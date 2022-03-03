package br.com.marcelodsp.kafkateste.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.marcelodsp.kafkateste.documents.Funcionario;
import br.com.marcelodsp.kafkateste.kafaka.producer.KafkaProducer;
import br.com.marcelodsp.kafkateste.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FuncionarioService {

    private final FuncionarioRepository repository;
    private final KafkaProducer kafkaService;

    public Flux<Funcionario> listFuncionarios() {
        return repository.findAll();
    }

    @Transactional
    public Mono<Funcionario> save(Funcionario funcionario, boolean sendMessage) {
        return repository.save(funcionario).doOnSuccess(emp -> {
            if (sendMessage)
                kafkaService.sendToKafka(emp);
        });
    }

    public Mono<Funcionario> findById(String id) {
        return repository.findById(id);
    }

}
