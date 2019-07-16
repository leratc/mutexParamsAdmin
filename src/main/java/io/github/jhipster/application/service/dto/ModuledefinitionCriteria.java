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
 * Criteria class for the {@link io.github.jhipster.application.domain.Moduledefinition} entity. This class is used
 * in {@link io.github.jhipster.application.web.rest.ModuledefinitionResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /moduledefinitions?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class ModuledefinitionCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter libelle;

    private StringFilter description;

    private IntegerFilter numeroordre;

    private IntegerFilter effectifmax;

    private LongFilter produitId;

    public ModuledefinitionCriteria(){
    }

    public ModuledefinitionCriteria(ModuledefinitionCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.libelle = other.libelle == null ? null : other.libelle.copy();
        this.description = other.description == null ? null : other.description.copy();
        this.numeroordre = other.numeroordre == null ? null : other.numeroordre.copy();
        this.effectifmax = other.effectifmax == null ? null : other.effectifmax.copy();
        this.produitId = other.produitId == null ? null : other.produitId.copy();
    }

    @Override
    public ModuledefinitionCriteria copy() {
        return new ModuledefinitionCriteria(this);
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

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IntegerFilter getNumeroordre() {
        return numeroordre;
    }

    public void setNumeroordre(IntegerFilter numeroordre) {
        this.numeroordre = numeroordre;
    }

    public IntegerFilter getEffectifmax() {
        return effectifmax;
    }

    public void setEffectifmax(IntegerFilter effectifmax) {
        this.effectifmax = effectifmax;
    }

    public LongFilter getProduitId() {
        return produitId;
    }

    public void setProduitId(LongFilter produitId) {
        this.produitId = produitId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ModuledefinitionCriteria that = (ModuledefinitionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(libelle, that.libelle) &&
            Objects.equals(description, that.description) &&
            Objects.equals(numeroordre, that.numeroordre) &&
            Objects.equals(effectifmax, that.effectifmax) &&
            Objects.equals(produitId, that.produitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        libelle,
        description,
        numeroordre,
        effectifmax,
        produitId
        );
    }

    @Override
    public String toString() {
        return "ModuledefinitionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (libelle != null ? "libelle=" + libelle + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (numeroordre != null ? "numeroordre=" + numeroordre + ", " : "") +
                (effectifmax != null ? "effectifmax=" + effectifmax + ", " : "") +
                (produitId != null ? "produitId=" + produitId + ", " : "") +
            "}";
    }

}
