import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IProduit, Produit } from 'app/shared/model/produit.model';
import { ProduitService } from './produit.service';
import { IModuledefinition } from 'app/shared/model/moduledefinition.model';
import { ModuledefinitionService } from 'app/entities/moduledefinition';

@Component({
  selector: 'jhi-produit-update',
  templateUrl: './produit-update.component.html'
})
export class ProduitUpdateComponent implements OnInit {
  isSaving: boolean;

  moduledefinitions: IModuledefinition[];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required, Validators.maxLength(250)]],
    reference: [null, [Validators.required, Validators.maxLength(50)]],
    numeroordre: [null, [Validators.required]],
    typeproduit: [null, [Validators.required, Validators.maxLength(50)]],
    familleproduit: [null, [Validators.required, Validators.maxLength(50)]],
    produitadditionnel: [null, [Validators.required]],
    obligatoirepourentreprise: [null, [Validators.required]],
    effectifmax: [],
    chartegraphique: [null, [Validators.required, Validators.maxLength(15)]],
    alertetarificationexterne: [null, [Validators.maxLength(255)]],
    questionnairerecexige: [null, [Validators.required]],
    libellemodule: [null, [Validators.required, Validators.maxLength(50)]],
    nomchampbadh: [null, [Validators.maxLength(20)]],
    typequestionnairerec: [null, [Validators.maxLength(50)]],
    moduledefinitionId: [null, Validators.required]
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected produitService: ProduitService,
    protected moduledefinitionService: ModuledefinitionService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ produit }) => {
      this.updateForm(produit);
    });
    this.moduledefinitionService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IModuledefinition[]>) => mayBeOk.ok),
        map((response: HttpResponse<IModuledefinition[]>) => response.body)
      )
      .subscribe((res: IModuledefinition[]) => (this.moduledefinitions = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(produit: IProduit) {
    this.editForm.patchValue({
      id: produit.id,
      libelle: produit.libelle,
      reference: produit.reference,
      numeroordre: produit.numeroordre,
      typeproduit: produit.typeproduit,
      familleproduit: produit.familleproduit,
      produitadditionnel: produit.produitadditionnel,
      obligatoirepourentreprise: produit.obligatoirepourentreprise,
      effectifmax: produit.effectifmax,
      chartegraphique: produit.chartegraphique,
      alertetarificationexterne: produit.alertetarificationexterne,
      questionnairerecexige: produit.questionnairerecexige,
      libellemodule: produit.libellemodule,
      nomchampbadh: produit.nomchampbadh,
      typequestionnairerec: produit.typequestionnairerec,
      moduledefinitionId: produit.moduledefinitionId
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const produit = this.createFromForm();
    if (produit.id !== undefined) {
      this.subscribeToSaveResponse(this.produitService.update(produit));
    } else {
      this.subscribeToSaveResponse(this.produitService.create(produit));
    }
  }

  private createFromForm(): IProduit {
    return {
      ...new Produit(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      reference: this.editForm.get(['reference']).value,
      numeroordre: this.editForm.get(['numeroordre']).value,
      typeproduit: this.editForm.get(['typeproduit']).value,
      familleproduit: this.editForm.get(['familleproduit']).value,
      produitadditionnel: this.editForm.get(['produitadditionnel']).value,
      obligatoirepourentreprise: this.editForm.get(['obligatoirepourentreprise']).value,
      effectifmax: this.editForm.get(['effectifmax']).value,
      chartegraphique: this.editForm.get(['chartegraphique']).value,
      alertetarificationexterne: this.editForm.get(['alertetarificationexterne']).value,
      questionnairerecexige: this.editForm.get(['questionnairerecexige']).value,
      libellemodule: this.editForm.get(['libellemodule']).value,
      nomchampbadh: this.editForm.get(['nomchampbadh']).value,
      typequestionnairerec: this.editForm.get(['typequestionnairerec']).value,
      moduledefinitionId: this.editForm.get(['moduledefinitionId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduit>>) {
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

  trackModuledefinitionById(index: number, item: IModuledefinition) {
    return item.id;
  }
}
