package br.com.zupacademy.charles.mercadolivre.requestDto;

import br.com.zupacademy.charles.mercadolivre.annotation.UniqueValue;
import br.com.zupacademy.charles.mercadolivre.model.Categoria;
import br.com.zupacademy.charles.mercadolivre.repository.CategoriaRepository;
import org.springframework.util.Assert;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

public class CategoriaRequestDto {

    @NotBlank
    @UniqueValue(fieldName = "nome", domainClass = Categoria.class)
    private String nome;
    @Positive
    private Long idCategoriaMae;

    public CategoriaRequestDto(@NotBlank String nome, Long idCategoriaMae) {
        this.nome = nome;
        this.idCategoriaMae = idCategoriaMae;
    }

    public CategoriaRequestDto(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdCategoriaMae() {
        return idCategoriaMae;
    }

    public Categoria toModel(CategoriaRepository repository) {
        Categoria categoria = new Categoria(nome);
        if(idCategoriaMae != null){
            Categoria categoriaMae = repository.getById(idCategoriaMae);
            Assert.notNull(categoriaMae, "O id da categoria Mãe deve ser válido");
            categoria.setMae(categoriaMae);
        }
        return categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
