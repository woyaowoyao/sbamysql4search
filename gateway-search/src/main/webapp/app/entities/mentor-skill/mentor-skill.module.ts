import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaysearchSharedModule } from 'app/shared/shared.module';
import { MentorSkillComponent } from './mentor-skill.component';
import { MentorSkillDetailComponent } from './mentor-skill-detail.component';
import { MentorSkillUpdateComponent } from './mentor-skill-update.component';
import { MentorSkillDeletePopupComponent, MentorSkillDeleteDialogComponent } from './mentor-skill-delete-dialog.component';
import { mentorSkillRoute, mentorSkillPopupRoute } from './mentor-skill.route';

const ENTITY_STATES = [...mentorSkillRoute, ...mentorSkillPopupRoute];

@NgModule({
  imports: [GatewaysearchSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [
    MentorSkillComponent,
    MentorSkillDetailComponent,
    MentorSkillUpdateComponent,
    MentorSkillDeleteDialogComponent,
    MentorSkillDeletePopupComponent
  ],
  entryComponents: [MentorSkillDeleteDialogComponent]
})
export class GatewaysearchMentorSkillModule {}
