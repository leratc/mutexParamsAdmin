package io.github.jhipster.application.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.Moduledefinition;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ModuledefinitionRepository;
import io.github.jhipster.application.service.dto.ModuledefinitionCriteria;
import io.github.jhipster.application.service.dto.ModuledefinitionDTO;
import io.github.jhipster.application.service.mapper.ModuledefinitionMapper;

/**
 * Service for executing complex queries for {@link Moduledefinition} entities in the database.
 * The main input is a {@link ModuledefinitionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ModuledefinitionDTO} or a {@link Page} of {@link ModuledefinitionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ModuledefinitionQueryService extends QueryService<Moduledefinition> {

    private final Logger log = LoggerFactory.getLogger(ModuledefinitionQueryService.class);

    private final ModuledefinitionRepository moduledefinitionRepository;

    private final ModuledefinitionMapper moduledefinitionMapper;

    public ModuledefinitionQueryService(ModuledefinitionRepository moduledefinitionRepository, ModuledefinitionMapper moduledefinitionMapper) {
        this.moduledefinitionRepository = moduledefinitionRepository;
        this.moduledefinitionMapper = moduledefinitionMapper;
    }

    /**
     * Return a {@link List} of {@link ModuledefinitionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ModuledefinitionDTO> findByCriteria(ModuledefinitionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Moduledefinition> specification = createSpecification(criteria);
        return moduledefinitionMapper.toDto(moduledefinitionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ModuledefinitionDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ModuledefinitionDTO> findByCriteria(ModuledefinitionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Moduledefinition> specification = createSpecification(criteria);
        return moduledefinitionRepository.findAll(specification, page)
            .map(moduledefinitionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ModuledefinitionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Moduledefinition> specification = createSpecification(criteria);
        return moduledefinitionRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Moduledefinition> createSpecification(ModuledefinitionCriteria criteria) {
        Specification<Moduledefinition> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Moduledefinition_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Moduledefinition_.libelle));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Moduledefinition_.description));
            }
            if (criteria.getNumeroordre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroordre(), Moduledefinition_.numeroordre));
            }
            if (criteria.getEffectifmax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectifmax(), Moduledefinition_.effectifmax));
            }
            if (criteria.getProduitId() != null) {
                specification = specification.and(buildSpecification(criteria.getProduitId(),
                    root -> root.join(Moduledefinition_.produits, JoinType.LEFT).get(Produit_.id)));
            }
        }
        return specification;
    }
}
