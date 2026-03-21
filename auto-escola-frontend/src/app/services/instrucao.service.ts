import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InstrucaoService {
  private readonly apiUrl = 'http://localhost:8080/instrucao';
  private readonly http = inject(HttpClient);

  private getAuthHeaders(): HttpHeaders {
    const token = localStorage.getItem('auth_token');
    return new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${token}`
    });
  }

  agendarInstrucao(dados: any): Observable<any> {
    return this.http.post<any>(this.apiUrl, dados, { headers: this.getAuthHeaders() });
  }

  buscarHorariosOcupados(idInstrutor: number, data: string): Observable<string[]> {
    // data deve estar no formato yyyy-MM-dd
    return this.http.get<string[]>(`${this.apiUrl}/horarios-ocupados/${idInstrutor}/${data}`, { headers: this.getAuthHeaders() });
  }

  listarInstrucoes(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}?page=0&size=100&sort=id,desc`, { headers: this.getAuthHeaders() });
  }
}
