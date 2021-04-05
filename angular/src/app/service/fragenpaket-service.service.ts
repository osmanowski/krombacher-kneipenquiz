import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Fragenpaket} from '../model/fragenpaket';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FragenpaketServiceService {


  private getPackageUrl: string;
  private addPackageUrl: string;

  constructor(private http: HttpClient) {
    this.getPackageUrl = 'http://localhost:8080/api/allFragenPakete';
    this.addPackageUrl = 'http://localhost:8080/api/addPaket';
  }

  public findAll(): Observable<Fragenpaket[]> {
    return this.http.get<Fragenpaket[]>(this.getPackageUrl);
  }

  public save(fragenpaket: Fragenpaket) {
    return this.http.post<Fragenpaket>(this.addPackageUrl, fragenpaket);
  }
}
