package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.Moduledefinition;
import io.github.jhipster.application.repository.ModuledefinitionRepository;
import io.github.jhipster.application.service.dto.ModuledefinitionDTO;
import io.github.jhipster.application.service.mapper.ModuledefinitionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Moduledefinition}.
 */
@Service
@Transactional
public class ModuledefinitionService {

    private final Logger log = LoggerFactory.getLogger(ModuledefinitionService.class);

    private final ModuledefinitionRepository moduledefinitionRepository;

    private final ModuledefinitionMapper moduledefinitionMapper;

    public ModuledefinitionService(ModuledefinitionRepository moduledefinitionRepository, ModuledefinitionMapper moduledefinitionMapper) {
        this.moduledefinitionRepository = moduledefinitionRepository;
        this.moduledefinitionMapper = moduledefinitionMapper;
    }

    /**
     * Save a moduledefinition.
     *
     * @param moduledefinitionDTO the entity to save.
     * @return the persisted entity.
     */
    public ModuledefinitionDTO save(ModuledefinitionDTO moduledefinitionDTO) {
        log.debug("Request to save Moduledefinition : {}", moduledefinitionDTO);
        Moduledefinition moduledefinition = moduledefinitionMapper.toEntity(moduledefinitionDTO);
        moduledefinition = moduledefinitionRepository.save(moduledefinition);
        return moduledefinitionMapper.toDto(moduledefinition);
    }

    /**
     * Get all the moduledefinitions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ModuledefinitionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Moduledefinitions");
        return moduledefinitionRepository.findAll(pageable)
            .map(moduledefinitionMapper::toDto);
    }


    /**
     * Get one moduledefinition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ModuledefinitionDTO> findOne(Long id) {
        log.debug("Request to get Moduledefinition : {}", id);
        return moduledefinitionRepository.findById(id)
            .map(moduledefinitionMapper::toDto);
    }

    /**
     * Delete the moduledefinition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Moduledefinition : {}", id);
        moduledefinitionRepository.deleteById(id);
    }
}
