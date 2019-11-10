import { IMentor } from 'app/shared/model/mentor.model';

export interface IMentorSkill {
  id?: number;
  selfRate?: string;
  experience?: number;
  mentor?: IMentor;
}

export class MentorSkill implements IMentorSkill {
  constructor(public id?: number, public selfRate?: string, public experience?: number, public mentor?: IMentor) {}
}
