import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Fragenpaket} from '../model/fragenpaket';
import {Kneipe} from '../model/kneipe';

@Injectable({
  providedIn: 'root'
})
export class KneipenServiceService {


  private getKneipeUrl: string;
  private addKneipeUrl: string;
  private deleteKneipeUrl: string;

  constructor(private http: HttpClient) {
    this.getKneipeUrl = 'http://localhost:8080/api/getallkneipen';
    this.addKneipeUrl = 'http://localhost:8080/api/addkneipe';
    this.deleteKneipeUrl = 'http://localhost:8080/api/deletekneipe/';
  }

  public findAll(): Observable<Kneipe[]> {
    return this.http.get<Kneipe[]>(this.getKneipeUrl);
  }

  public save(kneipe: Kneipe) {
    return this.http.post<Kneipe>(this.addKneipeUrl, kneipe);
  }

  public delete(id: string) {
    return this.http.delete(this.deleteKneipeUrl + id);
  }
}
