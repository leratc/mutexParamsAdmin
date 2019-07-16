package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String libelle;

    @NotNull
    @Size(max = 50)
    private String reference;

    @NotNull
    private Integer numeroordre;

    @NotNull
    @Size(max = 50)
    private String typeproduit;

    @NotNull
    @Size(max = 50)
    private String familleproduit;

    @NotNull
    private Boolean produitadditionnel;

    @NotNull
    private Boolean obligatoirepourentreprise;

    private Integer effectifmax;

    @NotNull
    @Size(max = 15)
    private String chartegraphique;

    @Size(max = 255)
    private String alertetarificationexterne;

    @NotNull
    private Boolean questionnairerecexige;

    @NotNull
    @Size(max = 50)
    private String libellemodule;

    @Size(max = 20)
    private String nomchampbadh;

    @Size(max = 50)
    private String typequestionnairerec;


    private Long moduledefinitionId;

    private String moduledefinitionLibelle;

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

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getNumeroordre() {
        return numeroordre;
    }

    public void setNumeroordre(Integer numeroordre) {
        this.numeroordre = numeroordre;
    }

    public String getTypeproduit() {
        return typeproduit;
    }

    public void setTypeproduit(String typeproduit) {
        this.typeproduit = typeproduit;
    }

    public String getFamilleproduit() {
        return familleproduit;
    }

    public void setFamilleproduit(String familleproduit) {
        this.familleproduit = familleproduit;
    }

    public Boolean isProduitadditionnel() {
        return produitadditionnel;
    }

    public void setProduitadditionnel(Boolean produitadditionnel) {
        this.produitadditionnel = produitadditionnel;
    }

    public Boolean isObligatoirepourentreprise() {
        return obligatoirepourentreprise;
    }

    public void setObligatoirepourentreprise(Boolean obligatoirepourentreprise) {
        this.obligatoirepourentreprise = obligatoirepourentreprise;
    }

    public Integer getEffectifmax() {
        return effectifmax;
    }

    public void setEffectifmax(Integer effectifmax) {
        this.effectifmax = effectifmax;
    }

    public String getChartegraphique() {
        return chartegraphique;
    }

    public void setChartegraphique(String chartegraphique) {
        this.chartegraphique = chartegraphique;
    }

    public String getAlertetarificationexterne() {
        return alertetarificationexterne;
    }

    public void setAlertetarificationexterne(String alertetarificationexterne) {
        this.alertetarificationexterne = alertetarificationexterne;
    }

    public Boolean isQuestionnairerecexige() {
        return questionnairerecexige;
    }

    public void setQuestionnairerecexige(Boolean questionnairerecexige) {
        this.questionnairerecexige = questionnairerecexige;
    }

    public String getLibellemodule() {
        return libellemodule;
    }

    public void setLibellemodule(String libellemodule) {
        this.libellemodule = libellemodule;
    }

    public String getNomchampbadh() {
        return nomchampbadh;
    }

    public void setNomchampbadh(String nomchampbadh) {
        this.nomchampbadh = nomchampbadh;
    }

    public String getTypequestionnairerec() {
        return typequestionnairerec;
    }

    public void setTypequestionnairerec(String typequestionnairerec) {
        this.typequestionnairerec = typequestionnairerec;
    }

    public Long getModuledefinitionId() {
        return moduledefinitionId;
    }

    public void setModuledefinitionId(Long moduledefinitionId) {
        this.moduledefinitionId = moduledefinitionId;
    }

    public String getModuledefinitionLibelle() {
        return moduledefinitionLibelle;
    }

    public void setModuledefinitionLibelle(String moduledefinitionLibelle) {
        this.moduledefinitionLibelle = moduledefinitionLibelle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ProduitDTO produitDTO = (ProduitDTO) o;
        if (produitDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), produitDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", reference='" + getReference() + "'" +
            ", numeroordre=" + getNumeroordre() +
            ", typeproduit='" + getTypeproduit() + "'" +
            ", familleproduit='" + getFamilleproduit() + "'" +
            ", produitadditionnel='" + isProduitadditionnel() + "'" +
            ", obligatoirepourentreprise='" + isObligatoirepourentreprise() + "'" +
            ", effectifmax=" + getEffectifmax() +
            ", chartegraphique='" + getChartegraphique() + "'" +
            ", alertetarificationexterne='" + getAlertetarificationexterne() + "'" +
            ", questionnairerecexige='" + isQuestionnairerecexige() + "'" +
            ", libellemodule='" + getLibellemodule() + "'" +
            ", nomchampbadh='" + getNomchampbadh() + "'" +
            ", typequestionnairerec='" + getTypequestionnairerec() + "'" +
            ", moduledefinition=" + getModuledefinitionId() +
            ", moduledefinition='" + getModuledefinitionLibelle() + "'" +
            "}";
    }
}
