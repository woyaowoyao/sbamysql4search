import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ITrainingRecord } from 'app/shared/model/training-record.model';
import { TrainingRecordService } from './training-record.service';

@Component({
  selector: 'jhi-training-record-delete-dialog',
  templateUrl: './training-record-delete-dialog.component.html'
})
export class TrainingRecordDeleteDialogComponent {
  trainingRecord: ITrainingRecord;

  constructor(
    protected trainingRecordService: TrainingRecordService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.trainingRecordService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'trainingRecordListModification',
        content: 'Deleted an trainingRecord'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-training-record-delete-popup',
  template: ''
})
export class TrainingRecordDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ trainingRecord }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(TrainingRecordDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.trainingRecord = trainingRecord;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/training-record', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/training-record', { outlets: { popup: null } }]);
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
