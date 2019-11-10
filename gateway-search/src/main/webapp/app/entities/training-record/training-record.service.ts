import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ITrainingRecord } from 'app/shared/model/training-record.model';

type EntityResponseType = HttpResponse<ITrainingRecord>;
type EntityArrayResponseType = HttpResponse<ITrainingRecord[]>;

@Injectable({ providedIn: 'root' })
export class TrainingRecordService {
  public resourceUrl = SERVER_API_URL + 'api/training-records';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/training-records';

  constructor(protected http: HttpClient) {}

  create(trainingRecord: ITrainingRecord): Observable<EntityResponseType> {
    return this.http.post<ITrainingRecord>(this.resourceUrl, trainingRecord, { observe: 'response' });
  }

  update(trainingRecord: ITrainingRecord): Observable<EntityResponseType> {
    return this.http.put<ITrainingRecord>(this.resourceUrl, trainingRecord, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ITrainingRecord>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrainingRecord[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITrainingRecord[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
