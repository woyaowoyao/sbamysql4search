package com.laidongs.sba.gateway.service;

import com.laidongs.sba.gateway.domain.PaymentRecord;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PaymentRecord}.
 */
public interface PaymentRecordService {

    /**
     * Save a paymentRecord.
     *
     * @param paymentRecord the entity to save.
     * @return the persisted entity.
     */
    PaymentRecord save(PaymentRecord paymentRecord);

    /**
     * Get all the paymentRecords.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PaymentRecord> findAll(Pageable pageable);


    /**
     * Get the "id" paymentRecord.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PaymentRecord> findOne(Long id);

    /**
     * Delete the "id" paymentRecord.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
