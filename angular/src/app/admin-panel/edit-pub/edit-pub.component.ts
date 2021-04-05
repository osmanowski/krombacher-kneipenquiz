import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from '@angular/forms';
import {Kneipe} from '../../model/kneipe';
import {KneipenServiceService} from '../../service/kneipen-service.service';

@Component({
  selector: 'app-edit-pub',
  templateUrl: './edit-pub.component.html',
  styleUrls: ['./edit-pub.component.css']
})
export class EditPubComponent implements OnInit {

  kneipen: Kneipe[];
  kneipe: Kneipe;
  kneipenGroup: FormGroup;

  constructor(private kneipenServiceService: KneipenServiceService) {
    this.kneipe = new Kneipe();
    this.kneipenGroup = new FormGroup({
      idKneipe: new FormControl()
    });
  }

  ngOnInit() {
    this.kneipenServiceService.findAll().subscribe(data => {
      this.kneipen = data;
    });
  }


  onSubmit() {
    this.kneipenServiceService.delete(this.kneipenGroup.get('idKneipe').value).subscribe();
    location.reload();

  }
}
