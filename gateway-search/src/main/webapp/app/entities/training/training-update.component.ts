import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { ITraining, Training } from 'app/shared/model/training.model';
import { TrainingService } from './training.service';

@Component({
  selector: 'jhi-training-update',
  templateUrl: './training-update.component.html'
})
export class TrainingUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    status: [null, [Validators.required]],
    name: [null, [Validators.required]],
    commissionAmount: [null, [Validators.required]],
    avgRating: [],
    startDate: [],
    endDate: [],
    remarks: []
  });

  constructor(protected trainingService: TrainingService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ training }) => {
      this.updateForm(training);
    });
  }

  updateForm(training: ITraining) {
    this.editForm.patchValue({
      id: training.id,
      status: training.status,
      name: training.name,
      commissionAmount: training.commissionAmount,
      avgRating: training.avgRating,
      startDate: training.startDate != null ? training.startDate.format(DATE_TIME_FORMAT) : null,
      endDate: training.endDate != null ? training.endDate.format(DATE_TIME_FORMAT) : null,
      remarks: training.remarks
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const training = this.createFromForm();
    if (training.id !== undefined) {
      this.subscribeToSaveResponse(this.trainingService.update(training));
    } else {
      this.subscribeToSaveResponse(this.trainingService.create(training));
    }
  }

  private createFromForm(): ITraining {
    return {
      ...new Training(),
      id: this.editForm.get(['id']).value,
      status: this.editForm.get(['status']).value,
      name: this.editForm.get(['name']).value,
      commissionAmount: this.editForm.get(['commissionAmount']).value,
      avgRating: this.editForm.get(['avgRating']).value,
      startDate:
        this.editForm.get(['startDate']).value != null ? moment(this.editForm.get(['startDate']).value, DATE_TIME_FORMAT) : undefined,
      endDate: this.editForm.get(['endDate']).value != null ? moment(this.editForm.get(['endDate']).value, DATE_TIME_FORMAT) : undefined,
      remarks: this.editForm.get(['remarks']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITraining>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
