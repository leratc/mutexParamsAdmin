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

import io.github.jhipster.application.domain.Prestation;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.PrestationRepository;
import io.github.jhipster.application.service.dto.PrestationCriteria;
import io.github.jhipster.application.service.dto.PrestationDTO;
import io.github.jhipster.application.service.mapper.PrestationMapper;

/**
 * Service for executing complex queries for {@link Prestation} entities in the database.
 * The main input is a {@link PrestationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PrestationDTO} or a {@link Page} of {@link PrestationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PrestationQueryService extends QueryService<Prestation> {

    private final Logger log = LoggerFactory.getLogger(PrestationQueryService.class);

    private final PrestationRepository prestationRepository;

    private final PrestationMapper prestationMapper;

    public PrestationQueryService(PrestationRepository prestationRepository, PrestationMapper prestationMapper) {
        this.prestationRepository = prestationRepository;
        this.prestationMapper = prestationMapper;
    }

    /**
     * Return a {@link List} of {@link PrestationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PrestationDTO> findByCriteria(PrestationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Prestation> specification = createSpecification(criteria);
        return prestationMapper.toDto(prestationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PrestationDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PrestationDTO> findByCriteria(PrestationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Prestation> specification = createSpecification(criteria);
        return prestationRepository.findAll(specification, page)
            .map(prestationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PrestationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Prestation> specification = createSpecification(criteria);
        return prestationRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Prestation> createSpecification(PrestationCriteria criteria) {
        Specification<Prestation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Prestation_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Prestation_.libelle));
            }
            if (criteria.getFormuleLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormuleLibelle(), Prestation_.formuleLibelle));
            }
            if (criteria.getFormule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormule(), Prestation_.formule));
            }
            if (criteria.getFormuleApplication() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormuleApplication(), Prestation_.formuleApplication));
            }
            if (criteria.getEpingleGarantie() != null) {
                specification = specification.and(buildSpecification(criteria.getEpingleGarantie(), Prestation_.epingleGarantie));
            }
            if (criteria.getNumeroOrdre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroOrdre(), Prestation_.numeroOrdre));
            }
            if (criteria.getGarantieId() != null) {
                specification = specification.and(buildSpecification(criteria.getGarantieId(),
                    root -> root.join(Prestation_.garantie, JoinType.LEFT).get(Garantie_.id)));
            }
        }
        return specification;
    }
}
