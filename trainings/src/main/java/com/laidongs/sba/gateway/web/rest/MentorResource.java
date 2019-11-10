package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.domain.Mentor;
import com.laidongs.sba.gateway.service.MentorService;
import com.laidongs.sba.gateway.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.laidongs.sba.gateway.domain.Mentor}.
 */
@RestController
@RequestMapping("/api")
public class MentorResource {

    private final Logger log = LoggerFactory.getLogger(MentorResource.class);

    private static final String ENTITY_NAME = "trainingsMentor";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MentorService mentorService;

    public MentorResource(MentorService mentorService) {
        this.mentorService = mentorService;
    }

    /**
     * {@code POST  /mentors} : Create a new mentor.
     *
     * @param mentor the mentor to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mentor, or with status {@code 400 (Bad Request)} if the mentor has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mentors")
    public ResponseEntity<Mentor> createMentor(@Valid @RequestBody Mentor mentor) throws URISyntaxException {
        log.debug("REST request to save Mentor : {}", mentor);
        if (mentor.getId() != null) {
            throw new BadRequestAlertException("A new mentor cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Mentor result = mentorService.save(mentor);
        return ResponseEntity.created(new URI("/api/mentors/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mentors} : Updates an existing mentor.
     *
     * @param mentor the mentor to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mentor,
     * or with status {@code 400 (Bad Request)} if the mentor is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mentor couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mentors")
    public ResponseEntity<Mentor> updateMentor(@Valid @RequestBody Mentor mentor) throws URISyntaxException {
        log.debug("REST request to update Mentor : {}", mentor);
        if (mentor.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Mentor result = mentorService.save(mentor);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mentor.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mentors} : get all the mentors.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mentors in body.
     */
    @GetMapping("/mentors")
    public List<Mentor> getAllMentors() {
        log.debug("REST request to get all Mentors");
        return mentorService.findAll();
    }

    /**
     * {@code GET  /mentors/:id} : get the "id" mentor.
     *
     * @param id the id of the mentor to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mentor, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mentors/{id}")
    public ResponseEntity<Mentor> getMentor(@PathVariable Long id) {
        log.debug("REST request to get Mentor : {}", id);
        Optional<Mentor> mentor = mentorService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mentor);
    }

    /**
     * {@code DELETE  /mentors/:id} : delete the "id" mentor.
     *
     * @param id the id of the mentor to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mentors/{id}")
    public ResponseEntity<Void> deleteMentor(@PathVariable Long id) {
        log.debug("REST request to delete Mentor : {}", id);
        mentorService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
