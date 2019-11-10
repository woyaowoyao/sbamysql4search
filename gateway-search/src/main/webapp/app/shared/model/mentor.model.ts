import { Moment } from 'moment';
import { TrainStatus } from 'app/shared/model/enumerations/train-status.model';

export interface IMentor {
  id?: number;
  username?: string;
  linkedin?: string;
  regDatetime?: Moment;
  regCode?: string;
  experience?: number;
  status?: TrainStatus;
}

export class Mentor implements IMentor {
  constructor(
    public id?: number,
    public username?: string,
    public linkedin?: string,
    public regDatetime?: Moment,
    public regCode?: string,
    public experience?: number,
    public status?: TrainStatus
  ) {}
}
