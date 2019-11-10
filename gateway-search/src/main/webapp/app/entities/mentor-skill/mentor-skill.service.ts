import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IMentorSkill } from 'app/shared/model/mentor-skill.model';

type EntityResponseType = HttpResponse<IMentorSkill>;
type EntityArrayResponseType = HttpResponse<IMentorSkill[]>;

@Injectable({ providedIn: 'root' })
export class MentorSkillService {
  public resourceUrl = SERVER_API_URL + 'api/mentor-skills';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/mentor-skills';

  constructor(protected http: HttpClient) {}

  create(mentorSkill: IMentorSkill): Observable<EntityResponseType> {
    return this.http.post<IMentorSkill>(this.resourceUrl, mentorSkill, { observe: 'response' });
  }

  update(mentorSkill: IMentorSkill): Observable<EntityResponseType> {
    return this.http.put<IMentorSkill>(this.resourceUrl, mentorSkill, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IMentorSkill>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMentorSkill[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMentorSkill[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
