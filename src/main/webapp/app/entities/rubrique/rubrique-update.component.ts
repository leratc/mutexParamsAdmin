import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { IRubrique, Rubrique } from 'app/shared/model/rubrique.model';
import { RubriqueService } from './rubrique.service';

@Component({
  selector: 'jhi-rubrique-update',
  templateUrl: './rubrique-update.component.html'
})
export class RubriqueUpdateComponent implements OnInit {
  isSaving: boolean;

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required, Validators.maxLength(250)]],
    visible: [null, [Validators.required]],
    numeroOrdre: [null, [Validators.required]],
    tooltip: [null, [Validators.maxLength(255)]]
  });

  constructor(protected rubriqueService: RubriqueService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ rubrique }) => {
      this.updateForm(rubrique);
    });
  }

  updateForm(rubrique: IRubrique) {
    this.editForm.patchValue({
      id: rubrique.id,
      libelle: rubrique.libelle,
      visible: rubrique.visible,
      numeroOrdre: rubrique.numeroOrdre,
      tooltip: rubrique.tooltip
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const rubrique = this.createFromForm();
    if (rubrique.id !== undefined) {
      this.subscribeToSaveResponse(this.rubriqueService.update(rubrique));
    } else {
      this.subscribeToSaveResponse(this.rubriqueService.create(rubrique));
    }
  }

  private createFromForm(): IRubrique {
    return {
      ...new Rubrique(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      visible: this.editForm.get(['visible']).value,
      numeroOrdre: this.editForm.get(['numeroOrdre']).value,
      tooltip: this.editForm.get(['tooltip']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRubrique>>) {
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
