import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { MentorSkillDetailComponent } from 'app/entities/mentor-skill/mentor-skill-detail.component';
import { MentorSkill } from 'app/shared/model/mentor-skill.model';

describe('Component Tests', () => {
  describe('MentorSkill Management Detail Component', () => {
    let comp: MentorSkillDetailComponent;
    let fixture: ComponentFixture<MentorSkillDetailComponent>;
    const route = ({ data: of({ mentorSkill: new MentorSkill(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MentorSkillDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MentorSkillDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MentorSkillDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mentorSkill).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
