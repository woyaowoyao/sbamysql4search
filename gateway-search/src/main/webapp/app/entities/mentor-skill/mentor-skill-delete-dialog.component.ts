import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IMentorSkill } from 'app/shared/model/mentor-skill.model';
import { MentorSkillService } from './mentor-skill.service';

@Component({
  selector: 'jhi-mentor-skill-delete-dialog',
  templateUrl: './mentor-skill-delete-dialog.component.html'
})
export class MentorSkillDeleteDialogComponent {
  mentorSkill: IMentorSkill;

  constructor(
    protected mentorSkillService: MentorSkillService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.mentorSkillService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'mentorSkillListModification',
        content: 'Deleted an mentorSkill'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-mentor-skill-delete-popup',
  template: ''
})
export class MentorSkillDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mentorSkill }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(MentorSkillDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.mentorSkill = mentorSkill;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/mentor-skill', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/mentor-skill', { outlets: { popup: null } }]);
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
