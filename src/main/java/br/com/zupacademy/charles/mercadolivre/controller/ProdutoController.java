package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.Produto;
import br.com.zupacademy.charles.mercadolivre.model.Usuario;
import br.com.zupacademy.charles.mercadolivre.model.UsuarioLogado;
import br.com.zupacademy.charles.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.ProdutoRequestDto;
import br.com.zupacademy.charles.mercadolivre.validation.ProibeCaracteristicasComMesmoNome;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository){
        this.repository = repository;
    }

    @InitBinder
    public void init(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new ProibeCaracteristicasComMesmoNome());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid ProdutoRequestDto requestDto,
                                          @AuthenticationPrincipal UsuarioLogado usuarioLogado){
        Usuario usuario = usuarioLogado.get();
        Produto produto = requestDto.toModel(manager, usuario);
        repository.save(produto);

        return ResponseEntity.ok().build();
    }

}
