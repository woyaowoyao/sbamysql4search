import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IMentorSkill } from 'app/shared/model/mentor-skill.model';

@Component({
  selector: 'jhi-mentor-skill-detail',
  templateUrl: './mentor-skill-detail.component.html'
})
export class MentorSkillDetailComponent implements OnInit {
  mentorSkill: IMentorSkill;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ mentorSkill }) => {
      this.mentorSkill = mentorSkill;
    });
  }

  previousState() {
    window.history.back();
  }
}
