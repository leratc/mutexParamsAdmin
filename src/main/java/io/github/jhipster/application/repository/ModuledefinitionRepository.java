package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Moduledefinition;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Moduledefinition entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ModuledefinitionRepository extends JpaRepository<Moduledefinition, Long>, JpaSpecificationExecutor<Moduledefinition> {

}
