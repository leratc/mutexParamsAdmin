import { IGarantie } from 'app/shared/model/garantie.model';

export interface IRubrique {
  id?: number;
  libelle?: string;
  visible?: boolean;
  numeroOrdre?: number;
  tooltip?: string;
  garanties?: IGarantie[];
}

export class Rubrique implements IRubrique {
  constructor(
    public id?: number,
    public libelle?: string,
    public visible?: boolean,
    public numeroOrdre?: number,
    public tooltip?: string,
    public garanties?: IGarantie[]
  ) {
    this.visible = this.visible || false;
  }
}
