import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPaymentRecord } from 'app/shared/model/payment-record.model';
import { PaymentRecordService } from './payment-record.service';

@Component({
  selector: 'jhi-payment-record-delete-dialog',
  templateUrl: './payment-record-delete-dialog.component.html'
})
export class PaymentRecordDeleteDialogComponent {
  paymentRecord: IPaymentRecord;

  constructor(
    protected paymentRecordService: PaymentRecordService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.paymentRecordService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'paymentRecordListModification',
        content: 'Deleted an paymentRecord'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-payment-record-delete-popup',
  template: ''
})
export class PaymentRecordDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ paymentRecord }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PaymentRecordDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.paymentRecord = paymentRecord;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/payment-record', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/payment-record', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
