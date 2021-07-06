package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.Opiniao;
import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import br.com.zupacademy.charles.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.charles.mercadolivre.repository.OpiniaoRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.OpiniaoRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class OpiniaoController {

    @PersistenceContext
    private EntityManager manager;

    private OpiniaoRepository repository;
    public OpiniaoController(OpiniaoRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/{id}/opinioes")
    @Transactional
    public ResponseEntity<Void> adicionar(@PathVariable("id") Long idProduto,
                                          @RequestBody @Valid OpiniaoRequestDto requestDto,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Usuario usuario = usuarioLogado.get();
        Produto produto = manager.find(Produto.class, idProduto);

        Opiniao opiniao = requestDto.toModel(usuario, produto);
        repository.save(opiniao);

        return ResponseEntity.ok().build();
    }
}
