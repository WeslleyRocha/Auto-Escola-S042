import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstrutorService {
  private readonly apiUrl = 'http://localhost:8080/instrutores';
  private readonly http = inject(HttpClient);

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  cadastrarInstrutor(dados: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, dados, { headers: this.getAuthHeaders() });
  }

  listarInstrutoresAtivos(): Observable<any> {
    // Usando page=0 e size=100 para simplificar a busca de instrutores num select
    return this.http.get(`${this.apiUrl}?page=0&size=100&sort=id,asc`, { headers: this.getAuthHeaders() });
  }
}
