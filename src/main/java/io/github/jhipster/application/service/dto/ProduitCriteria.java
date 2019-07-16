package io.github.jhipster.application.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link io.github.jhipster.application.domain.Produit} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ProduitResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /produits?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ProduitCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter reference;

    private IntegerFilter numeroordre;

    private StringFilter typeproduit;

    private StringFilter familleproduit;

    private BooleanFilter produitadditionnel;

    private BooleanFilter obligatoirepourentreprise;

    private IntegerFilter effectifmax;

    private StringFilter chartegraphique;

    private StringFilter alertetarificationexterne;

    private BooleanFilter questionnairerecexige;

    private StringFilter libellemodule;

    private StringFilter nomchampbadh;

    private StringFilter typequestionnairerec;

    private LongFilter moduledefinitionId;

    private LongFilter garantieId;

    public ProduitCriteria(){
    }

    public ProduitCriteria(ProduitCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.reference = other.reference == null ? null : other.reference.copy();
        this.numeroordre = other.numeroordre == null ? null : other.numeroordre.copy();
        this.typeproduit = other.typeproduit == null ? null : other.typeproduit.copy();
        this.familleproduit = other.familleproduit == null ? null : other.familleproduit.copy();
        this.produitadditionnel = other.produitadditionnel == null ? null : other.produitadditionnel.copy();
        this.obligatoirepourentreprise = other.obligatoirepourentreprise == null ? null : other.obligatoirepourentreprise.copy();
        this.effectifmax = other.effectifmax == null ? null : other.effectifmax.copy();
        this.chartegraphique = other.chartegraphique == null ? null : other.chartegraphique.copy();
        this.alertetarificationexterne = other.alertetarificationexterne == null ? null : other.alertetarificationexterne.copy();
        this.questionnairerecexige = other.questionnairerecexige == null ? null : other.questionnairerecexige.copy();
        this.libellemodule = other.libellemodule == null ? null : other.libellemodule.copy();
        this.nomchampbadh = other.nomchampbadh == null ? null : other.nomchampbadh.copy();
        this.typequestionnairerec = other.typequestionnairerec == null ? null : other.typequestionnairerec.copy();
        this.moduledefinitionId = other.moduledefinitionId == null ? null : other.moduledefinitionId.copy();
        this.garantieId = other.garantieId == null ? null : other.garantieId.copy();
    }

    @Override
    public ProduitCriteria copy() {
        return new ProduitCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getLibelle() {
        return libelle;
    }

    public void setLibelle(StringFilter libelle) {
        this.libelle = libelle;
    }

    public StringFilter getReference() {
        return reference;
    }

    public void setReference(StringFilter reference) {
        this.reference = reference;
    }

    public IntegerFilter getNumeroordre() {
        return numeroordre;
    }

    public void setNumeroordre(IntegerFilter numeroordre) {
        this.numeroordre = numeroordre;
    }

    public StringFilter getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(StringFilter typeproduit) {
        this.typeproduit = typeproduit;
    }

    public StringFilter getFamilleproduit() {
        return familleproduit;
    }

    public void setFamilleproduit(StringFilter familleproduit) {
        this.familleproduit = familleproduit;
    }

    public BooleanFilter getProduitadditionnel() {
        return produitadditionnel;
    }

    public void setProduitadditionnel(BooleanFilter produitadditionnel) {
        this.produitadditionnel = produitadditionnel;
    }

    public BooleanFilter getObligatoirepourentreprise() {
        return obligatoirepourentreprise;
    }

    public void setObligatoirepourentreprise(BooleanFilter obligatoirepourentreprise) {
        this.obligatoirepourentreprise = obligatoirepourentreprise;
    }

    public IntegerFilter getEffectifmax() {
        return effectifmax;
    }

    public void setEffectifmax(IntegerFilter effectifmax) {
        this.effectifmax = effectifmax;
    }

    public StringFilter getChartegraphique() {
        return chartegraphique;
    }

    public void setChartegraphique(StringFilter chartegraphique) {
        this.chartegraphique = chartegraphique;
    }

    public StringFilter getAlertetarificationexterne() {
        return alertetarificationexterne;
    }

    public void setAlertetarificationexterne(StringFilter alertetarificationexterne) {
        this.alertetarificationexterne = alertetarificationexterne;
    }

    public BooleanFilter getQuestionnairerecexige() {
        return questionnairerecexige;
    }

    public void setQuestionnairerecexige(BooleanFilter questionnairerecexige) {
        this.questionnairerecexige = questionnairerecexige;
    }

    public StringFilter getLibellemodule() {
        return libellemodule;
    }

    public void setLibellemodule(StringFilter libellemodule) {
        this.libellemodule = libellemodule;
    }

    public StringFilter getNomchampbadh() {
        return nomchampbadh;
    }

    public void setNomchampbadh(StringFilter nomchampbadh) {
        this.nomchampbadh = nomchampbadh;
    }

    public StringFilter getTypequestionnairerec() {
        return typequestionnairerec;
    }

    public void setTypequestionnairerec(StringFilter typequestionnairerec) {
        this.typequestionnairerec = typequestionnairerec;
    }

    public LongFilter getModuledefinitionId() {
        return moduledefinitionId;
    }

    public void setModuledefinitionId(LongFilter moduledefinitionId) {
        this.moduledefinitionId = moduledefinitionId;
    }

    public LongFilter getGarantieId() {
        return garantieId;
    }

    public void setGarantieId(LongFilter garantieId) {
        this.garantieId = garantieId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProduitCriteria that = (ProduitCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(reference, that.reference) &&
            Objects.equals(numeroordre, that.numeroordre) &&
            Objects.equals(typeproduit, that.typeproduit) &&
            Objects.equals(familleproduit, that.familleproduit) &&
            Objects.equals(produitadditionnel, that.produitadditionnel) &&
            Objects.equals(obligatoirepourentreprise, that.obligatoirepourentreprise) &&
            Objects.equals(effectifmax, that.effectifmax) &&
            Objects.equals(chartegraphique, that.chartegraphique) &&
            Objects.equals(alertetarificationexterne, that.alertetarificationexterne) &&
            Objects.equals(questionnairerecexige, that.questionnairerecexige) &&
            Objects.equals(libellemodule, that.libellemodule) &&
            Objects.equals(nomchampbadh, that.nomchampbadh) &&
            Objects.equals(typequestionnairerec, that.typequestionnairerec) &&
            Objects.equals(moduledefinitionId, that.moduledefinitionId) &&
            Objects.equals(garantieId, that.garantieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        reference,
        numeroordre,
        typeproduit,
        familleproduit,
        produitadditionnel,
        obligatoirepourentreprise,
        effectifmax,
        chartegraphique,
        alertetarificationexterne,
        questionnairerecexige,
        libellemodule,
        nomchampbadh,
        typequestionnairerec,
        moduledefinitionId,
        garantieId
        );
    }

    @Override
    public String toString() {
        return "ProduitCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (reference != null ? "reference=" + reference + ", " : "") +
                (numeroordre != null ? "numeroordre=" + numeroordre + ", " : "") +
                (typeproduit != null ? "typeproduit=" + typeproduit + ", " : "") +
                (familleproduit != null ? "familleproduit=" + familleproduit + ", " : "") +
                (produitadditionnel != null ? "produitadditionnel=" + produitadditionnel + ", " : "") +
                (obligatoirepourentreprise != null ? "obligatoirepourentreprise=" + obligatoirepourentreprise + ", " : "") +
                (effectifmax != null ? "effectifmax=" + effectifmax + ", " : "") +
                (chartegraphique != null ? "chartegraphique=" + chartegraphique + ", " : "") +
                (alertetarificationexterne != null ? "alertetarificationexterne=" + alertetarificationexterne + ", " : "") +
                (questionnairerecexige != null ? "questionnairerecexige=" + questionnairerecexige + ", " : "") +
                (libellemodule != null ? "libellemodule=" + libellemodule + ", " : "") +
                (nomchampbadh != null ? "nomchampbadh=" + nomchampbadh + ", " : "") +
                (typequestionnairerec != null ? "typequestionnairerec=" + typequestionnairerec + ", " : "") +
                (moduledefinitionId != null ? "moduledefinitionId=" + moduledefinitionId + ", " : "") +
                (garantieId != null ? "garantieId=" + garantieId + ", " : "") +
            "}";
    }

}
