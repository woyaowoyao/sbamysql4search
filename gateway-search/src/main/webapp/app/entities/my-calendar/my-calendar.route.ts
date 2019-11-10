import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MyCalendar } from 'app/shared/model/my-calendar.model';
import { MyCalendarService } from './my-calendar.service';
import { MyCalendarComponent } from './my-calendar.component';
import { MyCalendarDetailComponent } from './my-calendar-detail.component';
import { MyCalendarUpdateComponent } from './my-calendar-update.component';
import { MyCalendarDeletePopupComponent } from './my-calendar-delete-dialog.component';
import { IMyCalendar } from 'app/shared/model/my-calendar.model';

@Injectable({ providedIn: 'root' })
export class MyCalendarResolve implements Resolve<IMyCalendar> {
  constructor(private service: MyCalendarService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMyCalendar> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MyCalendar>) => response.ok),
        map((myCalendar: HttpResponse<MyCalendar>) => myCalendar.body)
      );
    }
    return of(new MyCalendar());
  }
}

export const myCalendarRoute: Routes = [
  {
    path: '',
    component: MyCalendarComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MyCalendars'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MyCalendarDetailComponent,
    resolve: {
      myCalendar: MyCalendarResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MyCalendars'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MyCalendarUpdateComponent,
    resolve: {
      myCalendar: MyCalendarResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MyCalendars'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MyCalendarUpdateComponent,
    resolve: {
      myCalendar: MyCalendarResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MyCalendars'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const myCalendarPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MyCalendarDeletePopupComponent,
    resolve: {
      myCalendar: MyCalendarResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MyCalendars'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
