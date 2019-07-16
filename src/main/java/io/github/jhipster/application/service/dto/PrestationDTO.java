package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Prestation} entity.
 */
public class PrestationDTO implements Serializable {

    private Long id;

    @Size(max = 255)
    private String libelle;

    @Size(max = 255)
    private String formuleLibelle;

    @Size(max = 255)
    private String formule;

    @Size(max = 255)
    private String formuleApplication;

    @NotNull
    private Boolean epingleGarantie;

    @NotNull
    private Integer numeroOrdre;


    private Long garantieId;

    private String garantieReference;

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

    public String getFormuleLibelle() {
        return formuleLibelle;
    }

    public void setFormuleLibelle(String formuleLibelle) {
        this.formuleLibelle = formuleLibelle;
    }

    public String getFormule() {
        return formule;
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public String getFormuleApplication() {
        return formuleApplication;
    }

    public void setFormuleApplication(String formuleApplication) {
        this.formuleApplication = formuleApplication;
    }

    public Boolean isEpingleGarantie() {
        return epingleGarantie;
    }

    public void setEpingleGarantie(Boolean epingleGarantie) {
        this.epingleGarantie = epingleGarantie;
    }

    public Integer getNumeroOrdre() {
        return numeroOrdre;
    }

    public void setNumeroOrdre(Integer numeroOrdre) {
        this.numeroOrdre = numeroOrdre;
    }

    public Long getGarantieId() {
        return garantieId;
    }

    public void setGarantieId(Long garantieId) {
        this.garantieId = garantieId;
    }

    public String getGarantieReference() {
        return garantieReference;
    }

    public void setGarantieReference(String garantieReference) {
        this.garantieReference = garantieReference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrestationDTO prestationDTO = (PrestationDTO) o;
        if (prestationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prestationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrestationDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", formuleLibelle='" + getFormuleLibelle() + "'" +
            ", formule='" + getFormule() + "'" +
            ", formuleApplication='" + getFormuleApplication() + "'" +
            ", epingleGarantie='" + isEpingleGarantie() + "'" +
            ", numeroOrdre=" + getNumeroOrdre() +
            ", garantie=" + getGarantieId() +
            ", garantie='" + getGarantieReference() + "'" +
            "}";
    }
}
