package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Moduledefinition.
 */
@Entity
@Table(name = "moduledefinition")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Moduledefinition implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "libelle", length = 255, nullable = false)
    private String libelle;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;

    @NotNull
    @Column(name = "numeroordre", nullable = false)
    private Integer numeroordre;

    @Column(name = "effectifmax")
    private Integer effectifmax;

    @OneToMany(mappedBy = "moduledefinition")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Produit> produits = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public Moduledefinition libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public Moduledefinition description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumeroordre() {
        return numeroordre;
    }

    public Moduledefinition numeroordre(Integer numeroordre) {
        this.numeroordre = numeroordre;
        return this;
    }

    public void setNumeroordre(Integer numeroordre) {
        this.numeroordre = numeroordre;
    }

    public Integer getEffectifmax() {
        return effectifmax;
    }

    public Moduledefinition effectifmax(Integer effectifmax) {
        this.effectifmax = effectifmax;
        return this;
    }

    public void setEffectifmax(Integer effectifmax) {
        this.effectifmax = effectifmax;
    }

    public Set<Produit> getProduits() {
        return produits;
    }

    public Moduledefinition produits(Set<Produit> produits) {
        this.produits = produits;
        return this;
    }

    public Moduledefinition addProduit(Produit produit) {
        this.produits.add(produit);
        produit.setModuledefinition(this);
        return this;
    }

    public Moduledefinition removeProduit(Produit produit) {
        this.produits.remove(produit);
        produit.setModuledefinition(null);
        return this;
    }

    public void setProduits(Set<Produit> produits) {
        this.produits = produits;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Moduledefinition)) {
            return false;
        }
        return id != null && id.equals(((Moduledefinition) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Moduledefinition{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", numeroordre=" + getNumeroordre() +
            ", effectifmax=" + getEffectifmax() +
            "}";
    }
}
