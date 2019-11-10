package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.TrainingRecordService;
import com.laidongs.sba.gateway.domain.TrainingRecord;
import com.laidongs.sba.gateway.repository.TrainingRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TrainingRecord}.
 */
@Service
@Transactional
public class TrainingRecordServiceImpl implements TrainingRecordService {

    private final Logger log = LoggerFactory.getLogger(TrainingRecordServiceImpl.class);

    private final TrainingRecordRepository trainingRecordRepository;

    public TrainingRecordServiceImpl(TrainingRecordRepository trainingRecordRepository) {
        this.trainingRecordRepository = trainingRecordRepository;
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
        return trainingRecordRepository.save(trainingRecord);
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
    }
}
