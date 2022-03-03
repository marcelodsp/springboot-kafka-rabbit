package br.com.marcelodsp.rabbitteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcelodsp.rabbitteste.entity.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, String> {

}
