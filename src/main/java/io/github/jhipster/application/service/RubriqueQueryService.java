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

import io.github.jhipster.application.domain.Rubrique;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.RubriqueRepository;
import io.github.jhipster.application.service.dto.RubriqueCriteria;
import io.github.jhipster.application.service.dto.RubriqueDTO;
import io.github.jhipster.application.service.mapper.RubriqueMapper;

/**
 * Service for executing complex queries for {@link Rubrique} entities in the database.
 * The main input is a {@link RubriqueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RubriqueDTO} or a {@link Page} of {@link RubriqueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RubriqueQueryService extends QueryService<Rubrique> {

    private final Logger log = LoggerFactory.getLogger(RubriqueQueryService.class);

    private final RubriqueRepository rubriqueRepository;

    private final RubriqueMapper rubriqueMapper;

    public RubriqueQueryService(RubriqueRepository rubriqueRepository, RubriqueMapper rubriqueMapper) {
        this.rubriqueRepository = rubriqueRepository;
        this.rubriqueMapper = rubriqueMapper;
    }

    /**
     * Return a {@link List} of {@link RubriqueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RubriqueDTO> findByCriteria(RubriqueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Rubrique> specification = createSpecification(criteria);
        return rubriqueMapper.toDto(rubriqueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RubriqueDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RubriqueDTO> findByCriteria(RubriqueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Rubrique> specification = createSpecification(criteria);
        return rubriqueRepository.findAll(specification, page)
            .map(rubriqueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RubriqueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Rubrique> specification = createSpecification(criteria);
        return rubriqueRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Rubrique> createSpecification(RubriqueCriteria criteria) {
        Specification<Rubrique> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Rubrique_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Rubrique_.libelle));
            }
            if (criteria.getVisible() != null) {
                specification = specification.and(buildSpecification(criteria.getVisible(), Rubrique_.visible));
            }
            if (criteria.getNumeroOrdre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroOrdre(), Rubrique_.numeroOrdre));
            }
            if (criteria.getTooltip() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTooltip(), Rubrique_.tooltip));
            }
            if (criteria.getGarantieId() != null) {
                specification = specification.and(buildSpecification(criteria.getGarantieId(),
                    root -> root.join(Rubrique_.garanties, JoinType.LEFT).get(Garantie_.id)));
            }
        }
        return specification;
    }
}
