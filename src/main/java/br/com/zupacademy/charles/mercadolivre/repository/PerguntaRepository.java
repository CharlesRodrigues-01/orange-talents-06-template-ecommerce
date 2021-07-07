package br.com.zupacademy.charles.mercadolivre.repository;

import br.com.zupacademy.charles.mercadolivre.model.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta, Long> {
}
