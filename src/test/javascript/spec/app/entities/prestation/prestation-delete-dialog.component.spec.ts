/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { PrestationDeleteDialogComponent } from 'app/entities/prestation/prestation-delete-dialog.component';
import { PrestationService } from 'app/entities/prestation/prestation.service';

describe('Component Tests', () => {
  describe('Prestation Management Delete Component', () => {
    let comp: PrestationDeleteDialogComponent;
    let fixture: ComponentFixture<PrestationDeleteDialogComponent>;
    let service: PrestationService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [PrestationDeleteDialogComponent]
      })
        .overrideTemplate(PrestationDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PrestationDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrestationService);
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
