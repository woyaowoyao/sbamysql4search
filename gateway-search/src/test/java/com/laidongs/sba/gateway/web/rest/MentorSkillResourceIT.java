package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.GatewaysearchApp;
import com.laidongs.sba.gateway.domain.MentorSkill;
import com.laidongs.sba.gateway.repository.MentorSkillRepository;
import com.laidongs.sba.gateway.repository.search.MentorSkillSearchRepository;
import com.laidongs.sba.gateway.service.MentorSkillService;
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
import java.util.Collections;
import java.util.List;

import static com.laidongs.sba.gateway.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MentorSkillResource} REST controller.
 */
@SpringBootTest(classes = GatewaysearchApp.class)
public class MentorSkillResourceIT {

    private static final String DEFAULT_SELF_RATE = "AAAAAAAAAA";
    private static final String UPDATED_SELF_RATE = "BBBBBBBBBB";

    private static final Float DEFAULT_EXPERIENCE = 1F;
    private static final Float UPDATED_EXPERIENCE = 2F;

    @Autowired
    private MentorSkillRepository mentorSkillRepository;

    @Autowired
    private MentorSkillService mentorSkillService;

    /**
     * This repository is mocked in the com.laidongs.sba.gateway.repository.search test package.
     *
     * @see com.laidongs.sba.gateway.repository.search.MentorSkillSearchRepositoryMockConfiguration
     */
    @Autowired
    private MentorSkillSearchRepository mockMentorSkillSearchRepository;

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

    private MockMvc restMentorSkillMockMvc;

    private MentorSkill mentorSkill;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MentorSkillResource mentorSkillResource = new MentorSkillResource(mentorSkillService);
        this.restMentorSkillMockMvc = MockMvcBuilders.standaloneSetup(mentorSkillResource)
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
    public static MentorSkill createEntity(EntityManager em) {
        MentorSkill mentorSkill = new MentorSkill()
            .selfRate(DEFAULT_SELF_RATE)
            .experience(DEFAULT_EXPERIENCE);
        return mentorSkill;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MentorSkill createUpdatedEntity(EntityManager em) {
        MentorSkill mentorSkill = new MentorSkill()
            .selfRate(UPDATED_SELF_RATE)
            .experience(UPDATED_EXPERIENCE);
        return mentorSkill;
    }

    @BeforeEach
    public void initTest() {
        mentorSkill = createEntity(em);
    }

    @Test
    @Transactional
    public void createMentorSkill() throws Exception {
        int databaseSizeBeforeCreate = mentorSkillRepository.findAll().size();

        // Create the MentorSkill
        restMentorSkillMockMvc.perform(post("/api/mentor-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentorSkill)))
            .andExpect(status().isCreated());

        // Validate the MentorSkill in the database
        List<MentorSkill> mentorSkillList = mentorSkillRepository.findAll();
        assertThat(mentorSkillList).hasSize(databaseSizeBeforeCreate + 1);
        MentorSkill testMentorSkill = mentorSkillList.get(mentorSkillList.size() - 1);
        assertThat(testMentorSkill.getSelfRate()).isEqualTo(DEFAULT_SELF_RATE);
        assertThat(testMentorSkill.getExperience()).isEqualTo(DEFAULT_EXPERIENCE);

        // Validate the MentorSkill in Elasticsearch
        verify(mockMentorSkillSearchRepository, times(1)).save(testMentorSkill);
    }

    @Test
    @Transactional
    public void createMentorSkillWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mentorSkillRepository.findAll().size();

        // Create the MentorSkill with an existing ID
        mentorSkill.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMentorSkillMockMvc.perform(post("/api/mentor-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentorSkill)))
            .andExpect(status().isBadRequest());

        // Validate the MentorSkill in the database
        List<MentorSkill> mentorSkillList = mentorSkillRepository.findAll();
        assertThat(mentorSkillList).hasSize(databaseSizeBeforeCreate);

        // Validate the MentorSkill in Elasticsearch
        verify(mockMentorSkillSearchRepository, times(0)).save(mentorSkill);
    }


    @Test
    @Transactional
    public void getAllMentorSkills() throws Exception {
        // Initialize the database
        mentorSkillRepository.saveAndFlush(mentorSkill);

        // Get all the mentorSkillList
        restMentorSkillMockMvc.perform(get("/api/mentor-skills?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mentorSkill.getId().intValue())))
            .andExpect(jsonPath("$.[*].selfRate").value(hasItem(DEFAULT_SELF_RATE)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.doubleValue())));
    }
    
    @Test
    @Transactional
    public void getMentorSkill() throws Exception {
        // Initialize the database
        mentorSkillRepository.saveAndFlush(mentorSkill);

        // Get the mentorSkill
        restMentorSkillMockMvc.perform(get("/api/mentor-skills/{id}", mentorSkill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mentorSkill.getId().intValue()))
            .andExpect(jsonPath("$.selfRate").value(DEFAULT_SELF_RATE))
            .andExpect(jsonPath("$.experience").value(DEFAULT_EXPERIENCE.doubleValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMentorSkill() throws Exception {
        // Get the mentorSkill
        restMentorSkillMockMvc.perform(get("/api/mentor-skills/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMentorSkill() throws Exception {
        // Initialize the database
        mentorSkillService.save(mentorSkill);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockMentorSkillSearchRepository);

        int databaseSizeBeforeUpdate = mentorSkillRepository.findAll().size();

        // Update the mentorSkill
        MentorSkill updatedMentorSkill = mentorSkillRepository.findById(mentorSkill.getId()).get();
        // Disconnect from session so that the updates on updatedMentorSkill are not directly saved in db
        em.detach(updatedMentorSkill);
        updatedMentorSkill
            .selfRate(UPDATED_SELF_RATE)
            .experience(UPDATED_EXPERIENCE);

        restMentorSkillMockMvc.perform(put("/api/mentor-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedMentorSkill)))
            .andExpect(status().isOk());

        // Validate the MentorSkill in the database
        List<MentorSkill> mentorSkillList = mentorSkillRepository.findAll();
        assertThat(mentorSkillList).hasSize(databaseSizeBeforeUpdate);
        MentorSkill testMentorSkill = mentorSkillList.get(mentorSkillList.size() - 1);
        assertThat(testMentorSkill.getSelfRate()).isEqualTo(UPDATED_SELF_RATE);
        assertThat(testMentorSkill.getExperience()).isEqualTo(UPDATED_EXPERIENCE);

        // Validate the MentorSkill in Elasticsearch
        verify(mockMentorSkillSearchRepository, times(1)).save(testMentorSkill);
    }

    @Test
    @Transactional
    public void updateNonExistingMentorSkill() throws Exception {
        int databaseSizeBeforeUpdate = mentorSkillRepository.findAll().size();

        // Create the MentorSkill

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restMentorSkillMockMvc.perform(put("/api/mentor-skills")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mentorSkill)))
            .andExpect(status().isBadRequest());

        // Validate the MentorSkill in the database
        List<MentorSkill> mentorSkillList = mentorSkillRepository.findAll();
        assertThat(mentorSkillList).hasSize(databaseSizeBeforeUpdate);

        // Validate the MentorSkill in Elasticsearch
        verify(mockMentorSkillSearchRepository, times(0)).save(mentorSkill);
    }

    @Test
    @Transactional
    public void deleteMentorSkill() throws Exception {
        // Initialize the database
        mentorSkillService.save(mentorSkill);

        int databaseSizeBeforeDelete = mentorSkillRepository.findAll().size();

        // Delete the mentorSkill
        restMentorSkillMockMvc.perform(delete("/api/mentor-skills/{id}", mentorSkill.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<MentorSkill> mentorSkillList = mentorSkillRepository.findAll();
        assertThat(mentorSkillList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the MentorSkill in Elasticsearch
        verify(mockMentorSkillSearchRepository, times(1)).deleteById(mentorSkill.getId());
    }

    @Test
    @Transactional
    public void searchMentorSkill() throws Exception {
        // Initialize the database
        mentorSkillService.save(mentorSkill);
        when(mockMentorSkillSearchRepository.search(queryStringQuery("id:" + mentorSkill.getId())))
            .thenReturn(Collections.singletonList(mentorSkill));
        // Search the mentorSkill
        restMentorSkillMockMvc.perform(get("/api/_search/mentor-skills?query=id:" + mentorSkill.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mentorSkill.getId().intValue())))
            .andExpect(jsonPath("$.[*].selfRate").value(hasItem(DEFAULT_SELF_RATE)))
            .andExpect(jsonPath("$.[*].experience").value(hasItem(DEFAULT_EXPERIENCE.doubleValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MentorSkill.class);
        MentorSkill mentorSkill1 = new MentorSkill();
        mentorSkill1.setId(1L);
        MentorSkill mentorSkill2 = new MentorSkill();
        mentorSkill2.setId(mentorSkill1.getId());
        assertThat(mentorSkill1).isEqualTo(mentorSkill2);
        mentorSkill2.setId(2L);
        assertThat(mentorSkill1).isNotEqualTo(mentorSkill2);
        mentorSkill1.setId(null);
        assertThat(mentorSkill1).isNotEqualTo(mentorSkill2);
    }
}
