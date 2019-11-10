import { Moment } from 'moment';
import { TrainStatus } from 'app/shared/model/enumerations/train-status.model';

export interface ITraining {
  id?: number;
  status?: TrainStatus;
  name?: string;
  commissionAmount?: number;
  avgRating?: number;
  startDate?: Moment;
  endDate?: Moment;
  remarks?: string;
}

export class Training implements ITraining {
  constructor(
    public id?: number,
    public status?: TrainStatus,
    public name?: string,
    public commissionAmount?: number,
    public avgRating?: number,
    public startDate?: Moment,
    public endDate?: Moment,
    public remarks?: string
  ) {}
}
