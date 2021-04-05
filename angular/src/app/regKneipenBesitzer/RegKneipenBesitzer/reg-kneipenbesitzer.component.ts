import { Component, OnInit } from '@angular/core';
import {Kneipenbesitzer} from '../../model/kneipenbesitzer';
import {FormControl, FormGroup} from '@angular/forms';
import {KneipenbesitzerService} from '../../service/kneipenbesitzer-service.service';
import {Router} from '@angular/router';



@Component({
  selector: 'app-reg-kneipenbesitzer',
  templateUrl: './reg-kneipenbesitzer.component.html',
  styleUrls: ['./reg-kneipenbesitzer.component.css']
})


export class RegKneipenbesitzerComponent implements OnInit {
  Kneipenbesitzer: Kneipenbesitzer;
  addKneipenBesitzer: FormGroup;
  Array: string[];

  constructor(private KneipenBesitzerService: KneipenbesitzerService, private router: Router) {
    this.Kneipenbesitzer = new Kneipenbesitzer();
    this.addKneipenBesitzer = new FormGroup({
      benutzername: new FormControl(),
      Pw: new FormControl(),
      eMail: new FormControl(),
    });
    this.Array = new Array();
  }

  onSubmit() {
    this.Array[0] = this.addKneipenBesitzer.get('benutzername').value;
    this.Array[1] = this.addKneipenBesitzer.get('Pw').value;
    this.Array[2] = this.addKneipenBesitzer.get('eMail').value;
    this.KneipenBesitzerService.save(this.Array).subscribe();
    //location.reload();

  }


  ngOnInit(): void {
  }

  backLogin() {
    this.router.navigate(['/']);
  }
}
