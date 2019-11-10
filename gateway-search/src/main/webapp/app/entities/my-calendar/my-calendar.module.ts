import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaysearchSharedModule } from 'app/shared/shared.module';
import { MyCalendarComponent } from './my-calendar.component';
import { MyCalendarDetailComponent } from './my-calendar-detail.component';
import { MyCalendarUpdateComponent } from './my-calendar-update.component';
import { MyCalendarDeletePopupComponent, MyCalendarDeleteDialogComponent } from './my-calendar-delete-dialog.component';
import { myCalendarRoute, myCalendarPopupRoute } from './my-calendar.route';

const ENTITY_STATES = [...myCalendarRoute, ...myCalendarPopupRoute];

@NgModule({
  imports: [GatewaysearchSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MyCalendarComponent,
    MyCalendarDetailComponent,
    MyCalendarUpdateComponent,
    MyCalendarDeleteDialogComponent,
    MyCalendarDeletePopupComponent
  ],
  entryComponents: [MyCalendarDeleteDialogComponent]
})
export class GatewaysearchMyCalendarModule {}
