package io.github.jhipster.application.service.dto;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link io.github.jhipster.application.domain.Moduledefinition} entity.
 */
public class ModuledefinitionDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String libelle;

    @Size(max = 255)
    private String description;

    @NotNull
    private Integer numeroordre;

    private Integer effectifmax;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumeroordre() {
        return numeroordre;
    }

    public void setNumeroordre(Integer numeroordre) {
        this.numeroordre = numeroordre;
    }

    public Integer getEffectifmax() {
        return effectifmax;
    }

    public void setEffectifmax(Integer effectifmax) {
        this.effectifmax = effectifmax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ModuledefinitionDTO moduledefinitionDTO = (ModuledefinitionDTO) o;
        if (moduledefinitionDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), moduledefinitionDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ModuledefinitionDTO{" +
            "id=" + getId() +
            ", libelle='" + getLibelle() + "'" +
            ", description='" + getDescription() + "'" +
            ", numeroordre=" + getNumeroordre() +
            ", effectifmax=" + getEffectifmax() +
            "}";
    }
}
