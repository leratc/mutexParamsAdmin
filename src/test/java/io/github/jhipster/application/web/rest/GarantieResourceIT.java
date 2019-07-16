package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MutexParamsAdminApp;
import io.github.jhipster.application.domain.Garantie;
import io.github.jhipster.application.domain.Produit;
import io.github.jhipster.application.domain.Rubrique;
import io.github.jhipster.application.domain.Prestation;
import io.github.jhipster.application.repository.GarantieRepository;
import io.github.jhipster.application.service.GarantieService;
import io.github.jhipster.application.service.dto.GarantieDTO;
import io.github.jhipster.application.service.mapper.GarantieMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.GarantieCriteria;
import io.github.jhipster.application.service.GarantieQueryService;

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
import org.springframework.util.Base64Utils;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link GarantieResource} REST controller.
 */
@SpringBootTest(classes = MutexParamsAdminApp.class)
public class GarantieResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLESELECTION = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLESELECTION = "BBBBBBBBBB";

    private static final String DEFAULT_PRECISIONLIBELLESELECTION = "AAAAAAAAAA";
    private static final String UPDATED_PRECISIONLIBELLESELECTION = "BBBBBBBBBB";

    private static final String DEFAULT_TOOLTIP = "AAAAAAAAAA";
    private static final String UPDATED_TOOLTIP = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_CHOISIE_PAR_DEFAUT = false;
    private static final Boolean UPDATED_CHOISIE_PAR_DEFAUT = true;

    private static final Boolean DEFAULT_MODIFIABLE = false;
    private static final Boolean UPDATED_MODIFIABLE = true;

    private static final Boolean DEFAULT_VISIBLE = false;
    private static final Boolean UPDATED_VISIBLE = true;

    private static final Integer DEFAULT_NUMERO_ORDRE = 1;
    private static final Integer UPDATED_NUMERO_ORDRE = 2;

    private static final String DEFAULT_FILTRE_IDENTIFIANT = "AAAAAAAAAA";
    private static final String UPDATED_FILTRE_IDENTIFIANT = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE_SPECIFICITE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE_SPECIFICITE = "BBBBBBBBBB";

    private static final String DEFAULT_LIBELLE_RISQUE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE_RISQUE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUPEMENT_TARIFAIRE = "AAAAA";
    private static final String UPDATED_GROUPEMENT_TARIFAIRE = "BBBBB";

    @Autowired
    private GarantieRepository garantieRepository;

    @Autowired
    private GarantieMapper garantieMapper;

    @Autowired
    private GarantieService garantieService;

    @Autowired
    private GarantieQueryService garantieQueryService;

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

    private MockMvc restGarantieMockMvc;

    private Garantie garantie;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final GarantieResource garantieResource = new GarantieResource(garantieService, garantieQueryService);
        this.restGarantieMockMvc = MockMvcBuilders.standaloneSetup(garantieResource)
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
    public static Garantie createEntity(EntityManager em) {
        Garantie garantie = new Garantie()
            .libelle(DEFAULT_LIBELLE)
            .libelleselection(DEFAULT_LIBELLESELECTION)
            .precisionlibelleselection(DEFAULT_PRECISIONLIBELLESELECTION)
            .tooltip(DEFAULT_TOOLTIP)
            .reference(DEFAULT_REFERENCE)
            .choisieParDefaut(DEFAULT_CHOISIE_PAR_DEFAUT)
            .modifiable(DEFAULT_MODIFIABLE)
            .visible(DEFAULT_VISIBLE)
            .numeroOrdre(DEFAULT_NUMERO_ORDRE)
            .filtreIdentifiant(DEFAULT_FILTRE_IDENTIFIANT)
            .typeSpecificite(DEFAULT_TYPE_SPECIFICITE)
            .libelleRisque(DEFAULT_LIBELLE_RISQUE)
            .groupementTarifaire(DEFAULT_GROUPEMENT_TARIFAIRE);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        garantie.setProduit(produit);
        // Add required entity
        Rubrique rubrique;
        if (TestUtil.findAll(em, Rubrique.class).isEmpty()) {
            rubrique = RubriqueResourceIT.createEntity(em);
            em.persist(rubrique);
            em.flush();
        } else {
            rubrique = TestUtil.findAll(em, Rubrique.class).get(0);
        }
        garantie.setRubrique(rubrique);
        return garantie;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Garantie createUpdatedEntity(EntityManager em) {
        Garantie garantie = new Garantie()
            .libelle(UPDATED_LIBELLE)
            .libelleselection(UPDATED_LIBELLESELECTION)
            .precisionlibelleselection(UPDATED_PRECISIONLIBELLESELECTION)
            .tooltip(UPDATED_TOOLTIP)
            .reference(UPDATED_REFERENCE)
            .choisieParDefaut(UPDATED_CHOISIE_PAR_DEFAUT)
            .modifiable(UPDATED_MODIFIABLE)
            .visible(UPDATED_VISIBLE)
            .numeroOrdre(UPDATED_NUMERO_ORDRE)
            .filtreIdentifiant(UPDATED_FILTRE_IDENTIFIANT)
            .typeSpecificite(UPDATED_TYPE_SPECIFICITE)
            .libelleRisque(UPDATED_LIBELLE_RISQUE)
            .groupementTarifaire(UPDATED_GROUPEMENT_TARIFAIRE);
        // Add required entity
        Produit produit;
        if (TestUtil.findAll(em, Produit.class).isEmpty()) {
            produit = ProduitResourceIT.createUpdatedEntity(em);
            em.persist(produit);
            em.flush();
        } else {
            produit = TestUtil.findAll(em, Produit.class).get(0);
        }
        garantie.setProduit(produit);
        // Add required entity
        Rubrique rubrique;
        if (TestUtil.findAll(em, Rubrique.class).isEmpty()) {
            rubrique = RubriqueResourceIT.createUpdatedEntity(em);
            em.persist(rubrique);
            em.flush();
        } else {
            rubrique = TestUtil.findAll(em, Rubrique.class).get(0);
        }
        garantie.setRubrique(rubrique);
        return garantie;
    }

    @BeforeEach
    public void initTest() {
        garantie = createEntity(em);
    }

    @Test
    @Transactional
    public void createGarantie() throws Exception {
        int databaseSizeBeforeCreate = garantieRepository.findAll().size();

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);
        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isCreated());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeCreate + 1);
        Garantie testGarantie = garantieList.get(garantieList.size() - 1);
        assertThat(testGarantie.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testGarantie.getLibelleselection()).isEqualTo(DEFAULT_LIBELLESELECTION);
        assertThat(testGarantie.getPrecisionlibelleselection()).isEqualTo(DEFAULT_PRECISIONLIBELLESELECTION);
        assertThat(testGarantie.getTooltip()).isEqualTo(DEFAULT_TOOLTIP);
        assertThat(testGarantie.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testGarantie.isChoisieParDefaut()).isEqualTo(DEFAULT_CHOISIE_PAR_DEFAUT);
        assertThat(testGarantie.isModifiable()).isEqualTo(DEFAULT_MODIFIABLE);
        assertThat(testGarantie.isVisible()).isEqualTo(DEFAULT_VISIBLE);
        assertThat(testGarantie.getNumeroOrdre()).isEqualTo(DEFAULT_NUMERO_ORDRE);
        assertThat(testGarantie.getFiltreIdentifiant()).isEqualTo(DEFAULT_FILTRE_IDENTIFIANT);
        assertThat(testGarantie.getTypeSpecificite()).isEqualTo(DEFAULT_TYPE_SPECIFICITE);
        assertThat(testGarantie.getLibelleRisque()).isEqualTo(DEFAULT_LIBELLE_RISQUE);
        assertThat(testGarantie.getGroupementTarifaire()).isEqualTo(DEFAULT_GROUPEMENT_TARIFAIRE);
    }

    @Test
    @Transactional
    public void createGarantieWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = garantieRepository.findAll().size();

        // Create the Garantie with an existing ID
        garantie.setId(1L);
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // An entity with an existing ID cannot be created, so this API call must fail
        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = garantieRepository.findAll().size();
        // set the field null
        garantie.setReference(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChoisieParDefautIsRequired() throws Exception {
        int databaseSizeBeforeTest = garantieRepository.findAll().size();
        // set the field null
        garantie.setChoisieParDefaut(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkModifiableIsRequired() throws Exception {
        int databaseSizeBeforeTest = garantieRepository.findAll().size();
        // set the field null
        garantie.setModifiable(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVisibleIsRequired() throws Exception {
        int databaseSizeBeforeTest = garantieRepository.findAll().size();
        // set the field null
        garantie.setVisible(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroOrdreIsRequired() throws Exception {
        int databaseSizeBeforeTest = garantieRepository.findAll().size();
        // set the field null
        garantie.setNumeroOrdre(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGroupementTarifaireIsRequired() throws Exception {
        int databaseSizeBeforeTest = garantieRepository.findAll().size();
        // set the field null
        garantie.setGroupementTarifaire(null);

        // Create the Garantie, which fails.
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        restGarantieMockMvc.perform(post("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllGaranties() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList
        restGarantieMockMvc.perform(get("/api/garanties?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(garantie.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].libelleselection").value(hasItem(DEFAULT_LIBELLESELECTION.toString())))
            .andExpect(jsonPath("$.[*].precisionlibelleselection").value(hasItem(DEFAULT_PRECISIONLIBELLESELECTION.toString())))
            .andExpect(jsonPath("$.[*].tooltip").value(hasItem(DEFAULT_TOOLTIP.toString())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].choisieParDefaut").value(hasItem(DEFAULT_CHOISIE_PAR_DEFAUT.booleanValue())))
            .andExpect(jsonPath("$.[*].modifiable").value(hasItem(DEFAULT_MODIFIABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].visible").value(hasItem(DEFAULT_VISIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)))
            .andExpect(jsonPath("$.[*].filtreIdentifiant").value(hasItem(DEFAULT_FILTRE_IDENTIFIANT.toString())))
            .andExpect(jsonPath("$.[*].typeSpecificite").value(hasItem(DEFAULT_TYPE_SPECIFICITE.toString())))
            .andExpect(jsonPath("$.[*].libelleRisque").value(hasItem(DEFAULT_LIBELLE_RISQUE.toString())))
            .andExpect(jsonPath("$.[*].groupementTarifaire").value(hasItem(DEFAULT_GROUPEMENT_TARIFAIRE.toString())));
    }
    
    @Test
    @Transactional
    public void getGarantie() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get the garantie
        restGarantieMockMvc.perform(get("/api/garanties/{id}", garantie.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(garantie.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.libelleselection").value(DEFAULT_LIBELLESELECTION.toString()))
            .andExpect(jsonPath("$.precisionlibelleselection").value(DEFAULT_PRECISIONLIBELLESELECTION.toString()))
            .andExpect(jsonPath("$.tooltip").value(DEFAULT_TOOLTIP.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.choisieParDefaut").value(DEFAULT_CHOISIE_PAR_DEFAUT.booleanValue()))
            .andExpect(jsonPath("$.modifiable").value(DEFAULT_MODIFIABLE.booleanValue()))
            .andExpect(jsonPath("$.visible").value(DEFAULT_VISIBLE.booleanValue()))
            .andExpect(jsonPath("$.numeroOrdre").value(DEFAULT_NUMERO_ORDRE))
            .andExpect(jsonPath("$.filtreIdentifiant").value(DEFAULT_FILTRE_IDENTIFIANT.toString()))
            .andExpect(jsonPath("$.typeSpecificite").value(DEFAULT_TYPE_SPECIFICITE.toString()))
            .andExpect(jsonPath("$.libelleRisque").value(DEFAULT_LIBELLE_RISQUE.toString()))
            .andExpect(jsonPath("$.groupementTarifaire").value(DEFAULT_GROUPEMENT_TARIFAIRE.toString()));
    }

    @Test
    @Transactional
    public void getAllGarantiesByTooltipIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where tooltip equals to DEFAULT_TOOLTIP
        defaultGarantieShouldBeFound("tooltip.equals=" + DEFAULT_TOOLTIP);

        // Get all the garantieList where tooltip equals to UPDATED_TOOLTIP
        defaultGarantieShouldNotBeFound("tooltip.equals=" + UPDATED_TOOLTIP);
    }

    @Test
    @Transactional
    public void getAllGarantiesByTooltipIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where tooltip in DEFAULT_TOOLTIP or UPDATED_TOOLTIP
        defaultGarantieShouldBeFound("tooltip.in=" + DEFAULT_TOOLTIP + "," + UPDATED_TOOLTIP);

        // Get all the garantieList where tooltip equals to UPDATED_TOOLTIP
        defaultGarantieShouldNotBeFound("tooltip.in=" + UPDATED_TOOLTIP);
    }

    @Test
    @Transactional
    public void getAllGarantiesByTooltipIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where tooltip is not null
        defaultGarantieShouldBeFound("tooltip.specified=true");

        // Get all the garantieList where tooltip is null
        defaultGarantieShouldNotBeFound("tooltip.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByReferenceIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where reference equals to DEFAULT_REFERENCE
        defaultGarantieShouldBeFound("reference.equals=" + DEFAULT_REFERENCE);

        // Get all the garantieList where reference equals to UPDATED_REFERENCE
        defaultGarantieShouldNotBeFound("reference.equals=" + UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByReferenceIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where reference in DEFAULT_REFERENCE or UPDATED_REFERENCE
        defaultGarantieShouldBeFound("reference.in=" + DEFAULT_REFERENCE + "," + UPDATED_REFERENCE);

        // Get all the garantieList where reference equals to UPDATED_REFERENCE
        defaultGarantieShouldNotBeFound("reference.in=" + UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByReferenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where reference is not null
        defaultGarantieShouldBeFound("reference.specified=true");

        // Get all the garantieList where reference is null
        defaultGarantieShouldNotBeFound("reference.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByChoisieParDefautIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where choisieParDefaut equals to DEFAULT_CHOISIE_PAR_DEFAUT
        defaultGarantieShouldBeFound("choisieParDefaut.equals=" + DEFAULT_CHOISIE_PAR_DEFAUT);

        // Get all the garantieList where choisieParDefaut equals to UPDATED_CHOISIE_PAR_DEFAUT
        defaultGarantieShouldNotBeFound("choisieParDefaut.equals=" + UPDATED_CHOISIE_PAR_DEFAUT);
    }

    @Test
    @Transactional
    public void getAllGarantiesByChoisieParDefautIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where choisieParDefaut in DEFAULT_CHOISIE_PAR_DEFAUT or UPDATED_CHOISIE_PAR_DEFAUT
        defaultGarantieShouldBeFound("choisieParDefaut.in=" + DEFAULT_CHOISIE_PAR_DEFAUT + "," + UPDATED_CHOISIE_PAR_DEFAUT);

        // Get all the garantieList where choisieParDefaut equals to UPDATED_CHOISIE_PAR_DEFAUT
        defaultGarantieShouldNotBeFound("choisieParDefaut.in=" + UPDATED_CHOISIE_PAR_DEFAUT);
    }

    @Test
    @Transactional
    public void getAllGarantiesByChoisieParDefautIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where choisieParDefaut is not null
        defaultGarantieShouldBeFound("choisieParDefaut.specified=true");

        // Get all the garantieList where choisieParDefaut is null
        defaultGarantieShouldNotBeFound("choisieParDefaut.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByModifiableIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where modifiable equals to DEFAULT_MODIFIABLE
        defaultGarantieShouldBeFound("modifiable.equals=" + DEFAULT_MODIFIABLE);

        // Get all the garantieList where modifiable equals to UPDATED_MODIFIABLE
        defaultGarantieShouldNotBeFound("modifiable.equals=" + UPDATED_MODIFIABLE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByModifiableIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where modifiable in DEFAULT_MODIFIABLE or UPDATED_MODIFIABLE
        defaultGarantieShouldBeFound("modifiable.in=" + DEFAULT_MODIFIABLE + "," + UPDATED_MODIFIABLE);

        // Get all the garantieList where modifiable equals to UPDATED_MODIFIABLE
        defaultGarantieShouldNotBeFound("modifiable.in=" + UPDATED_MODIFIABLE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByModifiableIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where modifiable is not null
        defaultGarantieShouldBeFound("modifiable.specified=true");

        // Get all the garantieList where modifiable is null
        defaultGarantieShouldNotBeFound("modifiable.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByVisibleIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where visible equals to DEFAULT_VISIBLE
        defaultGarantieShouldBeFound("visible.equals=" + DEFAULT_VISIBLE);

        // Get all the garantieList where visible equals to UPDATED_VISIBLE
        defaultGarantieShouldNotBeFound("visible.equals=" + UPDATED_VISIBLE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByVisibleIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where visible in DEFAULT_VISIBLE or UPDATED_VISIBLE
        defaultGarantieShouldBeFound("visible.in=" + DEFAULT_VISIBLE + "," + UPDATED_VISIBLE);

        // Get all the garantieList where visible equals to UPDATED_VISIBLE
        defaultGarantieShouldNotBeFound("visible.in=" + UPDATED_VISIBLE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByVisibleIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where visible is not null
        defaultGarantieShouldBeFound("visible.specified=true");

        // Get all the garantieList where visible is null
        defaultGarantieShouldNotBeFound("visible.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByNumeroOrdreIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where numeroOrdre equals to DEFAULT_NUMERO_ORDRE
        defaultGarantieShouldBeFound("numeroOrdre.equals=" + DEFAULT_NUMERO_ORDRE);

        // Get all the garantieList where numeroOrdre equals to UPDATED_NUMERO_ORDRE
        defaultGarantieShouldNotBeFound("numeroOrdre.equals=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByNumeroOrdreIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where numeroOrdre in DEFAULT_NUMERO_ORDRE or UPDATED_NUMERO_ORDRE
        defaultGarantieShouldBeFound("numeroOrdre.in=" + DEFAULT_NUMERO_ORDRE + "," + UPDATED_NUMERO_ORDRE);

        // Get all the garantieList where numeroOrdre equals to UPDATED_NUMERO_ORDRE
        defaultGarantieShouldNotBeFound("numeroOrdre.in=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByNumeroOrdreIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where numeroOrdre is not null
        defaultGarantieShouldBeFound("numeroOrdre.specified=true");

        // Get all the garantieList where numeroOrdre is null
        defaultGarantieShouldNotBeFound("numeroOrdre.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByNumeroOrdreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where numeroOrdre greater than or equals to DEFAULT_NUMERO_ORDRE
        defaultGarantieShouldBeFound("numeroOrdre.greaterOrEqualThan=" + DEFAULT_NUMERO_ORDRE);

        // Get all the garantieList where numeroOrdre greater than or equals to UPDATED_NUMERO_ORDRE
        defaultGarantieShouldNotBeFound("numeroOrdre.greaterOrEqualThan=" + UPDATED_NUMERO_ORDRE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByNumeroOrdreIsLessThanSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where numeroOrdre less than or equals to DEFAULT_NUMERO_ORDRE
        defaultGarantieShouldNotBeFound("numeroOrdre.lessThan=" + DEFAULT_NUMERO_ORDRE);

        // Get all the garantieList where numeroOrdre less than or equals to UPDATED_NUMERO_ORDRE
        defaultGarantieShouldBeFound("numeroOrdre.lessThan=" + UPDATED_NUMERO_ORDRE);
    }


    @Test
    @Transactional
    public void getAllGarantiesByFiltreIdentifiantIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where filtreIdentifiant equals to DEFAULT_FILTRE_IDENTIFIANT
        defaultGarantieShouldBeFound("filtreIdentifiant.equals=" + DEFAULT_FILTRE_IDENTIFIANT);

        // Get all the garantieList where filtreIdentifiant equals to UPDATED_FILTRE_IDENTIFIANT
        defaultGarantieShouldNotBeFound("filtreIdentifiant.equals=" + UPDATED_FILTRE_IDENTIFIANT);
    }

    @Test
    @Transactional
    public void getAllGarantiesByFiltreIdentifiantIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where filtreIdentifiant in DEFAULT_FILTRE_IDENTIFIANT or UPDATED_FILTRE_IDENTIFIANT
        defaultGarantieShouldBeFound("filtreIdentifiant.in=" + DEFAULT_FILTRE_IDENTIFIANT + "," + UPDATED_FILTRE_IDENTIFIANT);

        // Get all the garantieList where filtreIdentifiant equals to UPDATED_FILTRE_IDENTIFIANT
        defaultGarantieShouldNotBeFound("filtreIdentifiant.in=" + UPDATED_FILTRE_IDENTIFIANT);
    }

    @Test
    @Transactional
    public void getAllGarantiesByFiltreIdentifiantIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where filtreIdentifiant is not null
        defaultGarantieShouldBeFound("filtreIdentifiant.specified=true");

        // Get all the garantieList where filtreIdentifiant is null
        defaultGarantieShouldNotBeFound("filtreIdentifiant.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByTypeSpecificiteIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where typeSpecificite equals to DEFAULT_TYPE_SPECIFICITE
        defaultGarantieShouldBeFound("typeSpecificite.equals=" + DEFAULT_TYPE_SPECIFICITE);

        // Get all the garantieList where typeSpecificite equals to UPDATED_TYPE_SPECIFICITE
        defaultGarantieShouldNotBeFound("typeSpecificite.equals=" + UPDATED_TYPE_SPECIFICITE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByTypeSpecificiteIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where typeSpecificite in DEFAULT_TYPE_SPECIFICITE or UPDATED_TYPE_SPECIFICITE
        defaultGarantieShouldBeFound("typeSpecificite.in=" + DEFAULT_TYPE_SPECIFICITE + "," + UPDATED_TYPE_SPECIFICITE);

        // Get all the garantieList where typeSpecificite equals to UPDATED_TYPE_SPECIFICITE
        defaultGarantieShouldNotBeFound("typeSpecificite.in=" + UPDATED_TYPE_SPECIFICITE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByTypeSpecificiteIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where typeSpecificite is not null
        defaultGarantieShouldBeFound("typeSpecificite.specified=true");

        // Get all the garantieList where typeSpecificite is null
        defaultGarantieShouldNotBeFound("typeSpecificite.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByLibelleRisqueIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where libelleRisque equals to DEFAULT_LIBELLE_RISQUE
        defaultGarantieShouldBeFound("libelleRisque.equals=" + DEFAULT_LIBELLE_RISQUE);

        // Get all the garantieList where libelleRisque equals to UPDATED_LIBELLE_RISQUE
        defaultGarantieShouldNotBeFound("libelleRisque.equals=" + UPDATED_LIBELLE_RISQUE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByLibelleRisqueIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where libelleRisque in DEFAULT_LIBELLE_RISQUE or UPDATED_LIBELLE_RISQUE
        defaultGarantieShouldBeFound("libelleRisque.in=" + DEFAULT_LIBELLE_RISQUE + "," + UPDATED_LIBELLE_RISQUE);

        // Get all the garantieList where libelleRisque equals to UPDATED_LIBELLE_RISQUE
        defaultGarantieShouldNotBeFound("libelleRisque.in=" + UPDATED_LIBELLE_RISQUE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByLibelleRisqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where libelleRisque is not null
        defaultGarantieShouldBeFound("libelleRisque.specified=true");

        // Get all the garantieList where libelleRisque is null
        defaultGarantieShouldNotBeFound("libelleRisque.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByGroupementTarifaireIsEqualToSomething() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where groupementTarifaire equals to DEFAULT_GROUPEMENT_TARIFAIRE
        defaultGarantieShouldBeFound("groupementTarifaire.equals=" + DEFAULT_GROUPEMENT_TARIFAIRE);

        // Get all the garantieList where groupementTarifaire equals to UPDATED_GROUPEMENT_TARIFAIRE
        defaultGarantieShouldNotBeFound("groupementTarifaire.equals=" + UPDATED_GROUPEMENT_TARIFAIRE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByGroupementTarifaireIsInShouldWork() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where groupementTarifaire in DEFAULT_GROUPEMENT_TARIFAIRE or UPDATED_GROUPEMENT_TARIFAIRE
        defaultGarantieShouldBeFound("groupementTarifaire.in=" + DEFAULT_GROUPEMENT_TARIFAIRE + "," + UPDATED_GROUPEMENT_TARIFAIRE);

        // Get all the garantieList where groupementTarifaire equals to UPDATED_GROUPEMENT_TARIFAIRE
        defaultGarantieShouldNotBeFound("groupementTarifaire.in=" + UPDATED_GROUPEMENT_TARIFAIRE);
    }

    @Test
    @Transactional
    public void getAllGarantiesByGroupementTarifaireIsNullOrNotNull() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        // Get all the garantieList where groupementTarifaire is not null
        defaultGarantieShouldBeFound("groupementTarifaire.specified=true");

        // Get all the garantieList where groupementTarifaire is null
        defaultGarantieShouldNotBeFound("groupementTarifaire.specified=false");
    }

    @Test
    @Transactional
    public void getAllGarantiesByProduitIsEqualToSomething() throws Exception {
        // Get already existing entity
        Produit produit = garantie.getProduit();
        garantieRepository.saveAndFlush(garantie);
        Long produitId = produit.getId();

        // Get all the garantieList where produit equals to produitId
        defaultGarantieShouldBeFound("produitId.equals=" + produitId);

        // Get all the garantieList where produit equals to produitId + 1
        defaultGarantieShouldNotBeFound("produitId.equals=" + (produitId + 1));
    }


    @Test
    @Transactional
    public void getAllGarantiesByRubriqueIsEqualToSomething() throws Exception {
        // Get already existing entity
        Rubrique rubrique = garantie.getRubrique();
        garantieRepository.saveAndFlush(garantie);
        Long rubriqueId = rubrique.getId();

        // Get all the garantieList where rubrique equals to rubriqueId
        defaultGarantieShouldBeFound("rubriqueId.equals=" + rubriqueId);

        // Get all the garantieList where rubrique equals to rubriqueId + 1
        defaultGarantieShouldNotBeFound("rubriqueId.equals=" + (rubriqueId + 1));
    }


    @Test
    @Transactional
    public void getAllGarantiesByPrestationIsEqualToSomething() throws Exception {
        // Initialize the database
        Prestation prestation = PrestationResourceIT.createEntity(em);
        em.persist(prestation);
        em.flush();
        garantie.addPrestation(prestation);
        garantieRepository.saveAndFlush(garantie);
        Long prestationId = prestation.getId();

        // Get all the garantieList where prestation equals to prestationId
        defaultGarantieShouldBeFound("prestationId.equals=" + prestationId);

        // Get all the garantieList where prestation equals to prestationId + 1
        defaultGarantieShouldNotBeFound("prestationId.equals=" + (prestationId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultGarantieShouldBeFound(String filter) throws Exception {
        restGarantieMockMvc.perform(get("/api/garanties?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(garantie.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].libelleselection").value(hasItem(DEFAULT_LIBELLESELECTION.toString())))
            .andExpect(jsonPath("$.[*].precisionlibelleselection").value(hasItem(DEFAULT_PRECISIONLIBELLESELECTION.toString())))
            .andExpect(jsonPath("$.[*].tooltip").value(hasItem(DEFAULT_TOOLTIP)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].choisieParDefaut").value(hasItem(DEFAULT_CHOISIE_PAR_DEFAUT.booleanValue())))
            .andExpect(jsonPath("$.[*].modifiable").value(hasItem(DEFAULT_MODIFIABLE.booleanValue())))
            .andExpect(jsonPath("$.[*].visible").value(hasItem(DEFAULT_VISIBLE.booleanValue())))
            .andExpect(jsonPath("$.[*].numeroOrdre").value(hasItem(DEFAULT_NUMERO_ORDRE)))
            .andExpect(jsonPath("$.[*].filtreIdentifiant").value(hasItem(DEFAULT_FILTRE_IDENTIFIANT)))
            .andExpect(jsonPath("$.[*].typeSpecificite").value(hasItem(DEFAULT_TYPE_SPECIFICITE)))
            .andExpect(jsonPath("$.[*].libelleRisque").value(hasItem(DEFAULT_LIBELLE_RISQUE)))
            .andExpect(jsonPath("$.[*].groupementTarifaire").value(hasItem(DEFAULT_GROUPEMENT_TARIFAIRE)));

        // Check, that the count call also returns 1
        restGarantieMockMvc.perform(get("/api/garanties/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultGarantieShouldNotBeFound(String filter) throws Exception {
        restGarantieMockMvc.perform(get("/api/garanties?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restGarantieMockMvc.perform(get("/api/garanties/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingGarantie() throws Exception {
        // Get the garantie
        restGarantieMockMvc.perform(get("/api/garanties/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateGarantie() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        int databaseSizeBeforeUpdate = garantieRepository.findAll().size();

        // Update the garantie
        Garantie updatedGarantie = garantieRepository.findById(garantie.getId()).get();
        // Disconnect from session so that the updates on updatedGarantie are not directly saved in db
        em.detach(updatedGarantie);
        updatedGarantie
            .libelle(UPDATED_LIBELLE)
            .libelleselection(UPDATED_LIBELLESELECTION)
            .precisionlibelleselection(UPDATED_PRECISIONLIBELLESELECTION)
            .tooltip(UPDATED_TOOLTIP)
            .reference(UPDATED_REFERENCE)
            .choisieParDefaut(UPDATED_CHOISIE_PAR_DEFAUT)
            .modifiable(UPDATED_MODIFIABLE)
            .visible(UPDATED_VISIBLE)
            .numeroOrdre(UPDATED_NUMERO_ORDRE)
            .filtreIdentifiant(UPDATED_FILTRE_IDENTIFIANT)
            .typeSpecificite(UPDATED_TYPE_SPECIFICITE)
            .libelleRisque(UPDATED_LIBELLE_RISQUE)
            .groupementTarifaire(UPDATED_GROUPEMENT_TARIFAIRE);
        GarantieDTO garantieDTO = garantieMapper.toDto(updatedGarantie);

        restGarantieMockMvc.perform(put("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isOk());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeUpdate);
        Garantie testGarantie = garantieList.get(garantieList.size() - 1);
        assertThat(testGarantie.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testGarantie.getLibelleselection()).isEqualTo(UPDATED_LIBELLESELECTION);
        assertThat(testGarantie.getPrecisionlibelleselection()).isEqualTo(UPDATED_PRECISIONLIBELLESELECTION);
        assertThat(testGarantie.getTooltip()).isEqualTo(UPDATED_TOOLTIP);
        assertThat(testGarantie.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testGarantie.isChoisieParDefaut()).isEqualTo(UPDATED_CHOISIE_PAR_DEFAUT);
        assertThat(testGarantie.isModifiable()).isEqualTo(UPDATED_MODIFIABLE);
        assertThat(testGarantie.isVisible()).isEqualTo(UPDATED_VISIBLE);
        assertThat(testGarantie.getNumeroOrdre()).isEqualTo(UPDATED_NUMERO_ORDRE);
        assertThat(testGarantie.getFiltreIdentifiant()).isEqualTo(UPDATED_FILTRE_IDENTIFIANT);
        assertThat(testGarantie.getTypeSpecificite()).isEqualTo(UPDATED_TYPE_SPECIFICITE);
        assertThat(testGarantie.getLibelleRisque()).isEqualTo(UPDATED_LIBELLE_RISQUE);
        assertThat(testGarantie.getGroupementTarifaire()).isEqualTo(UPDATED_GROUPEMENT_TARIFAIRE);
    }

    @Test
    @Transactional
    public void updateNonExistingGarantie() throws Exception {
        int databaseSizeBeforeUpdate = garantieRepository.findAll().size();

        // Create the Garantie
        GarantieDTO garantieDTO = garantieMapper.toDto(garantie);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restGarantieMockMvc.perform(put("/api/garanties")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(garantieDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Garantie in the database
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteGarantie() throws Exception {
        // Initialize the database
        garantieRepository.saveAndFlush(garantie);

        int databaseSizeBeforeDelete = garantieRepository.findAll().size();

        // Delete the garantie
        restGarantieMockMvc.perform(delete("/api/garanties/{id}", garantie.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Garantie> garantieList = garantieRepository.findAll();
        assertThat(garantieList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Garantie.class);
        Garantie garantie1 = new Garantie();
        garantie1.setId(1L);
        Garantie garantie2 = new Garantie();
        garantie2.setId(garantie1.getId());
        assertThat(garantie1).isEqualTo(garantie2);
        garantie2.setId(2L);
        assertThat(garantie1).isNotEqualTo(garantie2);
        garantie1.setId(null);
        assertThat(garantie1).isNotEqualTo(garantie2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(GarantieDTO.class);
        GarantieDTO garantieDTO1 = new GarantieDTO();
        garantieDTO1.setId(1L);
        GarantieDTO garantieDTO2 = new GarantieDTO();
        assertThat(garantieDTO1).isNotEqualTo(garantieDTO2);
        garantieDTO2.setId(garantieDTO1.getId());
        assertThat(garantieDTO1).isEqualTo(garantieDTO2);
        garantieDTO2.setId(2L);
        assertThat(garantieDTO1).isNotEqualTo(garantieDTO2);
        garantieDTO1.setId(null);
        assertThat(garantieDTO1).isNotEqualTo(garantieDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(garantieMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(garantieMapper.fromId(null)).isNull();
    }
}
