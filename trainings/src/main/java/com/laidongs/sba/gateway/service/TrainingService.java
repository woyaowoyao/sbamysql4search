package com.laidongs.sba.gateway.service;

import com.laidongs.sba.gateway.domain.Training;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Training}.
 */
public interface TrainingService {

    /**
     * Save a training.
     *
     * @param training the entity to save.
     * @return the persisted entity.
     */
    Training save(Training training);

    /**
     * Get all the trainings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Training> findAll(Pageable pageable);


    /**
     * Get the "id" training.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Training> findOne(Long id);

    /**
     * Delete the "id" training.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
