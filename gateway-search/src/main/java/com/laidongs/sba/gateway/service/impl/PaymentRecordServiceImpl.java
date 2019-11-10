package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.PaymentRecordService;
import com.laidongs.sba.gateway.domain.PaymentRecord;
import com.laidongs.sba.gateway.repository.PaymentRecordRepository;
import com.laidongs.sba.gateway.repository.search.PaymentRecordSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link PaymentRecord}.
 */
@Service
@Transactional
public class PaymentRecordServiceImpl implements PaymentRecordService {

    private final Logger log = LoggerFactory.getLogger(PaymentRecordServiceImpl.class);

    private final PaymentRecordRepository paymentRecordRepository;

    private final PaymentRecordSearchRepository paymentRecordSearchRepository;

    public PaymentRecordServiceImpl(PaymentRecordRepository paymentRecordRepository, PaymentRecordSearchRepository paymentRecordSearchRepository) {
        this.paymentRecordRepository = paymentRecordRepository;
        this.paymentRecordSearchRepository = paymentRecordSearchRepository;
    }

    /**
     * Save a paymentRecord.
     *
     * @param paymentRecord the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PaymentRecord save(PaymentRecord paymentRecord) {
        log.debug("Request to save PaymentRecord : {}", paymentRecord);
        PaymentRecord result = paymentRecordRepository.save(paymentRecord);
        paymentRecordSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the paymentRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentRecord> findAll(Pageable pageable) {
        log.debug("Request to get all PaymentRecords");
        return paymentRecordRepository.findAll(pageable);
    }


    /**
     * Get one paymentRecord by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentRecord> findOne(Long id) {
        log.debug("Request to get PaymentRecord : {}", id);
        return paymentRecordRepository.findById(id);
    }

    /**
     * Delete the paymentRecord by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentRecord : {}", id);
        paymentRecordRepository.deleteById(id);
        paymentRecordSearchRepository.deleteById(id);
    }

    /**
     * Search for the paymentRecord corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PaymentRecord> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of PaymentRecords for query {}", query);
        return paymentRecordSearchRepository.search(queryStringQuery(query), pageable);    }
}
