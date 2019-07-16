package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.MutexParamsAdminApp;
import io.github.jhipster.application.domain.Produit;
import io.github.jhipster.application.domain.Moduledefinition;
import io.github.jhipster.application.domain.Garantie;
import io.github.jhipster.application.repository.ProduitRepository;
import io.github.jhipster.application.service.ProduitService;
import io.github.jhipster.application.service.dto.ProduitDTO;
import io.github.jhipster.application.service.mapper.ProduitMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ProduitCriteria;
import io.github.jhipster.application.service.ProduitQueryService;

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
 * Integration tests for the {@Link ProduitResource} REST controller.
 */
@SpringBootTest(classes = MutexParamsAdminApp.class)
public class ProduitResourceIT {

    private static final String DEFAULT_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_REFERENCE = "BBBBBBBBBB";

    private static final Integer DEFAULT_NUMEROORDRE = 1;
    private static final Integer UPDATED_NUMEROORDRE = 2;

    private static final String DEFAULT_TYPEPRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_TYPEPRODUIT = "BBBBBBBBBB";

    private static final String DEFAULT_FAMILLEPRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_FAMILLEPRODUIT = "BBBBBBBBBB";

    private static final Boolean DEFAULT_PRODUITADDITIONNEL = false;
    private static final Boolean UPDATED_PRODUITADDITIONNEL = true;

    private static final Boolean DEFAULT_OBLIGATOIREPOURENTREPRISE = false;
    private static final Boolean UPDATED_OBLIGATOIREPOURENTREPRISE = true;

    private static final Integer DEFAULT_EFFECTIFMAX = 1;
    private static final Integer UPDATED_EFFECTIFMAX = 2;

    private static final String DEFAULT_CHARTEGRAPHIQUE = "AAAAAAAAAA";
    private static final String UPDATED_CHARTEGRAPHIQUE = "BBBBBBBBBB";

    private static final String DEFAULT_ALERTETARIFICATIONEXTERNE = "AAAAAAAAAA";
    private static final String UPDATED_ALERTETARIFICATIONEXTERNE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_QUESTIONNAIRERECEXIGE = false;
    private static final Boolean UPDATED_QUESTIONNAIRERECEXIGE = true;

    private static final String DEFAULT_LIBELLEMODULE = "AAAAAAAAAA";
    private static final String UPDATED_LIBELLEMODULE = "BBBBBBBBBB";

    private static final String DEFAULT_NOMCHAMPBADH = "AAAAAAAAAA";
    private static final String UPDATED_NOMCHAMPBADH = "BBBBBBBBBB";

    private static final String DEFAULT_TYPEQUESTIONNAIREREC = "AAAAAAAAAA";
    private static final String UPDATED_TYPEQUESTIONNAIREREC = "BBBBBBBBBB";

    @Autowired
    private ProduitRepository produitRepository;

    @Autowired
    private ProduitMapper produitMapper;

    @Autowired
    private ProduitService produitService;

    @Autowired
    private ProduitQueryService produitQueryService;

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

    private MockMvc restProduitMockMvc;

    private Produit produit;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ProduitResource produitResource = new ProduitResource(produitService, produitQueryService);
        this.restProduitMockMvc = MockMvcBuilders.standaloneSetup(produitResource)
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
    public static Produit createEntity(EntityManager em) {
        Produit produit = new Produit()
            .libelle(DEFAULT_LIBELLE)
            .reference(DEFAULT_REFERENCE)
            .numeroordre(DEFAULT_NUMEROORDRE)
            .typeproduit(DEFAULT_TYPEPRODUIT)
            .familleproduit(DEFAULT_FAMILLEPRODUIT)
            .produitadditionnel(DEFAULT_PRODUITADDITIONNEL)
            .obligatoirepourentreprise(DEFAULT_OBLIGATOIREPOURENTREPRISE)
            .effectifmax(DEFAULT_EFFECTIFMAX)
            .chartegraphique(DEFAULT_CHARTEGRAPHIQUE)
            .alertetarificationexterne(DEFAULT_ALERTETARIFICATIONEXTERNE)
            .questionnairerecexige(DEFAULT_QUESTIONNAIRERECEXIGE)
            .libellemodule(DEFAULT_LIBELLEMODULE)
            .nomchampbadh(DEFAULT_NOMCHAMPBADH)
            .typequestionnairerec(DEFAULT_TYPEQUESTIONNAIREREC);
        // Add required entity
        Moduledefinition moduledefinition;
        if (TestUtil.findAll(em, Moduledefinition.class).isEmpty()) {
            moduledefinition = ModuledefinitionResourceIT.createEntity(em);
            em.persist(moduledefinition);
            em.flush();
        } else {
            moduledefinition = TestUtil.findAll(em, Moduledefinition.class).get(0);
        }
        produit.setModuledefinition(moduledefinition);
        return produit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Produit createUpdatedEntity(EntityManager em) {
        Produit produit = new Produit()
            .libelle(UPDATED_LIBELLE)
            .reference(UPDATED_REFERENCE)
            .numeroordre(UPDATED_NUMEROORDRE)
            .typeproduit(UPDATED_TYPEPRODUIT)
            .familleproduit(UPDATED_FAMILLEPRODUIT)
            .produitadditionnel(UPDATED_PRODUITADDITIONNEL)
            .obligatoirepourentreprise(UPDATED_OBLIGATOIREPOURENTREPRISE)
            .effectifmax(UPDATED_EFFECTIFMAX)
            .chartegraphique(UPDATED_CHARTEGRAPHIQUE)
            .alertetarificationexterne(UPDATED_ALERTETARIFICATIONEXTERNE)
            .questionnairerecexige(UPDATED_QUESTIONNAIRERECEXIGE)
            .libellemodule(UPDATED_LIBELLEMODULE)
            .nomchampbadh(UPDATED_NOMCHAMPBADH)
            .typequestionnairerec(UPDATED_TYPEQUESTIONNAIREREC);
        // Add required entity
        Moduledefinition moduledefinition;
        if (TestUtil.findAll(em, Moduledefinition.class).isEmpty()) {
            moduledefinition = ModuledefinitionResourceIT.createUpdatedEntity(em);
            em.persist(moduledefinition);
            em.flush();
        } else {
            moduledefinition = TestUtil.findAll(em, Moduledefinition.class).get(0);
        }
        produit.setModuledefinition(moduledefinition);
        return produit;
    }

    @BeforeEach
    public void initTest() {
        produit = createEntity(em);
    }

    @Test
    @Transactional
    public void createProduit() throws Exception {
        int databaseSizeBeforeCreate = produitRepository.findAll().size();

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);
        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isCreated());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeCreate + 1);
        Produit testProduit = produitList.get(produitList.size() - 1);
        assertThat(testProduit.getLibelle()).isEqualTo(DEFAULT_LIBELLE);
        assertThat(testProduit.getReference()).isEqualTo(DEFAULT_REFERENCE);
        assertThat(testProduit.getNumeroordre()).isEqualTo(DEFAULT_NUMEROORDRE);
        assertThat(testProduit.getTypeproduit()).isEqualTo(DEFAULT_TYPEPRODUIT);
        assertThat(testProduit.getFamilleproduit()).isEqualTo(DEFAULT_FAMILLEPRODUIT);
        assertThat(testProduit.isProduitadditionnel()).isEqualTo(DEFAULT_PRODUITADDITIONNEL);
        assertThat(testProduit.isObligatoirepourentreprise()).isEqualTo(DEFAULT_OBLIGATOIREPOURENTREPRISE);
        assertThat(testProduit.getEffectifmax()).isEqualTo(DEFAULT_EFFECTIFMAX);
        assertThat(testProduit.getChartegraphique()).isEqualTo(DEFAULT_CHARTEGRAPHIQUE);
        assertThat(testProduit.getAlertetarificationexterne()).isEqualTo(DEFAULT_ALERTETARIFICATIONEXTERNE);
        assertThat(testProduit.isQuestionnairerecexige()).isEqualTo(DEFAULT_QUESTIONNAIRERECEXIGE);
        assertThat(testProduit.getLibellemodule()).isEqualTo(DEFAULT_LIBELLEMODULE);
        assertThat(testProduit.getNomchampbadh()).isEqualTo(DEFAULT_NOMCHAMPBADH);
        assertThat(testProduit.getTypequestionnairerec()).isEqualTo(DEFAULT_TYPEQUESTIONNAIREREC);
    }

    @Test
    @Transactional
    public void createProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = produitRepository.findAll().size();

        // Create the Produit with an existing ID
        produit.setId(1L);
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLibelleIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setLibelle(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkReferenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setReference(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNumeroordreIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setNumeroordre(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTypeproduitIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setTypeproduit(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFamilleproduitIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setFamilleproduit(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkProduitadditionnelIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setProduitadditionnel(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkObligatoirepourentrepriseIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setObligatoirepourentreprise(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkChartegraphiqueIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setChartegraphique(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuestionnairerecexigeIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setQuestionnairerecexige(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLibellemoduleIsRequired() throws Exception {
        int databaseSizeBeforeTest = produitRepository.findAll().size();
        // set the field null
        produit.setLibellemodule(null);

        // Create the Produit, which fails.
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        restProduitMockMvc.perform(post("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllProduits() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList
        restProduitMockMvc.perform(get("/api/produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produit.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE.toString())))
            .andExpect(jsonPath("$.[*].numeroordre").value(hasItem(DEFAULT_NUMEROORDRE)))
            .andExpect(jsonPath("$.[*].typeproduit").value(hasItem(DEFAULT_TYPEPRODUIT.toString())))
            .andExpect(jsonPath("$.[*].familleproduit").value(hasItem(DEFAULT_FAMILLEPRODUIT.toString())))
            .andExpect(jsonPath("$.[*].produitadditionnel").value(hasItem(DEFAULT_PRODUITADDITIONNEL.booleanValue())))
            .andExpect(jsonPath("$.[*].obligatoirepourentreprise").value(hasItem(DEFAULT_OBLIGATOIREPOURENTREPRISE.booleanValue())))
            .andExpect(jsonPath("$.[*].effectifmax").value(hasItem(DEFAULT_EFFECTIFMAX)))
            .andExpect(jsonPath("$.[*].chartegraphique").value(hasItem(DEFAULT_CHARTEGRAPHIQUE.toString())))
            .andExpect(jsonPath("$.[*].alertetarificationexterne").value(hasItem(DEFAULT_ALERTETARIFICATIONEXTERNE.toString())))
            .andExpect(jsonPath("$.[*].questionnairerecexige").value(hasItem(DEFAULT_QUESTIONNAIRERECEXIGE.booleanValue())))
            .andExpect(jsonPath("$.[*].libellemodule").value(hasItem(DEFAULT_LIBELLEMODULE.toString())))
            .andExpect(jsonPath("$.[*].nomchampbadh").value(hasItem(DEFAULT_NOMCHAMPBADH.toString())))
            .andExpect(jsonPath("$.[*].typequestionnairerec").value(hasItem(DEFAULT_TYPEQUESTIONNAIREREC.toString())));
    }
    
    @Test
    @Transactional
    public void getProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get the produit
        restProduitMockMvc.perform(get("/api/produits/{id}", produit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(produit.getId().intValue()))
            .andExpect(jsonPath("$.libelle").value(DEFAULT_LIBELLE.toString()))
            .andExpect(jsonPath("$.reference").value(DEFAULT_REFERENCE.toString()))
            .andExpect(jsonPath("$.numeroordre").value(DEFAULT_NUMEROORDRE))
            .andExpect(jsonPath("$.typeproduit").value(DEFAULT_TYPEPRODUIT.toString()))
            .andExpect(jsonPath("$.familleproduit").value(DEFAULT_FAMILLEPRODUIT.toString()))
            .andExpect(jsonPath("$.produitadditionnel").value(DEFAULT_PRODUITADDITIONNEL.booleanValue()))
            .andExpect(jsonPath("$.obligatoirepourentreprise").value(DEFAULT_OBLIGATOIREPOURENTREPRISE.booleanValue()))
            .andExpect(jsonPath("$.effectifmax").value(DEFAULT_EFFECTIFMAX))
            .andExpect(jsonPath("$.chartegraphique").value(DEFAULT_CHARTEGRAPHIQUE.toString()))
            .andExpect(jsonPath("$.alertetarificationexterne").value(DEFAULT_ALERTETARIFICATIONEXTERNE.toString()))
            .andExpect(jsonPath("$.questionnairerecexige").value(DEFAULT_QUESTIONNAIRERECEXIGE.booleanValue()))
            .andExpect(jsonPath("$.libellemodule").value(DEFAULT_LIBELLEMODULE.toString()))
            .andExpect(jsonPath("$.nomchampbadh").value(DEFAULT_NOMCHAMPBADH.toString()))
            .andExpect(jsonPath("$.typequestionnairerec").value(DEFAULT_TYPEQUESTIONNAIREREC.toString()));
    }

    @Test
    @Transactional
    public void getAllProduitsByLibelleIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where libelle equals to DEFAULT_LIBELLE
        defaultProduitShouldBeFound("libelle.equals=" + DEFAULT_LIBELLE);

        // Get all the produitList where libelle equals to UPDATED_LIBELLE
        defaultProduitShouldNotBeFound("libelle.equals=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllProduitsByLibelleIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where libelle in DEFAULT_LIBELLE or UPDATED_LIBELLE
        defaultProduitShouldBeFound("libelle.in=" + DEFAULT_LIBELLE + "," + UPDATED_LIBELLE);

        // Get all the produitList where libelle equals to UPDATED_LIBELLE
        defaultProduitShouldNotBeFound("libelle.in=" + UPDATED_LIBELLE);
    }

    @Test
    @Transactional
    public void getAllProduitsByLibelleIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where libelle is not null
        defaultProduitShouldBeFound("libelle.specified=true");

        // Get all the produitList where libelle is null
        defaultProduitShouldNotBeFound("libelle.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByReferenceIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where reference equals to DEFAULT_REFERENCE
        defaultProduitShouldBeFound("reference.equals=" + DEFAULT_REFERENCE);

        // Get all the produitList where reference equals to UPDATED_REFERENCE
        defaultProduitShouldNotBeFound("reference.equals=" + UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void getAllProduitsByReferenceIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where reference in DEFAULT_REFERENCE or UPDATED_REFERENCE
        defaultProduitShouldBeFound("reference.in=" + DEFAULT_REFERENCE + "," + UPDATED_REFERENCE);

        // Get all the produitList where reference equals to UPDATED_REFERENCE
        defaultProduitShouldNotBeFound("reference.in=" + UPDATED_REFERENCE);
    }

    @Test
    @Transactional
    public void getAllProduitsByReferenceIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where reference is not null
        defaultProduitShouldBeFound("reference.specified=true");

        // Get all the produitList where reference is null
        defaultProduitShouldNotBeFound("reference.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByNumeroordreIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where numeroordre equals to DEFAULT_NUMEROORDRE
        defaultProduitShouldBeFound("numeroordre.equals=" + DEFAULT_NUMEROORDRE);

        // Get all the produitList where numeroordre equals to UPDATED_NUMEROORDRE
        defaultProduitShouldNotBeFound("numeroordre.equals=" + UPDATED_NUMEROORDRE);
    }

    @Test
    @Transactional
    public void getAllProduitsByNumeroordreIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where numeroordre in DEFAULT_NUMEROORDRE or UPDATED_NUMEROORDRE
        defaultProduitShouldBeFound("numeroordre.in=" + DEFAULT_NUMEROORDRE + "," + UPDATED_NUMEROORDRE);

        // Get all the produitList where numeroordre equals to UPDATED_NUMEROORDRE
        defaultProduitShouldNotBeFound("numeroordre.in=" + UPDATED_NUMEROORDRE);
    }

    @Test
    @Transactional
    public void getAllProduitsByNumeroordreIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where numeroordre is not null
        defaultProduitShouldBeFound("numeroordre.specified=true");

        // Get all the produitList where numeroordre is null
        defaultProduitShouldNotBeFound("numeroordre.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByNumeroordreIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where numeroordre greater than or equals to DEFAULT_NUMEROORDRE
        defaultProduitShouldBeFound("numeroordre.greaterOrEqualThan=" + DEFAULT_NUMEROORDRE);

        // Get all the produitList where numeroordre greater than or equals to UPDATED_NUMEROORDRE
        defaultProduitShouldNotBeFound("numeroordre.greaterOrEqualThan=" + UPDATED_NUMEROORDRE);
    }

    @Test
    @Transactional
    public void getAllProduitsByNumeroordreIsLessThanSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where numeroordre less than or equals to DEFAULT_NUMEROORDRE
        defaultProduitShouldNotBeFound("numeroordre.lessThan=" + DEFAULT_NUMEROORDRE);

        // Get all the produitList where numeroordre less than or equals to UPDATED_NUMEROORDRE
        defaultProduitShouldBeFound("numeroordre.lessThan=" + UPDATED_NUMEROORDRE);
    }


    @Test
    @Transactional
    public void getAllProduitsByTypeproduitIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where typeproduit equals to DEFAULT_TYPEPRODUIT
        defaultProduitShouldBeFound("typeproduit.equals=" + DEFAULT_TYPEPRODUIT);

        // Get all the produitList where typeproduit equals to UPDATED_TYPEPRODUIT
        defaultProduitShouldNotBeFound("typeproduit.equals=" + UPDATED_TYPEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllProduitsByTypeproduitIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where typeproduit in DEFAULT_TYPEPRODUIT or UPDATED_TYPEPRODUIT
        defaultProduitShouldBeFound("typeproduit.in=" + DEFAULT_TYPEPRODUIT + "," + UPDATED_TYPEPRODUIT);

        // Get all the produitList where typeproduit equals to UPDATED_TYPEPRODUIT
        defaultProduitShouldNotBeFound("typeproduit.in=" + UPDATED_TYPEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllProduitsByTypeproduitIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where typeproduit is not null
        defaultProduitShouldBeFound("typeproduit.specified=true");

        // Get all the produitList where typeproduit is null
        defaultProduitShouldNotBeFound("typeproduit.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByFamilleproduitIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where familleproduit equals to DEFAULT_FAMILLEPRODUIT
        defaultProduitShouldBeFound("familleproduit.equals=" + DEFAULT_FAMILLEPRODUIT);

        // Get all the produitList where familleproduit equals to UPDATED_FAMILLEPRODUIT
        defaultProduitShouldNotBeFound("familleproduit.equals=" + UPDATED_FAMILLEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllProduitsByFamilleproduitIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where familleproduit in DEFAULT_FAMILLEPRODUIT or UPDATED_FAMILLEPRODUIT
        defaultProduitShouldBeFound("familleproduit.in=" + DEFAULT_FAMILLEPRODUIT + "," + UPDATED_FAMILLEPRODUIT);

        // Get all the produitList where familleproduit equals to UPDATED_FAMILLEPRODUIT
        defaultProduitShouldNotBeFound("familleproduit.in=" + UPDATED_FAMILLEPRODUIT);
    }

    @Test
    @Transactional
    public void getAllProduitsByFamilleproduitIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where familleproduit is not null
        defaultProduitShouldBeFound("familleproduit.specified=true");

        // Get all the produitList where familleproduit is null
        defaultProduitShouldNotBeFound("familleproduit.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByProduitadditionnelIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where produitadditionnel equals to DEFAULT_PRODUITADDITIONNEL
        defaultProduitShouldBeFound("produitadditionnel.equals=" + DEFAULT_PRODUITADDITIONNEL);

        // Get all the produitList where produitadditionnel equals to UPDATED_PRODUITADDITIONNEL
        defaultProduitShouldNotBeFound("produitadditionnel.equals=" + UPDATED_PRODUITADDITIONNEL);
    }

    @Test
    @Transactional
    public void getAllProduitsByProduitadditionnelIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where produitadditionnel in DEFAULT_PRODUITADDITIONNEL or UPDATED_PRODUITADDITIONNEL
        defaultProduitShouldBeFound("produitadditionnel.in=" + DEFAULT_PRODUITADDITIONNEL + "," + UPDATED_PRODUITADDITIONNEL);

        // Get all the produitList where produitadditionnel equals to UPDATED_PRODUITADDITIONNEL
        defaultProduitShouldNotBeFound("produitadditionnel.in=" + UPDATED_PRODUITADDITIONNEL);
    }

    @Test
    @Transactional
    public void getAllProduitsByProduitadditionnelIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where produitadditionnel is not null
        defaultProduitShouldBeFound("produitadditionnel.specified=true");

        // Get all the produitList where produitadditionnel is null
        defaultProduitShouldNotBeFound("produitadditionnel.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByObligatoirepourentrepriseIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where obligatoirepourentreprise equals to DEFAULT_OBLIGATOIREPOURENTREPRISE
        defaultProduitShouldBeFound("obligatoirepourentreprise.equals=" + DEFAULT_OBLIGATOIREPOURENTREPRISE);

        // Get all the produitList where obligatoirepourentreprise equals to UPDATED_OBLIGATOIREPOURENTREPRISE
        defaultProduitShouldNotBeFound("obligatoirepourentreprise.equals=" + UPDATED_OBLIGATOIREPOURENTREPRISE);
    }

    @Test
    @Transactional
    public void getAllProduitsByObligatoirepourentrepriseIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where obligatoirepourentreprise in DEFAULT_OBLIGATOIREPOURENTREPRISE or UPDATED_OBLIGATOIREPOURENTREPRISE
        defaultProduitShouldBeFound("obligatoirepourentreprise.in=" + DEFAULT_OBLIGATOIREPOURENTREPRISE + "," + UPDATED_OBLIGATOIREPOURENTREPRISE);

        // Get all the produitList where obligatoirepourentreprise equals to UPDATED_OBLIGATOIREPOURENTREPRISE
        defaultProduitShouldNotBeFound("obligatoirepourentreprise.in=" + UPDATED_OBLIGATOIREPOURENTREPRISE);
    }

    @Test
    @Transactional
    public void getAllProduitsByObligatoirepourentrepriseIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where obligatoirepourentreprise is not null
        defaultProduitShouldBeFound("obligatoirepourentreprise.specified=true");

        // Get all the produitList where obligatoirepourentreprise is null
        defaultProduitShouldNotBeFound("obligatoirepourentreprise.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByEffectifmaxIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where effectifmax equals to DEFAULT_EFFECTIFMAX
        defaultProduitShouldBeFound("effectifmax.equals=" + DEFAULT_EFFECTIFMAX);

        // Get all the produitList where effectifmax equals to UPDATED_EFFECTIFMAX
        defaultProduitShouldNotBeFound("effectifmax.equals=" + UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void getAllProduitsByEffectifmaxIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where effectifmax in DEFAULT_EFFECTIFMAX or UPDATED_EFFECTIFMAX
        defaultProduitShouldBeFound("effectifmax.in=" + DEFAULT_EFFECTIFMAX + "," + UPDATED_EFFECTIFMAX);

        // Get all the produitList where effectifmax equals to UPDATED_EFFECTIFMAX
        defaultProduitShouldNotBeFound("effectifmax.in=" + UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void getAllProduitsByEffectifmaxIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where effectifmax is not null
        defaultProduitShouldBeFound("effectifmax.specified=true");

        // Get all the produitList where effectifmax is null
        defaultProduitShouldNotBeFound("effectifmax.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByEffectifmaxIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where effectifmax greater than or equals to DEFAULT_EFFECTIFMAX
        defaultProduitShouldBeFound("effectifmax.greaterOrEqualThan=" + DEFAULT_EFFECTIFMAX);

        // Get all the produitList where effectifmax greater than or equals to UPDATED_EFFECTIFMAX
        defaultProduitShouldNotBeFound("effectifmax.greaterOrEqualThan=" + UPDATED_EFFECTIFMAX);
    }

    @Test
    @Transactional
    public void getAllProduitsByEffectifmaxIsLessThanSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where effectifmax less than or equals to DEFAULT_EFFECTIFMAX
        defaultProduitShouldNotBeFound("effectifmax.lessThan=" + DEFAULT_EFFECTIFMAX);

        // Get all the produitList where effectifmax less than or equals to UPDATED_EFFECTIFMAX
        defaultProduitShouldBeFound("effectifmax.lessThan=" + UPDATED_EFFECTIFMAX);
    }


    @Test
    @Transactional
    public void getAllProduitsByChartegraphiqueIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where chartegraphique equals to DEFAULT_CHARTEGRAPHIQUE
        defaultProduitShouldBeFound("chartegraphique.equals=" + DEFAULT_CHARTEGRAPHIQUE);

        // Get all the produitList where chartegraphique equals to UPDATED_CHARTEGRAPHIQUE
        defaultProduitShouldNotBeFound("chartegraphique.equals=" + UPDATED_CHARTEGRAPHIQUE);
    }

    @Test
    @Transactional
    public void getAllProduitsByChartegraphiqueIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where chartegraphique in DEFAULT_CHARTEGRAPHIQUE or UPDATED_CHARTEGRAPHIQUE
        defaultProduitShouldBeFound("chartegraphique.in=" + DEFAULT_CHARTEGRAPHIQUE + "," + UPDATED_CHARTEGRAPHIQUE);

        // Get all the produitList where chartegraphique equals to UPDATED_CHARTEGRAPHIQUE
        defaultProduitShouldNotBeFound("chartegraphique.in=" + UPDATED_CHARTEGRAPHIQUE);
    }

    @Test
    @Transactional
    public void getAllProduitsByChartegraphiqueIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where chartegraphique is not null
        defaultProduitShouldBeFound("chartegraphique.specified=true");

        // Get all the produitList where chartegraphique is null
        defaultProduitShouldNotBeFound("chartegraphique.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByAlertetarificationexterneIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where alertetarificationexterne equals to DEFAULT_ALERTETARIFICATIONEXTERNE
        defaultProduitShouldBeFound("alertetarificationexterne.equals=" + DEFAULT_ALERTETARIFICATIONEXTERNE);

        // Get all the produitList where alertetarificationexterne equals to UPDATED_ALERTETARIFICATIONEXTERNE
        defaultProduitShouldNotBeFound("alertetarificationexterne.equals=" + UPDATED_ALERTETARIFICATIONEXTERNE);
    }

    @Test
    @Transactional
    public void getAllProduitsByAlertetarificationexterneIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where alertetarificationexterne in DEFAULT_ALERTETARIFICATIONEXTERNE or UPDATED_ALERTETARIFICATIONEXTERNE
        defaultProduitShouldBeFound("alertetarificationexterne.in=" + DEFAULT_ALERTETARIFICATIONEXTERNE + "," + UPDATED_ALERTETARIFICATIONEXTERNE);

        // Get all the produitList where alertetarificationexterne equals to UPDATED_ALERTETARIFICATIONEXTERNE
        defaultProduitShouldNotBeFound("alertetarificationexterne.in=" + UPDATED_ALERTETARIFICATIONEXTERNE);
    }

    @Test
    @Transactional
    public void getAllProduitsByAlertetarificationexterneIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where alertetarificationexterne is not null
        defaultProduitShouldBeFound("alertetarificationexterne.specified=true");

        // Get all the produitList where alertetarificationexterne is null
        defaultProduitShouldNotBeFound("alertetarificationexterne.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByQuestionnairerecexigeIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where questionnairerecexige equals to DEFAULT_QUESTIONNAIRERECEXIGE
        defaultProduitShouldBeFound("questionnairerecexige.equals=" + DEFAULT_QUESTIONNAIRERECEXIGE);

        // Get all the produitList where questionnairerecexige equals to UPDATED_QUESTIONNAIRERECEXIGE
        defaultProduitShouldNotBeFound("questionnairerecexige.equals=" + UPDATED_QUESTIONNAIRERECEXIGE);
    }

    @Test
    @Transactional
    public void getAllProduitsByQuestionnairerecexigeIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where questionnairerecexige in DEFAULT_QUESTIONNAIRERECEXIGE or UPDATED_QUESTIONNAIRERECEXIGE
        defaultProduitShouldBeFound("questionnairerecexige.in=" + DEFAULT_QUESTIONNAIRERECEXIGE + "," + UPDATED_QUESTIONNAIRERECEXIGE);

        // Get all the produitList where questionnairerecexige equals to UPDATED_QUESTIONNAIRERECEXIGE
        defaultProduitShouldNotBeFound("questionnairerecexige.in=" + UPDATED_QUESTIONNAIRERECEXIGE);
    }

    @Test
    @Transactional
    public void getAllProduitsByQuestionnairerecexigeIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where questionnairerecexige is not null
        defaultProduitShouldBeFound("questionnairerecexige.specified=true");

        // Get all the produitList where questionnairerecexige is null
        defaultProduitShouldNotBeFound("questionnairerecexige.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByLibellemoduleIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where libellemodule equals to DEFAULT_LIBELLEMODULE
        defaultProduitShouldBeFound("libellemodule.equals=" + DEFAULT_LIBELLEMODULE);

        // Get all the produitList where libellemodule equals to UPDATED_LIBELLEMODULE
        defaultProduitShouldNotBeFound("libellemodule.equals=" + UPDATED_LIBELLEMODULE);
    }

    @Test
    @Transactional
    public void getAllProduitsByLibellemoduleIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where libellemodule in DEFAULT_LIBELLEMODULE or UPDATED_LIBELLEMODULE
        defaultProduitShouldBeFound("libellemodule.in=" + DEFAULT_LIBELLEMODULE + "," + UPDATED_LIBELLEMODULE);

        // Get all the produitList where libellemodule equals to UPDATED_LIBELLEMODULE
        defaultProduitShouldNotBeFound("libellemodule.in=" + UPDATED_LIBELLEMODULE);
    }

    @Test
    @Transactional
    public void getAllProduitsByLibellemoduleIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where libellemodule is not null
        defaultProduitShouldBeFound("libellemodule.specified=true");

        // Get all the produitList where libellemodule is null
        defaultProduitShouldNotBeFound("libellemodule.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByNomchampbadhIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where nomchampbadh equals to DEFAULT_NOMCHAMPBADH
        defaultProduitShouldBeFound("nomchampbadh.equals=" + DEFAULT_NOMCHAMPBADH);

        // Get all the produitList where nomchampbadh equals to UPDATED_NOMCHAMPBADH
        defaultProduitShouldNotBeFound("nomchampbadh.equals=" + UPDATED_NOMCHAMPBADH);
    }

    @Test
    @Transactional
    public void getAllProduitsByNomchampbadhIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where nomchampbadh in DEFAULT_NOMCHAMPBADH or UPDATED_NOMCHAMPBADH
        defaultProduitShouldBeFound("nomchampbadh.in=" + DEFAULT_NOMCHAMPBADH + "," + UPDATED_NOMCHAMPBADH);

        // Get all the produitList where nomchampbadh equals to UPDATED_NOMCHAMPBADH
        defaultProduitShouldNotBeFound("nomchampbadh.in=" + UPDATED_NOMCHAMPBADH);
    }

    @Test
    @Transactional
    public void getAllProduitsByNomchampbadhIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where nomchampbadh is not null
        defaultProduitShouldBeFound("nomchampbadh.specified=true");

        // Get all the produitList where nomchampbadh is null
        defaultProduitShouldNotBeFound("nomchampbadh.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByTypequestionnairerecIsEqualToSomething() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where typequestionnairerec equals to DEFAULT_TYPEQUESTIONNAIREREC
        defaultProduitShouldBeFound("typequestionnairerec.equals=" + DEFAULT_TYPEQUESTIONNAIREREC);

        // Get all the produitList where typequestionnairerec equals to UPDATED_TYPEQUESTIONNAIREREC
        defaultProduitShouldNotBeFound("typequestionnairerec.equals=" + UPDATED_TYPEQUESTIONNAIREREC);
    }

    @Test
    @Transactional
    public void getAllProduitsByTypequestionnairerecIsInShouldWork() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where typequestionnairerec in DEFAULT_TYPEQUESTIONNAIREREC or UPDATED_TYPEQUESTIONNAIREREC
        defaultProduitShouldBeFound("typequestionnairerec.in=" + DEFAULT_TYPEQUESTIONNAIREREC + "," + UPDATED_TYPEQUESTIONNAIREREC);

        // Get all the produitList where typequestionnairerec equals to UPDATED_TYPEQUESTIONNAIREREC
        defaultProduitShouldNotBeFound("typequestionnairerec.in=" + UPDATED_TYPEQUESTIONNAIREREC);
    }

    @Test
    @Transactional
    public void getAllProduitsByTypequestionnairerecIsNullOrNotNull() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        // Get all the produitList where typequestionnairerec is not null
        defaultProduitShouldBeFound("typequestionnairerec.specified=true");

        // Get all the produitList where typequestionnairerec is null
        defaultProduitShouldNotBeFound("typequestionnairerec.specified=false");
    }

    @Test
    @Transactional
    public void getAllProduitsByModuledefinitionIsEqualToSomething() throws Exception {
        // Get already existing entity
        Moduledefinition moduledefinition = produit.getModuledefinition();
        produitRepository.saveAndFlush(produit);
        Long moduledefinitionId = moduledefinition.getId();

        // Get all the produitList where moduledefinition equals to moduledefinitionId
        defaultProduitShouldBeFound("moduledefinitionId.equals=" + moduledefinitionId);

        // Get all the produitList where moduledefinition equals to moduledefinitionId + 1
        defaultProduitShouldNotBeFound("moduledefinitionId.equals=" + (moduledefinitionId + 1));
    }


    @Test
    @Transactional
    public void getAllProduitsByGarantieIsEqualToSomething() throws Exception {
        // Initialize the database
        Garantie garantie = GarantieResourceIT.createEntity(em);
        em.persist(garantie);
        em.flush();
        produit.addGarantie(garantie);
        produitRepository.saveAndFlush(produit);
        Long garantieId = garantie.getId();

        // Get all the produitList where garantie equals to garantieId
        defaultProduitShouldBeFound("garantieId.equals=" + garantieId);

        // Get all the produitList where garantie equals to garantieId + 1
        defaultProduitShouldNotBeFound("garantieId.equals=" + (garantieId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultProduitShouldBeFound(String filter) throws Exception {
        restProduitMockMvc.perform(get("/api/produits?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(produit.getId().intValue())))
            .andExpect(jsonPath("$.[*].libelle").value(hasItem(DEFAULT_LIBELLE)))
            .andExpect(jsonPath("$.[*].reference").value(hasItem(DEFAULT_REFERENCE)))
            .andExpect(jsonPath("$.[*].numeroordre").value(hasItem(DEFAULT_NUMEROORDRE)))
            .andExpect(jsonPath("$.[*].typeproduit").value(hasItem(DEFAULT_TYPEPRODUIT)))
            .andExpect(jsonPath("$.[*].familleproduit").value(hasItem(DEFAULT_FAMILLEPRODUIT)))
            .andExpect(jsonPath("$.[*].produitadditionnel").value(hasItem(DEFAULT_PRODUITADDITIONNEL.booleanValue())))
            .andExpect(jsonPath("$.[*].obligatoirepourentreprise").value(hasItem(DEFAULT_OBLIGATOIREPOURENTREPRISE.booleanValue())))
            .andExpect(jsonPath("$.[*].effectifmax").value(hasItem(DEFAULT_EFFECTIFMAX)))
            .andExpect(jsonPath("$.[*].chartegraphique").value(hasItem(DEFAULT_CHARTEGRAPHIQUE)))
            .andExpect(jsonPath("$.[*].alertetarificationexterne").value(hasItem(DEFAULT_ALERTETARIFICATIONEXTERNE)))
            .andExpect(jsonPath("$.[*].questionnairerecexige").value(hasItem(DEFAULT_QUESTIONNAIRERECEXIGE.booleanValue())))
            .andExpect(jsonPath("$.[*].libellemodule").value(hasItem(DEFAULT_LIBELLEMODULE)))
            .andExpect(jsonPath("$.[*].nomchampbadh").value(hasItem(DEFAULT_NOMCHAMPBADH)))
            .andExpect(jsonPath("$.[*].typequestionnairerec").value(hasItem(DEFAULT_TYPEQUESTIONNAIREREC)));

        // Check, that the count call also returns 1
        restProduitMockMvc.perform(get("/api/produits/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultProduitShouldNotBeFound(String filter) throws Exception {
        restProduitMockMvc.perform(get("/api/produits?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restProduitMockMvc.perform(get("/api/produits/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingProduit() throws Exception {
        // Get the produit
        restProduitMockMvc.perform(get("/api/produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        int databaseSizeBeforeUpdate = produitRepository.findAll().size();

        // Update the produit
        Produit updatedProduit = produitRepository.findById(produit.getId()).get();
        // Disconnect from session so that the updates on updatedProduit are not directly saved in db
        em.detach(updatedProduit);
        updatedProduit
            .libelle(UPDATED_LIBELLE)
            .reference(UPDATED_REFERENCE)
            .numeroordre(UPDATED_NUMEROORDRE)
            .typeproduit(UPDATED_TYPEPRODUIT)
            .familleproduit(UPDATED_FAMILLEPRODUIT)
            .produitadditionnel(UPDATED_PRODUITADDITIONNEL)
            .obligatoirepourentreprise(UPDATED_OBLIGATOIREPOURENTREPRISE)
            .effectifmax(UPDATED_EFFECTIFMAX)
            .chartegraphique(UPDATED_CHARTEGRAPHIQUE)
            .alertetarificationexterne(UPDATED_ALERTETARIFICATIONEXTERNE)
            .questionnairerecexige(UPDATED_QUESTIONNAIRERECEXIGE)
            .libellemodule(UPDATED_LIBELLEMODULE)
            .nomchampbadh(UPDATED_NOMCHAMPBADH)
            .typequestionnairerec(UPDATED_TYPEQUESTIONNAIREREC);
        ProduitDTO produitDTO = produitMapper.toDto(updatedProduit);

        restProduitMockMvc.perform(put("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isOk());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeUpdate);
        Produit testProduit = produitList.get(produitList.size() - 1);
        assertThat(testProduit.getLibelle()).isEqualTo(UPDATED_LIBELLE);
        assertThat(testProduit.getReference()).isEqualTo(UPDATED_REFERENCE);
        assertThat(testProduit.getNumeroordre()).isEqualTo(UPDATED_NUMEROORDRE);
        assertThat(testProduit.getTypeproduit()).isEqualTo(UPDATED_TYPEPRODUIT);
        assertThat(testProduit.getFamilleproduit()).isEqualTo(UPDATED_FAMILLEPRODUIT);
        assertThat(testProduit.isProduitadditionnel()).isEqualTo(UPDATED_PRODUITADDITIONNEL);
        assertThat(testProduit.isObligatoirepourentreprise()).isEqualTo(UPDATED_OBLIGATOIREPOURENTREPRISE);
        assertThat(testProduit.getEffectifmax()).isEqualTo(UPDATED_EFFECTIFMAX);
        assertThat(testProduit.getChartegraphique()).isEqualTo(UPDATED_CHARTEGRAPHIQUE);
        assertThat(testProduit.getAlertetarificationexterne()).isEqualTo(UPDATED_ALERTETARIFICATIONEXTERNE);
        assertThat(testProduit.isQuestionnairerecexige()).isEqualTo(UPDATED_QUESTIONNAIRERECEXIGE);
        assertThat(testProduit.getLibellemodule()).isEqualTo(UPDATED_LIBELLEMODULE);
        assertThat(testProduit.getNomchampbadh()).isEqualTo(UPDATED_NOMCHAMPBADH);
        assertThat(testProduit.getTypequestionnairerec()).isEqualTo(UPDATED_TYPEQUESTIONNAIREREC);
    }

    @Test
    @Transactional
    public void updateNonExistingProduit() throws Exception {
        int databaseSizeBeforeUpdate = produitRepository.findAll().size();

        // Create the Produit
        ProduitDTO produitDTO = produitMapper.toDto(produit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restProduitMockMvc.perform(put("/api/produits")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(produitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Produit in the database
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteProduit() throws Exception {
        // Initialize the database
        produitRepository.saveAndFlush(produit);

        int databaseSizeBeforeDelete = produitRepository.findAll().size();

        // Delete the produit
        restProduitMockMvc.perform(delete("/api/produits/{id}", produit.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Produit> produitList = produitRepository.findAll();
        assertThat(produitList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Produit.class);
        Produit produit1 = new Produit();
        produit1.setId(1L);
        Produit produit2 = new Produit();
        produit2.setId(produit1.getId());
        assertThat(produit1).isEqualTo(produit2);
        produit2.setId(2L);
        assertThat(produit1).isNotEqualTo(produit2);
        produit1.setId(null);
        assertThat(produit1).isNotEqualTo(produit2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProduitDTO.class);
        ProduitDTO produitDTO1 = new ProduitDTO();
        produitDTO1.setId(1L);
        ProduitDTO produitDTO2 = new ProduitDTO();
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
        produitDTO2.setId(produitDTO1.getId());
        assertThat(produitDTO1).isEqualTo(produitDTO2);
        produitDTO2.setId(2L);
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
        produitDTO1.setId(null);
        assertThat(produitDTO1).isNotEqualTo(produitDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(produitMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(produitMapper.fromId(null)).isNull();
    }
}
