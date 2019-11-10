import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewaysearchTestModule } from '../../../test.module';
import { MyCalendarComponent } from 'app/entities/my-calendar/my-calendar.component';
import { MyCalendarService } from 'app/entities/my-calendar/my-calendar.service';
import { MyCalendar } from 'app/shared/model/my-calendar.model';

describe('Component Tests', () => {
  describe('MyCalendar Management Component', () => {
    let comp: MyCalendarComponent;
    let fixture: ComponentFixture<MyCalendarComponent>;
    let service: MyCalendarService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MyCalendarComponent],
        providers: []
      })
        .overrideTemplate(MyCalendarComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MyCalendarComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MyCalendarService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new MyCalendar(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.myCalendars[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
