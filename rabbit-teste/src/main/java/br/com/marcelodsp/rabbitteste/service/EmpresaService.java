package br.com.marcelodsp.rabbitteste.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.marcelodsp.rabbitteste.entity.Empresa;
import br.com.marcelodsp.rabbitteste.rabbit.producer.RabbitProducer;
import br.com.marcelodsp.rabbitteste.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpresaService {

	private final EmpresaRepository empresaRepository;
	private final RabbitProducer producer;

	@Transactional
	public Optional<Empresa> save(Empresa empresa, boolean sendMessage) {
		Empresa emp = empresaRepository.save(empresa);
		if (sendMessage)
			producer.sendToRabbit(emp);
		return Optional.of(emp);
	}

	public Optional<Empresa> findById(String id) {
		return empresaRepository.findById(id);
	}

	public Optional<List<Empresa>> listEmpresas() {
		return Optional.of(empresaRepository.findAll());
	}

}
