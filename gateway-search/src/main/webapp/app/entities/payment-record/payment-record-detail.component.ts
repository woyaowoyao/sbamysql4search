import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPaymentRecord } from 'app/shared/model/payment-record.model';

@Component({
  selector: 'jhi-payment-record-detail',
  templateUrl: './payment-record-detail.component.html'
})
export class PaymentRecordDetailComponent implements OnInit {
  paymentRecord: IPaymentRecord;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ paymentRecord }) => {
      this.paymentRecord = paymentRecord;
    });
  }

  previousState() {
    window.history.back();
  }
}
