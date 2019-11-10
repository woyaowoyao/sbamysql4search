package com.laidongs.sba.gateway.service;

import com.laidongs.sba.gateway.domain.TrainingRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TrainingRecord}.
 */
public interface TrainingRecordService {

    /**
     * Save a trainingRecord.
     *
     * @param trainingRecord the entity to save.
     * @return the persisted entity.
     */
    TrainingRecord save(TrainingRecord trainingRecord);

    /**
     * Get all the trainingRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TrainingRecord> findAll(Pageable pageable);


    /**
     * Get the "id" trainingRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TrainingRecord> findOne(Long id);

    /**
     * Delete the "id" trainingRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Search for the trainingRecord corresponding to the query.
     *
     * @param query the query of the search.
     * 
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TrainingRecord> search(String query, Pageable pageable);
}
