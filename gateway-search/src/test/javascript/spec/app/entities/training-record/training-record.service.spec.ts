import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { take, map } from 'rxjs/operators';
import { TrainingRecordService } from 'app/entities/training-record/training-record.service';
import { ITrainingRecord, TrainingRecord } from 'app/shared/model/training-record.model';
import { TrainRecordStatus } from 'app/shared/model/enumerations/train-record-status.model';
import { ProgressType } from 'app/shared/model/enumerations/progress-type.model';

describe('Service Tests', () => {
  describe('TrainingRecord Service', () => {
    let injector: TestBed;
    let service: TrainingRecordService;
    let httpMock: HttpTestingController;
    let elemDefault: ITrainingRecord;
    let expectedResult;
    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = {};
      injector = getTestBed();
      service = injector.get(TrainingRecordService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new TrainingRecord(0, TrainRecordStatus.Propose, ProgressType.One, 0, 0, 0, 'AAAAAAA', 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);
        service
          .find(123)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: elemDefault });
      });

      it('should create a TrainingRecord', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
        service
          .create(new TrainingRecord(null))
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should update a TrainingRecord', () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            progress: 'BBBBBB',
            commissionAmount: 1,
            avgRating: 1,
            amountReceived: 1,
            userName: 'BBBBBB',
            fees: 1,
            remarks: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);
        service
          .update(expected)
          .pipe(take(1))
          .subscribe(resp => (expectedResult = resp));
        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject({ body: expected });
      });

      it('should return a list of TrainingRecord', () => {
        const returnedFromService = Object.assign(
          {
            status: 'BBBBBB',
            progress: 'BBBBBB',
            commissionAmount: 1,
            avgRating: 1,
            amountReceived: 1,
            userName: 'BBBBBB',
            fees: 1,
            remarks: 'BBBBBB'
          },
          elemDefault
        );
        const expected = Object.assign({}, returnedFromService);
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

      it('should delete a TrainingRecord', () => {
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
