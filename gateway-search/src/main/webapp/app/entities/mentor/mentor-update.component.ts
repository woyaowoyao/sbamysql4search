import { Component, OnInit } from '@angular/core';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { IMentor, Mentor } from 'app/shared/model/mentor.model';
import { MentorService } from './mentor.service';

@Component({
  selector: 'jhi-mentor-update',
  templateUrl: './mentor-update.component.html'
})
export class MentorUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    username: [null, [Validators.required]],
    linkedin: [],
    regDatetime: [],
    regCode: [],
    experience: [],
    status: [null, [Validators.required]]
  });

  constructor(protected mentorService: MentorService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ mentor }) => {
      this.updateForm(mentor);
    });
  }

  updateForm(mentor: IMentor) {
    this.editForm.patchValue({
      id: mentor.id,
      username: mentor.username,
      linkedin: mentor.linkedin,
      regDatetime: mentor.regDatetime != null ? mentor.regDatetime.format(DATE_TIME_FORMAT) : null,
      regCode: mentor.regCode,
      experience: mentor.experience,
      status: mentor.status
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const mentor = this.createFromForm();
    if (mentor.id !== undefined) {
      this.subscribeToSaveResponse(this.mentorService.update(mentor));
    } else {
      this.subscribeToSaveResponse(this.mentorService.create(mentor));
    }
  }

  private createFromForm(): IMentor {
    return {
      ...new Mentor(),
      id: this.editForm.get(['id']).value,
      username: this.editForm.get(['username']).value,
      linkedin: this.editForm.get(['linkedin']).value,
      regDatetime:
        this.editForm.get(['regDatetime']).value != null ? moment(this.editForm.get(['regDatetime']).value, DATE_TIME_FORMAT) : undefined,
      regCode: this.editForm.get(['regCode']).value,
      experience: this.editForm.get(['experience']).value,
      status: this.editForm.get(['status']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMentor>>) {
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
