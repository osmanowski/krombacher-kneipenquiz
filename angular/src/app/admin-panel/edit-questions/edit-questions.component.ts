import {Component, OnInit} from '@angular/core';
import {Frage} from '../../model/frage';
import {FrageServiceService} from '../../service/frage-service.service';
import {FragenpaketServiceService} from '../../service/fragenpaket-service.service';
import {Fragenpaket} from '../../model/fragenpaket';
import {FormControl, FormGroup} from '@angular/forms';
import {init} from 'protractor/built/launcher';

@Component({
  selector: 'app-edit-questions',
  templateUrl: './edit-questions.component.html',
  styleUrls: ['./edit-questions.component.css']
})
export class EditQuestionsComponent implements OnInit {

  fragen: Frage[];
  fragenpaket: Fragenpaket[];
  frage: Frage;
  fragenGroup: FormGroup;

  constructor(private frageServiceService: FrageServiceService) {
    this.frage = new Frage();
    this.fragenGroup = new FormGroup({
      idFrage: new FormControl()
    });
  }

  ngOnInit() {
    this.frageServiceService.findAll().subscribe(data => {
      this.fragen = data;
    });
  }


  onSubmit() {
    this.frageServiceService.delete(this.fragenGroup.get('idFrage').value).subscribe();

    location.reload();

  }
}
