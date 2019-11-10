import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { PaymentRecordService } from 'app/entities/payment-record/payment-record.service';
import { IPaymentRecord, PaymentRecord } from 'app/shared/model/payment-record.model';
import { PayTraType } from 'app/shared/model/enumerations/pay-tra-type.model';

describe('Service Tests', () => {
  describe('PaymentRecord Service', () => {
    let injector: TestBed;
    let service: PaymentRecordService;
    let httpMock: HttpTestingController;
    let elemDefault: IPaymentRecord;
    let expectedResult;
    let currentDate: moment.Moment;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(PaymentRecordService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new PaymentRecord(0, PayTraType.PAID, 0, 0, currentDate, 0, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            issuedTime: currentDate.format(DATE_TIME_FORMAT)
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

      it('should create a PaymentRecord', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            issuedTime: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            issuedTime: currentDate
          },
          returnedFromService
        );
        service
          .create(new PaymentRecord(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a PaymentRecord', () => {
        const returnedFromService = Object.assign(
          {
            txnType: 'BBBBBB',
            amount: 1,
            totalAmountToMentor: 1,
            issuedTime: currentDate.format(DATE_TIME_FORMAT),
            userId: 1,
            trainRecordId: 1,
            remarks: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            issuedTime: currentDate
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

      it('should return a list of PaymentRecord', () => {
        const returnedFromService = Object.assign(
          {
            txnType: 'BBBBBB',
            amount: 1,
            totalAmountToMentor: 1,
            issuedTime: currentDate.format(DATE_TIME_FORMAT),
            userId: 1,
            trainRecordId: 1,
            remarks: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign(
          {
            issuedTime: currentDate
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

      it('should delete a PaymentRecord', () => {
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
