package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MutexParamsAdminApp;
import io.github.jhipster.application.domain.Rubrique;
import io.github.jhipster.application.domain.Garantie;
import io.github.jhipster.application.repository.RubriqueRepository;
import io.github.jhipster.application.service.RubriqueService;
import io.github.jhipster.application.service.dto.RubriqueDTO;
import io.github.jhipster.application.service.mapper.RubriqueMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.RubriqueCriteria;
import io.github.jhipster.application.service.RubriqueQueryService;

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
 * Integration tests for the {@Link RubriqueResource} REST controller.
 */
@SpringBootTest(classes = MutexParamsAdminApp.class)
public class RubriqueResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VISIBLE = false;
    private static final Boolean UPDATED_VISIBLE = true;

    private static final Integer DEFAULT_NUMERO_ORDRE = 1;
    private static final Integer UPDATED_NUMERO_ORDRE = 2;

    private static final String DEFAULT_TOOLTIP = "AAAAAAAAAA";
    private static final String UPDATED_TOOLTIP = "BBBBBBBBBB";

    @Autowired
    private RubriqueRepository rubriqueRepository;

    @Autowired
    private RubriqueMapper rubriqueMapper;

    @Autowired
    private RubriqueService rubriqueService;

    @Autowired
    private RubriqueQueryService rubriqueQueryService;

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

    private MockMvc restRubriqueMockMvc;

    private Rubrique rubrique;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RubriqueResource rubriqueResource = new RubriqueResource(rubriqueService, rubriqueQueryService);
        this.restRubriqueMockMvc = MockMvcBuilders.standaloneSetup(rubriqueResource)
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
    public static Rubrique createEntity(EntityManager em) {
        Rubrique rubrique = new Rubrique()
            .libelle(DEFAULT_LIBELLE)
            .visible(DEFAULT_VISIBLE)
            .numeroOrdre(DEFAULT_NUMERO_ORDRE)
            .tooltip(DEFAULT_TOOLTIP);
        return rubrique;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Rubrique createUpdatedEntity(EntityManager em) {
        Rubrique rubrique = new Rubrique()
            .libelle(UPDATED_LIBELLE)
            .visible(UPDATED_VISIBLE)
            .numeroOrdre(UPDATED_NUMERO_ORDRE)
            .tooltip(UPDATED_TOOLTIP);
        return rubrique;
    }

    @BeforeEach
    public void initTest() {
        rubrique = createEntity(em);
    }

    @Test
    @Transactional
    public void createRubrique() throws Exception {
        int databaseSizeBeforeCreate = rubriqueRepository.findAll().size();

        // Create the Rubrique
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(rubrique);
        restRubriqueMockMvc.perform(post("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isCreated());

        // Validate the Rubrique in the database
        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeCreate + 1);
        Rubrique testRubrique = rubriqueList.get(rubriqueList.size() - 1);
        assertThat(testRubrique.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testRubrique.isVisible()).isEqualTo(DEFAULT_VISIBLE);
        assertThat(testRubrique.getNumeroOrdre()).isEqualTo(DEFAULT_NUMERO_ORDRE);
        assertThat(testRubrique.getTooltip()).isEqualTo(DEFAULT_TOOLTIP);
    }

    @Test
    @Transactional
    public void createRubriqueWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = rubriqueRepository.findAll().size();

        // Create the Rubrique with an existing ID
        rubrique.setId(1L);
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(rubrique);

        // An entity with an existing ID cannot be created, so this API call must fail
        restRubriqueMockMvc.perform(post("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rubrique in the database
        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = rubriqueRepository.findAll().size();
        // set the field null
        rubrique.setLibelle(null);

        // Create the Rubrique, which fails.
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(rubrique);

        restRubriqueMockMvc.perform(post("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isBadRequest());

        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVisibleIsRequired() throws Exception {
        int databaseSizeBeforeTest = rubriqueRepository.findAll().size();
        // set the field null
        rubrique.setVisible(null);

        // Create the Rubrique, which fails.
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(rubrique);

        restRubriqueMockMvc.perform(post("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isBadRequest());

        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroOrdreIsRequired() throws Exception {
        int databaseSizeBeforeTest = rubriqueRepository.findAll().size();
        // set the field null
        rubrique.setNumeroOrdre(null);

        // Create the Rubrique, which fails.
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(rubrique);

        restRubriqueMockMvc.perform(post("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isBadRequest());

        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllRubriques() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList
        restRubriqueMockMvc.perform(get("/api/rubriques?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rubrique.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].visible").value(hasItem(DEFAULT_VISIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)))
            .andExpect(jsonPath("$.[*].tooltip").value(hasItem(DEFAULT_TOOLTIP.toString())));
    }
    
    @Test
    @Transactional
    public void getRubrique() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get the rubrique
        restRubriqueMockMvc.perform(get("/api/rubriques/{id}", rubrique.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(rubrique.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.visible").value(DEFAULT_VISIBLE.booleanValue()))
            .andExpect(jsonPath("$.numeroOrdre").value(DEFAULT_NUMERO_ORDRE))
            .andExpect(jsonPath("$.tooltip").value(DEFAULT_TOOLTIP.toString()));
    }

    @Test
    @Transactional
    public void getAllRubriquesByLibelleIsEqualToSomething() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where libelle equals to DEFAULT_LIBELLE
        defaultRubriqueShouldBeFound("libelle.equals=" + DEFAULT_LIBELLE);

        // Get all the rubriqueList where libelle equals to UPDATED_LIBELLE
        defaultRubriqueShouldNotBeFound("libelle.equals=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByLibelleIsInShouldWork() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where libelle in DEFAULT_LIBELLE or UPDATED_LIBELLE
        defaultRubriqueShouldBeFound("libelle.in=" + DEFAULT_LIBELLE + "," + UPDATED_LIBELLE);

        // Get all the rubriqueList where libelle equals to UPDATED_LIBELLE
        defaultRubriqueShouldNotBeFound("libelle.in=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByLibelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where libelle is not null
        defaultRubriqueShouldBeFound("libelle.specified=true");

        // Get all the rubriqueList where libelle is null
        defaultRubriqueShouldNotBeFound("libelle.specified=false");
    }

    @Test
    @Transactional
    public void getAllRubriquesByVisibleIsEqualToSomething() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where visible equals to DEFAULT_VISIBLE
        defaultRubriqueShouldBeFound("visible.equals=" + DEFAULT_VISIBLE);

        // Get all the rubriqueList where visible equals to UPDATED_VISIBLE
        defaultRubriqueShouldNotBeFound("visible.equals=" + UPDATED_VISIBLE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByVisibleIsInShouldWork() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where visible in DEFAULT_VISIBLE or UPDATED_VISIBLE
        defaultRubriqueShouldBeFound("visible.in=" + DEFAULT_VISIBLE + "," + UPDATED_VISIBLE);

        // Get all the rubriqueList where visible equals to UPDATED_VISIBLE
        defaultRubriqueShouldNotBeFound("visible.in=" + UPDATED_VISIBLE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByVisibleIsNullOrNotNull() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where visible is not null
        defaultRubriqueShouldBeFound("visible.specified=true");

        // Get all the rubriqueList where visible is null
        defaultRubriqueShouldNotBeFound("visible.specified=false");
    }

    @Test
    @Transactional
    public void getAllRubriquesByNumeroOrdreIsEqualToSomething() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where numeroOrdre equals to DEFAULT_NUMERO_ORDRE
        defaultRubriqueShouldBeFound("numeroOrdre.equals=" + DEFAULT_NUMERO_ORDRE);

        // Get all the rubriqueList where numeroOrdre equals to UPDATED_NUMERO_ORDRE
        defaultRubriqueShouldNotBeFound("numeroOrdre.equals=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByNumeroOrdreIsInShouldWork() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where numeroOrdre in DEFAULT_NUMERO_ORDRE or UPDATED_NUMERO_ORDRE
        defaultRubriqueShouldBeFound("numeroOrdre.in=" + DEFAULT_NUMERO_ORDRE + "," + UPDATED_NUMERO_ORDRE);

        // Get all the rubriqueList where numeroOrdre equals to UPDATED_NUMERO_ORDRE
        defaultRubriqueShouldNotBeFound("numeroOrdre.in=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByNumeroOrdreIsNullOrNotNull() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where numeroOrdre is not null
        defaultRubriqueShouldBeFound("numeroOrdre.specified=true");

        // Get all the rubriqueList where numeroOrdre is null
        defaultRubriqueShouldNotBeFound("numeroOrdre.specified=false");
    }

    @Test
    @Transactional
    public void getAllRubriquesByNumeroOrdreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where numeroOrdre greater than or equals to DEFAULT_NUMERO_ORDRE
        defaultRubriqueShouldBeFound("numeroOrdre.greaterOrEqualThan=" + DEFAULT_NUMERO_ORDRE);

        // Get all the rubriqueList where numeroOrdre greater than or equals to UPDATED_NUMERO_ORDRE
        defaultRubriqueShouldNotBeFound("numeroOrdre.greaterOrEqualThan=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllRubriquesByNumeroOrdreIsLessThanSomething() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where numeroOrdre less than or equals to DEFAULT_NUMERO_ORDRE
        defaultRubriqueShouldNotBeFound("numeroOrdre.lessThan=" + DEFAULT_NUMERO_ORDRE);

        // Get all the rubriqueList where numeroOrdre less than or equals to UPDATED_NUMERO_ORDRE
        defaultRubriqueShouldBeFound("numeroOrdre.lessThan=" + UPDATED_NUMERO_ORDRE);
    }


    @Test
    @Transactional
    public void getAllRubriquesByTooltipIsEqualToSomething() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where tooltip equals to DEFAULT_TOOLTIP
        defaultRubriqueShouldBeFound("tooltip.equals=" + DEFAULT_TOOLTIP);

        // Get all the rubriqueList where tooltip equals to UPDATED_TOOLTIP
        defaultRubriqueShouldNotBeFound("tooltip.equals=" + UPDATED_TOOLTIP);
    }

    @Test
    @Transactional
    public void getAllRubriquesByTooltipIsInShouldWork() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where tooltip in DEFAULT_TOOLTIP or UPDATED_TOOLTIP
        defaultRubriqueShouldBeFound("tooltip.in=" + DEFAULT_TOOLTIP + "," + UPDATED_TOOLTIP);

        // Get all the rubriqueList where tooltip equals to UPDATED_TOOLTIP
        defaultRubriqueShouldNotBeFound("tooltip.in=" + UPDATED_TOOLTIP);
    }

    @Test
    @Transactional
    public void getAllRubriquesByTooltipIsNullOrNotNull() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        // Get all the rubriqueList where tooltip is not null
        defaultRubriqueShouldBeFound("tooltip.specified=true");

        // Get all the rubriqueList where tooltip is null
        defaultRubriqueShouldNotBeFound("tooltip.specified=false");
    }

    @Test
    @Transactional
    public void getAllRubriquesByGarantieIsEqualToSomething() throws Exception {
        // Initialize the database
        Garantie garantie = GarantieResourceIT.createEntity(em);
        em.persist(garantie);
        em.flush();
        rubrique.addGarantie(garantie);
        rubriqueRepository.saveAndFlush(rubrique);
        Long garantieId = garantie.getId();

        // Get all the rubriqueList where garantie equals to garantieId
        defaultRubriqueShouldBeFound("garantieId.equals=" + garantieId);

        // Get all the rubriqueList where garantie equals to garantieId + 1
        defaultRubriqueShouldNotBeFound("garantieId.equals=" + (garantieId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultRubriqueShouldBeFound(String filter) throws Exception {
        restRubriqueMockMvc.perform(get("/api/rubriques?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rubrique.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].visible").value(hasItem(DEFAULT_VISIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)))
            .andExpect(jsonPath("$.[*].tooltip").value(hasItem(DEFAULT_TOOLTIP)));

        // Check, that the count call also returns 1
        restRubriqueMockMvc.perform(get("/api/rubriques/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultRubriqueShouldNotBeFound(String filter) throws Exception {
        restRubriqueMockMvc.perform(get("/api/rubriques?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restRubriqueMockMvc.perform(get("/api/rubriques/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingRubrique() throws Exception {
        // Get the rubrique
        restRubriqueMockMvc.perform(get("/api/rubriques/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateRubrique() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        int databaseSizeBeforeUpdate = rubriqueRepository.findAll().size();

        // Update the rubrique
        Rubrique updatedRubrique = rubriqueRepository.findById(rubrique.getId()).get();
        // Disconnect from session so that the updates on updatedRubrique are not directly saved in db
        em.detach(updatedRubrique);
        updatedRubrique
            .libelle(UPDATED_LIBELLE)
            .visible(UPDATED_VISIBLE)
            .numeroOrdre(UPDATED_NUMERO_ORDRE)
            .tooltip(UPDATED_TOOLTIP);
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(updatedRubrique);

        restRubriqueMockMvc.perform(put("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isOk());

        // Validate the Rubrique in the database
        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeUpdate);
        Rubrique testRubrique = rubriqueList.get(rubriqueList.size() - 1);
        assertThat(testRubrique.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testRubrique.isVisible()).isEqualTo(UPDATED_VISIBLE);
        assertThat(testRubrique.getNumeroOrdre()).isEqualTo(UPDATED_NUMERO_ORDRE);
        assertThat(testRubrique.getTooltip()).isEqualTo(UPDATED_TOOLTIP);
    }

    @Test
    @Transactional
    public void updateNonExistingRubrique() throws Exception {
        int databaseSizeBeforeUpdate = rubriqueRepository.findAll().size();

        // Create the Rubrique
        RubriqueDTO rubriqueDTO = rubriqueMapper.toDto(rubrique);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRubriqueMockMvc.perform(put("/api/rubriques")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(rubriqueDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Rubrique in the database
        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteRubrique() throws Exception {
        // Initialize the database
        rubriqueRepository.saveAndFlush(rubrique);

        int databaseSizeBeforeDelete = rubriqueRepository.findAll().size();

        // Delete the rubrique
        restRubriqueMockMvc.perform(delete("/api/rubriques/{id}", rubrique.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Rubrique> rubriqueList = rubriqueRepository.findAll();
        assertThat(rubriqueList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Rubrique.class);
        Rubrique rubrique1 = new Rubrique();
        rubrique1.setId(1L);
        Rubrique rubrique2 = new Rubrique();
        rubrique2.setId(rubrique1.getId());
        assertThat(rubrique1).isEqualTo(rubrique2);
        rubrique2.setId(2L);
        assertThat(rubrique1).isNotEqualTo(rubrique2);
        rubrique1.setId(null);
        assertThat(rubrique1).isNotEqualTo(rubrique2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RubriqueDTO.class);
        RubriqueDTO rubriqueDTO1 = new RubriqueDTO();
        rubriqueDTO1.setId(1L);
        RubriqueDTO rubriqueDTO2 = new RubriqueDTO();
        assertThat(rubriqueDTO1).isNotEqualTo(rubriqueDTO2);
        rubriqueDTO2.setId(rubriqueDTO1.getId());
        assertThat(rubriqueDTO1).isEqualTo(rubriqueDTO2);
        rubriqueDTO2.setId(2L);
        assertThat(rubriqueDTO1).isNotEqualTo(rubriqueDTO2);
        rubriqueDTO1.setId(null);
        assertThat(rubriqueDTO1).isNotEqualTo(rubriqueDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(rubriqueMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(rubriqueMapper.fromId(null)).isNull();
    }
}
