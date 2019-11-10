import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPaymentRecord } from 'app/shared/model/payment-record.model';

type EntityResponseType = HttpResponse<IPaymentRecord>;
type EntityArrayResponseType = HttpResponse<IPaymentRecord[]>;

@Injectable({ providedIn: 'root' })
export class PaymentRecordService {
  public resourceUrl = SERVER_API_URL + 'api/payment-records';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/payment-records';

  constructor(protected http: HttpClient) {}

  create(paymentRecord: IPaymentRecord): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentRecord);
    return this.http
      .post<IPaymentRecord>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(paymentRecord: IPaymentRecord): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(paymentRecord);
    return this.http
      .put<IPaymentRecord>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IPaymentRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentRecord[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IPaymentRecord[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(paymentRecord: IPaymentRecord): IPaymentRecord {
    const copy: IPaymentRecord = Object.assign({}, paymentRecord, {
      issuedTime: paymentRecord.issuedTime != null && paymentRecord.issuedTime.isValid() ? paymentRecord.issuedTime.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.issuedTime = res.body.issuedTime != null ? moment(res.body.issuedTime) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((paymentRecord: IPaymentRecord) => {
        paymentRecord.issuedTime = paymentRecord.issuedTime != null ? moment(paymentRecord.issuedTime) : null;
      });
    }
    return res;
  }
}
