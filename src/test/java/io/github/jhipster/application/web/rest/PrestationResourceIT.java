package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MutexParamsAdminApp;
import io.github.jhipster.application.domain.Prestation;
import io.github.jhipster.application.domain.Garantie;
import io.github.jhipster.application.repository.PrestationRepository;
import io.github.jhipster.application.service.PrestationService;
import io.github.jhipster.application.service.dto.PrestationDTO;
import io.github.jhipster.application.service.mapper.PrestationMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.PrestationCriteria;
import io.github.jhipster.application.service.PrestationQueryService;

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
 * Integration tests for the {@Link PrestationResource} REST controller.
 */
@SpringBootTest(classes = MutexParamsAdminApp.class)
public class PrestationResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULE_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_FORMULE_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULE = "AAAAAAAAAA";
    private static final String UPDATED_FORMULE = "BBBBBBBBBB";

    private static final String DEFAULT_FORMULE_APPLICATION = "AAAAAAAAAA";
    private static final String UPDATED_FORMULE_APPLICATION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_EPINGLE_GARANTIE = false;
    private static final Boolean UPDATED_EPINGLE_GARANTIE = true;

    private static final Integer DEFAULT_NUMERO_ORDRE = 1;
    private static final Integer UPDATED_NUMERO_ORDRE = 2;

    @Autowired
    private PrestationRepository prestationRepository;

    @Autowired
    private PrestationMapper prestationMapper;

    @Autowired
    private PrestationService prestationService;

    @Autowired
    private PrestationQueryService prestationQueryService;

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

    private MockMvc restPrestationMockMvc;

    private Prestation prestation;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final PrestationResource prestationResource = new PrestationResource(prestationService, prestationQueryService);
        this.restPrestationMockMvc = MockMvcBuilders.standaloneSetup(prestationResource)
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
    public static Prestation createEntity(EntityManager em) {
        Prestation prestation = new Prestation()
            .libelle(DEFAULT_LIBELLE)
            .formuleLibelle(DEFAULT_FORMULE_LIBELLE)
            .formule(DEFAULT_FORMULE)
            .formuleApplication(DEFAULT_FORMULE_APPLICATION)
            .epingleGarantie(DEFAULT_EPINGLE_GARANTIE)
            .numeroOrdre(DEFAULT_NUMERO_ORDRE);
        // Add required entity
        Garantie garantie;
        if (TestUtil.findAll(em, Garantie.class).isEmpty()) {
            garantie = GarantieResourceIT.createEntity(em);
            em.persist(garantie);
            em.flush();
        } else {
            garantie = TestUtil.findAll(em, Garantie.class).get(0);
        }
        prestation.setGarantie(garantie);
        return prestation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Prestation createUpdatedEntity(EntityManager em) {
        Prestation prestation = new Prestation()
            .libelle(UPDATED_LIBELLE)
            .formuleLibelle(UPDATED_FORMULE_LIBELLE)
            .formule(UPDATED_FORMULE)
            .formuleApplication(UPDATED_FORMULE_APPLICATION)
            .epingleGarantie(UPDATED_EPINGLE_GARANTIE)
            .numeroOrdre(UPDATED_NUMERO_ORDRE);
        // Add required entity
        Garantie garantie;
        if (TestUtil.findAll(em, Garantie.class).isEmpty()) {
            garantie = GarantieResourceIT.createUpdatedEntity(em);
            em.persist(garantie);
            em.flush();
        } else {
            garantie = TestUtil.findAll(em, Garantie.class).get(0);
        }
        prestation.setGarantie(garantie);
        return prestation;
    }

    @BeforeEach
    public void initTest() {
        prestation = createEntity(em);
    }

    @Test
    @Transactional
    public void createPrestation() throws Exception {
        int databaseSizeBeforeCreate = prestationRepository.findAll().size();

        // Create the Prestation
        PrestationDTO prestationDTO = prestationMapper.toDto(prestation);
        restPrestationMockMvc.perform(post("/api/prestations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prestationDTO)))
            .andExpect(status().isCreated());

        // Validate the Prestation in the database
        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeCreate + 1);
        Prestation testPrestation = prestationList.get(prestationList.size() - 1);
        assertThat(testPrestation.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testPrestation.getFormuleLibelle()).isEqualTo(DEFAULT_FORMULE_LIBELLE);
        assertThat(testPrestation.getFormule()).isEqualTo(DEFAULT_FORMULE);
        assertThat(testPrestation.getFormuleApplication()).isEqualTo(DEFAULT_FORMULE_APPLICATION);
        assertThat(testPrestation.isEpingleGarantie()).isEqualTo(DEFAULT_EPINGLE_GARANTIE);
        assertThat(testPrestation.getNumeroOrdre()).isEqualTo(DEFAULT_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void createPrestationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = prestationRepository.findAll().size();

        // Create the Prestation with an existing ID
        prestation.setId(1L);
        PrestationDTO prestationDTO = prestationMapper.toDto(prestation);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPrestationMockMvc.perform(post("/api/prestations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prestationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prestation in the database
        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEpingleGarantieIsRequired() throws Exception {
        int databaseSizeBeforeTest = prestationRepository.findAll().size();
        // set the field null
        prestation.setEpingleGarantie(null);

        // Create the Prestation, which fails.
        PrestationDTO prestationDTO = prestationMapper.toDto(prestation);

        restPrestationMockMvc.perform(post("/api/prestations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prestationDTO)))
            .andExpect(status().isBadRequest());

        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroOrdreIsRequired() throws Exception {
        int databaseSizeBeforeTest = prestationRepository.findAll().size();
        // set the field null
        prestation.setNumeroOrdre(null);

        // Create the Prestation, which fails.
        PrestationDTO prestationDTO = prestationMapper.toDto(prestation);

        restPrestationMockMvc.perform(post("/api/prestations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prestationDTO)))
            .andExpect(status().isBadRequest());

        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllPrestations() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList
        restPrestationMockMvc.perform(get("/api/prestations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prestation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].formuleLibelle").value(hasItem(DEFAULT_FORMULE_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].formule").value(hasItem(DEFAULT_FORMULE.toString())))
            .andExpect(jsonPath("$.[*].formuleApplication").value(hasItem(DEFAULT_FORMULE_APPLICATION.toString())))
            .andExpect(jsonPath("$.[*].epingleGarantie").value(hasItem(DEFAULT_EPINGLE_GARANTIE.booleanValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)));
    }
    
    @Test
    @Transactional
    public void getPrestation() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get the prestation
        restPrestationMockMvc.perform(get("/api/prestations/{id}", prestation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(prestation.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.formuleLibelle").value(DEFAULT_FORMULE_LIBELLE.toString()))
            .andExpect(jsonPath("$.formule").value(DEFAULT_FORMULE.toString()))
            .andExpect(jsonPath("$.formuleApplication").value(DEFAULT_FORMULE_APPLICATION.toString()))
            .andExpect(jsonPath("$.epingleGarantie").value(DEFAULT_EPINGLE_GARANTIE.booleanValue()))
            .andExpect(jsonPath("$.numeroOrdre").value(DEFAULT_NUMERO_ORDRE));
    }

    @Test
    @Transactional
    public void getAllPrestationsByLibelleIsEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where libelle equals to DEFAULT_LIBELLE
        defaultPrestationShouldBeFound("libelle.equals=" + DEFAULT_LIBELLE);

        // Get all the prestationList where libelle equals to UPDATED_LIBELLE
        defaultPrestationShouldNotBeFound("libelle.equals=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByLibelleIsInShouldWork() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where libelle in DEFAULT_LIBELLE or UPDATED_LIBELLE
        defaultPrestationShouldBeFound("libelle.in=" + DEFAULT_LIBELLE + "," + UPDATED_LIBELLE);

        // Get all the prestationList where libelle equals to UPDATED_LIBELLE
        defaultPrestationShouldNotBeFound("libelle.in=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByLibelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where libelle is not null
        defaultPrestationShouldBeFound("libelle.specified=true");

        // Get all the prestationList where libelle is null
        defaultPrestationShouldNotBeFound("libelle.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleLibelleIsEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formuleLibelle equals to DEFAULT_FORMULE_LIBELLE
        defaultPrestationShouldBeFound("formuleLibelle.equals=" + DEFAULT_FORMULE_LIBELLE);

        // Get all the prestationList where formuleLibelle equals to UPDATED_FORMULE_LIBELLE
        defaultPrestationShouldNotBeFound("formuleLibelle.equals=" + UPDATED_FORMULE_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleLibelleIsInShouldWork() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formuleLibelle in DEFAULT_FORMULE_LIBELLE or UPDATED_FORMULE_LIBELLE
        defaultPrestationShouldBeFound("formuleLibelle.in=" + DEFAULT_FORMULE_LIBELLE + "," + UPDATED_FORMULE_LIBELLE);

        // Get all the prestationList where formuleLibelle equals to UPDATED_FORMULE_LIBELLE
        defaultPrestationShouldNotBeFound("formuleLibelle.in=" + UPDATED_FORMULE_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleLibelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formuleLibelle is not null
        defaultPrestationShouldBeFound("formuleLibelle.specified=true");

        // Get all the prestationList where formuleLibelle is null
        defaultPrestationShouldNotBeFound("formuleLibelle.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleIsEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formule equals to DEFAULT_FORMULE
        defaultPrestationShouldBeFound("formule.equals=" + DEFAULT_FORMULE);

        // Get all the prestationList where formule equals to UPDATED_FORMULE
        defaultPrestationShouldNotBeFound("formule.equals=" + UPDATED_FORMULE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleIsInShouldWork() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formule in DEFAULT_FORMULE or UPDATED_FORMULE
        defaultPrestationShouldBeFound("formule.in=" + DEFAULT_FORMULE + "," + UPDATED_FORMULE);

        // Get all the prestationList where formule equals to UPDATED_FORMULE
        defaultPrestationShouldNotBeFound("formule.in=" + UPDATED_FORMULE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleIsNullOrNotNull() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formule is not null
        defaultPrestationShouldBeFound("formule.specified=true");

        // Get all the prestationList where formule is null
        defaultPrestationShouldNotBeFound("formule.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleApplicationIsEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formuleApplication equals to DEFAULT_FORMULE_APPLICATION
        defaultPrestationShouldBeFound("formuleApplication.equals=" + DEFAULT_FORMULE_APPLICATION);

        // Get all the prestationList where formuleApplication equals to UPDATED_FORMULE_APPLICATION
        defaultPrestationShouldNotBeFound("formuleApplication.equals=" + UPDATED_FORMULE_APPLICATION);
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleApplicationIsInShouldWork() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formuleApplication in DEFAULT_FORMULE_APPLICATION or UPDATED_FORMULE_APPLICATION
        defaultPrestationShouldBeFound("formuleApplication.in=" + DEFAULT_FORMULE_APPLICATION + "," + UPDATED_FORMULE_APPLICATION);

        // Get all the prestationList where formuleApplication equals to UPDATED_FORMULE_APPLICATION
        defaultPrestationShouldNotBeFound("formuleApplication.in=" + UPDATED_FORMULE_APPLICATION);
    }

    @Test
    @Transactional
    public void getAllPrestationsByFormuleApplicationIsNullOrNotNull() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where formuleApplication is not null
        defaultPrestationShouldBeFound("formuleApplication.specified=true");

        // Get all the prestationList where formuleApplication is null
        defaultPrestationShouldNotBeFound("formuleApplication.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrestationsByEpingleGarantieIsEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where epingleGarantie equals to DEFAULT_EPINGLE_GARANTIE
        defaultPrestationShouldBeFound("epingleGarantie.equals=" + DEFAULT_EPINGLE_GARANTIE);

        // Get all the prestationList where epingleGarantie equals to UPDATED_EPINGLE_GARANTIE
        defaultPrestationShouldNotBeFound("epingleGarantie.equals=" + UPDATED_EPINGLE_GARANTIE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByEpingleGarantieIsInShouldWork() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where epingleGarantie in DEFAULT_EPINGLE_GARANTIE or UPDATED_EPINGLE_GARANTIE
        defaultPrestationShouldBeFound("epingleGarantie.in=" + DEFAULT_EPINGLE_GARANTIE + "," + UPDATED_EPINGLE_GARANTIE);

        // Get all the prestationList where epingleGarantie equals to UPDATED_EPINGLE_GARANTIE
        defaultPrestationShouldNotBeFound("epingleGarantie.in=" + UPDATED_EPINGLE_GARANTIE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByEpingleGarantieIsNullOrNotNull() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where epingleGarantie is not null
        defaultPrestationShouldBeFound("epingleGarantie.specified=true");

        // Get all the prestationList where epingleGarantie is null
        defaultPrestationShouldNotBeFound("epingleGarantie.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrestationsByNumeroOrdreIsEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where numeroOrdre equals to DEFAULT_NUMERO_ORDRE
        defaultPrestationShouldBeFound("numeroOrdre.equals=" + DEFAULT_NUMERO_ORDRE);

        // Get all the prestationList where numeroOrdre equals to UPDATED_NUMERO_ORDRE
        defaultPrestationShouldNotBeFound("numeroOrdre.equals=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByNumeroOrdreIsInShouldWork() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where numeroOrdre in DEFAULT_NUMERO_ORDRE or UPDATED_NUMERO_ORDRE
        defaultPrestationShouldBeFound("numeroOrdre.in=" + DEFAULT_NUMERO_ORDRE + "," + UPDATED_NUMERO_ORDRE);

        // Get all the prestationList where numeroOrdre equals to UPDATED_NUMERO_ORDRE
        defaultPrestationShouldNotBeFound("numeroOrdre.in=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByNumeroOrdreIsNullOrNotNull() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where numeroOrdre is not null
        defaultPrestationShouldBeFound("numeroOrdre.specified=true");

        // Get all the prestationList where numeroOrdre is null
        defaultPrestationShouldNotBeFound("numeroOrdre.specified=false");
    }

    @Test
    @Transactional
    public void getAllPrestationsByNumeroOrdreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where numeroOrdre greater than or equals to DEFAULT_NUMERO_ORDRE
        defaultPrestationShouldBeFound("numeroOrdre.greaterOrEqualThan=" + DEFAULT_NUMERO_ORDRE);

        // Get all the prestationList where numeroOrdre greater than or equals to UPDATED_NUMERO_ORDRE
        defaultPrestationShouldNotBeFound("numeroOrdre.greaterOrEqualThan=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllPrestationsByNumeroOrdreIsLessThanSomething() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        // Get all the prestationList where numeroOrdre less than or equals to DEFAULT_NUMERO_ORDRE
        defaultPrestationShouldNotBeFound("numeroOrdre.lessThan=" + DEFAULT_NUMERO_ORDRE);

        // Get all the prestationList where numeroOrdre less than or equals to UPDATED_NUMERO_ORDRE
        defaultPrestationShouldBeFound("numeroOrdre.lessThan=" + UPDATED_NUMERO_ORDRE);
    }


    @Test
    @Transactional
    public void getAllPrestationsByGarantieIsEqualToSomething() throws Exception {
        // Get already existing entity
        Garantie garantie = prestation.getGarantie();
        prestationRepository.saveAndFlush(prestation);
        Long garantieId = garantie.getId();

        // Get all the prestationList where garantie equals to garantieId
        defaultPrestationShouldBeFound("garantieId.equals=" + garantieId);

        // Get all the prestationList where garantie equals to garantieId + 1
        defaultPrestationShouldNotBeFound("garantieId.equals=" + (garantieId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultPrestationShouldBeFound(String filter) throws Exception {
        restPrestationMockMvc.perform(get("/api/prestations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(prestation.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].formuleLibelle").value(hasItem(DEFAULT_FORMULE_LIBELLE)))
            .andExpect(jsonPath("$.[*].formule").value(hasItem(DEFAULT_FORMULE)))
            .andExpect(jsonPath("$.[*].formuleApplication").value(hasItem(DEFAULT_FORMULE_APPLICATION)))
            .andExpect(jsonPath("$.[*].epingleGarantie").value(hasItem(DEFAULT_EPINGLE_GARANTIE.booleanValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)));

        // Check, that the count call also returns 1
        restPrestationMockMvc.perform(get("/api/prestations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultPrestationShouldNotBeFound(String filter) throws Exception {
        restPrestationMockMvc.perform(get("/api/prestations?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restPrestationMockMvc.perform(get("/api/prestations/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingPrestation() throws Exception {
        // Get the prestation
        restPrestationMockMvc.perform(get("/api/prestations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePrestation() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        int databaseSizeBeforeUpdate = prestationRepository.findAll().size();

        // Update the prestation
        Prestation updatedPrestation = prestationRepository.findById(prestation.getId()).get();
        // Disconnect from session so that the updates on updatedPrestation are not directly saved in db
        em.detach(updatedPrestation);
        updatedPrestation
            .libelle(UPDATED_LIBELLE)
            .formuleLibelle(UPDATED_FORMULE_LIBELLE)
            .formule(UPDATED_FORMULE)
            .formuleApplication(UPDATED_FORMULE_APPLICATION)
            .epingleGarantie(UPDATED_EPINGLE_GARANTIE)
            .numeroOrdre(UPDATED_NUMERO_ORDRE);
        PrestationDTO prestationDTO = prestationMapper.toDto(updatedPrestation);

        restPrestationMockMvc.perform(put("/api/prestations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prestationDTO)))
            .andExpect(status().isOk());

        // Validate the Prestation in the database
        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeUpdate);
        Prestation testPrestation = prestationList.get(prestationList.size() - 1);
        assertThat(testPrestation.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testPrestation.getFormuleLibelle()).isEqualTo(UPDATED_FORMULE_LIBELLE);
        assertThat(testPrestation.getFormule()).isEqualTo(UPDATED_FORMULE);
        assertThat(testPrestation.getFormuleApplication()).isEqualTo(UPDATED_FORMULE_APPLICATION);
        assertThat(testPrestation.isEpingleGarantie()).isEqualTo(UPDATED_EPINGLE_GARANTIE);
        assertThat(testPrestation.getNumeroOrdre()).isEqualTo(UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void updateNonExistingPrestation() throws Exception {
        int databaseSizeBeforeUpdate = prestationRepository.findAll().size();

        // Create the Prestation
        PrestationDTO prestationDTO = prestationMapper.toDto(prestation);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPrestationMockMvc.perform(put("/api/prestations")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(prestationDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Prestation in the database
        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePrestation() throws Exception {
        // Initialize the database
        prestationRepository.saveAndFlush(prestation);

        int databaseSizeBeforeDelete = prestationRepository.findAll().size();

        // Delete the prestation
        restPrestationMockMvc.perform(delete("/api/prestations/{id}", prestation.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Prestation> prestationList = prestationRepository.findAll();
        assertThat(prestationList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Prestation.class);
        Prestation prestation1 = new Prestation();
        prestation1.setId(1L);
        Prestation prestation2 = new Prestation();
        prestation2.setId(prestation1.getId());
        assertThat(prestation1).isEqualTo(prestation2);
        prestation2.setId(2L);
        assertThat(prestation1).isNotEqualTo(prestation2);
        prestation1.setId(null);
        assertThat(prestation1).isNotEqualTo(prestation2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(PrestationDTO.class);
        PrestationDTO prestationDTO1 = new PrestationDTO();
        prestationDTO1.setId(1L);
        PrestationDTO prestationDTO2 = new PrestationDTO();
        assertThat(prestationDTO1).isNotEqualTo(prestationDTO2);
        prestationDTO2.setId(prestationDTO1.getId());
        assertThat(prestationDTO1).isEqualTo(prestationDTO2);
        prestationDTO2.setId(2L);
        assertThat(prestationDTO1).isNotEqualTo(prestationDTO2);
        prestationDTO1.setId(null);
        assertThat(prestationDTO1).isNotEqualTo(prestationDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(prestationMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(prestationMapper.fromId(null)).isNull();
    }
}
