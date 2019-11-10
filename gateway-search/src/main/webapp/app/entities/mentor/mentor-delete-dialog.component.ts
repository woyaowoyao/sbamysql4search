import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMentor } from 'app/shared/model/mentor.model';
import { MentorService } from './mentor.service';

@Component({
  selector: 'jhi-mentor-delete-dialog',
  templateUrl: './mentor-delete-dialog.component.html'
})
export class MentorDeleteDialogComponent {
  mentor: IMentor;

  constructor(protected mentorService: MentorService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mentorService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mentorListModification',
        content: 'Deleted an mentor'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mentor-delete-popup',
  template: ''
})
export class MentorDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mentor }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MentorDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mentor = mentor;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/mentor', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/mentor', { outlets: { popup: null } }]);
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
