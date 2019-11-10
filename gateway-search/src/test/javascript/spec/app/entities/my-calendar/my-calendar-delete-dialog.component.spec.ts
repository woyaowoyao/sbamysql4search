import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { GatewaysearchTestModule } from '../../../test.module';
import { MyCalendarDeleteDialogComponent } from 'app/entities/my-calendar/my-calendar-delete-dialog.component';
import { MyCalendarService } from 'app/entities/my-calendar/my-calendar.service';

describe('Component Tests', () => {
  describe('MyCalendar Management Delete Component', () => {
    let comp: MyCalendarDeleteDialogComponent;
    let fixture: ComponentFixture<MyCalendarDeleteDialogComponent>;
    let service: MyCalendarService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MyCalendarDeleteDialogComponent]
      })
        .overrideTemplate(MyCalendarDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MyCalendarDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MyCalendarService);
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
