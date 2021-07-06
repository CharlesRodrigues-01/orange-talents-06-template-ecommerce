package br.com.zupacademy.charles.mercadolivre.controller;

import br.com.zupacademy.charles.mercadolivre.model.*;
import br.com.zupacademy.charles.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.charles.mercadolivre.repository.UsuarioRepository;
import br.com.zupacademy.charles.mercadolivre.requestDto.ImagensRequestDto;
import br.com.zupacademy.charles.mercadolivre.requestDto.ProdutoRequestDto;
import br.com.zupacademy.charles.mercadolivre.validation.ProibeCaracteristicasComMesmoNome;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    private UploaderFake uploaderFake;
    private ProdutoRepository repository;

    public ProdutoController(ProdutoRepository repository, UploaderFake uploaderFake) {
        this.repository = repository;
        this.uploaderFake = uploaderFake;
    }

    @InitBinder(value = "ProdutoRequestDto")
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

    @PostMapping("/{id}/imagens")
    @Transactional
    public ResponseEntity<Void> adicionaImagem(@PathVariable("id") Long id, @Valid ImagensRequestDto requestDto,
                                               @AuthenticationPrincipal UsuarioLogado usuarioLogado){

        Usuario usuario = usuarioLogado.get();
        Produto produto = manager.find(Produto.class, id);

        if (!produto.pertenceAoUsuario(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    "Você só pode adicionar imagens a um produto que vc cadastrou");
        }

        Set<String> links = uploaderFake.envia(requestDto.getImagens());
        produto.associaImagens(links);
        manager.merge(produto);
        return ResponseEntity.ok().build();
    }

}
