import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IModuledefinition } from 'app/shared/model/moduledefinition.model';
import { ModuledefinitionService } from './moduledefinition.service';

@Component({
  selector: 'jhi-moduledefinition-delete-dialog',
  templateUrl: './moduledefinition-delete-dialog.component.html'
})
export class ModuledefinitionDeleteDialogComponent {
  moduledefinition: IModuledefinition;

  constructor(
    protected moduledefinitionService: ModuledefinitionService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.moduledefinitionService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'moduledefinitionListModification',
        content: 'Deleted an moduledefinition'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-moduledefinition-delete-popup',
  template: ''
})
export class ModuledefinitionDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ moduledefinition }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ModuledefinitionDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.moduledefinition = moduledefinition;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/moduledefinition', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/moduledefinition', { outlets: { popup: null } }]);
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
