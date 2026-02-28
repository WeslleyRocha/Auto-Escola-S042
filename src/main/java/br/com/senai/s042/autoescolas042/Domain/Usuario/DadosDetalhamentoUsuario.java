package br.com.senai.s042.autoescolas042.Domain.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String senha
) {

    public DadosDetalhamentoUsuario(Usuario usuario){

        this(
                usuario.getId(),
                usuario.getLogin(),
                usuario.getSenha()
        );
    }
}
