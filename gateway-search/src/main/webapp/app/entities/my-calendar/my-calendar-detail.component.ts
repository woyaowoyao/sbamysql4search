import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMyCalendar } from 'app/shared/model/my-calendar.model';

@Component({
  selector: 'jhi-my-calendar-detail',
  templateUrl: './my-calendar-detail.component.html'
})
export class MyCalendarDetailComponent implements OnInit {
  myCalendar: IMyCalendar;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ myCalendar }) => {
      this.myCalendar = myCalendar;
    });
  }

  previousState() {
    window.history.back();
  }
}
