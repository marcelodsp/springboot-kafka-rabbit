package br.com.marcelodsp.rabbitteste.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelodsp.rabbitteste.entity.Empresa;
import br.com.marcelodsp.rabbitteste.service.EmpresaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/empresas")
public class EmpresaController {

	private final EmpresaService service;

	@GetMapping
	public ResponseEntity<List<Empresa>> listEmpresas() {
		return ResponseEntity.of(service.listEmpresas());
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Empresa> save(@RequestBody Empresa empresa) {
		return ResponseEntity.of(service.save(empresa,true));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> edit(@PathVariable String id, @RequestBody Empresa newEmp) {
		return ResponseEntity.of(service.findById(id)
				.flatMap(oldEmp -> {
					BeanUtils.copyProperties(newEmp, oldEmp);
					oldEmp.setId(id);
					return service.save(oldEmp,true);
				}));
	}

}
