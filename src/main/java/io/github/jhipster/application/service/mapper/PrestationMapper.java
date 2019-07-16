package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.PrestationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Prestation} and its DTO {@link PrestationDTO}.
 */
@Mapper(componentModel = "spring", uses = {GarantieMapper.class})
public interface PrestationMapper extends EntityMapper<PrestationDTO, Prestation> {

    @Mapping(source = "garantie.id", target = "garantieId")
    @Mapping(source = "garantie.reference", target = "garantieReference")
    PrestationDTO toDto(Prestation prestation);

    @Mapping(source = "garantieId", target = "garantie")
    Prestation toEntity(PrestationDTO prestationDTO);

    default Prestation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Prestation prestation = new Prestation();
        prestation.setId(id);
        return prestation;
    }
}
