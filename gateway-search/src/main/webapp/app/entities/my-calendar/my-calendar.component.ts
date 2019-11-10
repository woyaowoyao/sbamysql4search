import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IMyCalendar } from 'app/shared/model/my-calendar.model';
import { AccountService } from 'app/core/auth/account.service';
import { MyCalendarService } from './my-calendar.service';

@Component({
  selector: 'jhi-my-calendar',
  templateUrl: './my-calendar.component.html'
})
export class MyCalendarComponent implements OnInit, OnDestroy {
  myCalendars: IMyCalendar[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected myCalendarService: MyCalendarService,
    protected eventManager: JhiEventManager,
    protected activatedRoute: ActivatedRoute,
    protected accountService: AccountService
  ) {
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll() {
    if (this.currentSearch) {
      this.myCalendarService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IMyCalendar[]>) => res.ok),
          map((res: HttpResponse<IMyCalendar[]>) => res.body)
        )
        .subscribe((res: IMyCalendar[]) => (this.myCalendars = res));
      return;
    }
    this.myCalendarService
      .query()
      .pipe(
        filter((res: HttpResponse<IMyCalendar[]>) => res.ok),
        map((res: HttpResponse<IMyCalendar[]>) => res.body)
      )
      .subscribe((res: IMyCalendar[]) => {
        this.myCalendars = res;
        this.currentSearch = '';
      });
  }

  search(query) {
    if (!query) {
      return this.clear();
    }
    this.currentSearch = query;
    this.loadAll();
  }

  clear() {
    this.currentSearch = '';
    this.loadAll();
  }

  ngOnInit() {
    this.loadAll();
    this.accountService.identity().subscribe(account => {
      this.currentAccount = account;
    });
    this.registerChangeInMyCalendars();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMyCalendar) {
    return item.id;
  }

  registerChangeInMyCalendars() {
    this.eventSubscriber = this.eventManager.subscribe('myCalendarListModification', response => this.loadAll());
  }
}
