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

import io.github.jhipster.application.domain.Garantie;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.GarantieRepository;
import io.github.jhipster.application.service.dto.GarantieCriteria;
import io.github.jhipster.application.service.dto.GarantieDTO;
import io.github.jhipster.application.service.mapper.GarantieMapper;

/**
 * Service for executing complex queries for {@link Garantie} entities in the database.
 * The main input is a {@link GarantieCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link GarantieDTO} or a {@link Page} of {@link GarantieDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class GarantieQueryService extends QueryService<Garantie> {

    private final Logger log = LoggerFactory.getLogger(GarantieQueryService.class);

    private final GarantieRepository garantieRepository;

    private final GarantieMapper garantieMapper;

    public GarantieQueryService(GarantieRepository garantieRepository, GarantieMapper garantieMapper) {
        this.garantieRepository = garantieRepository;
        this.garantieMapper = garantieMapper;
    }

    /**
     * Return a {@link List} of {@link GarantieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<GarantieDTO> findByCriteria(GarantieCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Garantie> specification = createSpecification(criteria);
        return garantieMapper.toDto(garantieRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link GarantieDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<GarantieDTO> findByCriteria(GarantieCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Garantie> specification = createSpecification(criteria);
        return garantieRepository.findAll(specification, page)
            .map(garantieMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(GarantieCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Garantie> specification = createSpecification(criteria);
        return garantieRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Garantie> createSpecification(GarantieCriteria criteria) {
        Specification<Garantie> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Garantie_.id));
            }
            if (criteria.getTooltip() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTooltip(), Garantie_.tooltip));
            }
            if (criteria.getReference() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReference(), Garantie_.reference));
            }
            if (criteria.getChoisieParDefaut() != null) {
                specification = specification.and(buildSpecification(criteria.getChoisieParDefaut(), Garantie_.choisieParDefaut));
            }
            if (criteria.getModifiable() != null) {
                specification = specification.and(buildSpecification(criteria.getModifiable(), Garantie_.modifiable));
            }
            if (criteria.getVisible() != null) {
                specification = specification.and(buildSpecification(criteria.getVisible(), Garantie_.visible));
            }
            if (criteria.getNumeroOrdre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroOrdre(), Garantie_.numeroOrdre));
            }
            if (criteria.getFiltreIdentifiant() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFiltreIdentifiant(), Garantie_.filtreIdentifiant));
            }
            if (criteria.getTypeSpecificite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeSpecificite(), Garantie_.typeSpecificite));
            }
            if (criteria.getLibelleRisque() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelleRisque(), Garantie_.libelleRisque));
            }
            if (criteria.getGroupementTarifaire() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroupementTarifaire(), Garantie_.groupementTarifaire));
            }
            if (criteria.getProduitId() != null) {
                specification = specification.and(buildSpecification(criteria.getProduitId(),
                    root -> root.join(Garantie_.produit, JoinType.LEFT).get(Produit_.id)));
            }
            if (criteria.getRubriqueId() != null) {
                specification = specification.and(buildSpecification(criteria.getRubriqueId(),
                    root -> root.join(Garantie_.rubrique, JoinType.LEFT).get(Rubrique_.id)));
            }
            if (criteria.getPrestationId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrestationId(),
                    root -> root.join(Garantie_.prestations, JoinType.LEFT).get(Prestation_.id)));
            }
        }
        return specification;
    }
}
