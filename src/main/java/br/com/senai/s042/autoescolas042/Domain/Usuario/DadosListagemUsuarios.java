package br.com.senai.s042.autoescolas042.Domain.Usuario;

public record DadosListagemUsuarios(
        Long id,
        String login,
        String senha) {

    public DadosListagemUsuarios(Usuario usuario){
        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
