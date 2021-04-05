import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Quizrunde} from '../model/quizrunde';
@Injectable({
  providedIn: 'root'
})

export class QuizrundeService {
  private getQuizRundeUrl: string;
  private addQuizRundeUrl: string;

  constructor(private http: HttpClient) {
    this.getQuizRundeUrl = 'http://localhost:8080/api/getQuizRunde';
    this.addQuizRundeUrl = 'http://localhost:8080/api/addQuizrunde';

  }

  public findAll(): Observable<Quizrunde[]> {
    return this.http.get<Quizrunde[]>(this.getQuizRundeUrl);
  }

  public save(quizrunde: Quizrunde) {
    return this.http.post<Quizrunde>(this.addQuizRundeUrl, quizrunde);
  }


}
