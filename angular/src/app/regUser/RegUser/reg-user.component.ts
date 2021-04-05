import { Component, OnInit } from '@angular/core';
import {Spieler} from '../../model/Spieler';
import {FormControl, FormGroup} from '@angular/forms';
import {SpielerServiceService} from '../../service/spieler-service.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-reg-user',
  templateUrl: './reg-user.component.html',
  styleUrls: ['./reg-user.component.css']
})
export class ReguserComponent implements OnInit {
  Spieler: Spieler;
  addSpieler: FormGroup;
  Array: string[];
  constructor(
    private spielerServiceService: SpielerServiceService,
    private router: Router,
  ) {
    this.Spieler = new Spieler();
    this.Array = new Array();

    this.addSpieler = new FormGroup({
      benutzername: new FormControl(),
      Pw: new FormControl(),
      eMail: new FormControl(),
    });
  }

  onSubmit() {
    this.Spieler.benutzername = this.addSpieler.get('benutzername').value;
    this.Spieler.Pw = this.addSpieler.get('Pw').value;
    this.Spieler.eMail = this.addSpieler.get('eMail').value;
    this.Array[0] = this.addSpieler.get('benutzername').value;
    this.Array[1] = this.addSpieler.get('Pw').value;
    this.Array[2] = this.addSpieler.get('eMail').value;
    this.spielerServiceService.regUs(this.Array).subscribe();
    //location.reload();

  }

  ngOnInit() {
  }
  backLogin() {
    this.router.navigate(['/']);
  }

}
