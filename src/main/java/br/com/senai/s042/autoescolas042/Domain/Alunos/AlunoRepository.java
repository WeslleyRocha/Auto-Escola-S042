package br.com.senai.s042.autoescolas042.Domain.Alunos;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    Page<Aluno> findAllByAtivoTrue(Pageable pageable);

    @Query("""
    SELECT i.ativo
    FROM Aluno i 
    WHERE
    i.id = id
""")
    Boolean findArivoById(@NotNull Long id);
}
