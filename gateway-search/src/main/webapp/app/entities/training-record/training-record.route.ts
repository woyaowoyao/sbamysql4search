import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { TrainingRecord } from 'app/shared/model/training-record.model';
import { TrainingRecordService } from './training-record.service';
import { TrainingRecordComponent } from './training-record.component';
import { TrainingRecordDetailComponent } from './training-record-detail.component';
import { TrainingRecordUpdateComponent } from './training-record-update.component';
import { TrainingRecordDeletePopupComponent } from './training-record-delete-dialog.component';
import { ITrainingRecord } from 'app/shared/model/training-record.model';

@Injectable({ providedIn: 'root' })
export class TrainingRecordResolve implements Resolve<ITrainingRecord> {
  constructor(private service: TrainingRecordService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ITrainingRecord> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<TrainingRecord>) => response.ok),
        map((trainingRecord: HttpResponse<TrainingRecord>) => trainingRecord.body)
      );
    }
    return of(new TrainingRecord());
  }
}

export const trainingRecordRoute: Routes = [
  {
    path: '',
    component: TrainingRecordComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'TrainingRecords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: TrainingRecordDetailComponent,
    resolve: {
      trainingRecord: TrainingRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TrainingRecords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: TrainingRecordUpdateComponent,
    resolve: {
      trainingRecord: TrainingRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TrainingRecords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: TrainingRecordUpdateComponent,
    resolve: {
      trainingRecord: TrainingRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TrainingRecords'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const trainingRecordPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: TrainingRecordDeletePopupComponent,
    resolve: {
      trainingRecord: TrainingRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'TrainingRecords'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
