import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewaysearchTestModule } from '../../../test.module';
import { PaymentRecordDeleteDialogComponent } from 'app/entities/payment-record/payment-record-delete-dialog.component';
import { PaymentRecordService } from 'app/entities/payment-record/payment-record.service';

describe('Component Tests', () => {
  describe('PaymentRecord Management Delete Component', () => {
    let comp: PaymentRecordDeleteDialogComponent;
    let fixture: ComponentFixture<PaymentRecordDeleteDialogComponent>;
    let service: PaymentRecordService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [PaymentRecordDeleteDialogComponent]
      })
        .overrideTemplate(PaymentRecordDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaymentRecordDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PaymentRecordService);
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
