package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.Categoria;
import br.com.zupacademy.charles.mercadolivre.repository.CategoriaRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.CategoriaRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    CategoriaRepository repository;
    public CategoriaController(CategoriaRepository repository){
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CategoriaRequestDto requestDto){

        Categoria categoria = requestDto.toModel(repository);
        repository.save(categoria);
        return ResponseEntity.ok().build();

    }
}
