export interface IPrestation {
  id?: number;
  libelle?: string;
  formuleLibelle?: string;
  formule?: string;
  formuleApplication?: string;
  epingleGarantie?: boolean;
  numeroOrdre?: number;
  garantieReference?: string;
  garantieId?: number;
}

export class Prestation implements IPrestation {
  constructor(
    public id?: number,
    public libelle?: string,
    public formuleLibelle?: string,
    public formule?: string,
    public formuleApplication?: string,
    public epingleGarantie?: boolean,
    public numeroOrdre?: number,
    public garantieReference?: string,
    public garantieId?: number
  ) {
    this.epingleGarantie = this.epingleGarantie || false;
  }
}
