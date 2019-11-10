package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.GatewaysearchApp;
import com.laidongs.sba.gateway.domain.Mentor;
import com.laidongs.sba.gateway.repository.MentorRepository;
import com.laidongs.sba.gateway.repository.search.MentorSearchRepository;
import com.laidongs.sba.gateway.service.MentorService;
import com.laidongs.sba.gateway.web.rest.errors.ExceptionTranslator;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static com.laidongs.sba.gateway.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.laidongs.sba.gateway.domain.enumeration.TrainStatus;
/**
 * Integration tests for the {@link MentorResource} REST controller.
 */
@SpringBootTest(classes = GatewaysearchApp.class)
public class MentorResourceIT {

    private static final String DEFAULT_USERNAME = "AAAAAAAAAA";
    private static final String UPDATED_USERNAME = "BBBBBBBBBB";

    private static final String DEFAULT_LINKEDIN = "AAAAAAAAAA";
    private static final String UPDATED_LINKEDIN = "BBBBBBBBBB";

    private static final Instant DEFAULT_REG_DATETIME = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_REG_DATETIME = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_REG_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REG_CODE = "BBBBBBBBBB";

    private static final Float DEFAULT_EXPERIENCE = 1F;
    private static final Float UPDATED_EXPERIENCE = 2F;

    private static final TrainStatus DEFAULT_STATUS = TrainStatus.Active;
    private static final TrainStatus UPDATED_STATUS = TrainStatus.Inactive;

    @Autowired
    private MentorRepository mentorRepository;

    @Autowired
    private MentorService mentorService;

    /**
     * This repository is mocked in the com.laidongs.sba.gateway.repository.search test package.
     *
     * @see com.laidongs.sba.gateway.repository.search.MentorSearchRepositoryMockConfiguration
     */
    @Autowired
    private MentorSearchRepository mockMentorSearchRepository;

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

    private MockMvc restMentorMockMvc;

    private Mentor mentor;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MentorResource mentorResource = new MentorResource(mentorService);
        this.restMentorMockMvc = MockMvcBuilders.standaloneSetup(mentorResource)
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
    public static Mentor createEntity(EntityManager em) {
        Mentor mentor = new Mentor()
            .username(DEFAULT_USERNAME)
            .linkedin(DEFAULT_LINKEDIN)
            .regDatetime(DEFAULT_REG_DATETIME)
            .regCode(DEFAULT_REG_CODE)
            .experience(DEFAULT_EXPERIENCE)
            .status(DEFAULT_STATUS);
        return mentor;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Mentor createUpdatedEntity(EntityManager em) {
        Mentor mentor = new Mentor()
            .username(UPDATED_USERNAME)
            .linkedin(UPDATED_LINKEDIN)
            .regDatetime(UPDATED_REG_DATETIME)
            .regCode(UPDATED_REG_CODE)
            .experience(UPDATED_EXPERIENCE)
            .status(UPDATED_STATUS);
        return mentor;
    }

    @BeforeEach
    public void initTest() {
        mentor = createEntity(em);
    }

    @Test
    @Transactional
    public void createMentor() throws Exception {
        int databaseSizeBeforeCreate = mentorRepository.findAll().size();

        // Create the Mentor
        restMentorMockMvc.perform(post("/api/mentors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentor)))
            .andExpect(status().isCreated());

        // Validate the Mentor in the database
        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeCreate + 1);
        Mentor testMentor = mentorList.get(mentorList.size() - 1);
        assertThat(testMentor.getUsername()).isEqualTo(DEFAULT_USERNAME);
        assertThat(testMentor.getLinkedin()).isEqualTo(DEFAULT_LINKEDIN);
        assertThat(testMentor.getRegDatetime()).isEqualTo(DEFAULT_REG_DATETIME);
        assertThat(testMentor.getRegCode()).isEqualTo(DEFAULT_REG_CODE);
        assertThat(testMentor.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);
        assertThat(testMentor.getStatus()).isEqualTo(DEFAULT_STATUS);

        // Validate the Mentor in Elasticsearch
        verify(mockMentorSearchRepository, times(1)).save(testMentor);
    }

    @Test
    @Transactional
    public void createMentorWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mentorRepository.findAll().size();

        // Create the Mentor with an existing ID
        mentor.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMentorMockMvc.perform(post("/api/mentors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentor)))
            .andExpect(status().isBadRequest());

        // Validate the Mentor in the database
        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeCreate);

        // Validate the Mentor in Elasticsearch
        verify(mockMentorSearchRepository, times(0)).save(mentor);
    }


    @Test
    @Transactional
    public void checkUsernameIsRequired() throws Exception {
        int databaseSizeBeforeTest = mentorRepository.findAll().size();
        // set the field null
        mentor.setUsername(null);

        // Create the Mentor, which fails.

        restMentorMockMvc.perform(post("/api/mentors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentor)))
            .andExpect(status().isBadRequest());

        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStatusIsRequired() throws Exception {
        int databaseSizeBeforeTest = mentorRepository.findAll().size();
        // set the field null
        mentor.setStatus(null);

        // Create the Mentor, which fails.

        restMentorMockMvc.perform(post("/api/mentors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentor)))
            .andExpect(status().isBadRequest());

        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllMentors() throws Exception {
        // Initialize the database
        mentorRepository.saveAndFlush(mentor);

        // Get all the mentorList
        restMentorMockMvc.perform(get("/api/mentors?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mentor.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].regDatetime").value(hasItem(DEFAULT_REG_DATETIME.toString())))
            .andExpect(jsonPath("$.[*].regCode").value(hasItem(DEFAULT_REG_CODE)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }
    
    @Test
    @Transactional
    public void getMentor() throws Exception {
        // Initialize the database
        mentorRepository.saveAndFlush(mentor);

        // Get the mentor
        restMentorMockMvc.perform(get("/api/mentors/{id}", mentor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mentor.getId().intValue()))
            .andExpect(jsonPath("$.username").value(DEFAULT_USERNAME))
            .andExpect(jsonPath("$.linkedin").value(DEFAULT_LINKEDIN))
            .andExpect(jsonPath("$.regDatetime").value(DEFAULT_REG_DATETIME.toString()))
            .andExpect(jsonPath("$.regCode").value(DEFAULT_REG_CODE))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.doubleValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMentor() throws Exception {
        // Get the mentor
        restMentorMockMvc.perform(get("/api/mentors/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMentor() throws Exception {
        // Initialize the database
        mentorService.save(mentor);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockMentorSearchRepository);

        int databaseSizeBeforeUpdate = mentorRepository.findAll().size();

        // Update the mentor
        Mentor updatedMentor = mentorRepository.findById(mentor.getId()).get();
        // Disconnect from session so that the updates on updatedMentor are not directly saved in db
        em.detach(updatedMentor);
        updatedMentor
            .username(UPDATED_USERNAME)
            .linkedin(UPDATED_LINKEDIN)
            .regDatetime(UPDATED_REG_DATETIME)
            .regCode(UPDATED_REG_CODE)
            .experience(UPDATED_EXPERIENCE)
            .status(UPDATED_STATUS);

        restMentorMockMvc.perform(put("/api/mentors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMentor)))
            .andExpect(status().isOk());

        // Validate the Mentor in the database
        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeUpdate);
        Mentor testMentor = mentorList.get(mentorList.size() - 1);
        assertThat(testMentor.getUsername()).isEqualTo(UPDATED_USERNAME);
        assertThat(testMentor.getLinkedin()).isEqualTo(UPDATED_LINKEDIN);
        assertThat(testMentor.getRegDatetime()).isEqualTo(UPDATED_REG_DATETIME);
        assertThat(testMentor.getRegCode()).isEqualTo(UPDATED_REG_CODE);
        assertThat(testMentor.getExperience()).isEqualTo(UPDATED_EXPERIENCE);
        assertThat(testMentor.getStatus()).isEqualTo(UPDATED_STATUS);

        // Validate the Mentor in Elasticsearch
        verify(mockMentorSearchRepository, times(1)).save(testMentor);
    }

    @Test
    @Transactional
    public void updateNonExistingMentor() throws Exception {
        int databaseSizeBeforeUpdate = mentorRepository.findAll().size();

        // Create the Mentor

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMentorMockMvc.perform(put("/api/mentors")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentor)))
            .andExpect(status().isBadRequest());

        // Validate the Mentor in the database
        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeUpdate);

        // Validate the Mentor in Elasticsearch
        verify(mockMentorSearchRepository, times(0)).save(mentor);
    }

    @Test
    @Transactional
    public void deleteMentor() throws Exception {
        // Initialize the database
        mentorService.save(mentor);

        int databaseSizeBeforeDelete = mentorRepository.findAll().size();

        // Delete the mentor
        restMentorMockMvc.perform(delete("/api/mentors/{id}", mentor.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Mentor> mentorList = mentorRepository.findAll();
        assertThat(mentorList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the Mentor in Elasticsearch
        verify(mockMentorSearchRepository, times(1)).deleteById(mentor.getId());
    }

    @Test
    @Transactional
    public void searchMentor() throws Exception {
        // Initialize the database
        mentorService.save(mentor);
        when(mockMentorSearchRepository.search(queryStringQuery("id:" + mentor.getId())))
            .thenReturn(Collections.singletonList(mentor));
        // Search the mentor
        restMentorMockMvc.perform(get("/api/_search/mentors?query=id:" + mentor.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mentor.getId().intValue())))
            .andExpect(jsonPath("$.[*].username").value(hasItem(DEFAULT_USERNAME)))
            .andExpect(jsonPath("$.[*].linkedin").value(hasItem(DEFAULT_LINKEDIN)))
            .andExpect(jsonPath("$.[*].regDatetime").value(hasItem(DEFAULT_REG_DATETIME.toString())))
            .andExpect(jsonPath("$.[*].regCode").value(hasItem(DEFAULT_REG_CODE)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.doubleValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mentor.class);
        Mentor mentor1 = new Mentor();
        mentor1.setId(1L);
        Mentor mentor2 = new Mentor();
        mentor2.setId(mentor1.getId());
        assertThat(mentor1).isEqualTo(mentor2);
        mentor2.setId(2L);
        assertThat(mentor1).isNotEqualTo(mentor2);
        mentor1.setId(null);
        assertThat(mentor1).isNotEqualTo(mentor2);
    }
}
