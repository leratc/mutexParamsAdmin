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
 * Criteria class for the {@link io.github.jhipster.application.domain.Prestation} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.PrestationResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /prestations?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class PrestationCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter formuleLibelle;

    private StringFilter formule;

    private StringFilter formuleApplication;

    private BooleanFilter epingleGarantie;

    private IntegerFilter numeroOrdre;

    private LongFilter garantieId;

    public PrestationCriteria(){
    }

    public PrestationCriteria(PrestationCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.formuleLibelle = other.formuleLibelle == null ? null : other.formuleLibelle.copy();
        this.formule = other.formule == null ? null : other.formule.copy();
        this.formuleApplication = other.formuleApplication == null ? null : other.formuleApplication.copy();
        this.epingleGarantie = other.epingleGarantie == null ? null : other.epingleGarantie.copy();
        this.numeroOrdre = other.numeroOrdre == null ? null : other.numeroOrdre.copy();
        this.garantieId = other.garantieId == null ? null : other.garantieId.copy();
    }

    @Override
    public PrestationCriteria copy() {
        return new PrestationCriteria(this);
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

    public StringFilter getFormuleLibelle() {
        return formuleLibelle;
    }

    public void setFormuleLibelle(StringFilter formuleLibelle) {
        this.formuleLibelle = formuleLibelle;
    }

    public StringFilter getFormule() {
        return formule;
    }

    public void setFormule(StringFilter formule) {
        this.formule = formule;
    }

    public StringFilter getFormuleApplication() {
        return formuleApplication;
    }

    public void setFormuleApplication(StringFilter formuleApplication) {
        this.formuleApplication = formuleApplication;
    }

    public BooleanFilter getEpingleGarantie() {
        return epingleGarantie;
    }

    public void setEpingleGarantie(BooleanFilter epingleGarantie) {
        this.epingleGarantie = epingleGarantie;
    }

    public IntegerFilter getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(IntegerFilter numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
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
        final PrestationCriteria that = (PrestationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(formuleLibelle, that.formuleLibelle) &&
            Objects.equals(formule, that.formule) &&
            Objects.equals(formuleApplication, that.formuleApplication) &&
            Objects.equals(epingleGarantie, that.epingleGarantie) &&
            Objects.equals(numeroOrdre, that.numeroOrdre) &&
            Objects.equals(garantieId, that.garantieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        formuleLibelle,
        formule,
        formuleApplication,
        epingleGarantie,
        numeroOrdre,
        garantieId
        );
    }

    @Override
    public String toString() {
        return "PrestationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (formuleLibelle != null ? "formuleLibelle=" + formuleLibelle + ", " : "") +
                (formule != null ? "formule=" + formule + ", " : "") +
                (formuleApplication != null ? "formuleApplication=" + formuleApplication + ", " : "") +
                (epingleGarantie != null ? "epingleGarantie=" + epingleGarantie + ", " : "") +
                (numeroOrdre != null ? "numeroOrdre=" + numeroOrdre + ", " : "") +
                (garantieId != null ? "garantieId=" + garantieId + ", " : "") +
            "}";
    }

}
