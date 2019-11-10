import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IPaymentRecord, PaymentRecord } from 'app/shared/model/payment-record.model';
import { PaymentRecordService } from './payment-record.service';

@Component({
  selector: 'jhi-payment-record-update',
  templateUrl: './payment-record-update.component.html'
})
export class PaymentRecordUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    txnType: [null, [Validators.required]],
    amount: [null, [Validators.required]],
    totalAmountToMentor: [null, [Validators.required]],
    issuedTime: [null, [Validators.required]],
    userId: [],
    trainRecordId: [],
    remarks: []
  });

  constructor(protected paymentRecordService: PaymentRecordService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ paymentRecord }) => {
      this.updateForm(paymentRecord);
    });
  }

  updateForm(paymentRecord: IPaymentRecord) {
    this.editForm.patchValue({
      id: paymentRecord.id,
      txnType: paymentRecord.txnType,
      amount: paymentRecord.amount,
      totalAmountToMentor: paymentRecord.totalAmountToMentor,
      issuedTime: paymentRecord.issuedTime != null ? paymentRecord.issuedTime.format(DATE_TIME_FORMAT) : null,
      userId: paymentRecord.userId,
      trainRecordId: paymentRecord.trainRecordId,
      remarks: paymentRecord.remarks
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const paymentRecord = this.createFromForm();
    if (paymentRecord.id !== undefined) {
      this.subscribeToSaveResponse(this.paymentRecordService.update(paymentRecord));
    } else {
      this.subscribeToSaveResponse(this.paymentRecordService.create(paymentRecord));
    }
  }

  private createFromForm(): IPaymentRecord {
    return {
      ...new PaymentRecord(),
      id: this.editForm.get(['id']).value,
      txnType: this.editForm.get(['txnType']).value,
      amount: this.editForm.get(['amount']).value,
      totalAmountToMentor: this.editForm.get(['totalAmountToMentor']).value,
      issuedTime:
        this.editForm.get(['issuedTime']).value != null ? moment(this.editForm.get(['issuedTime']).value, DATE_TIME_FORMAT) : undefined,
      userId: this.editForm.get(['userId']).value,
      trainRecordId: this.editForm.get(['trainRecordId']).value,
      remarks: this.editForm.get(['remarks']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPaymentRecord>>) {
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
