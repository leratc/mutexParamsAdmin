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
 * Criteria class for the {@link io.github.jhipster.application.domain.Garantie} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.GarantieResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /garanties?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class GarantieCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter tooltip;

    private StringFilter reference;

    private BooleanFilter choisieParDefaut;

    private BooleanFilter modifiable;

    private BooleanFilter visible;

    private IntegerFilter numeroOrdre;

    private StringFilter filtreIdentifiant;

    private StringFilter typeSpecificite;

    private StringFilter libelleRisque;

    private StringFilter groupementTarifaire;

    private LongFilter produitId;

    private LongFilter rubriqueId;

    private LongFilter prestationId;

    public GarantieCriteria(){
    }

    public GarantieCriteria(GarantieCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.tooltip = other.tooltip == null ? null : other.tooltip.copy();
        this.reference = other.reference == null ? null : other.reference.copy();
        this.choisieParDefaut = other.choisieParDefaut == null ? null : other.choisieParDefaut.copy();
        this.modifiable = other.modifiable == null ? null : other.modifiable.copy();
        this.visible = other.visible == null ? null : other.visible.copy();
        this.numeroOrdre = other.numeroOrdre == null ? null : other.numeroOrdre.copy();
        this.filtreIdentifiant = other.filtreIdentifiant == null ? null : other.filtreIdentifiant.copy();
        this.typeSpecificite = other.typeSpecificite == null ? null : other.typeSpecificite.copy();
        this.libelleRisque = other.libelleRisque == null ? null : other.libelleRisque.copy();
        this.groupementTarifaire = other.groupementTarifaire == null ? null : other.groupementTarifaire.copy();
        this.produitId = other.produitId == null ? null : other.produitId.copy();
        this.rubriqueId = other.rubriqueId == null ? null : other.rubriqueId.copy();
        this.prestationId = other.prestationId == null ? null : other.prestationId.copy();
    }

    @Override
    public GarantieCriteria copy() {
        return new GarantieCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTooltip() {
        return tooltip;
    }

    public void setTooltip(StringFilter tooltip) {
        this.tooltip = tooltip;
    }

    public StringFilter getReference() {
        return reference;
    }

    public void setReference(StringFilter reference) {
        this.reference = reference;
    }

    public BooleanFilter getChoisieParDefaut() {
        return choisieParDefaut;
    }

    public void setChoisieParDefaut(BooleanFilter choisieParDefaut) {
        this.choisieParDefaut = choisieParDefaut;
    }

    public BooleanFilter getModifiable() {
        return modifiable;
    }

    public void setModifiable(BooleanFilter modifiable) {
        this.modifiable = modifiable;
    }

    public BooleanFilter getVisible() {
        return visible;
    }

    public void setVisible(BooleanFilter visible) {
        this.visible = visible;
    }

    public IntegerFilter getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(IntegerFilter numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public StringFilter getFiltreIdentifiant() {
        return filtreIdentifiant;
    }

    public void setFiltreIdentifiant(StringFilter filtreIdentifiant) {
        this.filtreIdentifiant = filtreIdentifiant;
    }

    public StringFilter getTypeSpecificite() {
        return typeSpecificite;
    }

    public void setTypeSpecificite(StringFilter typeSpecificite) {
        this.typeSpecificite = typeSpecificite;
    }

    public StringFilter getLibelleRisque() {
        return libelleRisque;
    }

    public void setLibelleRisque(StringFilter libelleRisque) {
        this.libelleRisque = libelleRisque;
    }

    public StringFilter getGroupementTarifaire() {
        return groupementTarifaire;
    }

    public void setGroupementTarifaire(StringFilter groupementTarifaire) {
        this.groupementTarifaire = groupementTarifaire;
    }

    public LongFilter getProduitId() {
        return produitId;
    }

    public void setProduitId(LongFilter produitId) {
        this.produitId = produitId;
    }

    public LongFilter getRubriqueId() {
        return rubriqueId;
    }

    public void setRubriqueId(LongFilter rubriqueId) {
        this.rubriqueId = rubriqueId;
    }

    public LongFilter getPrestationId() {
        return prestationId;
    }

    public void setPrestationId(LongFilter prestationId) {
        this.prestationId = prestationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final GarantieCriteria that = (GarantieCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(tooltip, that.tooltip) &&
            Objects.equals(reference, that.reference) &&
            Objects.equals(choisieParDefaut, that.choisieParDefaut) &&
            Objects.equals(modifiable, that.modifiable) &&
            Objects.equals(visible, that.visible) &&
            Objects.equals(numeroOrdre, that.numeroOrdre) &&
            Objects.equals(filtreIdentifiant, that.filtreIdentifiant) &&
            Objects.equals(typeSpecificite, that.typeSpecificite) &&
            Objects.equals(libelleRisque, that.libelleRisque) &&
            Objects.equals(groupementTarifaire, that.groupementTarifaire) &&
            Objects.equals(produitId, that.produitId) &&
            Objects.equals(rubriqueId, that.rubriqueId) &&
            Objects.equals(prestationId, that.prestationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        tooltip,
        reference,
        choisieParDefaut,
        modifiable,
        visible,
        numeroOrdre,
        filtreIdentifiant,
        typeSpecificite,
        libelleRisque,
        groupementTarifaire,
        produitId,
        rubriqueId,
        prestationId
        );
    }

    @Override
    public String toString() {
        return "GarantieCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (tooltip != null ? "tooltip=" + tooltip + ", " : "") +
                (reference != null ? "reference=" + reference + ", " : "") +
                (choisieParDefaut != null ? "choisieParDefaut=" + choisieParDefaut + ", " : "") +
                (modifiable != null ? "modifiable=" + modifiable + ", " : "") +
                (visible != null ? "visible=" + visible + ", " : "") +
                (numeroOrdre != null ? "numeroOrdre=" + numeroOrdre + ", " : "") +
                (filtreIdentifiant != null ? "filtreIdentifiant=" + filtreIdentifiant + ", " : "") +
                (typeSpecificite != null ? "typeSpecificite=" + typeSpecificite + ", " : "") +
                (libelleRisque != null ? "libelleRisque=" + libelleRisque + ", " : "") +
                (groupementTarifaire != null ? "groupementTarifaire=" + groupementTarifaire + ", " : "") +
                (produitId != null ? "produitId=" + produitId + ", " : "") +
                (rubriqueId != null ? "rubriqueId=" + rubriqueId + ", " : "") +
                (prestationId != null ? "prestationId=" + prestationId + ", " : "") +
            "}";
    }

}
