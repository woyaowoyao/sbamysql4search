package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.MentorSkillService;
import com.laidongs.sba.gateway.domain.MentorSkill;
import com.laidongs.sba.gateway.repository.MentorSkillRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MentorSkill}.
 */
@Service
@Transactional
public class MentorSkillServiceImpl implements MentorSkillService {

    private final Logger log = LoggerFactory.getLogger(MentorSkillServiceImpl.class);

    private final MentorSkillRepository mentorSkillRepository;

    public MentorSkillServiceImpl(MentorSkillRepository mentorSkillRepository) {
        this.mentorSkillRepository = mentorSkillRepository;
    }

    /**
     * Save a mentorSkill.
     *
     * @param mentorSkill the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MentorSkill save(MentorSkill mentorSkill) {
        log.debug("Request to save MentorSkill : {}", mentorSkill);
        return mentorSkillRepository.save(mentorSkill);
    }

    /**
     * Get all the mentorSkills.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MentorSkill> findAll() {
        log.debug("Request to get all MentorSkills");
        return mentorSkillRepository.findAll();
    }


    /**
     * Get one mentorSkill by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MentorSkill> findOne(Long id) {
        log.debug("Request to get MentorSkill : {}", id);
        return mentorSkillRepository.findById(id);
    }

    /**
     * Delete the mentorSkill by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MentorSkill : {}", id);
        mentorSkillRepository.deleteById(id);
    }
}
