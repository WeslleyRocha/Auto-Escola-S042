package br.com.senai.s042.autoescolas042.Domain.Instrutor;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {

    Page<Instrutor> findAllByAtivoTrue(Pageable pageable);

    @Query(""" 
        SELECT i FROM Instrutor i 
        WHERE i.ativo = true 
        AND i.especialidade = :especialidade
        AND i.id NOT IN (
        SELECT a.instrutor.id FROM Instrucao a WHERE a.data = :data
        )
        
        ORDER BY rand()
        LIMIT 01

""")
    Instrutor escolherInstrutorAleatorioDisponivel(Especialidade especialidade, @NotNull @Future LocalDateTime data);

    @Query("""
    SELECT i.ativo
    FROM Instrutor i
    WHERE i.id = :id
""")
    Boolean findArivoById(@Param("id") Long id);
}
