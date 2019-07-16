import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IGarantie } from 'app/shared/model/garantie.model';

@Component({
  selector: 'jhi-garantie-detail',
  templateUrl: './garantie-detail.component.html'
})
export class GarantieDetailComponent implements OnInit {
  garantie: IGarantie;

  constructor(protected dataUtils: JhiDataUtils, protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ garantie }) => {
      this.garantie = garantie;
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }
  previousState() {
    window.history.back();
  }
}
