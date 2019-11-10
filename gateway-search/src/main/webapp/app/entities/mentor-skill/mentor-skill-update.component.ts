import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMentorSkill, MentorSkill } from 'app/shared/model/mentor-skill.model';
import { MentorSkillService } from './mentor-skill.service';
import { IMentor } from 'app/shared/model/mentor.model';
import { MentorService } from 'app/entities/mentor/mentor.service';

@Component({
  selector: 'jhi-mentor-skill-update',
  templateUrl: './mentor-skill-update.component.html'
})
export class MentorSkillUpdateComponent implements OnInit {
  isSaving: boolean;

  mentors: IMentor[];

  editForm = this.fb.group({
    id: [],
    selfRate: [],
    experience: [],
    mentor: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected mentorSkillService: MentorSkillService,
    protected mentorService: MentorService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mentorSkill }) => {
      this.updateForm(mentorSkill);
    });
    this.mentorService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IMentor[]>) => mayBeOk.ok),
        map((response: HttpResponse<IMentor[]>) => response.body)
      )
      .subscribe((res: IMentor[]) => (this.mentors = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(mentorSkill: IMentorSkill) {
    this.editForm.patchValue({
      id: mentorSkill.id,
      selfRate: mentorSkill.selfRate,
      experience: mentorSkill.experience,
      mentor: mentorSkill.mentor
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mentorSkill = this.createFromForm();
    if (mentorSkill.id !== undefined) {
      this.subscribeToSaveResponse(this.mentorSkillService.update(mentorSkill));
    } else {
      this.subscribeToSaveResponse(this.mentorSkillService.create(mentorSkill));
    }
  }

  private createFromForm(): IMentorSkill {
    return {
      ...new MentorSkill(),
      id: this.editForm.get(['id']).value,
      selfRate: this.editForm.get(['selfRate']).value,
      experience: this.editForm.get(['experience']).value,
      mentor: this.editForm.get(['mentor']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMentorSkill>>) {
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

  trackMentorById(index: number, item: IMentor) {
    return item.id;
  }
}
