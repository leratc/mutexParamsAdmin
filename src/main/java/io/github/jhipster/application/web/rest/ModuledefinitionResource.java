package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.service.ModuledefinitionService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ModuledefinitionDTO;
import io.github.jhipster.application.service.dto.ModuledefinitionCriteria;
import io.github.jhipster.application.service.ModuledefinitionQueryService;

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
 * REST controller for managing {@link io.github.jhipster.application.domain.Moduledefinition}.
 */
@RestController
@RequestMapping("/api")
public class ModuledefinitionResource {

    private final Logger log = LoggerFactory.getLogger(ModuledefinitionResource.class);

    private static final String ENTITY_NAME = "moduledefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModuledefinitionService moduledefinitionService;

    private final ModuledefinitionQueryService moduledefinitionQueryService;

    public ModuledefinitionResource(ModuledefinitionService moduledefinitionService, ModuledefinitionQueryService moduledefinitionQueryService) {
        this.moduledefinitionService = moduledefinitionService;
        this.moduledefinitionQueryService = moduledefinitionQueryService;
    }

    /**
     * {@code POST  /moduledefinitions} : Create a new moduledefinition.
     *
     * @param moduledefinitionDTO the moduledefinitionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new moduledefinitionDTO, or with status {@code 400 (Bad Request)} if the moduledefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/moduledefinitions")
    public ResponseEntity<ModuledefinitionDTO> createModuledefinition(@Valid @RequestBody ModuledefinitionDTO moduledefinitionDTO) throws URISyntaxException {
        log.debug("REST request to save Moduledefinition : {}", moduledefinitionDTO);
        if (moduledefinitionDTO.getId() != null) {
            throw new BadRequestAlertException("A new moduledefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ModuledefinitionDTO result = moduledefinitionService.save(moduledefinitionDTO);
        return ResponseEntity.created(new URI("/api/moduledefinitions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /moduledefinitions} : Updates an existing moduledefinition.
     *
     * @param moduledefinitionDTO the moduledefinitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated moduledefinitionDTO,
     * or with status {@code 400 (Bad Request)} if the moduledefinitionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the moduledefinitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/moduledefinitions")
    public ResponseEntity<ModuledefinitionDTO> updateModuledefinition(@Valid @RequestBody ModuledefinitionDTO moduledefinitionDTO) throws URISyntaxException {
        log.debug("REST request to update Moduledefinition : {}", moduledefinitionDTO);
        if (moduledefinitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ModuledefinitionDTO result = moduledefinitionService.save(moduledefinitionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, moduledefinitionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /moduledefinitions} : get all the moduledefinitions.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of moduledefinitions in body.
     */
    @GetMapping("/moduledefinitions")
    public ResponseEntity<List<ModuledefinitionDTO>> getAllModuledefinitions(ModuledefinitionCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get Moduledefinitions by criteria: {}", criteria);
        Page<ModuledefinitionDTO> page = moduledefinitionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /moduledefinitions/count} : count all the moduledefinitions.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/moduledefinitions/count")
    public ResponseEntity<Long> countModuledefinitions(ModuledefinitionCriteria criteria) {
        log.debug("REST request to count Moduledefinitions by criteria: {}", criteria);
        return ResponseEntity.ok().body(moduledefinitionQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /moduledefinitions/:id} : get the "id" moduledefinition.
     *
     * @param id the id of the moduledefinitionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the moduledefinitionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/moduledefinitions/{id}")
    public ResponseEntity<ModuledefinitionDTO> getModuledefinition(@PathVariable Long id) {
        log.debug("REST request to get Moduledefinition : {}", id);
        Optional<ModuledefinitionDTO> moduledefinitionDTO = moduledefinitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(moduledefinitionDTO);
    }

    /**
     * {@code DELETE  /moduledefinitions/:id} : delete the "id" moduledefinition.
     *
     * @param id the id of the moduledefinitionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/moduledefinitions/{id}")
    public ResponseEntity<Void> deleteModuledefinition(@PathVariable Long id) {
        log.debug("REST request to delete Moduledefinition : {}", id);
        moduledefinitionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
