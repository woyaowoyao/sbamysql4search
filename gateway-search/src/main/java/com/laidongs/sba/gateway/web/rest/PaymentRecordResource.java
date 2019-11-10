package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.domain.PaymentRecord;
import com.laidongs.sba.gateway.service.PaymentRecordService;
import com.laidongs.sba.gateway.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.laidongs.sba.gateway.domain.PaymentRecord}.
 */
@RestController
@RequestMapping("/api")
public class PaymentRecordResource {

    private final Logger log = LoggerFactory.getLogger(PaymentRecordResource.class);

    private static final String ENTITY_NAME = "paymentRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentRecordService paymentRecordService;

    public PaymentRecordResource(PaymentRecordService paymentRecordService) {
        this.paymentRecordService = paymentRecordService;
    }

    /**
     * {@code POST  /payment-records} : Create a new paymentRecord.
     *
     * @param paymentRecord the paymentRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentRecord, or with status {@code 400 (Bad Request)} if the paymentRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-records")
    public ResponseEntity<PaymentRecord> createPaymentRecord(@Valid @RequestBody PaymentRecord paymentRecord) throws URISyntaxException {
        log.debug("REST request to save PaymentRecord : {}", paymentRecord);
        if (paymentRecord.getId() != null) {
            throw new BadRequestAlertException("A new paymentRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PaymentRecord result = paymentRecordService.save(paymentRecord);
        return ResponseEntity.created(new URI("/api/payment-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /payment-records} : Updates an existing paymentRecord.
     *
     * @param paymentRecord the paymentRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentRecord,
     * or with status {@code 400 (Bad Request)} if the paymentRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-records")
    public ResponseEntity<PaymentRecord> updatePaymentRecord(@Valid @RequestBody PaymentRecord paymentRecord) throws URISyntaxException {
        log.debug("REST request to update PaymentRecord : {}", paymentRecord);
        if (paymentRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PaymentRecord result = paymentRecordService.save(paymentRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, paymentRecord.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /payment-records} : get all the paymentRecords.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentRecords in body.
     */
    @GetMapping("/payment-records")
    public ResponseEntity<List<PaymentRecord>> getAllPaymentRecords(Pageable pageable) {
        log.debug("REST request to get a page of PaymentRecords");
        Page<PaymentRecord> page = paymentRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /payment-records/:id} : get the "id" paymentRecord.
     *
     * @param id the id of the paymentRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-records/{id}")
    public ResponseEntity<PaymentRecord> getPaymentRecord(@PathVariable Long id) {
        log.debug("REST request to get PaymentRecord : {}", id);
        Optional<PaymentRecord> paymentRecord = paymentRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(paymentRecord);
    }

    /**
     * {@code DELETE  /payment-records/:id} : delete the "id" paymentRecord.
     *
     * @param id the id of the paymentRecord to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-records/{id}")
    public ResponseEntity<Void> deletePaymentRecord(@PathVariable Long id) {
        log.debug("REST request to delete PaymentRecord : {}", id);
        paymentRecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/payment-records?query=:query} : search for the paymentRecord corresponding
     * to the query.
     *
     * @param query the query of the paymentRecord search.
     * @param pageable the pagination information.
     * @return the result of the search.
     */
    @GetMapping("/_search/payment-records")
    public ResponseEntity<List<PaymentRecord>> searchPaymentRecords(@RequestParam String query, Pageable pageable) {
        log.debug("REST request to search for a page of PaymentRecords for query {}", query);
        Page<PaymentRecord> page = paymentRecordService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
