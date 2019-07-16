/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { PrestationUpdateComponent } from 'app/entities/prestation/prestation-update.component';
import { PrestationService } from 'app/entities/prestation/prestation.service';
import { Prestation } from 'app/shared/model/prestation.model';

describe('Component Tests', () => {
  describe('Prestation Management Update Component', () => {
    let comp: PrestationUpdateComponent;
    let fixture: ComponentFixture<PrestationUpdateComponent>;
    let service: PrestationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [PrestationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PrestationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrestationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrestationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Prestation(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Prestation();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
