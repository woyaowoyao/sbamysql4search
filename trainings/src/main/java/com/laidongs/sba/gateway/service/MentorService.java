package com.laidongs.sba.gateway.service;

import com.laidongs.sba.gateway.domain.Mentor;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Mentor}.
 */
public interface MentorService {

    /**
     * Save a mentor.
     *
     * @param mentor the entity to save.
     * @return the persisted entity.
     */
    Mentor save(Mentor mentor);

    /**
     * Get all the mentors.
     *
     * @return the list of entities.
     */
    List<Mentor> findAll();


    /**
     * Get the "id" mentor.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Mentor> findOne(Long id);

    /**
     * Delete the "id" mentor.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
