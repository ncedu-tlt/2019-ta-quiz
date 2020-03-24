import {Answer} from "./Answer";

export class Question{
  constructor(
    public id:string,
    public questionName:string,
    public answers: Answer[],
  ) {}
}
