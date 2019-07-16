package io.github.jhipster.application.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Rubrique.
 */
@Entity
@Table(name = "rubrique")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Rubrique implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 250)
    @Column(name = "libelle", length = 250, nullable = false)
    private String libelle;

    @NotNull
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @NotNull
    @Column(name = "numero_ordre", nullable = false)
    private Integer numeroOrdre;

    @Size(max = 255)
    @Column(name = "tooltip", length = 255)
    private String tooltip;

    @OneToMany(mappedBy = "rubrique")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Garantie> garanties = new HashSet<>();

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

    public Rubrique libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Boolean isVisible() {
        return visible;
    }

    public Rubrique visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public Rubrique numeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
        return this;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public String getTooltip() {
        return tooltip;
    }

    public Rubrique tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public Set<Garantie> getGaranties() {
        return garanties;
    }

    public Rubrique garanties(Set<Garantie> garanties) {
        this.garanties = garanties;
        return this;
    }

    public Rubrique addGarantie(Garantie garantie) {
        this.garanties.add(garantie);
        garantie.setRubrique(this);
        return this;
    }

    public Rubrique removeGarantie(Garantie garantie) {
        this.garanties.remove(garantie);
        garantie.setRubrique(null);
        return this;
    }

    public void setGaranties(Set<Garantie> garanties) {
        this.garanties = garanties;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Rubrique)) {
            return false;
        }
        return id != null && id.equals(((Rubrique) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Rubrique{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", visible='" + isVisible() + "'" +
            ", numeroOrdre=" + getNumeroOrdre() +
            ", tooltip='" + getTooltip() + "'" +
            "}";
    }
}
