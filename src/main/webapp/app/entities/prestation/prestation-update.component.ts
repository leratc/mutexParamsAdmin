import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IPrestation, Prestation } from 'app/shared/model/prestation.model';
import { PrestationService } from './prestation.service';
import { IGarantie } from 'app/shared/model/garantie.model';
import { GarantieService } from 'app/entities/garantie';

@Component({
  selector: 'jhi-prestation-update',
  templateUrl: './prestation-update.component.html'
})
export class PrestationUpdateComponent implements OnInit {
  isSaving: boolean;

  garanties: IGarantie[];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.maxLength(255)]],
    formuleLibelle: [null, [Validators.maxLength(255)]],
    formule: [null, [Validators.maxLength(255)]],
    formuleApplication: [null, [Validators.maxLength(255)]],
    epingleGarantie: [null, [Validators.required]],
    numeroOrdre: [null, [Validators.required]],
    garantieId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected prestationService: PrestationService,
    protected garantieService: GarantieService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ prestation }) => {
      this.updateForm(prestation);
    });
    this.garantieService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGarantie[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGarantie[]>) => response.body)
      )
      .subscribe((res: IGarantie[]) => (this.garanties = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(prestation: IPrestation) {
    this.editForm.patchValue({
      id: prestation.id,
      libelle: prestation.libelle,
      formuleLibelle: prestation.formuleLibelle,
      formule: prestation.formule,
      formuleApplication: prestation.formuleApplication,
      epingleGarantie: prestation.epingleGarantie,
      numeroOrdre: prestation.numeroOrdre,
      garantieId: prestation.garantieId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const prestation = this.createFromForm();
    if (prestation.id !== undefined) {
      this.subscribeToSaveResponse(this.prestationService.update(prestation));
    } else {
      this.subscribeToSaveResponse(this.prestationService.create(prestation));
    }
  }

  private createFromForm(): IPrestation {
    return {
      ...new Prestation(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      formuleLibelle: this.editForm.get(['formuleLibelle']).value,
      formule: this.editForm.get(['formule']).value,
      formuleApplication: this.editForm.get(['formuleApplication']).value,
      epingleGarantie: this.editForm.get(['epingleGarantie']).value,
      numeroOrdre: this.editForm.get(['numeroOrdre']).value,
      garantieId: this.editForm.get(['garantieId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPrestation>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackGarantieById(index: number, item: IGarantie) {
    return item.id;
  }
}
