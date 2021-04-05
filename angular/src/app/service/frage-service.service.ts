import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Frage} from '../model/frage';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FrageServiceService {


  private getQuestionUrl: string;
  private addQuestionUrl: string;
  private deleteQuestionUrl: string;

  constructor(private http: HttpClient) {
    this.getQuestionUrl = 'http://localhost:8080/api/fragen';
    this.addQuestionUrl = 'http://localhost:8080/api/addFrage';
    this.deleteQuestionUrl = 'http://localhost:8080/api/deletefrage/';
  }

  public findAll(): Observable<Frage[]> {
    return this.http.get<Frage[]>(this.getQuestionUrl);
  }

  public delete(id: string) {
    return this.http.delete(this.deleteQuestionUrl + id);
  }

  public save(frage: Frage) {
    return this.http.post<Frage>(this.addQuestionUrl, frage);
  }

}
