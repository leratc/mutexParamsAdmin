import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IModuledefinition } from 'app/shared/model/moduledefinition.model';

@Component({
  selector: 'jhi-moduledefinition-detail',
  templateUrl: './moduledefinition-detail.component.html'
})
export class ModuledefinitionDetailComponent implements OnInit {
  moduledefinition: IModuledefinition;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ moduledefinition }) => {
      this.moduledefinition = moduledefinition;
    });
  }

  previousState() {
    window.history.back();
  }
}
