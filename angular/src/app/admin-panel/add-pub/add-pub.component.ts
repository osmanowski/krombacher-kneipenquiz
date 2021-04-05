import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Kneipe} from '../../model/kneipe';
import {KneipenServiceService} from '../../service/kneipen-service.service';
import {Kneipenbesitzer} from '../../model/kneipenbesitzer';
import {KneipenbesitzerService} from '../../service/kneipenbesitzer-service.service';
import {parse} from 'ts-node';


@Component({
  selector: 'app-add-pub',
  templateUrl: './add-pub.component.html',
  styleUrls: ['./add-pub.component.css']
})
export class AddPubComponent implements OnInit {


  kneipe: Kneipe;
  addKneipe: FormGroup;
  kneipenbesitzerliste: Kneipenbesitzer[];
  kneipenbesitzer: Kneipenbesitzer;


  constructor(
    private kneipenServiceService: KneipenServiceService, private kneipenbesitzerService: KneipenbesitzerService) {
    this.kneipe = new Kneipe();
    this.kneipenbesitzer = new Kneipenbesitzer();


    this.addKneipe = new FormGroup({
      kneipenName: new FormControl(),
      adresse: new FormControl(),
      kneipenbesitzer: new FormControl()
    });
  }


  onSubmit() {
    this.kneipe.name = this.addKneipe.get('kneipenName').value;
    this.kneipe.adresse = this.addKneipe.get('adresse').value;
    // this.kneipe.benutzerid = this.addKneipe.get('kneipenbesitzer').value;
    // this.kneipenbesitzer.benutzerId = this.kneipenbesitzerliste[this.addKneipe.get('kneipenbesitzer').value - 1].benutzerId;
    // this.kneipenbesitzer.benutzername = this.kneipenbesitzerliste[this.addKneipe.get('kneipenbesitzer').value - 1].benutzername;
    // let wrapper: Kneipenbesitzer[] = [this.kneipenbesitzer];
    // this.kneipe.benutzerid = this.kneipenbesitzer.benutzerId;
    this.kneipenServiceService.save(this.kneipe).subscribe();
    location.reload();
  }

  ngOnInit() {
    this.kneipenbesitzerService.findAll().subscribe(data => {this.kneipenbesitzerliste = data; });
  }
}
