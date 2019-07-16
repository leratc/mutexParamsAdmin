import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPrestation } from 'app/shared/model/prestation.model';
import { PrestationService } from './prestation.service';

@Component({
  selector: 'jhi-prestation-delete-dialog',
  templateUrl: './prestation-delete-dialog.component.html'
})
export class PrestationDeleteDialogComponent {
  prestation: IPrestation;

  constructor(
    protected prestationService: PrestationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.prestationService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'prestationListModification',
        content: 'Deleted an prestation'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-prestation-delete-popup',
  template: ''
})
export class PrestationDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ prestation }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(PrestationDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.prestation = prestation;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/prestation', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/prestation', { outlets: { popup: null } }]);
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
