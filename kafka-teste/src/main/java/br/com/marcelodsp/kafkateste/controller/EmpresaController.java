package br.com.marcelodsp.kafkateste.controller;

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

import br.com.marcelodsp.kafkateste.documents.Empresa;
import br.com.marcelodsp.kafkateste.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/empresas")
public class EmpresaController {

	private final EmpresaService service;

	@GetMapping
	public Flux<Empresa> listEmpresas() {
		return service.listEmpresas();
	}

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public Mono<Empresa> save(@RequestBody Empresa empresa) {
		return service.save(empresa, true);
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<Empresa>> edit(@PathVariable String id, @RequestBody Empresa newEmp) {
		return service.findById(id).flatMap(oldEmp -> {
			BeanUtils.copyProperties(newEmp, oldEmp);
			oldEmp.setId(id);
			return service.save(oldEmp, true);
		}).map(ResponseEntity::ok).switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
	}

}
