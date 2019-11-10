import { Moment } from 'moment';
import { PayTraType } from 'app/shared/model/enumerations/pay-tra-type.model';

export interface IPaymentRecord {
  id?: number;
  txnType?: PayTraType;
  amount?: number;
  totalAmountToMentor?: number;
  issuedTime?: Moment;
  userId?: number;
  trainRecordId?: number;
  remarks?: string;
}

export class PaymentRecord implements IPaymentRecord {
  constructor(
    public id?: number,
    public txnType?: PayTraType,
    public amount?: number,
    public totalAmountToMentor?: number,
    public issuedTime?: Moment,
    public userId?: number,
    public trainRecordId?: number,
    public remarks?: string
  ) {}
}
