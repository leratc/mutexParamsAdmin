package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.RubriqueService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.RubriqueDTO;
import io.github.jhipster.application.service.dto.RubriqueCriteria;
import io.github.jhipster.application.service.RubriqueQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Rubrique}.
 */
@RestController
@RequestMapping("/api")
public class RubriqueResource {

    private final Logger log = LoggerFactory.getLogger(RubriqueResource.class);

    private static final String ENTITY_NAME = "rubrique";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RubriqueService rubriqueService;

    private final RubriqueQueryService rubriqueQueryService;

    public RubriqueResource(RubriqueService rubriqueService, RubriqueQueryService rubriqueQueryService) {
        this.rubriqueService = rubriqueService;
        this.rubriqueQueryService = rubriqueQueryService;
    }

    /**
     * {@code POST  /rubriques} : Create a new rubrique.
     *
     * @param rubriqueDTO the rubriqueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rubriqueDTO, or with status {@code 400 (Bad Request)} if the rubrique has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rubriques")
    public ResponseEntity<RubriqueDTO> createRubrique(@Valid @RequestBody RubriqueDTO rubriqueDTO) throws URISyntaxException {
        log.debug("REST request to save Rubrique : {}", rubriqueDTO);
        if (rubriqueDTO.getId() != null) {
            throw new BadRequestAlertException("A new rubrique cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RubriqueDTO result = rubriqueService.save(rubriqueDTO);
        return ResponseEntity.created(new URI("/api/rubriques/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rubriques} : Updates an existing rubrique.
     *
     * @param rubriqueDTO the rubriqueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rubriqueDTO,
     * or with status {@code 400 (Bad Request)} if the rubriqueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rubriqueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rubriques")
    public ResponseEntity<RubriqueDTO> updateRubrique(@Valid @RequestBody RubriqueDTO rubriqueDTO) throws URISyntaxException {
        log.debug("REST request to update Rubrique : {}", rubriqueDTO);
        if (rubriqueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        RubriqueDTO result = rubriqueService.save(rubriqueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, rubriqueDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /rubriques} : get all the rubriques.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rubriques in body.
     */
    @GetMapping("/rubriques")
    public ResponseEntity<List<RubriqueDTO>> getAllRubriques(RubriqueCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Rubriques by criteria: {}", criteria);
        Page<RubriqueDTO> page = rubriqueQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /rubriques/count} : count all the rubriques.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/rubriques/count")
    public ResponseEntity<Long> countRubriques(RubriqueCriteria criteria) {
        log.debug("REST request to count Rubriques by criteria: {}", criteria);
        return ResponseEntity.ok().body(rubriqueQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /rubriques/:id} : get the "id" rubrique.
     *
     * @param id the id of the rubriqueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rubriqueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rubriques/{id}")
    public ResponseEntity<RubriqueDTO> getRubrique(@PathVariable Long id) {
        log.debug("REST request to get Rubrique : {}", id);
        Optional<RubriqueDTO> rubriqueDTO = rubriqueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rubriqueDTO);
    }

    /**
     * {@code DELETE  /rubriques/:id} : delete the "id" rubrique.
     *
     * @param id the id of the rubriqueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rubriques/{id}")
    public ResponseEntity<Void> deleteRubrique(@PathVariable Long id) {
        log.debug("REST request to delete Rubrique : {}", id);
        rubriqueService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
