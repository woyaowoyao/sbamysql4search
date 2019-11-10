package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.domain.MyCalendar;
import com.laidongs.sba.gateway.service.MyCalendarService;
import com.laidongs.sba.gateway.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link com.laidongs.sba.gateway.domain.MyCalendar}.
 */
@RestController
@RequestMapping("/api")
public class MyCalendarResource {

    private final Logger log = LoggerFactory.getLogger(MyCalendarResource.class);

    private static final String ENTITY_NAME = "myCalendar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MyCalendarService myCalendarService;

    public MyCalendarResource(MyCalendarService myCalendarService) {
        this.myCalendarService = myCalendarService;
    }

    /**
     * {@code POST  /my-calendars} : Create a new myCalendar.
     *
     * @param myCalendar the myCalendar to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new myCalendar, or with status {@code 400 (Bad Request)} if the myCalendar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/my-calendars")
    public ResponseEntity<MyCalendar> createMyCalendar(@RequestBody MyCalendar myCalendar) throws URISyntaxException {
        log.debug("REST request to save MyCalendar : {}", myCalendar);
        if (myCalendar.getId() != null) {
            throw new BadRequestAlertException("A new myCalendar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MyCalendar result = myCalendarService.save(myCalendar);
        return ResponseEntity.created(new URI("/api/my-calendars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /my-calendars} : Updates an existing myCalendar.
     *
     * @param myCalendar the myCalendar to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated myCalendar,
     * or with status {@code 400 (Bad Request)} if the myCalendar is not valid,
     * or with status {@code 500 (Internal Server Error)} if the myCalendar couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/my-calendars")
    public ResponseEntity<MyCalendar> updateMyCalendar(@RequestBody MyCalendar myCalendar) throws URISyntaxException {
        log.debug("REST request to update MyCalendar : {}", myCalendar);
        if (myCalendar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MyCalendar result = myCalendarService.save(myCalendar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, myCalendar.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /my-calendars} : get all the myCalendars.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of myCalendars in body.
     */
    @GetMapping("/my-calendars")
    public List<MyCalendar> getAllMyCalendars() {
        log.debug("REST request to get all MyCalendars");
        return myCalendarService.findAll();
    }

    /**
     * {@code GET  /my-calendars/:id} : get the "id" myCalendar.
     *
     * @param id the id of the myCalendar to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the myCalendar, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/my-calendars/{id}")
    public ResponseEntity<MyCalendar> getMyCalendar(@PathVariable Long id) {
        log.debug("REST request to get MyCalendar : {}", id);
        Optional<MyCalendar> myCalendar = myCalendarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(myCalendar);
    }

    /**
     * {@code DELETE  /my-calendars/:id} : delete the "id" myCalendar.
     *
     * @param id the id of the myCalendar to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/my-calendars/{id}")
    public ResponseEntity<Void> deleteMyCalendar(@PathVariable Long id) {
        log.debug("REST request to delete MyCalendar : {}", id);
        myCalendarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/my-calendars?query=:query} : search for the myCalendar corresponding
     * to the query.
     *
     * @param query the query of the myCalendar search.
     * @return the result of the search.
     */
    @GetMapping("/_search/my-calendars")
    public List<MyCalendar> searchMyCalendars(@RequestParam String query) {
        log.debug("REST request to search MyCalendars for query {}", query);
        return myCalendarService.search(query);
    }
}
