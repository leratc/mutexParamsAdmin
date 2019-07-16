package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Rubrique} entity.
 */
public class RubriqueDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 250)
    private String libelle;

    @NotNull
    private Boolean visible;

    @NotNull
    private Integer numeroOrdre;

    @Size(max = 255)
    private String tooltip;


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

    public String getTooltip() {
        return tooltip;
    }

    public void setTooltip(String tooltip) {
        this.tooltip = tooltip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RubriqueDTO rubriqueDTO = (RubriqueDTO) o;
        if (rubriqueDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rubriqueDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RubriqueDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", visible='" + isVisible() + "'" +
            ", numeroOrdre=" + getNumeroOrdre() +
            ", tooltip='" + getTooltip() + "'" +
            "}";
    }
}
