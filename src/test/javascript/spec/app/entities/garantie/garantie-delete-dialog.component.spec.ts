/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { MutexParamsAdminTestModule } from '../../../test.module';
import { GarantieDeleteDialogComponent } from 'app/entities/garantie/garantie-delete-dialog.component';
import { GarantieService } from 'app/entities/garantie/garantie.service';

describe('Component Tests', () => {
  describe('Garantie Management Delete Component', () => {
    let comp: GarantieDeleteDialogComponent;
    let fixture: ComponentFixture<GarantieDeleteDialogComponent>;
    let service: GarantieService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MutexParamsAdminTestModule],
        declarations: [GarantieDeleteDialogComponent]
      })
        .overrideTemplate(GarantieDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(GarantieDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(GarantieService);
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
