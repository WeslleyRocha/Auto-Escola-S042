package br.com.senai.s042.autoescolas042.Domain.Instrutor;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InstrutorService {

    private final InstrutorRepository instrutorRepository;

    public InstrutorService(InstrutorRepository InstrutorRepository) {
        this.instrutorRepository = InstrutorRepository;
    }

    public Page<DadosListagemInstrutors> listar(Pageable pageable) {

        return instrutorRepository.findAllByAtivoTrue(pageable).map(DadosListagemInstrutors::new);
    }

    @Transactional
    public DadosDetalhamentoInstrutor cadastrar(DadosCadastroInstrutor dadosInstrutor) {

        Instrutor instrutor = new Instrutor(dadosInstrutor);
        instrutorRepository.save(instrutor);

        return new DadosDetalhamentoInstrutor(instrutor);
    }

    public DadosDetalhamentoInstrutor detalhar(Long id) {

        Instrutor instrutor = instrutorRepository.getReferenceById(id);

        return new  DadosDetalhamentoInstrutor(instrutor);
    }

    @Transactional
    public DadosDetalhamentoInstrutor atualizar(DadosAtualizacaoInstrutor dadosAtualizacaoInstrutor) {

        Instrutor instrutor = instrutorRepository.getReferenceById(Long.valueOf(dadosAtualizacaoInstrutor.id()));
        instrutor.atualizarInformacoes(dadosAtualizacaoInstrutor);
        instrutorRepository.save(instrutor);

        return new DadosDetalhamentoInstrutor(instrutor);
    }

    public void excluir(Long id) {

        Instrutor instrutor = instrutorRepository.getReferenceById(id);
        instrutor.excluir();
        instrutorRepository.save(instrutor);
    }
}
