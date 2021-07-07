package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.charles.mercadolivre.responseDto.DetalheProdutoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class DetalheProdutoController {

    @PersistenceContext
    EntityManager manager;

    ProdutoRepository repository;
    public DetalheProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalheProdutoResponseDto> buscaPorId(@PathVariable("id") Long idProduto) {

        Optional<Produto> produto = repository.findById(idProduto);
        if (produto.isPresent()) {
            return ResponseEntity.ok(new DetalheProdutoResponseDto(produto.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
