import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  agendamentosDisponiveis = [
    { data: '20/03/2026', hora: '08:00', tipo: 'Carro', instrutor: 'Carlos Silva' },
    { data: '20/03/2026', hora: '10:00', tipo: 'Moto', instrutor: 'Pedro Santos' },
    { data: '21/03/2026', hora: '14:00', tipo: 'Caminhão', instrutor: 'Roberto Lima' },
  ];
}
