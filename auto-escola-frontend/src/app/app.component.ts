import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from './services/auth.service';

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
  private cdr = inject(ChangeDetectorRef);

  // Controle do modal de login
  showLoginModal = false;

  // Dados do formulário
  loginData = {
    login: '',
    senha: '',
    lembrar: false
  };

  isLoggedIn = false;
  userName = '';
  errorMessage = ''; // Para mostrar erros de senha/usuário incorreto

  ngOnInit() {
    this.checkLoginStatus();
  }

  checkLoginStatus() {
    this.isLoggedIn = this.authService.isLoggedIn();
    if (this.isLoggedIn) {
      this.userName = this.authService.getUsuarioLogado() || '';
    }
  }

  openLoginModal() {
    this.showLoginModal = true;
    this.errorMessage = '';
  }

  closeLoginModal() {
    this.showLoginModal = false;
    this.loginData = { login: '', senha: '', lembrar: false };
    this.errorMessage = '';
    this.cdr.detectChanges(); // Força a atualização da view
  }

  onSubmitLogin() {
    this.errorMessage = ''; // Limpa a mensagem anterior antes de tentar
    this.cdr.detectChanges(); // Garante que a view saiba que a mensagem foi limpa

    if (this.loginData.login && this.loginData.senha) {
      this.authService.login(this.loginData).subscribe({
        next: (response) => {
          console.log('Login efetuado com sucesso:', response);
          this.checkLoginStatus();
          this.closeLoginModal(); // Fecha o modal imediatamente no sucesso
        },
        error: (err) => {
          console.error('Erro no login:', err);
          // Se não houver sucesso, define a mensagem e mantém o modal aberto
          this.errorMessage = 'Usuário ou senha incorretos. Tente novamente.';
          this.cdr.detectChanges(); // Força a atualização imediata da tela para mostrar o erro
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
    this.cdr.detectChanges(); // Garante que a barra superior mude na hora
  }
}
