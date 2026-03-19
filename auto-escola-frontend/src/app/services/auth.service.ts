import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly apiUrl = 'http://localhost:8080/login';
  private readonly http = inject(HttpClient);

  login(dados: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, dados).pipe(
      tap(response => {
        // No Java, o DTO DadosTokenJWT retorna { "token": "..." } ou algo parecido.
        // Se retornar apenas a string pura ou outro campo, ajustamos aqui.
        let tokenJwt = response.tokenJWT;
        if (tokenJwt) {
          localStorage.setItem('auth_token', tokenJwt);
          localStorage.setItem('usuario_logado', dados.login);
        }
      })
    );
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('usuario_logado');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('auth_token');
  }

  getUsuarioLogado(): string | null {
    return localStorage.getItem('usuario_logado');
  }
}
