import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, CommonModule, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.scss'
})
export class AppComponent {
  title = 'auto-escola-frontend';

  // Controle do modal de login
  showLoginModal = false;

  // Dados do formulário
  loginData = {
    login: '',
    senha: '',
    lembrar: false
  };

  // Controle de estado do usuário (simulação por enquanto)
  isLoggedIn = false;
  userName = '';

  openLoginModal() {
    this.showLoginModal = true;
  }

  closeLoginModal() {
    this.showLoginModal = false;
    this.loginData = { login: '', senha: '', lembrar: false }; // Limpa os dados ao fechar
  }

  onSubmitLogin() {
    // Por enquanto, vamos mockar o login.
    // O próximo passo será conectar isso ao backend Spring Boot
    if (this.loginData.login && this.loginData.senha) {
      this.isLoggedIn = true;
      this.userName = this.loginData.login; // ou 'Admin'
      this.closeLoginModal();
      console.log('Login efetuado com os dados:', this.loginData);
    } else {
      alert("Por favor, preencha login e senha.");
    }
  }

  logout() {
    this.isLoggedIn = false;
    this.userName = '';
  }
}
