import { Observable } from 'rxjs';
import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RegistrazioneDto } from './registrazione-dto';
import { Messaggio } from './messaggio';
import { Chat } from './chat';
import { InviaMessaggioDto } from './invia-messaggio-dto';
import { RichiediMessaggiDto } from './richiedi-messaggi-dto';
import { RichiediRegistrazioneDto } from './richiedi-registrazione-dto';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  nickName: string;
  messaggio: string;
  sessione: string;
  messaggi: Messaggio[] = [];
  contatti: Chat[] = [];

  constructor(private http: HttpClient) { }

  registrazione() {
    console.log("Sono in registrazione()");
    // Preparo i dati da inviare
    let dto: RichiediRegistrazioneDto = new RichiediRegistrazioneDto();
    dto.nickname = this.nickName;
    // Chiamata REST
    let oss: Observable<RegistrazioneDto> = this.http.post<RegistrazioneDto>(
      'http://localhost:8080/registrazione', dto);
    oss.subscribe(d => {
      this.messaggi = d.messaggi;
      this.contatti = d.contatti;
      this.sessione = d.sessione;
    });
  }

  inviaTutti() {
    console.log("Sono in inviaTutti()");
    // Preparo i dati da inviare
    let dto: InviaMessaggioDto = new InviaMessaggioDto();
    dto.messaggio = this.messaggio;
    dto.destinatario = null;
    dto.sessione = this.sessione;
    // Chiamata REST
    let oss: Observable<RegistrazioneDto> = this.http.post<RegistrazioneDto>(
      'http://localhost:8080/invia-tutti', dto);
    oss.subscribe(d => {
      this.messaggi = d.messaggi;
      this.contatti = d.contatti;
    });
  }

  aggiorna() {
    console.log("Sono in aggiorna()");
    // Preparo i dati da inviare
    let dto: RichiediMessaggiDto = new RichiediMessaggiDto();
    dto.sessione = this.sessione;
    // Chiamata REST
    let oss: Observable<RegistrazioneDto> = this.http.post<RegistrazioneDto>(
      'http://localhost:8080/aggiorna', dto);
    oss.subscribe(d => {
      this.messaggi = d.messaggi;
      this.contatti = d.contatti;
    });
  }

  inviaUno(c: Chat) {
    console.log("Sono in inviaUno()");
    // Preparo i dati da inviare
    let dto: InviaMessaggioDto = new InviaMessaggioDto();
    dto.messaggio = this.messaggio;
    dto.destinatario = c.nickname;
    dto.sessione = this.sessione;
    // Chiamata REST
    let oss: Observable<RegistrazioneDto> = this.http.post<RegistrazioneDto>(
      'http://localhost:8080/invia-uno', dto);
    oss.subscribe(d => {
      this.messaggi = d.messaggi;
      this.contatti = d.contatti;
    });
  }
}
