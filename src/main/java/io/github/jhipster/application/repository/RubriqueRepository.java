package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Rubrique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Rubrique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RubriqueRepository extends JpaRepository<Rubrique, Long>, JpaSpecificationExecutor<Rubrique> {

}
