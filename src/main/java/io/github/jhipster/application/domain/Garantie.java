package io.github.jhipster.application.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Garantie.
 */
@Entity
@Table(name = "garantie")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Garantie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Lob
    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Lob
    @Column(name = "libelleselection")
    private String libelleselection;

    @Lob
    @Column(name = "precisionlibelleselection")
    private String precisionlibelleselection;

    @Size(max = 255)
    @Column(name = "tooltip", length = 255)
    private String tooltip;

    @NotNull
    @Size(max = 50)
    @Column(name = "reference", length = 50, nullable = false)
    private String reference;

    @NotNull
    @Column(name = "choisie_par_defaut", nullable = false)
    private Boolean choisieParDefaut;

    @NotNull
    @Column(name = "modifiable", nullable = false)
    private Boolean modifiable;

    @NotNull
    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @NotNull
    @Column(name = "numero_ordre", nullable = false)
    private Integer numeroOrdre;

    @Size(max = 25)
    @Column(name = "filtre_identifiant", length = 25)
    private String filtreIdentifiant;

    @Size(max = 25)
    @Column(name = "type_specificite", length = 25)
    private String typeSpecificite;

    @Size(max = 250)
    @Column(name = "libelle_risque", length = 250)
    private String libelleRisque;

    @NotNull
    @Size(max = 5)
    @Column(name = "groupement_tarifaire", length = 5, nullable = false)
    private String groupementTarifaire;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "produit_id")
    @JsonIgnoreProperties("garanties")
    private Produit produit;

    @ManyToOne(optional = false)
    @NotNull
    @JoinColumn(name = "rubrique_id")
    @JsonIgnoreProperties("garanties")
    private Rubrique rubrique;

    @OneToMany(mappedBy = "garantie")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Prestation> prestations = new HashSet<>();

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

    public Garantie libelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelleselection() {
        return libelleselection;
    }

    public Garantie libelleselection(String libelleselection) {
        this.libelleselection = libelleselection;
        return this;
    }

    public void setLibelleselection(String libelleselection) {
        this.libelleselection = libelleselection;
    }

    public String getPrecisionlibelleselection() {
        return precisionlibelleselection;
    }

    public Garantie precisionlibelleselection(String precisionlibelleselection) {
        this.precisionlibelleselection = precisionlibelleselection;
        return this;
    }

    public void setPrecisionlibelleselection(String precisionlibelleselection) {
        this.precisionlibelleselection = precisionlibelleselection;
    }

    public String getTooltip() {
        return tooltip;
    }

    public Garantie tooltip(String tooltip) {
        this.tooltip = tooltip;
        return this;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getReference() {
        return reference;
    }

    public Garantie reference(String reference) {
        this.reference = reference;
        return this;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean isChoisieParDefaut() {
        return choisieParDefaut;
    }

    public Garantie choisieParDefaut(Boolean choisieParDefaut) {
        this.choisieParDefaut = choisieParDefaut;
        return this;
    }

    public void setChoisieParDefaut(Boolean choisieParDefaut) {
        this.choisieParDefaut = choisieParDefaut;
    }

    public Boolean isModifiable() {
        return modifiable;
    }

    public Garantie modifiable(Boolean modifiable) {
        this.modifiable = modifiable;
        return this;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    public Boolean isVisible() {
        return visible;
    }

    public Garantie visible(Boolean visible) {
        this.visible = visible;
        return this;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public Garantie numeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
        return this;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public String getFiltreIdentifiant() {
        return filtreIdentifiant;
    }

    public Garantie filtreIdentifiant(String filtreIdentifiant) {
        this.filtreIdentifiant = filtreIdentifiant;
        return this;
    }

    public void setFiltreIdentifiant(String filtreIdentifiant) {
        this.filtreIdentifiant = filtreIdentifiant;
    }

    public String getTypeSpecificite() {
        return typeSpecificite;
    }

    public Garantie typeSpecificite(String typeSpecificite) {
        this.typeSpecificite = typeSpecificite;
        return this;
    }

    public void setTypeSpecificite(String typeSpecificite) {
        this.typeSpecificite = typeSpecificite;
    }

    public String getLibelleRisque() {
        return libelleRisque;
    }

    public Garantie libelleRisque(String libelleRisque) {
        this.libelleRisque = libelleRisque;
        return this;
    }

    public void setLibelleRisque(String libelleRisque) {
        this.libelleRisque = libelleRisque;
    }

    public String getGroupementTarifaire() {
        return groupementTarifaire;
    }

    public Garantie groupementTarifaire(String groupementTarifaire) {
        this.groupementTarifaire = groupementTarifaire;
        return this;
    }

    public void setGroupementTarifaire(String groupementTarifaire) {
        this.groupementTarifaire = groupementTarifaire;
    }

    public Produit getProduit() {
        return produit;
    }

    public Garantie produit(Produit produit) {
        this.produit = produit;
        return this;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Rubrique getRubrique() {
        return rubrique;
    }

    public Garantie rubrique(Rubrique rubrique) {
        this.rubrique = rubrique;
        return this;
    }

    public void setRubrique(Rubrique rubrique) {
        this.rubrique = rubrique;
    }

    public Set<Prestation> getPrestations() {
        return prestations;
    }

    public Garantie prestations(Set<Prestation> prestations) {
        this.prestations = prestations;
        return this;
    }

    public Garantie addPrestation(Prestation prestation) {
        this.prestations.add(prestation);
        prestation.setGarantie(this);
        return this;
    }

    public Garantie removePrestation(Prestation prestation) {
        this.prestations.remove(prestation);
        prestation.setGarantie(null);
        return this;
    }

    public void setPrestations(Set<Prestation> prestations) {
        this.prestations = prestations;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Garantie)) {
            return false;
        }
        return id != null && id.equals(((Garantie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Garantie{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", libelleselection='" + getLibelleselection() + "'" +
            ", precisionlibelleselection='" + getPrecisionlibelleselection() + "'" +
            ", tooltip='" + getTooltip() + "'" +
            ", reference='" + getReference() + "'" +
            ", choisieParDefaut='" + isChoisieParDefaut() + "'" +
            ", modifiable='" + isModifiable() + "'" +
            ", visible='" + isVisible() + "'" +
            ", numeroOrdre=" + getNumeroOrdre() +
            ", filtreIdentifiant='" + getFiltreIdentifiant() + "'" +
            ", typeSpecificite='" + getTypeSpecificite() + "'" +
            ", libelleRisque='" + getLibelleRisque() + "'" +
            ", groupementTarifaire='" + getGroupementTarifaire() + "'" +
            "}";
    }
}
