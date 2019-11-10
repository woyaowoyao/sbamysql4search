import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { MyCalendarDetailComponent } from 'app/entities/my-calendar/my-calendar-detail.component';
import { MyCalendar } from 'app/shared/model/my-calendar.model';

describe('Component Tests', () => {
  describe('MyCalendar Management Detail Component', () => {
    let comp: MyCalendarDetailComponent;
    let fixture: ComponentFixture<MyCalendarDetailComponent>;
    const route = ({ data: of({ myCalendar: new MyCalendar(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MyCalendarDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MyCalendarDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MyCalendarDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.myCalendar).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
