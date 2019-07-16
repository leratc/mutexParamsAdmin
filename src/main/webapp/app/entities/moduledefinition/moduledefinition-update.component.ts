import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IModuledefinition, Moduledefinition } from 'app/shared/model/moduledefinition.model';
import { ModuledefinitionService } from './moduledefinition.service';

@Component({
  selector: 'jhi-moduledefinition-update',
  templateUrl: './moduledefinition-update.component.html'
})
export class ModuledefinitionUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required, Validators.maxLength(255)]],
    description: [null, [Validators.maxLength(255)]],
    numeroordre: [null, [Validators.required]],
    effectifmax: []
  });

  constructor(
    protected moduledefinitionService: ModuledefinitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ moduledefinition }) => {
      this.updateForm(moduledefinition);
    });
  }

  updateForm(moduledefinition: IModuledefinition) {
    this.editForm.patchValue({
      id: moduledefinition.id,
      libelle: moduledefinition.libelle,
      description: moduledefinition.description,
      numeroordre: moduledefinition.numeroordre,
      effectifmax: moduledefinition.effectifmax
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const moduledefinition = this.createFromForm();
    if (moduledefinition.id !== undefined) {
      this.subscribeToSaveResponse(this.moduledefinitionService.update(moduledefinition));
    } else {
      this.subscribeToSaveResponse(this.moduledefinitionService.create(moduledefinition));
    }
  }

  private createFromForm(): IModuledefinition {
    return {
      ...new Moduledefinition(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      description: this.editForm.get(['description']).value,
      numeroordre: this.editForm.get(['numeroordre']).value,
      effectifmax: this.editForm.get(['effectifmax']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModuledefinition>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
}
