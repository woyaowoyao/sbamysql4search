import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewaysearchTestModule } from '../../../test.module';
import { PaymentRecordDetailComponent } from 'app/entities/payment-record/payment-record-detail.component';
import { PaymentRecord } from 'app/shared/model/payment-record.model';

describe('Component Tests', () => {
  describe('PaymentRecord Management Detail Component', () => {
    let comp: PaymentRecordDetailComponent;
    let fixture: ComponentFixture<PaymentRecordDetailComponent>;
    const route = ({ data: of({ paymentRecord: new PaymentRecord(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [PaymentRecordDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PaymentRecordDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PaymentRecordDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.paymentRecord).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
