import { Component, OnInit} from '@angular/core';
import {WebSocketAPI} from '../../websocket/WebSocketAPI';
import $ from 'jquery';

@Component({
  selector: 'app-play',
  templateUrl: './play.component.html',
  styleUrls: ['./play.component.css']
})

export class PlayComponent implements OnInit {
  webSocketAPI: WebSocketAPI;
  mess: any;
  timeleft: any;
  id: string;
  pretimer: any;
  score = 0;
  ngOnInit() {
    this.webSocketAPI = new WebSocketAPI(new PlayComponent());
}

  connectToAll() {
    this.webSocketAPI._connect('');
  }

  connectToRoom(quiz: string) {
    if(quiz !== undefined) {
    this.webSocketAPI._connect(quiz);
    console.log('connect');


      setTimeout(() => {
        this.webSocketAPI._register(quiz, sessionStorage.authenticatedUser);
        console.log('register');
      }, 1000);

      setTimeout(() => {
          this.webSocketAPI._send(quiz, sessionStorage.authenticatedUser);
          console.log('started');
      }, 2000);
    }

    //reg
    if(quiz !== undefined) {


    }


    // start

  }

  disconnect() {
    this.webSocketAPI._disconnect();
  }

  sendScore() {
    this.webSocketAPI._sendScore(parseInt($('#chosen').data('remainingtime')), sessionStorage.authenticatedUser);
  }

  handleMessage(message) {
    this.mess = message;

    this.timeleft = message.timeleft;
    this.pretimer = message.timeLeft;


    if (this.mess.timeleft === 0) {
      $('#prog').removeAttr('value');
    } else {
      $('#prog').attr('value', this.timeleft);
    }
    if (this.timeleft !== undefined) {
      $('#proglabel').html('Noch ' + this.timeleft + ' Sekunden');
      $('#proglabel').data('timeleft' , this.timeleft );
    } else if (this.pretimer !== undefined) {
      $('#proglabel').html('Noch ' + this.pretimer + ' Sekunden');
    } else {
      $('#proglabel').html('');
    }

    if ( this.mess.neu ) {

      $('#chosen').data('chosen', 'false').data('chosenansw', '').data('remainingtime', '');
      $('.game-element button').removeClass('is-info').removeClass('is-success');

    }

    if (this.mess.fragenNr !== undefined) {
      $('.game-element').addClass('active');
      $('#qno').html('Frage ' + this.mess.fragenNr + ':');
      $('#frage').html(this.mess.frage);
      $('#antwA').html(this.mess.antwortA);
      $('#antwB').html(this.mess.antwortB);
      $('#antwC').html(this.mess.antwortC);
      $('#antwD').html(this.mess.antwortD);
    }

    if (this.mess.fragenNr === 1) {
      if ($('#chosen').data('chosen') !== 'true') {
        $('#chosen').data('chosen', 'false');
      }
    }

    if (this.mess.antwort !== undefined) {
      $('#antw' + this.mess.antwort).addClass('is-success');
      if (this.mess.antwort === $('#chosen').data('chosenansw')) {

        this.score +=  +$('#chosen').data('remainingtime');
        $('#chosen').data('score', this.score);
        $('#currentscore').html('Punkestand: ' + this.score);
        console.log('richtig! ::::');
        $('#sendscore').click();


      } else {
        console.log('falsch!');
      }
    }

    if (this.mess.punkte !== undefined) {
      $('.ans').removeClass('active');
      $('#proglabel').html('Der Gewinner ist: ' + this.mess.name);
      $('#currentscore').html('Mit einem score von: ' + this.mess.punkte);
      $('#chosen').data('chosen', 'false').data('chosenansw', '').data('remainingtime', '');
      $('.game-element button').removeClass('is-info').removeClass('is-success');

    }

  }

  markAnswer(userAnswer) {
    if ($('#chosen').data('chosen') === 'false') {
      $('#antw' + userAnswer).addClass('is-info');
      $('#chosen').data('chosen', 'true');
      $('#chosen').data('remainingtime', $('#proglabel').data('timeleft'));
      $('#chosen').data('chosenansw', userAnswer);

      console.log($('#chosen').data('remainingtime'));
    }
  }

}
