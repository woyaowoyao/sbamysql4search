package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.domain.TrainingRecord;
import com.laidongs.sba.gateway.service.TrainingRecordService;
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

/**
 * REST controller for managing {@link com.laidongs.sba.gateway.domain.TrainingRecord}.
 */
@RestController
@RequestMapping("/api")
public class TrainingRecordResource {

    private final Logger log = LoggerFactory.getLogger(TrainingRecordResource.class);

    private static final String ENTITY_NAME = "trainingsTrainingRecord";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TrainingRecordService trainingRecordService;

    public TrainingRecordResource(TrainingRecordService trainingRecordService) {
        this.trainingRecordService = trainingRecordService;
    }

    /**
     * {@code POST  /training-records} : Create a new trainingRecord.
     *
     * @param trainingRecord the trainingRecord to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new trainingRecord, or with status {@code 400 (Bad Request)} if the trainingRecord has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/training-records")
    public ResponseEntity<TrainingRecord> createTrainingRecord(@Valid @RequestBody TrainingRecord trainingRecord) throws URISyntaxException {
        log.debug("REST request to save TrainingRecord : {}", trainingRecord);
        if (trainingRecord.getId() != null) {
            throw new BadRequestAlertException("A new trainingRecord cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TrainingRecord result = trainingRecordService.save(trainingRecord);
        return ResponseEntity.created(new URI("/api/training-records/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /training-records} : Updates an existing trainingRecord.
     *
     * @param trainingRecord the trainingRecord to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated trainingRecord,
     * or with status {@code 400 (Bad Request)} if the trainingRecord is not valid,
     * or with status {@code 500 (Internal Server Error)} if the trainingRecord couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/training-records")
    public ResponseEntity<TrainingRecord> updateTrainingRecord(@Valid @RequestBody TrainingRecord trainingRecord) throws URISyntaxException {
        log.debug("REST request to update TrainingRecord : {}", trainingRecord);
        if (trainingRecord.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TrainingRecord result = trainingRecordService.save(trainingRecord);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, trainingRecord.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /training-records} : get all the trainingRecords.
     *

     * @param pageable the pagination information.

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of trainingRecords in body.
     */
    @GetMapping("/training-records")
    public ResponseEntity<List<TrainingRecord>> getAllTrainingRecords(Pageable pageable) {
        log.debug("REST request to get a page of TrainingRecords");
        Page<TrainingRecord> page = trainingRecordService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /training-records/:id} : get the "id" trainingRecord.
     *
     * @param id the id of the trainingRecord to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the trainingRecord, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/training-records/{id}")
    public ResponseEntity<TrainingRecord> getTrainingRecord(@PathVariable Long id) {
        log.debug("REST request to get TrainingRecord : {}", id);
        Optional<TrainingRecord> trainingRecord = trainingRecordService.findOne(id);
        return ResponseUtil.wrapOrNotFound(trainingRecord);
    }

    /**
     * {@code DELETE  /training-records/:id} : delete the "id" trainingRecord.
     *
     * @param id the id of the trainingRecord to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/training-records/{id}")
    public ResponseEntity<Void> deleteTrainingRecord(@PathVariable Long id) {
        log.debug("REST request to delete TrainingRecord : {}", id);
        trainingRecordService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
