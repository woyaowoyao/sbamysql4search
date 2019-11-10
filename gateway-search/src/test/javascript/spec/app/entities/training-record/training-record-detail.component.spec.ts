import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { TrainingRecordDetailComponent } from 'app/entities/training-record/training-record-detail.component';
import { TrainingRecord } from 'app/shared/model/training-record.model';

describe('Component Tests', () => {
  describe('TrainingRecord Management Detail Component', () => {
    let comp: TrainingRecordDetailComponent;
    let fixture: ComponentFixture<TrainingRecordDetailComponent>;
    const route = ({ data: of({ trainingRecord: new TrainingRecord(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [TrainingRecordDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(TrainingRecordDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TrainingRecordDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.trainingRecord).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
