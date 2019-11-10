import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { TrainingRecordUpdateComponent } from 'app/entities/training-record/training-record-update.component';
import { TrainingRecordService } from 'app/entities/training-record/training-record.service';
import { TrainingRecord } from 'app/shared/model/training-record.model';

describe('Component Tests', () => {
  describe('TrainingRecord Management Update Component', () => {
    let comp: TrainingRecordUpdateComponent;
    let fixture: ComponentFixture<TrainingRecordUpdateComponent>;
    let service: TrainingRecordService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [TrainingRecordUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(TrainingRecordUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TrainingRecordUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrainingRecordService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new TrainingRecord(123);
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
        const entity = new TrainingRecord();
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
