package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MutexParamsAdminApp;
import io.github.jhipster.application.domain.Moduledefinition;
import io.github.jhipster.application.domain.Produit;
import io.github.jhipster.application.repository.ModuledefinitionRepository;
import io.github.jhipster.application.service.ModuledefinitionService;
import io.github.jhipster.application.service.dto.ModuledefinitionDTO;
import io.github.jhipster.application.service.mapper.ModuledefinitionMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ModuledefinitionCriteria;
import io.github.jhipster.application.service.ModuledefinitionQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ModuledefinitionResource} REST controller.
 */
@SpringBootTest(classes = MutexParamsAdminApp.class)
public class ModuledefinitionResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMEROORDRE = 1;
    private static final Integer UPDATED_NUMEROORDRE = 2;

    private static final Integer DEFAULT_EFFECTIFMAX = 1;
    private static final Integer UPDATED_EFFECTIFMAX = 2;

    @Autowired
    private ModuledefinitionRepository moduledefinitionRepository;

    @Autowired
    private ModuledefinitionMapper moduledefinitionMapper;

    @Autowired
    private ModuledefinitionService moduledefinitionService;

    @Autowired
    private ModuledefinitionQueryService moduledefinitionQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restModuledefinitionMockMvc;

    private Moduledefinition moduledefinition;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ModuledefinitionResource moduledefinitionResource = new ModuledefinitionResource(moduledefinitionService, moduledefinitionQueryService);
        this.restModuledefinitionMockMvc = MockMvcBuilders.standaloneSetup(moduledefinitionResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Moduledefinition createEntity(EntityManager em) {
        Moduledefinition moduledefinition = new Moduledefinition()
            .libelle(DEFAULT_LIBELLE)
            .description(DEFAULT_DESCRIPTION)
            .numeroordre(DEFAULT_NUMEROORDRE)
            .effectifmax(DEFAULT_EFFECTIFMAX);
        return moduledefinition;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Moduledefinition createUpdatedEntity(EntityManager em) {
        Moduledefinition moduledefinition = new Moduledefinition()
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .numeroordre(UPDATED_NUMEROORDRE)
            .effectifmax(UPDATED_EFFECTIFMAX);
        return moduledefinition;
    }

    @BeforeEach
    public void initTest() {
        moduledefinition = createEntity(em);
    }

    @Test
    @Transactional
    public void createModuledefinition() throws Exception {
        int databaseSizeBeforeCreate = moduledefinitionRepository.findAll().size();

        // Create the Moduledefinition
        ModuledefinitionDTO moduledefinitionDTO = moduledefinitionMapper.toDto(moduledefinition);
        restModuledefinitionMockMvc.perform(post("/api/moduledefinitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moduledefinitionDTO)))
            .andExpect(status().isCreated());

        // Validate the Moduledefinition in the database
        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeCreate + 1);
        Moduledefinition testModuledefinition = moduledefinitionList.get(moduledefinitionList.size() - 1);
        assertThat(testModuledefinition.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testModuledefinition.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testModuledefinition.getNumeroordre()).isEqualTo(DEFAULT_NUMEROORDRE);
        assertThat(testModuledefinition.getEffectifmax()).isEqualTo(DEFAULT_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void createModuledefinitionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = moduledefinitionRepository.findAll().size();

        // Create the Moduledefinition with an existing ID
        moduledefinition.setId(1L);
        ModuledefinitionDTO moduledefinitionDTO = moduledefinitionMapper.toDto(moduledefinition);

        // An entity with an existing ID cannot be created, so this API call must fail
        restModuledefinitionMockMvc.perform(post("/api/moduledefinitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moduledefinitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Moduledefinition in the database
        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = moduledefinitionRepository.findAll().size();
        // set the field null
        moduledefinition.setLibelle(null);

        // Create the Moduledefinition, which fails.
        ModuledefinitionDTO moduledefinitionDTO = moduledefinitionMapper.toDto(moduledefinition);

        restModuledefinitionMockMvc.perform(post("/api/moduledefinitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moduledefinitionDTO)))
            .andExpect(status().isBadRequest());

        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroordreIsRequired() throws Exception {
        int databaseSizeBeforeTest = moduledefinitionRepository.findAll().size();
        // set the field null
        moduledefinition.setNumeroordre(null);

        // Create the Moduledefinition, which fails.
        ModuledefinitionDTO moduledefinitionDTO = moduledefinitionMapper.toDto(moduledefinition);

        restModuledefinitionMockMvc.perform(post("/api/moduledefinitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moduledefinitionDTO)))
            .andExpect(status().isBadRequest());

        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllModuledefinitions() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(moduledefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].numeroordre").value(hasItem(DEFAULT_NUMEROORDRE)))
            .andExpect(jsonPath("$.[*].effectifmax").value(hasItem(DEFAULT_EFFECTIFMAX)));
    }
    
    @Test
    @Transactional
    public void getModuledefinition() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get the moduledefinition
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions/{id}", moduledefinition.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(moduledefinition.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.numeroordre").value(DEFAULT_NUMEROORDRE))
            .andExpect(jsonPath("$.effectifmax").value(DEFAULT_EFFECTIFMAX));
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByLibelleIsEqualToSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where libelle equals to DEFAULT_LIBELLE
        defaultModuledefinitionShouldBeFound("libelle.equals=" + DEFAULT_LIBELLE);

        // Get all the moduledefinitionList where libelle equals to UPDATED_LIBELLE
        defaultModuledefinitionShouldNotBeFound("libelle.equals=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByLibelleIsInShouldWork() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where libelle in DEFAULT_LIBELLE or UPDATED_LIBELLE
        defaultModuledefinitionShouldBeFound("libelle.in=" + DEFAULT_LIBELLE + "," + UPDATED_LIBELLE);

        // Get all the moduledefinitionList where libelle equals to UPDATED_LIBELLE
        defaultModuledefinitionShouldNotBeFound("libelle.in=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByLibelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where libelle is not null
        defaultModuledefinitionShouldBeFound("libelle.specified=true");

        // Get all the moduledefinitionList where libelle is null
        defaultModuledefinitionShouldNotBeFound("libelle.specified=false");
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByDescriptionIsEqualToSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where description equals to DEFAULT_DESCRIPTION
        defaultModuledefinitionShouldBeFound("description.equals=" + DEFAULT_DESCRIPTION);

        // Get all the moduledefinitionList where description equals to UPDATED_DESCRIPTION
        defaultModuledefinitionShouldNotBeFound("description.equals=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByDescriptionIsInShouldWork() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where description in DEFAULT_DESCRIPTION or UPDATED_DESCRIPTION
        defaultModuledefinitionShouldBeFound("description.in=" + DEFAULT_DESCRIPTION + "," + UPDATED_DESCRIPTION);

        // Get all the moduledefinitionList where description equals to UPDATED_DESCRIPTION
        defaultModuledefinitionShouldNotBeFound("description.in=" + UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByDescriptionIsNullOrNotNull() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where description is not null
        defaultModuledefinitionShouldBeFound("description.specified=true");

        // Get all the moduledefinitionList where description is null
        defaultModuledefinitionShouldNotBeFound("description.specified=false");
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByNumeroordreIsEqualToSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where numeroordre equals to DEFAULT_NUMEROORDRE
        defaultModuledefinitionShouldBeFound("numeroordre.equals=" + DEFAULT_NUMEROORDRE);

        // Get all the moduledefinitionList where numeroordre equals to UPDATED_NUMEROORDRE
        defaultModuledefinitionShouldNotBeFound("numeroordre.equals=" + UPDATED_NUMEROORDRE);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByNumeroordreIsInShouldWork() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where numeroordre in DEFAULT_NUMEROORDRE or UPDATED_NUMEROORDRE
        defaultModuledefinitionShouldBeFound("numeroordre.in=" + DEFAULT_NUMEROORDRE + "," + UPDATED_NUMEROORDRE);

        // Get all the moduledefinitionList where numeroordre equals to UPDATED_NUMEROORDRE
        defaultModuledefinitionShouldNotBeFound("numeroordre.in=" + UPDATED_NUMEROORDRE);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByNumeroordreIsNullOrNotNull() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where numeroordre is not null
        defaultModuledefinitionShouldBeFound("numeroordre.specified=true");

        // Get all the moduledefinitionList where numeroordre is null
        defaultModuledefinitionShouldNotBeFound("numeroordre.specified=false");
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByNumeroordreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where numeroordre greater than or equals to DEFAULT_NUMEROORDRE
        defaultModuledefinitionShouldBeFound("numeroordre.greaterOrEqualThan=" + DEFAULT_NUMEROORDRE);

        // Get all the moduledefinitionList where numeroordre greater than or equals to UPDATED_NUMEROORDRE
        defaultModuledefinitionShouldNotBeFound("numeroordre.greaterOrEqualThan=" + UPDATED_NUMEROORDRE);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByNumeroordreIsLessThanSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where numeroordre less than or equals to DEFAULT_NUMEROORDRE
        defaultModuledefinitionShouldNotBeFound("numeroordre.lessThan=" + DEFAULT_NUMEROORDRE);

        // Get all the moduledefinitionList where numeroordre less than or equals to UPDATED_NUMEROORDRE
        defaultModuledefinitionShouldBeFound("numeroordre.lessThan=" + UPDATED_NUMEROORDRE);
    }


    @Test
    @Transactional
    public void getAllModuledefinitionsByEffectifmaxIsEqualToSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where effectifmax equals to DEFAULT_EFFECTIFMAX
        defaultModuledefinitionShouldBeFound("effectifmax.equals=" + DEFAULT_EFFECTIFMAX);

        // Get all the moduledefinitionList where effectifmax equals to UPDATED_EFFECTIFMAX
        defaultModuledefinitionShouldNotBeFound("effectifmax.equals=" + UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByEffectifmaxIsInShouldWork() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where effectifmax in DEFAULT_EFFECTIFMAX or UPDATED_EFFECTIFMAX
        defaultModuledefinitionShouldBeFound("effectifmax.in=" + DEFAULT_EFFECTIFMAX + "," + UPDATED_EFFECTIFMAX);

        // Get all the moduledefinitionList where effectifmax equals to UPDATED_EFFECTIFMAX
        defaultModuledefinitionShouldNotBeFound("effectifmax.in=" + UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByEffectifmaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where effectifmax is not null
        defaultModuledefinitionShouldBeFound("effectifmax.specified=true");

        // Get all the moduledefinitionList where effectifmax is null
        defaultModuledefinitionShouldNotBeFound("effectifmax.specified=false");
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByEffectifmaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where effectifmax greater than or equals to DEFAULT_EFFECTIFMAX
        defaultModuledefinitionShouldBeFound("effectifmax.greaterOrEqualThan=" + DEFAULT_EFFECTIFMAX);

        // Get all the moduledefinitionList where effectifmax greater than or equals to UPDATED_EFFECTIFMAX
        defaultModuledefinitionShouldNotBeFound("effectifmax.greaterOrEqualThan=" + UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void getAllModuledefinitionsByEffectifmaxIsLessThanSomething() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        // Get all the moduledefinitionList where effectifmax less than or equals to DEFAULT_EFFECTIFMAX
        defaultModuledefinitionShouldNotBeFound("effectifmax.lessThan=" + DEFAULT_EFFECTIFMAX);

        // Get all the moduledefinitionList where effectifmax less than or equals to UPDATED_EFFECTIFMAX
        defaultModuledefinitionShouldBeFound("effectifmax.lessThan=" + UPDATED_EFFECTIFMAX);
    }


    @Test
    @Transactional
    public void getAllModuledefinitionsByProduitIsEqualToSomething() throws Exception {
        // Initialize the database
        Produit produit = ProduitResourceIT.createEntity(em);
        em.persist(produit);
        em.flush();
        moduledefinition.addProduit(produit);
        moduledefinitionRepository.saveAndFlush(moduledefinition);
        Long produitId = produit.getId();

        // Get all the moduledefinitionList where produit equals to produitId
        defaultModuledefinitionShouldBeFound("produitId.equals=" + produitId);

        // Get all the moduledefinitionList where produit equals to produitId + 1
        defaultModuledefinitionShouldNotBeFound("produitId.equals=" + (produitId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultModuledefinitionShouldBeFound(String filter) throws Exception {
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(moduledefinition.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].numeroordre").value(hasItem(DEFAULT_NUMEROORDRE)))
            .andExpect(jsonPath("$.[*].effectifmax").value(hasItem(DEFAULT_EFFECTIFMAX)));

        // Check, that the count call also returns 1
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultModuledefinitionShouldNotBeFound(String filter) throws Exception {
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingModuledefinition() throws Exception {
        // Get the moduledefinition
        restModuledefinitionMockMvc.perform(get("/api/moduledefinitions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateModuledefinition() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        int databaseSizeBeforeUpdate = moduledefinitionRepository.findAll().size();

        // Update the moduledefinition
        Moduledefinition updatedModuledefinition = moduledefinitionRepository.findById(moduledefinition.getId()).get();
        // Disconnect from session so that the updates on updatedModuledefinition are not directly saved in db
        em.detach(updatedModuledefinition);
        updatedModuledefinition
            .libelle(UPDATED_LIBELLE)
            .description(UPDATED_DESCRIPTION)
            .numeroordre(UPDATED_NUMEROORDRE)
            .effectifmax(UPDATED_EFFECTIFMAX);
        ModuledefinitionDTO moduledefinitionDTO = moduledefinitionMapper.toDto(updatedModuledefinition);

        restModuledefinitionMockMvc.perform(put("/api/moduledefinitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moduledefinitionDTO)))
            .andExpect(status().isOk());

        // Validate the Moduledefinition in the database
        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeUpdate);
        Moduledefinition testModuledefinition = moduledefinitionList.get(moduledefinitionList.size() - 1);
        assertThat(testModuledefinition.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testModuledefinition.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testModuledefinition.getNumeroordre()).isEqualTo(UPDATED_NUMEROORDRE);
        assertThat(testModuledefinition.getEffectifmax()).isEqualTo(UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void updateNonExistingModuledefinition() throws Exception {
        int databaseSizeBeforeUpdate = moduledefinitionRepository.findAll().size();

        // Create the Moduledefinition
        ModuledefinitionDTO moduledefinitionDTO = moduledefinitionMapper.toDto(moduledefinition);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restModuledefinitionMockMvc.perform(put("/api/moduledefinitions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(moduledefinitionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Moduledefinition in the database
        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteModuledefinition() throws Exception {
        // Initialize the database
        moduledefinitionRepository.saveAndFlush(moduledefinition);

        int databaseSizeBeforeDelete = moduledefinitionRepository.findAll().size();

        // Delete the moduledefinition
        restModuledefinitionMockMvc.perform(delete("/api/moduledefinitions/{id}", moduledefinition.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Moduledefinition> moduledefinitionList = moduledefinitionRepository.findAll();
        assertThat(moduledefinitionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Moduledefinition.class);
        Moduledefinition moduledefinition1 = new Moduledefinition();
        moduledefinition1.setId(1L);
        Moduledefinition moduledefinition2 = new Moduledefinition();
        moduledefinition2.setId(moduledefinition1.getId());
        assertThat(moduledefinition1).isEqualTo(moduledefinition2);
        moduledefinition2.setId(2L);
        assertThat(moduledefinition1).isNotEqualTo(moduledefinition2);
        moduledefinition1.setId(null);
        assertThat(moduledefinition1).isNotEqualTo(moduledefinition2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ModuledefinitionDTO.class);
        ModuledefinitionDTO moduledefinitionDTO1 = new ModuledefinitionDTO();
        moduledefinitionDTO1.setId(1L);
        ModuledefinitionDTO moduledefinitionDTO2 = new ModuledefinitionDTO();
        assertThat(moduledefinitionDTO1).isNotEqualTo(moduledefinitionDTO2);
        moduledefinitionDTO2.setId(moduledefinitionDTO1.getId());
        assertThat(moduledefinitionDTO1).isEqualTo(moduledefinitionDTO2);
        moduledefinitionDTO2.setId(2L);
        assertThat(moduledefinitionDTO1).isNotEqualTo(moduledefinitionDTO2);
        moduledefinitionDTO1.setId(null);
        assertThat(moduledefinitionDTO1).isNotEqualTo(moduledefinitionDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(moduledefinitionMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(moduledefinitionMapper.fromId(null)).isNull();
    }
}
