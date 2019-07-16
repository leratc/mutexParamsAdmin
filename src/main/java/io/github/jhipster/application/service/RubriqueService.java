package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Rubrique;
import io.github.jhipster.application.repository.RubriqueRepository;
import io.github.jhipster.application.service.dto.RubriqueDTO;
import io.github.jhipster.application.service.mapper.RubriqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Rubrique}.
 */
@Service
@Transactional
public class RubriqueService {

    private final Logger log = LoggerFactory.getLogger(RubriqueService.class);

    private final RubriqueRepository rubriqueRepository;

    private final RubriqueMapper rubriqueMapper;

    public RubriqueService(RubriqueRepository rubriqueRepository, RubriqueMapper rubriqueMapper) {
        this.rubriqueRepository = rubriqueRepository;
        this.rubriqueMapper = rubriqueMapper;
    }

    /**
     * Save a rubrique.
     *
     * @param rubriqueDTO the entity to save.
     * @return the persisted entity.
     */
    public RubriqueDTO save(RubriqueDTO rubriqueDTO) {
        log.debug("Request to save Rubrique : {}", rubriqueDTO);
        Rubrique rubrique = rubriqueMapper.toEntity(rubriqueDTO);
        rubrique = rubriqueRepository.save(rubrique);
        return rubriqueMapper.toDto(rubrique);
    }

    /**
     * Get all the rubriques.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<RubriqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Rubriques");
        return rubriqueRepository.findAll(pageable)
            .map(rubriqueMapper::toDto);
    }


    /**
     * Get one rubrique by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RubriqueDTO> findOne(Long id) {
        log.debug("Request to get Rubrique : {}", id);
        return rubriqueRepository.findById(id)
            .map(rubriqueMapper::toDto);
    }

    /**
     * Delete the rubrique by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Rubrique : {}", id);
        rubriqueRepository.deleteById(id);
    }
}
