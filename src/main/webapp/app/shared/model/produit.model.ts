import { IGarantie } from 'app/shared/model/garantie.model';

export interface IProduit {
  id?: number;
  libelle?: string;
  reference?: string;
  numeroordre?: number;
  typeproduit?: string;
  familleproduit?: string;
  produitadditionnel?: boolean;
  obligatoirepourentreprise?: boolean;
  effectifmax?: number;
  chartegraphique?: string;
  alertetarificationexterne?: string;
  questionnairerecexige?: boolean;
  libellemodule?: string;
  nomchampbadh?: string;
  typequestionnairerec?: string;
  moduledefinitionLibelle?: string;
  moduledefinitionId?: number;
  garanties?: IGarantie[];
}

export class Produit implements IProduit {
  constructor(
    public id?: number,
    public libelle?: string,
    public reference?: string,
    public numeroordre?: number,
    public typeproduit?: string,
    public familleproduit?: string,
    public produitadditionnel?: boolean,
    public obligatoirepourentreprise?: boolean,
    public effectifmax?: number,
    public chartegraphique?: string,
    public alertetarificationexterne?: string,
    public questionnairerecexige?: boolean,
    public libellemodule?: string,
    public nomchampbadh?: string,
    public typequestionnairerec?: string,
    public moduledefinitionLibelle?: string,
    public moduledefinitionId?: number,
    public garanties?: IGarantie[]
  ) {
    this.produitadditionnel = this.produitadditionnel || false;
    this.obligatoirepourentreprise = this.obligatoirepourentreprise || false;
    this.questionnairerecexige = this.questionnairerecexige || false;
  }
}
