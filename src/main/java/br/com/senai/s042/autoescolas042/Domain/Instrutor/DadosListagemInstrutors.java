package br.com.senai.s042.autoescolas042.Domain.Instrutor;

public record DadosListagemInstrutors(
        Long id,
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade) {

    public DadosListagemInstrutors(Instrutor instrutor){
        this(instrutor.getId(),
                instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade()
        );
    }
}
