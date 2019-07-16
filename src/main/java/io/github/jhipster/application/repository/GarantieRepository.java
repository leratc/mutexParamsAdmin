package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.Garantie;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Garantie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GarantieRepository extends JpaRepository<Garantie, Long>, JpaSpecificationExecutor<Garantie> {

}
