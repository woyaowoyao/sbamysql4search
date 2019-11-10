import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMyCalendar } from 'app/shared/model/my-calendar.model';
import { MyCalendarService } from './my-calendar.service';

@Component({
  selector: 'jhi-my-calendar-delete-dialog',
  templateUrl: './my-calendar-delete-dialog.component.html'
})
export class MyCalendarDeleteDialogComponent {
  myCalendar: IMyCalendar;

  constructor(
    protected myCalendarService: MyCalendarService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.myCalendarService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'myCalendarListModification',
        content: 'Deleted an myCalendar'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-my-calendar-delete-popup',
  template: ''
})
export class MyCalendarDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ myCalendar }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MyCalendarDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.myCalendar = myCalendar;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/my-calendar', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/my-calendar', { outlets: { popup: null } }]);
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
