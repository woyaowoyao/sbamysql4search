package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.TrainingService;
import com.laidongs.sba.gateway.domain.Training;
import com.laidongs.sba.gateway.repository.TrainingRepository;
import com.laidongs.sba.gateway.repository.search.TrainingSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link Training}.
 */
@Service
@Transactional
public class TrainingServiceImpl implements TrainingService {

    private final Logger log = LoggerFactory.getLogger(TrainingServiceImpl.class);

    private final TrainingRepository trainingRepository;

    private final TrainingSearchRepository trainingSearchRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository, TrainingSearchRepository trainingSearchRepository) {
        this.trainingRepository = trainingRepository;
        this.trainingSearchRepository = trainingSearchRepository;
    }

    /**
     * Save a training.
     *
     * @param training the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Training save(Training training) {
        log.debug("Request to save Training : {}", training);
        Training result = trainingRepository.save(training);
        trainingSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the trainings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Training> findAll(Pageable pageable) {
        log.debug("Request to get all Trainings");
        return trainingRepository.findAll(pageable);
    }


    /**
     * Get one training by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Training> findOne(Long id) {
        log.debug("Request to get Training : {}", id);
        return trainingRepository.findById(id);
    }

    /**
     * Delete the training by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Training : {}", id);
        trainingRepository.deleteById(id);
        trainingSearchRepository.deleteById(id);
    }

    /**
     * Search for the training corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Training> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of Trainings for query {}", query);
        return trainingSearchRepository.search(queryStringQuery(query), pageable);    }
}
