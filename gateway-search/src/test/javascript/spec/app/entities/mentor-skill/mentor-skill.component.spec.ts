import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewaysearchTestModule } from '../../../test.module';
import { MentorSkillComponent } from 'app/entities/mentor-skill/mentor-skill.component';
import { MentorSkillService } from 'app/entities/mentor-skill/mentor-skill.service';
import { MentorSkill } from 'app/shared/model/mentor-skill.model';

describe('Component Tests', () => {
  describe('MentorSkill Management Component', () => {
    let comp: MentorSkillComponent;
    let fixture: ComponentFixture<MentorSkillComponent>;
    let service: MentorSkillService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MentorSkillComponent],
        providers: []
      })
        .overrideTemplate(MentorSkillComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MentorSkillComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MentorSkillService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MentorSkill(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mentorSkills[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
