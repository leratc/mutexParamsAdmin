package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.GarantieDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Garantie} and its DTO {@link GarantieDTO}.
 */
@Mapper(componentModel = "spring", uses = {ProduitMapper.class, RubriqueMapper.class})
public interface GarantieMapper extends EntityMapper<GarantieDTO, Garantie> {

    @Mapping(source = "produit.id", target = "produitId")
    @Mapping(source = "produit.reference", target = "produitReference")
    @Mapping(source = "rubrique.id", target = "rubriqueId")
    @Mapping(source = "rubrique.libelle", target = "rubriqueLibelle")
    GarantieDTO toDto(Garantie garantie);

    @Mapping(source = "produitId", target = "produit")
    @Mapping(source = "rubriqueId", target = "rubrique")
    @Mapping(target = "prestations")
    @Mapping(target = "removePrestation", ignore = true)
    Garantie toEntity(GarantieDTO garantieDTO);

    default Garantie fromId(Long id) {
        if (id == null) {
            return null;
        }
        Garantie garantie = new Garantie();
        garantie.setId(id);
        return garantie;
    }
}
