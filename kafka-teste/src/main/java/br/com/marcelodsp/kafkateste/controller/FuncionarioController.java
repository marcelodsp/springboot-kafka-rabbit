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

import br.com.marcelodsp.kafkateste.documents.Funcionario;
import br.com.marcelodsp.kafkateste.service.FuncionarioService;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    @GetMapping
    public Flux<Funcionario> listFuncionarios() {
        return service.listFuncionarios();
    }

    @PostMapping()
    @ResponseStatus(code = HttpStatus.CREATED)
    public Mono<Funcionario> save(@RequestBody Funcionario funcionario) {
        return service.save(funcionario, true);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Funcionario>> edit(@PathVariable String id, @RequestBody Funcionario newFunc) {
        return service.findById(id)
                .flatMap(oldFunc -> {
                    BeanUtils.copyProperties(newFunc, oldFunc);
                    oldFunc.setId(id);
                    return service.save(oldFunc, true);
                })
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

}
