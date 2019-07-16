import { IProduit } from 'app/shared/model/produit.model';

export interface IModuledefinition {
  id?: number;
  libelle?: string;
  description?: string;
  numeroordre?: number;
  effectifmax?: number;
  produits?: IProduit[];
}

export class Moduledefinition implements IModuledefinition {
  constructor(
    public id?: number,
    public libelle?: string,
    public description?: string,
    public numeroordre?: number,
    public effectifmax?: number,
    public produits?: IProduit[]
  ) {}
}
