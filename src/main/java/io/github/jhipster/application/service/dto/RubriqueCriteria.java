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
 * Criteria class for the {@link io.github.jhipster.application.domain.Rubrique} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.RubriqueResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /rubriques?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class RubriqueCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private BooleanFilter visible;

    private IntegerFilter numeroOrdre;

    private StringFilter tooltip;

    private LongFilter garantieId;

    public RubriqueCriteria(){
    }

    public RubriqueCriteria(RubriqueCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.visible = other.visible == null ? null : other.visible.copy();
        this.numeroOrdre = other.numeroOrdre == null ? null : other.numeroOrdre.copy();
        this.tooltip = other.tooltip == null ? null : other.tooltip.copy();
        this.garantieId = other.garantieId == null ? null : other.garantieId.copy();
    }

    @Override
    public RubriqueCriteria copy() {
        return new RubriqueCriteria(this);
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

    public StringFilter getTooltip() {
        return tooltip;
    }

    public void setTooltip(StringFilter tooltip) {
        this.tooltip = tooltip;
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
        final RubriqueCriteria that = (RubriqueCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(visible, that.visible) &&
            Objects.equals(numeroOrdre, that.numeroOrdre) &&
            Objects.equals(tooltip, that.tooltip) &&
            Objects.equals(garantieId, that.garantieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        visible,
        numeroOrdre,
        tooltip,
        garantieId
        );
    }

    @Override
    public String toString() {
        return "RubriqueCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (visible != null ? "visible=" + visible + ", " : "") +
                (numeroOrdre != null ? "numeroOrdre=" + numeroOrdre + ", " : "") +
                (tooltip != null ? "tooltip=" + tooltip + ", " : "") +
                (garantieId != null ? "garantieId=" + garantieId + ", " : "") +
            "}";
    }

}
