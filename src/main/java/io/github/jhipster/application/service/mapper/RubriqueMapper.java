package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.RubriqueDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Rubrique} and its DTO {@link RubriqueDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RubriqueMapper extends EntityMapper<RubriqueDTO, Rubrique> {


    @Mapping(target = "garanties", ignore = true)
    @Mapping(target = "removeGarantie", ignore = true)
    Rubrique toEntity(RubriqueDTO rubriqueDTO);

    default Rubrique fromId(Long id) {
        if (id == null) {
            return null;
        }
        Rubrique rubrique = new Rubrique();
        rubrique.setId(id);
        return rubrique;
    }
}
