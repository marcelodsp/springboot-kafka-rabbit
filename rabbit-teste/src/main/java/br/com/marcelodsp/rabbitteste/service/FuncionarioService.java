package br.com.marcelodsp.rabbitteste.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.marcelodsp.rabbitteste.entity.Funcionario;
import br.com.marcelodsp.rabbitteste.rabbit.producer.RabbitProducer;
import br.com.marcelodsp.rabbitteste.repository.FuncionarioRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FuncionarioService {

	private final FuncionarioRepository repository;
	private final RabbitProducer producer;

	@Transactional
	public Optional<Funcionario> save(Funcionario funcionario, boolean sendMessage) {
		var func = repository.save(funcionario);
		if (sendMessage)
			producer.sendToRabbit(func);
		return Optional.ofNullable(func);
	}

	public Optional<List<Funcionario>> listFuncionarios() {
		return Optional.of(repository.findAll());
	}

	public Optional<Funcionario> findById(String id) {
		return repository.findById(id);
	}

}
