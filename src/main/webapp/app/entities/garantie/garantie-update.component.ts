import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';
import { IGarantie, Garantie } from 'app/shared/model/garantie.model';
import { GarantieService } from './garantie.service';
import { IProduit } from 'app/shared/model/produit.model';
import { ProduitService } from 'app/entities/produit';
import { IRubrique } from 'app/shared/model/rubrique.model';
import { RubriqueService } from 'app/entities/rubrique';

@Component({
  selector: 'jhi-garantie-update',
  templateUrl: './garantie-update.component.html'
})
export class GarantieUpdateComponent implements OnInit {
  isSaving: boolean;

  produits: IProduit[];

  rubriques: IRubrique[];

  editForm = this.fb.group({
    id: [],
    libelle: [null, [Validators.required]],
    libelleselection: [],
    precisionlibelleselection: [],
    tooltip: [null, [Validators.maxLength(255)]],
    reference: [null, [Validators.required, Validators.maxLength(50)]],
    choisieParDefaut: [null, [Validators.required]],
    modifiable: [null, [Validators.required]],
    visible: [null, [Validators.required]],
    numeroOrdre: [null, [Validators.required]],
    filtreIdentifiant: [null, [Validators.maxLength(25)]],
    typeSpecificite: [null, [Validators.maxLength(25)]],
    libelleRisque: [null, [Validators.maxLength(250)]],
    groupementTarifaire: [null, [Validators.required, Validators.maxLength(5)]],
    produitId: [null, Validators.required],
    rubriqueId: [null, Validators.required]
  });

  constructor(
    protected dataUtils: JhiDataUtils,
    protected jhiAlertService: JhiAlertService,
    protected garantieService: GarantieService,
    protected produitService: ProduitService,
    protected rubriqueService: RubriqueService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ garantie }) => {
      this.updateForm(garantie);
    });
    this.produitService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IProduit[]>) => mayBeOk.ok),
        map((response: HttpResponse<IProduit[]>) => response.body)
      )
      .subscribe((res: IProduit[]) => (this.produits = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.rubriqueService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IRubrique[]>) => mayBeOk.ok),
        map((response: HttpResponse<IRubrique[]>) => response.body)
      )
      .subscribe((res: IRubrique[]) => (this.rubriques = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(garantie: IGarantie) {
    this.editForm.patchValue({
      id: garantie.id,
      libelle: garantie.libelle,
      libelleselection: garantie.libelleselection,
      precisionlibelleselection: garantie.precisionlibelleselection,
      tooltip: garantie.tooltip,
      reference: garantie.reference,
      choisieParDefaut: garantie.choisieParDefaut,
      modifiable: garantie.modifiable,
      visible: garantie.visible,
      numeroOrdre: garantie.numeroOrdre,
      filtreIdentifiant: garantie.filtreIdentifiant,
      typeSpecificite: garantie.typeSpecificite,
      libelleRisque: garantie.libelleRisque,
      groupementTarifaire: garantie.groupementTarifaire,
      produitId: garantie.produitId,
      rubriqueId: garantie.rubriqueId
    });
  }

  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }

  setFileData(event, field: string, isImage) {
    return new Promise((resolve, reject) => {
      if (event && event.target && event.target.files && event.target.files[0]) {
        const file = event.target.files[0];
        if (isImage && !/^image\//.test(file.type)) {
          reject(`File was expected to be an image but was found to be ${file.type}`);
        } else {
          const filedContentType: string = field + 'ContentType';
          this.dataUtils.toBase64(file, base64Data => {
            this.editForm.patchValue({
              [field]: base64Data,
              [filedContentType]: file.type
            });
          });
        }
      } else {
        reject(`Base64 data was not set as file could not be extracted from passed parameter: ${event}`);
      }
    }).then(
      () => console.log('blob added'), // sucess
      this.onError
    );
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const garantie = this.createFromForm();
    if (garantie.id !== undefined) {
      this.subscribeToSaveResponse(this.garantieService.update(garantie));
    } else {
      this.subscribeToSaveResponse(this.garantieService.create(garantie));
    }
  }

  private createFromForm(): IGarantie {
    return {
      ...new Garantie(),
      id: this.editForm.get(['id']).value,
      libelle: this.editForm.get(['libelle']).value,
      libelleselection: this.editForm.get(['libelleselection']).value,
      precisionlibelleselection: this.editForm.get(['precisionlibelleselection']).value,
      tooltip: this.editForm.get(['tooltip']).value,
      reference: this.editForm.get(['reference']).value,
      choisieParDefaut: this.editForm.get(['choisieParDefaut']).value,
      modifiable: this.editForm.get(['modifiable']).value,
      visible: this.editForm.get(['visible']).value,
      numeroOrdre: this.editForm.get(['numeroOrdre']).value,
      filtreIdentifiant: this.editForm.get(['filtreIdentifiant']).value,
      typeSpecificite: this.editForm.get(['typeSpecificite']).value,
      libelleRisque: this.editForm.get(['libelleRisque']).value,
      groupementTarifaire: this.editForm.get(['groupementTarifaire']).value,
      produitId: this.editForm.get(['produitId']).value,
      rubriqueId: this.editForm.get(['rubriqueId']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IGarantie>>) {
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

  trackProduitById(index: number, item: IProduit) {
    return item.id;
  }

  trackRubriqueById(index: number, item: IRubrique) {
    return item.id;
  }
}
