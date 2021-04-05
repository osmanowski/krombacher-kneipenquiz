import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {PlayComponent} from '../play/play/play.component';

export class WebSocketAPI {

  chosen: any;

  webSocketEndPoint: string = 'http://localhost:8080/socket';
  topic: string = '/topic/greetings';
  stompClient: any;
  playComponent: PlayComponent;
  playquiz: string;
  constructor(playComponent: PlayComponent) {
    this.playComponent = playComponent;
  }
  _connect(quizroom: string) {
    console.log('Init Websocket Connection');
    this.playquiz = quizroom;
    let ws = new SockJS(this.webSocketEndPoint);
    this.stompClient = Stomp.over(ws);
    const _this = this;
    _this.stompClient.connect({}, function(frame) {
      if(this.room === '') {
        _this.stompClient.subscribe(_this.topic, function (sdkEvent) {
          _this.onMessageReceived(sdkEvent);
        });
      } else {
        _this.stompClient.subscribe(_this.topic + '/' + quizroom, function (sdkEvent) {
          _this.onMessageReceived(sdkEvent);
        });
      }

    }, this.errorCallBack);
  };


  _disconnect() {
    if (this.stompClient !== null) {
      this.stompClient.disconnect();
    }
    console.log('Disconnected');
  }

  // error handling / trying to reconnect
  errorCallBack(error) {
    console.log('errorCallBack -> ' + error)
    if (this.playquiz) {
      setTimeout(() => {
        this._connect(this.playquiz);
      }, 5000);
    } else {
      setTimeout(() => {
        this._connect('');
      }, 5000);
    }

  }

  // sending a message to the server
  _send(message, uid) {
    console.log('sending message!');
    this.stompClient.send('/app/hello', {}, '{"name":"' + message + '","user_id":"' + uid + '"}');
  }

  _register(message, uid) {
    this.stompClient.send('/app/registrieren', {}, '{"name":"' + message + '","user_id":"' + uid + '"}');
  }

  // sending a message to the server
  _sendTest(message, uid) {
    console.log('sending message!');
    this.stompClient.send('/app/test', {}, '{"name":"' + message + '","user_id":"' + uid + '"}');
  }

  _sendScore(score, uid) {
    this.stompClient.send('/app/score', {}, '{"user_id":"' + uid + '","score":"' + score + '"}');
  }

  onMessageReceived(message) {
    console.log(message);


    this.playComponent.handleMessage(JSON.parse(message.body));
  }
}
