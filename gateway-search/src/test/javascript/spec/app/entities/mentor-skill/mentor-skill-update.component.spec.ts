import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { MentorSkillUpdateComponent } from 'app/entities/mentor-skill/mentor-skill-update.component';
import { MentorSkillService } from 'app/entities/mentor-skill/mentor-skill.service';
import { MentorSkill } from 'app/shared/model/mentor-skill.model';

describe('Component Tests', () => {
  describe('MentorSkill Management Update Component', () => {
    let comp: MentorSkillUpdateComponent;
    let fixture: ComponentFixture<MentorSkillUpdateComponent>;
    let service: MentorSkillService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MentorSkillUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MentorSkillUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MentorSkillUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MentorSkillService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MentorSkill(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MentorSkill();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
