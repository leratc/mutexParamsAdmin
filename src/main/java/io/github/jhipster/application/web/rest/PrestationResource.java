package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.PrestationService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.PrestationDTO;
import io.github.jhipster.application.service.dto.PrestationCriteria;
import io.github.jhipster.application.service.PrestationQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.Prestation}.
 */
@RestController
@RequestMapping("/api")
public class PrestationResource {

    private final Logger log = LoggerFactory.getLogger(PrestationResource.class);

    private static final String ENTITY_NAME = "prestation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PrestationService prestationService;

    private final PrestationQueryService prestationQueryService;

    public PrestationResource(PrestationService prestationService, PrestationQueryService prestationQueryService) {
        this.prestationService = prestationService;
        this.prestationQueryService = prestationQueryService;
    }

    /**
     * {@code POST  /prestations} : Create a new prestation.
     *
     * @param prestationDTO the prestationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new prestationDTO, or with status {@code 400 (Bad Request)} if the prestation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/prestations")
    public ResponseEntity<PrestationDTO> createPrestation(@Valid @RequestBody PrestationDTO prestationDTO) throws URISyntaxException {
        log.debug("REST request to save Prestation : {}", prestationDTO);
        if (prestationDTO.getId() != null) {
            throw new BadRequestAlertException("A new prestation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PrestationDTO result = prestationService.save(prestationDTO);
        return ResponseEntity.created(new URI("/api/prestations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /prestations} : Updates an existing prestation.
     *
     * @param prestationDTO the prestationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated prestationDTO,
     * or with status {@code 400 (Bad Request)} if the prestationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the prestationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/prestations")
    public ResponseEntity<PrestationDTO> updatePrestation(@Valid @RequestBody PrestationDTO prestationDTO) throws URISyntaxException {
        log.debug("REST request to update Prestation : {}", prestationDTO);
        if (prestationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PrestationDTO result = prestationService.save(prestationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, prestationDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /prestations} : get all the prestations.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of prestations in body.
     */
    @GetMapping("/prestations")
    public ResponseEntity<List<PrestationDTO>> getAllPrestations(PrestationCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Prestations by criteria: {}", criteria);
        Page<PrestationDTO> page = prestationQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /prestations/count} : count all the prestations.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/prestations/count")
    public ResponseEntity<Long> countPrestations(PrestationCriteria criteria) {
        log.debug("REST request to count Prestations by criteria: {}", criteria);
        return ResponseEntity.ok().body(prestationQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /prestations/:id} : get the "id" prestation.
     *
     * @param id the id of the prestationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the prestationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/prestations/{id}")
    public ResponseEntity<PrestationDTO> getPrestation(@PathVariable Long id) {
        log.debug("REST request to get Prestation : {}", id);
        Optional<PrestationDTO> prestationDTO = prestationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(prestationDTO);
    }

    /**
     * {@code DELETE  /prestations/:id} : delete the "id" prestation.
     *
     * @param id the id of the prestationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/prestations/{id}")
    public ResponseEntity<Void> deletePrestation(@PathVariable Long id) {
        log.debug("REST request to delete Prestation : {}", id);
        prestationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
