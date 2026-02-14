package br.com.senai.s042.autoescolas042.Instrutor;

public record DadosListagemInstrutors(
        String nome,
        String email,
        String telefone,
        String cnh,
        Especialidade especialidade) {

    public DadosListagemInstrutors(Instrutor instrutor){
        this(instrutor.getNome(),
                instrutor.getEmail(),
                instrutor.getTelefone(),
                instrutor.getCnh(),
                instrutor.getEspecialidade()
        );
    }
}
