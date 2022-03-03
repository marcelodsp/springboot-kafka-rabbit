package br.com.marcelodsp.kafkateste.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.marcelodsp.kafkateste.documents.Funcionario;

public interface FuncionarioRepository extends ReactiveMongoRepository<Funcionario, String> {
       
}
