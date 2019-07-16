package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ModuledefinitionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Moduledefinition} and its DTO {@link ModuledefinitionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ModuledefinitionMapper extends EntityMapper<ModuledefinitionDTO, Moduledefinition> {


    @Mapping(target = "produits", ignore = true)
    @Mapping(target = "removeProduit", ignore = true)
    Moduledefinition toEntity(ModuledefinitionDTO moduledefinitionDTO);

    default Moduledefinition fromId(Long id) {
        if (id == null) {
            return null;
        }
        Moduledefinition moduledefinition = new Moduledefinition();
        moduledefinition.setId(id);
        return moduledefinition;
    }
}
