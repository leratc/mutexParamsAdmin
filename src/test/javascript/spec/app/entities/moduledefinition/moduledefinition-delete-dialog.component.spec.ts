/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { ModuledefinitionDeleteDialogComponent } from 'app/entities/moduledefinition/moduledefinition-delete-dialog.component';
import { ModuledefinitionService } from 'app/entities/moduledefinition/moduledefinition.service';

describe('Component Tests', () => {
  describe('Moduledefinition Management Delete Component', () => {
    let comp: ModuledefinitionDeleteDialogComponent;
    let fixture: ComponentFixture<ModuledefinitionDeleteDialogComponent>;
    let service: ModuledefinitionService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [ModuledefinitionDeleteDialogComponent]
      })
        .overrideTemplate(ModuledefinitionDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ModuledefinitionDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ModuledefinitionService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
