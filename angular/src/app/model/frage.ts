import {Fragenpaket} from './fragenpaket';

export class Frage {
  id: number;
  antwortA: string;
  antwortB: string;
  antwortC: string;
  antwortD: string;
  erklaerung: string;
  frage: string;
  richtig: string;
  fragenPakete: Fragenpaket[];
}
