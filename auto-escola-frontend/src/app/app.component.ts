import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';
import { AlunoService } from './services/aluno.service';
import { InstrutorService } from './services/instrutor.service';
import { InstrucaoService } from './services/instrucao.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class AppComponent implements OnInit {
  title = 'auto-escola-frontend';

  private authService = inject(AuthService);
  private alunoService = inject(AlunoService);
  private instrutorService = inject(InstrutorService);
  private instrucaoService = inject(InstrucaoService);
  private cdr = inject(ChangeDetectorRef);

  // Controle dos modais
  showLoginModal = false;
  showCadastroAlunoModal = false;
  showCadastroInstrutorModal = false;
  showAgendamentoModal = false;

  // Consulta modais
  showConsultaAlunoModal = false;
  showConsultaInstrutorModal = false;
  showConsultaAulaModal = false;

  // Listas para os selects de agendamento e consultas
  listaAlunos: any[] = [];
  listaInstrutores: any[] = [];
  listaAulas: any[] = [];

  // Lista de horas geradas dinamicamente
  horariosDisponiveis: string[] = [];

  // Dados dos formulários
  loginData = { login: '', senha: '', lembrar: false };

  cadastroAlunoData = {
    nome: '', email: '', telefone: '', cpf: '', especialidade: '',
    endereco: { logradouro: '', numero: '', complemento: '', bairro: '', cidade: '', uf: '', cep: '' }
  };

  cadastroInstrutorData = {
    nome: '', email: '', telefone: '', cnh: '', especialidade: '',
    endereco: { logradouro: '', numero: '', complemento: '', bairro: '', cidade: '', uf: '', cep: '' }
  };

  agendamentoData = {
    idAluno: null,
    idInstrutor: null,
    especialidade: '',
    dataForm: '', // Data separada do form (yyyy-MM-dd)
    horaForm: ''  // Hora separada do form
  };

  isLoggedIn = false;
  userName = '';

  // Mensagens de feedback
  errorMessage = '';
  cadastroMessage = '';
  cadastroSucesso = false;

  ngOnInit() {
    this.checkLoginStatus();
    this.gerarHorarios();
  }

  checkLoginStatus() {
    this.isLoggedIn = this.authService.isLoggedIn();
    if (this.isLoggedIn) {
      this.userName = this.authService.getUsuarioLogado() || '';
    }
  }

  gerarHorarios() {
    this.horariosDisponiveis = [];
    for (let i = 8; i <= 20; i++) {
      const hora = i.toString().padStart(2, '0') + ':00';
      this.horariosDisponiveis.push(hora);
    }
  }

  // --- MÉTODOS DE LOGIN ---
  openLoginModal() {
    this.showLoginModal = true;
    this.errorMessage = '';
  }

  closeLoginModal() {
    this.showLoginModal = false;
    this.loginData = { login: '', senha: '', lembrar: false };
    this.errorMessage = '';
    this.cdr.detectChanges();
  }

  onSubmitLogin(event?: Event) {
    if (event) event.preventDefault();
    this.errorMessage = '';
    this.cdr.detectChanges();

    if (this.loginData.login && this.loginData.senha) {
      this.authService.login(this.loginData).subscribe({
        next: (response: any) => {
          this.checkLoginStatus();
          this.closeLoginModal();
        },
        error: (err: any) => {
          console.error('Erro no login:', err);
          this.errorMessage = 'Usuário ou senha incorretos. Tente novamente.';
          this.cdr.detectChanges();
        }
      });
    } else {
      this.errorMessage = 'Por favor, preencha o login e a senha.';
      this.cdr.detectChanges();
    }
  }

  logout() {
    this.authService.logout();
    this.checkLoginStatus();
    this.cdr.detectChanges();
  }

  // --- EXTRACTOR DE ERRO (Função Auxiliar) ---
  private extractErrorMessage(err: any, defaultMsg: string): string {
    if (err.error && typeof err.error === 'string') {
      try {
        const parsed = JSON.parse(err.error);
        return parsed.message || err.error;
      } catch (e) {
        return err.error;
      }
    } else if (err.error && err.error.message) {
      return err.error.message;
    } else if (err.error && err.error.errors && err.error.errors.length > 0) {
      return err.error.errors[0].defaultMessage || err.error.errors[0].message || 'Dados inválidos.';
    }
    return defaultMsg;
  }

  // --- MÉTODOS DE CADASTRO DE ALUNO ---
  openCadastroAlunoModal(event: Event) {
    event.preventDefault();
    this.showCadastroAlunoModal = true;
    this.cadastroMessage = '';
    this.cadastroSucesso = false;
    this.cdr.detectChanges();
  }

  closeCadastroAlunoModal() {
    this.showCadastroAlunoModal = false;
    this.resetCadastroAlunoData();
    this.cadastroMessage = '';
    this.cdr.detectChanges();
  }

  resetCadastroAlunoData() {
    this.cadastroAlunoData = {
      nome: '', email: '', telefone: '', cpf: '', especialidade: '',
      endereco: { logradouro: '', numero: '', complemento: '', bairro: '', cidade: '', uf: '', cep: '' }
    };
  }

  onSubmitCadastroAluno(event?: Event) {
    if (event) event.preventDefault();
    this.cadastroMessage = '';
    this.cdr.detectChanges();

    if (!this.cadastroAlunoData.nome || !this.cadastroAlunoData.cpf || !this.cadastroAlunoData.especialidade) {
      this.cadastroSucesso = false;
      this.cadastroMessage = 'Por favor, preencha os campos obrigatórios (Nome, CPF e Especialidade).';
      this.cdr.detectChanges();
      return;
    }

    this.alunoService.cadastrarAluno(this.cadastroAlunoData).subscribe({
      next: (res: any) => {
        this.cadastroSucesso = true;
        this.cadastroMessage = typeof res === 'string' ? res : 'Aluno cadastrado com sucesso!';
        this.cdr.detectChanges();

        setTimeout(() => { this.closeCadastroAlunoModal(); }, 2000);
      },
      error: (err: any) => {
        console.error('Erro ao cadastrar aluno:', err);
        this.cadastroSucesso = false;
        this.cadastroMessage = this.extractErrorMessage(err, 'Erro ao cadastrar aluno. Verifique os dados e tente novamente.');
        this.cdr.detectChanges();
      }
    });
  }

  // --- MÉTODOS DE CADASTRO DE INSTRUTOR ---
  openCadastroInstrutorModal(event: Event) {
    event.preventDefault();
    this.showCadastroInstrutorModal = true;
    this.cadastroMessage = '';
    this.cadastroSucesso = false;
    this.cdr.detectChanges();
  }

  closeCadastroInstrutorModal() {
    this.showCadastroInstrutorModal = false;
    this.resetCadastroInstrutorData();
    this.cadastroMessage = '';
    this.cdr.detectChanges();
  }

  resetCadastroInstrutorData() {
    this.cadastroInstrutorData = {
      nome: '', email: '', telefone: '', cnh: '', especialidade: '',
      endereco: { logradouro: '', numero: '', complemento: '', bairro: '', cidade: '', uf: '', cep: '' }
    };
  }

  onSubmitCadastroInstrutor(event?: Event) {
    if (event) event.preventDefault();
    this.cadastroMessage = '';
    this.cdr.detectChanges();

    if (!this.cadastroInstrutorData.nome || !this.cadastroInstrutorData.cnh || !this.cadastroInstrutorData.especialidade) {
      this.cadastroSucesso = false;
      this.cadastroMessage = 'Por favor, preencha os campos obrigatórios (Nome, CNH e Especialidade).';
      this.cdr.detectChanges();
      return;
    }

    this.instrutorService.cadastrarInstrutor(this.cadastroInstrutorData).subscribe({
      next: (res: any) => {
        this.cadastroSucesso = true;
        this.cadastroMessage = typeof res === 'string' ? res : 'Instrutor cadastrado com sucesso!';
        this.cdr.detectChanges();

        setTimeout(() => { this.closeCadastroInstrutorModal(); }, 2000);
      },
      error: (err: any) => {
        console.error('Erro ao cadastrar instrutor:', err);
        this.cadastroSucesso = false;
        this.cadastroMessage = this.extractErrorMessage(err, 'Erro ao cadastrar instrutor. Verifique os dados e tente novamente.');
        this.cdr.detectChanges();
      }
    });
  }

  // --- MÉTODOS DE AGENDAMENTO (INSTRUÇÃO) ---
  openAgendamentoModal(event: Event) {
    event.preventDefault();
    this.showAgendamentoModal = true;
    this.cadastroMessage = '';
    this.cadastroSucesso = false;

    // Carrega listas para os selects
    this.carregarDadosParaAgendamento();
    this.cdr.detectChanges();
  }

  carregarDadosParaAgendamento() {
    // Busca alunos
    this.alunoService.listarAlunosAtivos().subscribe({
      next: (res: any) => {
        this.listaAlunos = res.content || res;
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Erro ao buscar alunos:', err)
    });

    // Busca instrutores
    this.instrutorService.listarInstrutoresAtivos().subscribe({
      next: (res: any) => {
        this.listaInstrutores = res.content || res;
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Erro ao buscar instrutores:', err)
    });
  }

  closeAgendamentoModal() {
    this.showAgendamentoModal = false;
    this.resetAgendamentoData();
    this.cadastroMessage = '';
    this.cdr.detectChanges();
  }

  resetAgendamentoData() {
    this.agendamentoData = { idAluno: null, idInstrutor: null, especialidade: '', dataForm: '', horaForm: '' };
    this.gerarHorarios(); // Reseta os horários para o padrão
  }

  formatDateParaBackend(data: string, hora: string): string {
    // Backend espera: dd/MM/yyyy - HH:mm
    // Data vem do form como yyyy-MM-dd
    if(!data || !hora) return '';
    const [year, month, day] = data.split('-');
    return `${day}/${month}/${year} - ${hora}`;
  }

  isFimDeSemana(data: string): boolean {
    if(!data) return false;
    // Precisamos pegar a data local correta para evitar que converta pro fuso UTC e mude o dia
    const [year, month, day] = data.split('-');
    const dataObj = new Date(parseInt(year), parseInt(month) - 1, parseInt(day));
    const diaSemana = dataObj.getDay(); // 0 = Domingo, 6 = Sábado
    return diaSemana === 0 || diaSemana === 6;
  }

  onDataOuInstrutorChange() {
    // Recarrega os horários disponíveis quando a data ou instrutor mudar
    this.gerarHorarios();

    // So busca os horários ocupados se a data e o instrutor estiverem preenchidos
    if (this.agendamentoData.dataForm && this.agendamentoData.idInstrutor && this.agendamentoData.idInstrutor !== null && String(this.agendamentoData.idInstrutor) !== 'null') {

      this.instrucaoService.buscarHorariosOcupados(Number(this.agendamentoData.idInstrutor), this.agendamentoData.dataForm).subscribe({
        next: (horariosOcupados: string[]) => {
          // horariosOcupados vem no formato ["14:00", "15:00"] etc.
          // Filtra a lista principal removendo os que vieram do banco
          this.horariosDisponiveis = this.horariosDisponiveis.filter(hora => !horariosOcupados.includes(hora));

          // Se o horário selecionado atualmente não estiver mais disponível, limpa a seleção
          if (this.agendamentoData.horaForm && !this.horariosDisponiveis.includes(this.agendamentoData.horaForm)) {
             this.agendamentoData.horaForm = '';
          }
          this.cdr.detectChanges();
        },
        error: (err: any) => {
          console.error('Erro ao buscar horários ocupados:', err);
        }
      });
    }
  }

  onSubmitAgendamento(event?: Event) {
    if (event) event.preventDefault();
    this.cadastroMessage = '';
    this.cdr.detectChanges();

    if (!this.agendamentoData.idAluno || !this.agendamentoData.dataForm || !this.agendamentoData.horaForm) {
      this.cadastroSucesso = false;
      this.cadastroMessage = 'Os campos Aluno, Data e Hora são obrigatórios.';
      this.cdr.detectChanges();
      return;
    }

    if (this.isFimDeSemana(this.agendamentoData.dataForm)) {
      this.cadastroSucesso = false;
      this.cadastroMessage = 'As aulas só podem ser agendadas de Segunda a Sexta-feira.';
      this.cdr.detectChanges();
      return;
    }

    if ((!this.agendamentoData.idInstrutor || String(this.agendamentoData.idInstrutor) === 'null') && !this.agendamentoData.especialidade) {
      this.cadastroSucesso = false;
      this.cadastroMessage = 'Especialidade é obrigatória quando o Instrutor não for escolhido!';
      this.cdr.detectChanges();
      return;
    }

    const dataFormatada = this.formatDateParaBackend(this.agendamentoData.dataForm, this.agendamentoData.horaForm);

    const payload = {
      idAluno: this.agendamentoData.idAluno,
      data: dataFormatada,
      idInstrutor: this.agendamentoData.idInstrutor && String(this.agendamentoData.idInstrutor) !== 'null' ? this.agendamentoData.idInstrutor : null,
      especialidade: this.agendamentoData.especialidade ? this.agendamentoData.especialidade : null
    };

    this.instrucaoService.agendarInstrucao(payload).subscribe({
      next: (res: any) => {
        this.cadastroSucesso = true;
        this.cadastroMessage = 'Agendamento realizado com sucesso!';
        this.cdr.detectChanges();

        setTimeout(() => { this.closeAgendamentoModal(); }, 2000);
      },
      error: (err: any) => {
        console.error('Erro ao agendar:', err);
        this.cadastroSucesso = false;
        this.cadastroMessage = this.extractErrorMessage(err, 'Erro ao realizar agendamento. Verifique a disponibilidade.');
        this.cdr.detectChanges();
      }
    });
  }

  // --- MÉTODOS DE CONSULTA DE ALUNOS ---
   openConsultaAlunoModal(event: Event) {
    event.preventDefault();
    this.showConsultaAlunoModal = true;

    // Busca a lista atualizada
    this.alunoService.listarAlunosAtivos().subscribe({
      next: (res: any) => {
        this.listaAlunos = res.content || res;
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Erro ao buscar alunos para consulta:', err)
    });
    this.cdr.detectChanges();
  }

  closeConsultaAlunoModal() {
    this.showConsultaAlunoModal = false;
    this.cdr.detectChanges();
  }

 // --- MÉTODOS DE CONSULTA DE INSTRUTOR ---
   openConsultaInstrutorModal(event: Event) {
    event.preventDefault();
    this.showConsultaInstrutorModal = true;

    // Busca a lista atualizada
    this.instrutorService.listarInstrutoresAtivos().subscribe({
      next: (res: any) => {
        this.listaInstrutores = res.content || res;
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Erro ao buscar instrutor para consulta:', err)
    });
    this.cdr.detectChanges();
  }

  closeConsultaInstrutorModal() {
    this.showConsultaInstrutorModal = false;
    this.cdr.detectChanges();
  }

  // --- MÉTODOS DE CONSULTA DE AULAS ---
  openConsultaAulaModal(event: Event) {
    event.preventDefault();
    this.showConsultaAulaModal = true;

    // Busca a lista de aulas/instruções
    this.instrucaoService.listarInstrucoes().subscribe({
      next: (res: any) => {
        this.listaAulas = res.content || res;
        this.cdr.detectChanges();
      },
      error: (err: any) => console.error('Erro ao buscar aulas para consulta:', err)
    });
    this.cdr.detectChanges();
  }

  closeConsultaAulaModal() {
    this.showConsultaAulaModal = false;
    this.cdr.detectChanges();
  }
}
