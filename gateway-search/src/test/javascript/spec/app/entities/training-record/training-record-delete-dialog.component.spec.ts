import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewaysearchTestModule } from '../../../test.module';
import { TrainingRecordDeleteDialogComponent } from 'app/entities/training-record/training-record-delete-dialog.component';
import { TrainingRecordService } from 'app/entities/training-record/training-record.service';

describe('Component Tests', () => {
  describe('TrainingRecord Management Delete Component', () => {
    let comp: TrainingRecordDeleteDialogComponent;
    let fixture: ComponentFixture<TrainingRecordDeleteDialogComponent>;
    let service: TrainingRecordService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [TrainingRecordDeleteDialogComponent]
      })
        .overrideTemplate(TrainingRecordDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrainingRecordDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TrainingRecordService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
