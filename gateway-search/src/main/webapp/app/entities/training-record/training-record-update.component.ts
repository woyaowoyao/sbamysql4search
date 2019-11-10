import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { ITrainingRecord, TrainingRecord } from 'app/shared/model/training-record.model';
import { TrainingRecordService } from './training-record.service';
import { ITraining } from 'app/shared/model/training.model';
import { TrainingService } from 'app/entities/training/training.service';

@Component({
  selector: 'jhi-training-record-update',
  templateUrl: './training-record-update.component.html'
})
export class TrainingRecordUpdateComponent implements OnInit {
  isSaving: boolean;

  trainings: ITraining[];

  editForm = this.fb.group({
    id: [],
    status: [null, [Validators.required]],
    progress: [null, [Validators.required]],
    commissionAmount: [null, [Validators.required]],
    avgRating: [],
    amountReceived: [null, [Validators.required]],
    userName: [],
    fees: [null, [Validators.required]],
    remarks: [],
    training: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected trainingRecordService: TrainingRecordService,
    protected trainingService: TrainingService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ trainingRecord }) => {
      this.updateForm(trainingRecord);
    });
    this.trainingService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<ITraining[]>) => mayBeOk.ok),
        map((response: HttpResponse<ITraining[]>) => response.body)
      )
      .subscribe((res: ITraining[]) => (this.trainings = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(trainingRecord: ITrainingRecord) {
    this.editForm.patchValue({
      id: trainingRecord.id,
      status: trainingRecord.status,
      progress: trainingRecord.progress,
      commissionAmount: trainingRecord.commissionAmount,
      avgRating: trainingRecord.avgRating,
      amountReceived: trainingRecord.amountReceived,
      userName: trainingRecord.userName,
      fees: trainingRecord.fees,
      remarks: trainingRecord.remarks,
      training: trainingRecord.training
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const trainingRecord = this.createFromForm();
    if (trainingRecord.id !== undefined) {
      this.subscribeToSaveResponse(this.trainingRecordService.update(trainingRecord));
    } else {
      this.subscribeToSaveResponse(this.trainingRecordService.create(trainingRecord));
    }
  }

  private createFromForm(): ITrainingRecord {
    return {
      ...new TrainingRecord(),
      id: this.editForm.get(['id']).value,
      status: this.editForm.get(['status']).value,
      progress: this.editForm.get(['progress']).value,
      commissionAmount: this.editForm.get(['commissionAmount']).value,
      avgRating: this.editForm.get(['avgRating']).value,
      amountReceived: this.editForm.get(['amountReceived']).value,
      userName: this.editForm.get(['userName']).value,
      fees: this.editForm.get(['fees']).value,
      remarks: this.editForm.get(['remarks']).value,
      training: this.editForm.get(['training']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITrainingRecord>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackTrainingById(index: number, item: ITraining) {
    return item.id;
  }
}
