/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { PrestationDetailComponent } from 'app/entities/prestation/prestation-detail.component';
import { Prestation } from 'app/shared/model/prestation.model';

describe('Component Tests', () => {
  describe('Prestation Management Detail Component', () => {
    let comp: PrestationDetailComponent;
    let fixture: ComponentFixture<PrestationDetailComponent>;
    const route = ({ data: of({ prestation: new Prestation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [PrestationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PrestationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrestationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.prestation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
