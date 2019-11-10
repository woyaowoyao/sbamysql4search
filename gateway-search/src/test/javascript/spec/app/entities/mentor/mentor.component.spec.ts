import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { GatewaysearchTestModule } from '../../../test.module';
import { MentorComponent } from 'app/entities/mentor/mentor.component';
import { MentorService } from 'app/entities/mentor/mentor.service';
import { Mentor } from 'app/shared/model/mentor.model';

describe('Component Tests', () => {
  describe('Mentor Management Component', () => {
    let comp: MentorComponent;
    let fixture: ComponentFixture<MentorComponent>;
    let service: MentorService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewaysearchTestModule],
        declarations: [MentorComponent],
        providers: []
      })
        .overrideTemplate(MentorComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MentorComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MentorService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new Mentor(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.mentors[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
