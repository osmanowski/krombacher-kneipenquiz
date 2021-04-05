import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Kneipenbesitzer} from '../model/kneipenbesitzer';
import {Observable} from 'rxjs';
import {Frage} from '../model/frage';


@Injectable({
  providedIn: 'root'
})
 export class KneipenbesitzerService {
  private getBesitzerUrl: string;
  private addBesitzerUrl: string;

  constructor(private http: HttpClient) {
    this.getBesitzerUrl = 'http://localhost:8080/api/allbesitzer';
    this.addBesitzerUrl = 'http://localhost:8080/api/signupbesitzer';
  }

  public findBesitzer(benutzername: string): Observable<Kneipenbesitzer[]> {
    return this.http.get<Kneipenbesitzer[]>(this.getBesitzerUrl + benutzername);
  }
  public save(daten: string[]) {
    return this.http.post<string[]>(this.addBesitzerUrl, daten);
  }

  public findAll(): Observable<Kneipenbesitzer[]> {
    return this.http.get<Kneipenbesitzer[]>(this.getBesitzerUrl);
  }
}
