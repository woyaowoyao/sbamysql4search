import { ITraining } from 'app/shared/model/training.model';
import { TrainRecordStatus } from 'app/shared/model/enumerations/train-record-status.model';
import { ProgressType } from 'app/shared/model/enumerations/progress-type.model';

export interface ITrainingRecord {
  id?: number;
  status?: TrainRecordStatus;
  progress?: ProgressType;
  commissionAmount?: number;
  avgRating?: number;
  amountReceived?: number;
  userName?: string;
  fees?: number;
  remarks?: string;
  training?: ITraining;
}

export class TrainingRecord implements ITrainingRecord {
  constructor(
    public id?: number,
    public status?: TrainRecordStatus,
    public progress?: ProgressType,
    public commissionAmount?: number,
    public avgRating?: number,
    public amountReceived?: number,
    public userName?: string,
    public fees?: number,
    public remarks?: string,
    public training?: ITraining
  ) {}
}
