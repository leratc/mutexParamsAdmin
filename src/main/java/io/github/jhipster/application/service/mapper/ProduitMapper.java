package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {ModuledefinitionMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "moduledefinition.id", target = "moduledefinitionId")
    @Mapping(source = "moduledefinition.libelle", target = "moduledefinitionLibelle")
    ProduitDTO toDto(Produit produit);

    @Mapping(source = "moduledefinitionId", target = "moduledefinition")
    @Mapping(target = "garanties", ignore = true)
    @Mapping(target = "removeGarantie", ignore = true)
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
