import { Component, inject, OnInit } from '@angular/core';
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
  }

  onSubmitLogin() {
    if (this.loginData.login && this.loginData.senha) {
      this.authService.login(this.loginData).subscribe({
        next: (response) => {
          console.log('Login efetuado com sucesso:', response);
          this.checkLoginStatus();
          this.closeLoginModal();
        },
        error: (err) => {
          console.error('Erro no login:', err);
          this.errorMessage = 'Usuário ou senha inválidos. Tente novamente.';
        }
      });
    } else {
      this.errorMessage = 'Por favor, preencha o login e a senha.';
    }
  }

  logout() {
    this.authService.logout();
    this.checkLoginStatus();
  }
}
