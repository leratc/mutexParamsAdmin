/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { ModuledefinitionUpdateComponent } from 'app/entities/moduledefinition/moduledefinition-update.component';
import { ModuledefinitionService } from 'app/entities/moduledefinition/moduledefinition.service';
import { Moduledefinition } from 'app/shared/model/moduledefinition.model';

describe('Component Tests', () => {
  describe('Moduledefinition Management Update Component', () => {
    let comp: ModuledefinitionUpdateComponent;
    let fixture: ComponentFixture<ModuledefinitionUpdateComponent>;
    let service: ModuledefinitionService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [ModuledefinitionUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ModuledefinitionUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ModuledefinitionUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModuledefinitionService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Moduledefinition(123);
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
        const entity = new Moduledefinition();
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
