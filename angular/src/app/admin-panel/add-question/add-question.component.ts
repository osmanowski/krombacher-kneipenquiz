import {Component, OnInit} from '@angular/core';
import {Frage} from '../../model/frage';
import {FrageServiceService} from '../../service/frage-service.service';
import {Fragenpaket} from '../../model/fragenpaket';
import {FragenpaketServiceService} from '../../service/fragenpaket-service.service';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {


  frage: Frage;
  fragenpaket: Fragenpaket[];
  addFrage: FormGroup;
  insertionPaket: Fragenpaket;

  constructor(
    private frageServiceService: FrageServiceService,
    private fragenpaketServiceService: FragenpaketServiceService) {
    this.frage = new Frage();
    this.insertionPaket = new Fragenpaket();


    this.addFrage = new FormGroup({
      question: new FormControl(),
      answera: new FormControl(),
      answerb: new FormControl(),
      answerc: new FormControl(),
      answerd: new FormControl(),
      explanation: new FormControl(),
      correct: new FormControl(),
      questionPackage: new FormControl()
    });
  }


  onSubmit() {


    this.frage.frage = this.addFrage.get('question').value;
    this.frage.antwortA = this.addFrage.get('answera').value;
    this.frage.antwortB = this.addFrage.get('answerb').value;
    this.frage.antwortC = this.addFrage.get('answerc').value;
    this.frage.antwortD = this.addFrage.get('answerd').value;
    this.frage.erklaerung = this.addFrage.get('explanation').value;
    this.frage.richtig = this.addFrage.get('correct').value;
    this.insertionPaket.id = this.fragenpaket[this.addFrage.get('questionPackage').value - 1].id;
    this.insertionPaket.paketname = this.fragenpaket[this.addFrage.get('questionPackage').value - 1].paketname;
    let wrapper: Fragenpaket[] = [this.insertionPaket];

    this.frage.fragenPakete = wrapper;

    this.frageServiceService.save(this.frage).subscribe();


    //location.reload();

  }

  ngOnInit() {
    this.fragenpaketServiceService.findAll().subscribe(data => {
      this.fragenpaket = data;
    });
  }

}
