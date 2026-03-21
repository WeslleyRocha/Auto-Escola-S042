import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlunoService {
  private readonly apiUrl = 'http://localhost:8080/alunos';
  private readonly http = inject(HttpClient);

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  cadastrarAluno(dados: any): Observable<any> {
    // Usamos { responseType: 'text' } pois o backend retorna uma String pura no cadastro
    return this.http.post(this.apiUrl, dados, { headers: this.getAuthHeaders(), responseType: 'text' });
  }

  listarAlunosAtivos(): Observable<any> {
    // Usando page=0 e size=100 e sort=id,asc para ordenar pelo ID do menor para o maior
    return this.http.get(`${this.apiUrl}?page=0&size=100&sort=id,asc`, { headers: this.getAuthHeaders() });
  }
}
