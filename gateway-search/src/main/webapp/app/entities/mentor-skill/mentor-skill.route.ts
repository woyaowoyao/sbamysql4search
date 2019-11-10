import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { MentorSkill } from 'app/shared/model/mentor-skill.model';
import { MentorSkillService } from './mentor-skill.service';
import { MentorSkillComponent } from './mentor-skill.component';
import { MentorSkillDetailComponent } from './mentor-skill-detail.component';
import { MentorSkillUpdateComponent } from './mentor-skill-update.component';
import { MentorSkillDeletePopupComponent } from './mentor-skill-delete-dialog.component';
import { IMentorSkill } from 'app/shared/model/mentor-skill.model';

@Injectable({ providedIn: 'root' })
export class MentorSkillResolve implements Resolve<IMentorSkill> {
  constructor(private service: MentorSkillService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IMentorSkill> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<MentorSkill>) => response.ok),
        map((mentorSkill: HttpResponse<MentorSkill>) => mentorSkill.body)
      );
    }
    return of(new MentorSkill());
  }
}

export const mentorSkillRoute: Routes = [
  {
    path: '',
    component: MentorSkillComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MentorSkills'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: MentorSkillDetailComponent,
    resolve: {
      mentorSkill: MentorSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MentorSkills'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: MentorSkillUpdateComponent,
    resolve: {
      mentorSkill: MentorSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MentorSkills'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: MentorSkillUpdateComponent,
    resolve: {
      mentorSkill: MentorSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MentorSkills'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const mentorSkillPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: MentorSkillDeletePopupComponent,
    resolve: {
      mentorSkill: MentorSkillResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'MentorSkills'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
