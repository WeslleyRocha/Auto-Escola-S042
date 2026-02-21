package br.com.senai.s042.autoescolas042.Instrutor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstrutorRepository extends JpaRepository<Instrutor, Long> {
    List<Instrutor> findAllByAtivoTrue();
}
