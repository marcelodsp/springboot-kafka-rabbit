package br.com.marcelodsp.kafkateste.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.marcelodsp.kafkateste.documents.Empresa;

public interface EmpresaRepository extends ReactiveMongoRepository<Empresa, String> {

}
