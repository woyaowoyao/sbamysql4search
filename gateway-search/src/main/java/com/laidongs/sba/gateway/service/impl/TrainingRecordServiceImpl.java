package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.TrainingRecordService;
import com.laidongs.sba.gateway.domain.TrainingRecord;
import com.laidongs.sba.gateway.repository.TrainingRecordRepository;
import com.laidongs.sba.gateway.repository.search.TrainingRecordSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link TrainingRecord}.
 */
@Service
@Transactional
public class TrainingRecordServiceImpl implements TrainingRecordService {

    private final Logger log = LoggerFactory.getLogger(TrainingRecordServiceImpl.class);

    private final TrainingRecordRepository trainingRecordRepository;

    private final TrainingRecordSearchRepository trainingRecordSearchRepository;

    public TrainingRecordServiceImpl(TrainingRecordRepository trainingRecordRepository, TrainingRecordSearchRepository trainingRecordSearchRepository) {
        this.trainingRecordRepository = trainingRecordRepository;
        this.trainingRecordSearchRepository = trainingRecordSearchRepository;
    }

    /**
     * Save a trainingRecord.
     *
     * @param trainingRecord the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TrainingRecord save(TrainingRecord trainingRecord) {
        log.debug("Request to save TrainingRecord : {}", trainingRecord);
        TrainingRecord result = trainingRecordRepository.save(trainingRecord);
        trainingRecordSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the trainingRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrainingRecord> findAll(Pageable pageable) {
        log.debug("Request to get all TrainingRecords");
        return trainingRecordRepository.findAll(pageable);
    }


    /**
     * Get one trainingRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TrainingRecord> findOne(Long id) {
        log.debug("Request to get TrainingRecord : {}", id);
        return trainingRecordRepository.findById(id);
    }

    /**
     * Delete the trainingRecord by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TrainingRecord : {}", id);
        trainingRecordRepository.deleteById(id);
        trainingRecordSearchRepository.deleteById(id);
    }

    /**
     * Search for the trainingRecord corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TrainingRecord> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of TrainingRecords for query {}", query);
        return trainingRecordSearchRepository.search(queryStringQuery(query), pageable);    }
}
