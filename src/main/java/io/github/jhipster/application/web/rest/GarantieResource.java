package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.GarantieService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.GarantieDTO;
import io.github.jhipster.application.service.dto.GarantieCriteria;
import io.github.jhipster.application.service.GarantieQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Garantie}.
 */
@RestController
@RequestMapping("/api")
public class GarantieResource {

    private final Logger log = LoggerFactory.getLogger(GarantieResource.class);

    private static final String ENTITY_NAME = "garantie";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GarantieService garantieService;

    private final GarantieQueryService garantieQueryService;

    public GarantieResource(GarantieService garantieService, GarantieQueryService garantieQueryService) {
        this.garantieService = garantieService;
        this.garantieQueryService = garantieQueryService;
    }

    /**
     * {@code POST  /garanties} : Create a new garantie.
     *
     * @param garantieDTO the garantieDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new garantieDTO, or with status {@code 400 (Bad Request)} if the garantie has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/garanties")
    public ResponseEntity<GarantieDTO> createGarantie(@Valid @RequestBody GarantieDTO garantieDTO) throws URISyntaxException {
        log.debug("REST request to save Garantie : {}", garantieDTO);
        if (garantieDTO.getId() != null) {
            throw new BadRequestAlertException("A new garantie cannot already have an ID", ENTITY_NAME, "idexists");
        }
        GarantieDTO result = garantieService.save(garantieDTO);
        return ResponseEntity.created(new URI("/api/garanties/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /garanties} : Updates an existing garantie.
     *
     * @param garantieDTO the garantieDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated garantieDTO,
     * or with status {@code 400 (Bad Request)} if the garantieDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the garantieDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/garanties")
    public ResponseEntity<GarantieDTO> updateGarantie(@Valid @RequestBody GarantieDTO garantieDTO) throws URISyntaxException {
        log.debug("REST request to update Garantie : {}", garantieDTO);
        if (garantieDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        GarantieDTO result = garantieService.save(garantieDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, garantieDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /garanties} : get all the garanties.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of garanties in body.
     */
    @GetMapping("/garanties")
    public ResponseEntity<List<GarantieDTO>> getAllGaranties(GarantieCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Garanties by criteria: {}", criteria);
        Page<GarantieDTO> page = garantieQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /garanties/count} : count all the garanties.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/garanties/count")
    public ResponseEntity<Long> countGaranties(GarantieCriteria criteria) {
        log.debug("REST request to count Garanties by criteria: {}", criteria);
        return ResponseEntity.ok().body(garantieQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /garanties/:id} : get the "id" garantie.
     *
     * @param id the id of the garantieDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the garantieDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/garanties/{id}")
    public ResponseEntity<GarantieDTO> getGarantie(@PathVariable Long id) {
        log.debug("REST request to get Garantie : {}", id);
        Optional<GarantieDTO> garantieDTO = garantieService.findOne(id);
        return ResponseUtil.wrapOrNotFound(garantieDTO);
    }

    /**
     * {@code DELETE  /garanties/:id} : delete the "id" garantie.
     *
     * @param id the id of the garantieDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/garanties/{id}")
    public ResponseEntity<Void> deleteGarantie(@PathVariable Long id) {
        log.debug("REST request to delete Garantie : {}", id);
        garantieService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
