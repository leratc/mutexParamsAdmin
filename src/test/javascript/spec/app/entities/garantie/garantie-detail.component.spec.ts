/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { GarantieDetailComponent } from 'app/entities/garantie/garantie-detail.component';
import { Garantie } from 'app/shared/model/garantie.model';

describe('Component Tests', () => {
  describe('Garantie Management Detail Component', () => {
    let comp: GarantieDetailComponent;
    let fixture: ComponentFixture<GarantieDetailComponent>;
    const route = ({ data: of({ garantie: new Garantie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [GarantieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(GarantieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GarantieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.garantie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
