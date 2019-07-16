/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { ModuledefinitionDetailComponent } from 'app/entities/moduledefinition/moduledefinition-detail.component';
import { Moduledefinition } from 'app/shared/model/moduledefinition.model';

describe('Component Tests', () => {
  describe('Moduledefinition Management Detail Component', () => {
    let comp: ModuledefinitionDetailComponent;
    let fixture: ComponentFixture<ModuledefinitionDetailComponent>;
    const route = ({ data: of({ moduledefinition: new Moduledefinition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [ModuledefinitionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ModuledefinitionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModuledefinitionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.moduledefinition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
