import { IPrestation } from 'app/shared/model/prestation.model';

export interface IGarantie {
  id?: number;
  libelle?: any;
  libelleselection?: any;
  precisionlibelleselection?: any;
  tooltip?: string;
  reference?: string;
  choisieParDefaut?: boolean;
  modifiable?: boolean;
  visible?: boolean;
  numeroOrdre?: number;
  filtreIdentifiant?: string;
  typeSpecificite?: string;
  libelleRisque?: string;
  groupementTarifaire?: string;
  produitReference?: string;
  produitId?: number;
  rubriqueLibelle?: string;
  rubriqueId?: number;
  prestations?: IPrestation[];
}

export class Garantie implements IGarantie {
  constructor(
    public id?: number,
    public libelle?: any,
    public libelleselection?: any,
    public precisionlibelleselection?: any,
    public tooltip?: string,
    public reference?: string,
    public choisieParDefaut?: boolean,
    public modifiable?: boolean,
    public visible?: boolean,
    public numeroOrdre?: number,
    public filtreIdentifiant?: string,
    public typeSpecificite?: string,
    public libelleRisque?: string,
    public groupementTarifaire?: string,
    public produitReference?: string,
    public produitId?: number,
    public rubriqueLibelle?: string,
    public rubriqueId?: number,
    public prestations?: IPrestation[]
  ) {
    this.choisieParDefaut = this.choisieParDefaut || false;
    this.modifiable = this.modifiable || false;
    this.visible = this.visible || false;
  }
}
