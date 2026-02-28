package br.com.senai.s042.autoescolas042.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuariosRepository extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String longin);
}
