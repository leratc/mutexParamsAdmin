package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Prestation;
import io.github.jhipster.application.repository.PrestationRepository;
import io.github.jhipster.application.service.dto.PrestationDTO;
import io.github.jhipster.application.service.mapper.PrestationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Prestation}.
 */
@Service
@Transactional
public class PrestationService {

    private final Logger log = LoggerFactory.getLogger(PrestationService.class);

    private final PrestationRepository prestationRepository;

    private final PrestationMapper prestationMapper;

    public PrestationService(PrestationRepository prestationRepository, PrestationMapper prestationMapper) {
        this.prestationRepository = prestationRepository;
        this.prestationMapper = prestationMapper;
    }

    /**
     * Save a prestation.
     *
     * @param prestationDTO the entity to save.
     * @return the persisted entity.
     */
    public PrestationDTO save(PrestationDTO prestationDTO) {
        log.debug("Request to save Prestation : {}", prestationDTO);
        Prestation prestation = prestationMapper.toEntity(prestationDTO);
        prestation = prestationRepository.save(prestation);
        return prestationMapper.toDto(prestation);
    }

    /**
     * Get all the prestations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<PrestationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Prestations");
        return prestationRepository.findAll(pageable)
            .map(prestationMapper::toDto);
    }


    /**
     * Get one prestation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PrestationDTO> findOne(Long id) {
        log.debug("Request to get Prestation : {}", id);
        return prestationRepository.findById(id)
            .map(prestationMapper::toDto);
    }

    /**
     * Delete the prestation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Prestation : {}", id);
        prestationRepository.deleteById(id);
    }
}
