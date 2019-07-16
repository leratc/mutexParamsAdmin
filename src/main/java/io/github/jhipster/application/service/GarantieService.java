package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Garantie;
import io.github.jhipster.application.repository.GarantieRepository;
import io.github.jhipster.application.service.dto.GarantieDTO;
import io.github.jhipster.application.service.mapper.GarantieMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Garantie}.
 */
@Service
@Transactional
public class GarantieService {

    private final Logger log = LoggerFactory.getLogger(GarantieService.class);

    private final GarantieRepository garantieRepository;

    private final GarantieMapper garantieMapper;

    public GarantieService(GarantieRepository garantieRepository, GarantieMapper garantieMapper) {
        this.garantieRepository = garantieRepository;
        this.garantieMapper = garantieMapper;
    }

    /**
     * Save a garantie.
     *
     * @param garantieDTO the entity to save.
     * @return the persisted entity.
     */
    public GarantieDTO save(GarantieDTO garantieDTO) {
        log.debug("Request to save Garantie : {}", garantieDTO);
        Garantie garantie = garantieMapper.toEntity(garantieDTO);
        garantie = garantieRepository.save(garantie);
        return garantieMapper.toDto(garantie);
    }

    /**
     * Get all the garanties.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<GarantieDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Garanties");
        return garantieRepository.findAll(pageable)
            .map(garantieMapper::toDto);
    }


    /**
     * Get one garantie by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<GarantieDTO> findOne(Long id) {
        log.debug("Request to get Garantie : {}", id);
        return garantieRepository.findById(id)
            .map(garantieMapper::toDto);
    }

    /**
     * Delete the garantie by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Garantie : {}", id);
        garantieRepository.deleteById(id);
    }
}
