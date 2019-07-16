package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Prestation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Prestation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrestationRepository extends JpaRepository<Prestation, Long>, JpaSpecificationExecutor<Prestation> {

}
