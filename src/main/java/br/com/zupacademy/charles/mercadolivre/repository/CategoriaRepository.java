package br.com.zupacademy.charles.mercadolivre.repository;

import br.com.zupacademy.charles.mercadolivre.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
