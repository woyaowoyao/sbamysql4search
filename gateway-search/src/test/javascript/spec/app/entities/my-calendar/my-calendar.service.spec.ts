import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { MyCalendarService } from 'app/entities/my-calendar/my-calendar.service';
import { IMyCalendar, MyCalendar } from 'app/shared/model/my-calendar.model';
import { DurationType } from 'app/shared/model/enumerations/duration-type.model';

describe('Service Tests', () => {
  describe('MyCalendar Service', () => {
    let injector: TestBed;
    let service: MyCalendarService;
    let httpMock: HttpTestingController;
    let elemDefault: IMyCalendar;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(MyCalendarService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new MyCalendar(0, currentDate, DurationType.Morning0800, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            calDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a MyCalendar', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            calDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            calDate: currentDate
          },
          returnedFromService
        );
        service
          .create(new MyCalendar(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a MyCalendar', () => {
        const returnedFromService = Object.assign(
          {
            calDate: currentDate.format(DATE_TIME_FORMAT),
            duration: 'BBBBBB',
            remarks: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            calDate: currentDate
          },
          returnedFromService
        );
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of MyCalendar', () => {
        const returnedFromService = Object.assign(
          {
            calDate: currentDate.format(DATE_TIME_FORMAT),
            duration: 'BBBBBB',
            remarks: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            calDate: currentDate
          },
          returnedFromService
        );
        service
          .query(expected)
          .pipe(
            take(1),
            map(resp => resp.body)
          )
          .subscribe(body => (expectedResult = body));
        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a MyCalendar', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
