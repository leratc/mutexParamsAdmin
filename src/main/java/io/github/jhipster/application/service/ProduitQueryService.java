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

import io.github.jhipster.application.domain.Produit;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ProduitRepository;
import io.github.jhipster.application.service.dto.ProduitCriteria;
import io.github.jhipster.application.service.dto.ProduitDTO;
import io.github.jhipster.application.service.mapper.ProduitMapper;

/**
 * Service for executing complex queries for {@link Produit} entities in the database.
 * The main input is a {@link ProduitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ProduitDTO} or a {@link Page} of {@link ProduitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ProduitQueryService extends QueryService<Produit> {

    private final Logger log = LoggerFactory.getLogger(ProduitQueryService.class);

    private final ProduitRepository produitRepository;

    private final ProduitMapper produitMapper;

    public ProduitQueryService(ProduitRepository produitRepository, ProduitMapper produitMapper) {
        this.produitRepository = produitRepository;
        this.produitMapper = produitMapper;
    }

    /**
     * Return a {@link List} of {@link ProduitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ProduitDTO> findByCriteria(ProduitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Produit> specification = createSpecification(criteria);
        return produitMapper.toDto(produitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ProduitDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ProduitDTO> findByCriteria(ProduitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Produit> specification = createSpecification(criteria);
        return produitRepository.findAll(specification, page)
            .map(produitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ProduitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Produit> specification = createSpecification(criteria);
        return produitRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<Produit> createSpecification(ProduitCriteria criteria) {
        Specification<Produit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Produit_.id));
            }
            if (criteria.getLibelle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibelle(), Produit_.libelle));
            }
            if (criteria.getReference() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReference(), Produit_.reference));
            }
            if (criteria.getNumeroordre() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumeroordre(), Produit_.numeroordre));
            }
            if (criteria.getTypeproduit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeproduit(), Produit_.typeproduit));
            }
            if (criteria.getFamilleproduit() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamilleproduit(), Produit_.familleproduit));
            }
            if (criteria.getProduitadditionnel() != null) {
                specification = specification.and(buildSpecification(criteria.getProduitadditionnel(), Produit_.produitadditionnel));
            }
            if (criteria.getObligatoirepourentreprise() != null) {
                specification = specification.and(buildSpecification(criteria.getObligatoirepourentreprise(), Produit_.obligatoirepourentreprise));
            }
            if (criteria.getEffectifmax() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectifmax(), Produit_.effectifmax));
            }
            if (criteria.getChartegraphique() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChartegraphique(), Produit_.chartegraphique));
            }
            if (criteria.getAlertetarificationexterne() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAlertetarificationexterne(), Produit_.alertetarificationexterne));
            }
            if (criteria.getQuestionnairerecexige() != null) {
                specification = specification.and(buildSpecification(criteria.getQuestionnairerecexige(), Produit_.questionnairerecexige));
            }
            if (criteria.getLibellemodule() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLibellemodule(), Produit_.libellemodule));
            }
            if (criteria.getNomchampbadh() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNomchampbadh(), Produit_.nomchampbadh));
            }
            if (criteria.getTypequestionnairerec() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypequestionnairerec(), Produit_.typequestionnairerec));
            }
            if (criteria.getModuledefinitionId() != null) {
                specification = specification.and(buildSpecification(criteria.getModuledefinitionId(),
                    root -> root.join(Produit_.moduledefinition, JoinType.LEFT).get(Moduledefinition_.id)));
            }
            if (criteria.getGarantieId() != null) {
                specification = specification.and(buildSpecification(criteria.getGarantieId(),
                    root -> root.join(Produit_.garanties, JoinType.LEFT).get(Garantie_.id)));
            }
        }
        return specification;
    }
}
