import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Spieler} from '../model/Spieler';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SpielerServiceService {


  private getSpielerUrl: string;
  private addSpielerUrl: string;
  private regUsUrl: string;
  private spieler: Spieler;

  constructor(private http: HttpClient) {
    this.getSpielerUrl = 'http://localhost:8080/api/';
    this.addSpielerUrl = 'http://localhost:8080/api/signupplayer';
    this.regUsUrl = 'http://localhost:8080/api/regus/';
  }

  public findSpieler(benutzername: string): Observable<Spieler[]> {
    return this.http.get<Spieler[]>(this.getSpielerUrl + benutzername);
  }
  public save(spieler: Spieler) {
    return this.http.post<Spieler>(this.addSpielerUrl, spieler);
  }
  public regUs(daten: string[]) {
    return this.http.post(this.regUsUrl, daten);
  }


}
