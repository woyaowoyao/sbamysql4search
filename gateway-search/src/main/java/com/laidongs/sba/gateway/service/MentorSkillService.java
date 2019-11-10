package com.laidongs.sba.gateway.service;

import com.laidongs.sba.gateway.domain.MentorSkill;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link MentorSkill}.
 */
public interface MentorSkillService {

    /**
     * Save a mentorSkill.
     *
     * @param mentorSkill the entity to save.
     * @return the persisted entity.
     */
    MentorSkill save(MentorSkill mentorSkill);

    /**
     * Get all the mentorSkills.
     *
     * @return the list of entities.
     */
    List<MentorSkill> findAll();


    /**
     * Get the "id" mentorSkill.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MentorSkill> findOne(Long id);

    /**
     * Delete the "id" mentorSkill.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the mentorSkill corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @return the list of entities.
     */
    List<MentorSkill> search(String query);
}
