package br.com.senai.s042.autoescolas042.Controller;

import br.com.senai.s042.autoescolas042.Domain.Usuario.DadosAtualizacaoUsuario;
import br.com.senai.s042.autoescolas042.Domain.Usuario.DadosCadastroUsuario;
import br.com.senai.s042.autoescolas042.Domain.Usuario.DadosDetalhamentoUsuario;
import br.com.senai.s042.autoescolas042.Domain.Usuario.DadosListagemUsuarios;
import br.com.senai.s042.autoescolas042.Domain.Usuario.Usuario;
import br.com.senai.s042.autoescolas042.Domain.Usuario.UsuariosRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuarios>> listarUsuarios(
            @PageableDefault(size = 10, sort = {"login"}) Pageable pageable){

        Page page = usuariosRepository.findAllByAtivoTrue(pageable).map(DadosListagemUsuarios::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoUsuario> detalharUsuario(@PathVariable Long id){
        Usuario usuario = usuariosRepository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> cadastrarUsuarios(
            @RequestBody
            @Valid
            DadosCadastroUsuario dadosUsuario,
            UriComponentsBuilder uriBuilder) {

        Usuario usuario = new Usuario(dadosUsuario);
        usuario.setSenha(passwordEncoder.encode(dadosUsuario.senha()));
        usuariosRepository.save(usuario);

        URI uri = uriBuilder.path("/usuarios/{id}")
                .buildAndExpand(usuario.getId()).toUri();

        return  ResponseEntity.created(uri)
                .body(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping
    @Transactional
    public void atualizarUsuarios(@RequestBody DadosAtualizacaoUsuario dadosAtualizacaoUsuario){
        Usuario usuario = usuariosRepository.getReferenceById(Long.valueOf(dadosAtualizacaoUsuario.id()));

        usuario.atualizarInformacoes(dadosAtualizacaoUsuario);
        usuariosRepository.save(usuario);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id){

        Usuario usuario = usuariosRepository.getReferenceById(id);
        usuario.excluir();

        usuariosRepository.save(usuario);
        return ResponseEntity.noContent().build();
    }
}
