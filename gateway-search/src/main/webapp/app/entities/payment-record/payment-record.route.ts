import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { PaymentRecord } from 'app/shared/model/payment-record.model';
import { PaymentRecordService } from './payment-record.service';
import { PaymentRecordComponent } from './payment-record.component';
import { PaymentRecordDetailComponent } from './payment-record-detail.component';
import { PaymentRecordUpdateComponent } from './payment-record-update.component';
import { PaymentRecordDeletePopupComponent } from './payment-record-delete-dialog.component';
import { IPaymentRecord } from 'app/shared/model/payment-record.model';

@Injectable({ providedIn: 'root' })
export class PaymentRecordResolve implements Resolve<IPaymentRecord> {
  constructor(private service: PaymentRecordService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IPaymentRecord> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<PaymentRecord>) => response.ok),
        map((paymentRecord: HttpResponse<PaymentRecord>) => paymentRecord.body)
      );
    }
    return of(new PaymentRecord());
  }
}

export const paymentRecordRoute: Routes = [
  {
    path: '',
    component: PaymentRecordComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: ['ROLE_USER'],
      defaultSort: 'id,asc',
      pageTitle: 'PaymentRecords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PaymentRecordDetailComponent,
    resolve: {
      paymentRecord: PaymentRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PaymentRecords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PaymentRecordUpdateComponent,
    resolve: {
      paymentRecord: PaymentRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PaymentRecords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PaymentRecordUpdateComponent,
    resolve: {
      paymentRecord: PaymentRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PaymentRecords'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const paymentRecordPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: PaymentRecordDeletePopupComponent,
    resolve: {
      paymentRecord: PaymentRecordResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'PaymentRecords'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
