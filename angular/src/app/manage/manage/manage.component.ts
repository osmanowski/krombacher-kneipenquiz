import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, FormBuilder, FormArray} from '@angular/forms';
import {Fragenpaket} from '../../model/fragenpaket';
import {FragenpaketServiceService} from '../../service/fragenpaket-service.service';
import {Quizrunde} from '../../model/quizrunde';
import {QuizrundeService} from '../../service/quizrunde-service.service';

@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css']
})
export class ManageComponent implements OnInit {
  fragenpaket: Fragenpaket[];
  manageGroup: FormGroup;
  quizrunde: Quizrunde;
  choosedfragenpaket: Fragenpaket;



  constructor(private fragenpaketServiceService: FragenpaketServiceService, private quizrundeService: QuizrundeService) {
    this.quizrunde = new Quizrunde();
    this.choosedfragenpaket = new Fragenpaket();
    this.manageGroup = new FormGroup({fragenpaket: new FormControl(), startzeit: new FormControl(), fragenanzahl: new FormControl()});
  }

  ngOnInit() {
    this.fragenpaketServiceService.findAll().subscribe(data => {this.fragenpaket = data; });
  }

  onSubmit() {
    this.quizrunde.paketid = this.manageGroup.get('fragenpaket').value;
    this.quizrunde.fragenAnzahl = this.manageGroup.get('fragenanzahl').value;
    this.choosedfragenpaket.id = this.fragenpaket[this.manageGroup.get('fragenpaket').value - 1].id;
    this.choosedfragenpaket.paketname = this.fragenpaket[this.manageGroup.get('fragenpaket').value - 1].paketname;
    let wrapper: Fragenpaket[] = [this.choosedfragenpaket];
    this.quizrunde.paketid = this.choosedfragenpaket.id;
    this.quizrunde.time = this.manageGroup.get('startzeit').value;
    
    this.quizrundeService.save(this.quizrunde).subscribe();
    location.reload();

  }




}
