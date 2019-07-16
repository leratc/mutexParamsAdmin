package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Garantie} entity.
 */
public class GarantieDTO implements Serializable {

    private Long id;

    
    @Lob
    private String libelle;

    @Lob
    private String libelleselection;

    @Lob
    private String precisionlibelleselection;

    @Size(max = 255)
    private String tooltip;

    @NotNull
    @Size(max = 50)
    private String reference;

    @NotNull
    private Boolean choisieParDefaut;

    @NotNull
    private Boolean modifiable;

    @NotNull
    private Boolean visible;

    @NotNull
    private Integer numeroOrdre;

    @Size(max = 25)
    private String filtreIdentifiant;

    @Size(max = 25)
    private String typeSpecificite;

    @Size(max = 250)
    private String libelleRisque;

    @NotNull
    @Size(max = 5)
    private String groupementTarifaire;


    private Long produitId;

    private String produitReference;

    private Long rubriqueId;

    private String rubriqueLibelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelleselection() {
        return libelleselection;
    }

    public void setLibelleselection(String libelleselection) {
        this.libelleselection = libelleselection;
    }

    public String getPrecisionlibelleselection() {
        return precisionlibelleselection;
    }

    public void setPrecisionlibelleselection(String precisionlibelleselection) {
        this.precisionlibelleselection = precisionlibelleselection;
    }

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Boolean isChoisieParDefaut() {
        return choisieParDefaut;
    }

    public void setChoisieParDefaut(Boolean choisieParDefaut) {
        this.choisieParDefaut = choisieParDefaut;
    }

    public Boolean isModifiable() {
        return modifiable;
    }

    public void setModifiable(Boolean modifiable) {
        this.modifiable = modifiable;
    }

    public Boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public String getFiltreIdentifiant() {
        return filtreIdentifiant;
    }

    public void setFiltreIdentifiant(String filtreIdentifiant) {
        this.filtreIdentifiant = filtreIdentifiant;
    }

    public String getTypeSpecificite() {
        return typeSpecificite;
    }

    public void setTypeSpecificite(String typeSpecificite) {
        this.typeSpecificite = typeSpecificite;
    }

    public String getLibelleRisque() {
        return libelleRisque;
    }

    public void setLibelleRisque(String libelleRisque) {
        this.libelleRisque = libelleRisque;
    }

    public String getGroupementTarifaire() {
        return groupementTarifaire;
    }

    public void setGroupementTarifaire(String groupementTarifaire) {
        this.groupementTarifaire = groupementTarifaire;
    }

    public Long getProduitId() {
        return produitId;
    }

    public void setProduitId(Long produitId) {
        this.produitId = produitId;
    }

    public String getProduitReference() {
        return produitReference;
    }

    public void setProduitReference(String produitReference) {
        this.produitReference = produitReference;
    }

    public Long getRubriqueId() {
        return rubriqueId;
    }

    public void setRubriqueId(Long rubriqueId) {
        this.rubriqueId = rubriqueId;
    }

    public String getRubriqueLibelle() {
        return rubriqueLibelle;
    }

    public void setRubriqueLibelle(String rubriqueLibelle) {
        this.rubriqueLibelle = rubriqueLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GarantieDTO garantieDTO = (GarantieDTO) o;
        if (garantieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), garantieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "GarantieDTO{" +
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
            ", produit=" + getProduitId() +
            ", produit='" + getProduitReference() + "'" +
            ", rubrique=" + getRubriqueId() +
            ", rubrique='" + getRubriqueLibelle() + "'" +
            "}";
    }
}
