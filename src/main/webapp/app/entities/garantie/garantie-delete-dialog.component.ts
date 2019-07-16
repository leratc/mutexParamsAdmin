import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGarantie } from 'app/shared/model/garantie.model';
import { GarantieService } from './garantie.service';

@Component({
  selector: 'jhi-garantie-delete-dialog',
  templateUrl: './garantie-delete-dialog.component.html'
})
export class GarantieDeleteDialogComponent {
  garantie: IGarantie;

  constructor(protected garantieService: GarantieService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.garantieService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'garantieListModification',
        content: 'Deleted an garantie'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-garantie-delete-popup',
  template: ''
})
export class GarantieDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ garantie }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(GarantieDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.garantie = garantie;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/garantie', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/garantie', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
