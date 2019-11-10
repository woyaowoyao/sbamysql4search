import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { MyCalendarUpdateComponent } from 'app/entities/my-calendar/my-calendar-update.component';
import { MyCalendarService } from 'app/entities/my-calendar/my-calendar.service';
import { MyCalendar } from 'app/shared/model/my-calendar.model';

describe('Component Tests', () => {
  describe('MyCalendar Management Update Component', () => {
    let comp: MyCalendarUpdateComponent;
    let fixture: ComponentFixture<MyCalendarUpdateComponent>;
    let service: MyCalendarService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MyCalendarUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MyCalendarUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MyCalendarUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MyCalendarService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MyCalendar(123);
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
        const entity = new MyCalendar();
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
