import { Component, OnInit } from '@angular/core';
import {Fragenpaket} from '../../model/fragenpaket';
import {FragenpaketServiceService} from '../../service/fragenpaket-service.service';
import {KneipenServiceService} from '../../service/kneipen-service.service';
import {Kneipe} from '../../model/kneipe';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-add-question-package',
  templateUrl: './add-question-package.component.html',
  styleUrls: ['./add-question-package.component.css']
})
export class AddQuestionPackageComponent implements OnInit {

  fragenpaket: Fragenpaket;
  kneipen: Kneipe[];
  kneipenForm: FormGroup;

  constructor(private fragenpaketServiceService: FragenpaketServiceService,
              private kneipenServiceService: KneipenServiceService) {
    this.fragenpaket = new Fragenpaket();
    this.kneipenForm = new FormGroup({
      kneipen: new FormControl(),
      paketname: new FormControl()
    });

  }

  onSubmit() {
    this.fragenpaket.paketname = this.kneipenForm.get('paketname').value;
    this.fragenpaketServiceService.save(this.fragenpaket).subscribe();
    location.reload();
  }
  ngOnInit() {
    this.kneipenServiceService.findAll().subscribe(data => {
      this.kneipen = data;
    });
  }

}
