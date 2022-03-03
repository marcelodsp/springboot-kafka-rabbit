package br.com.marcelodsp.rabbitteste.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelodsp.rabbitteste.entity.Funcionario;
import br.com.marcelodsp.rabbitteste.service.FuncionarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/funcionarios")
public class FuncionarioController {

	private final FuncionarioService service;

	@GetMapping
	public ResponseEntity<List<Funcionario>> listFuncionarios() {
		return ResponseEntity.of(service.listFuncionarios());
	}

	@PostMapping()
	public ResponseEntity<Funcionario> save(@RequestBody Funcionario funcionario) {
		return ResponseEntity.of(service.save(funcionario, true));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@PathVariable String id, @RequestBody Funcionario newFunc) {
		return ResponseEntity.of(service.findById(id).map(oldFunc -> {
			BeanUtils.copyProperties(newFunc, oldFunc);
			oldFunc.setId(id);
			return service.save(oldFunc, true);
		}));
	}

}
