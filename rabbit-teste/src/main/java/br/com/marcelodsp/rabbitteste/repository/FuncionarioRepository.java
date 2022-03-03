package br.com.marcelodsp.rabbitteste.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.marcelodsp.rabbitteste.entity.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {

}
