import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPrestation } from 'app/shared/model/prestation.model';

@Component({
  selector: 'jhi-prestation-detail',
  templateUrl: './prestation-detail.component.html'
})
export class PrestationDetailComponent implements OnInit {
  prestation: IPrestation;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ prestation }) => {
      this.prestation = prestation;
    });
  }

  previousState() {
    window.history.back();
  }
}
