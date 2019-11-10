import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { filter, map } from 'rxjs/operators';
import { JhiEventManager } from 'ng-jhipster';

import { IMentor } from 'app/shared/model/mentor.model';
import { AccountService } from 'app/core/auth/account.service';
import { MentorService } from './mentor.service';

@Component({
  selector: 'jhi-mentor',
  templateUrl: './mentor.component.html'
})
export class MentorComponent implements OnInit, OnDestroy {
  mentors: IMentor[];
  currentAccount: any;
  eventSubscriber: Subscription;
  currentSearch: string;

  constructor(
    protected mentorService: MentorService,
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
      this.mentorService
        .search({
          query: this.currentSearch
        })
        .pipe(
          filter((res: HttpResponse<IMentor[]>) => res.ok),
          map((res: HttpResponse<IMentor[]>) => res.body)
        )
        .subscribe((res: IMentor[]) => (this.mentors = res));
      return;
    }
    this.mentorService
      .query()
      .pipe(
        filter((res: HttpResponse<IMentor[]>) => res.ok),
        map((res: HttpResponse<IMentor[]>) => res.body)
      )
      .subscribe((res: IMentor[]) => {
        this.mentors = res;
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
    this.registerChangeInMentors();
  }

  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  trackId(index: number, item: IMentor) {
    return item.id;
  }

  registerChangeInMentors() {
    this.eventSubscriber = this.eventManager.subscribe('mentorListModification', response => this.loadAll());
  }
}
