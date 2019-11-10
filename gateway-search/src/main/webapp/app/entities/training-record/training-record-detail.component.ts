import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ITrainingRecord } from 'app/shared/model/training-record.model';

@Component({
  selector: 'jhi-training-record-detail',
  templateUrl: './training-record-detail.component.html'
})
export class TrainingRecordDetailComponent implements OnInit {
  trainingRecord: ITrainingRecord;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trainingRecord }) => {
      this.trainingRecord = trainingRecord;
    });
  }

  previousState() {
    window.history.back();
  }
}
