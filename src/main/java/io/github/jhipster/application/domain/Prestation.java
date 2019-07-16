package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A Prestation.
 */
@Entity
@Table(name = "prestation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Prestation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @Column(name = "libelle", length = 255)
    private String libelle;

    @Size(max = 255)
    @Column(name = "formule_libelle", length = 255)
    private String formuleLibelle;

    @Size(max = 255)
    @Column(name = "formule", length = 255)
    private String formule;

    @Size(max = 255)
    @Column(name = "formule_application", length = 255)
    private String formuleApplication;

    @NotNull
    @Column(name = "epingle_garantie", nullable = false)
    private Boolean epingleGarantie;

    @NotNull
    @Column(name = "numero_ordre", nullable = false)
    private Integer numeroOrdre;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "garantie_id")
    @JsonIgnoreProperties("prestations")
    private Garantie garantie;

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

    public Prestation libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getFormuleLibelle() {
        return formuleLibelle;
    }

    public Prestation formuleLibelle(String formuleLibelle) {
        this.formuleLibelle = formuleLibelle;
        return this;
    }

    public void setFormuleLibelle(String formuleLibelle) {
        this.formuleLibelle = formuleLibelle;
    }

    public String getFormule() {
        return formule;
    }

    public Prestation formule(String formule) {
        this.formule = formule;
        return this;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public String getFormuleApplication() {
        return formuleApplication;
    }

    public Prestation formuleApplication(String formuleApplication) {
        this.formuleApplication = formuleApplication;
        return this;
    }

    public void setFormuleApplication(String formuleApplication) {
        this.formuleApplication = formuleApplication;
    }

    public Boolean isEpingleGarantie() {
        return epingleGarantie;
    }

    public Prestation epingleGarantie(Boolean epingleGarantie) {
        this.epingleGarantie = epingleGarantie;
        return this;
    }

    public void setEpingleGarantie(Boolean epingleGarantie) {
        this.epingleGarantie = epingleGarantie;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public Prestation numeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
        return this;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public Garantie getGarantie() {
        return garantie;
    }

    public Prestation garantie(Garantie garantie) {
        this.garantie = garantie;
        return this;
    }

    public void setGarantie(Garantie garantie) {
        this.garantie = garantie;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prestation)) {
            return false;
        }
        return id != null && id.equals(((Prestation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Prestation{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", formuleLibelle='" + getFormuleLibelle() + "'" +
            ", formule='" + getFormule() + "'" +
            ", formuleApplication='" + getFormuleApplication() + "'" +
            ", epingleGarantie='" + isEpingleGarantie() + "'" +
            ", numeroOrdre=" + getNumeroOrdre() +
            "}";
    }
}
