package com.laidongs.sba.gateway.web.rest;

import com.laidongs.sba.gateway.domain.MentorSkill;
import com.laidongs.sba.gateway.service.MentorSkillService;
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

/**
 * REST controller for managing {@link com.laidongs.sba.gateway.domain.MentorSkill}.
 */
@RestController
@RequestMapping("/api")
public class MentorSkillResource {

    private final Logger log = LoggerFactory.getLogger(MentorSkillResource.class);

    private static final String ENTITY_NAME = "trainingsMentorSkill";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final MentorSkillService mentorSkillService;

    public MentorSkillResource(MentorSkillService mentorSkillService) {
        this.mentorSkillService = mentorSkillService;
    }

    /**
     * {@code POST  /mentor-skills} : Create a new mentorSkill.
     *
     * @param mentorSkill the mentorSkill to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new mentorSkill, or with status {@code 400 (Bad Request)} if the mentorSkill has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/mentor-skills")
    public ResponseEntity<MentorSkill> createMentorSkill(@RequestBody MentorSkill mentorSkill) throws URISyntaxException {
        log.debug("REST request to save MentorSkill : {}", mentorSkill);
        if (mentorSkill.getId() != null) {
            throw new BadRequestAlertException("A new mentorSkill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MentorSkill result = mentorSkillService.save(mentorSkill);
        return ResponseEntity.created(new URI("/api/mentor-skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /mentor-skills} : Updates an existing mentorSkill.
     *
     * @param mentorSkill the mentorSkill to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated mentorSkill,
     * or with status {@code 400 (Bad Request)} if the mentorSkill is not valid,
     * or with status {@code 500 (Internal Server Error)} if the mentorSkill couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/mentor-skills")
    public ResponseEntity<MentorSkill> updateMentorSkill(@RequestBody MentorSkill mentorSkill) throws URISyntaxException {
        log.debug("REST request to update MentorSkill : {}", mentorSkill);
        if (mentorSkill.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        MentorSkill result = mentorSkillService.save(mentorSkill);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, mentorSkill.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /mentor-skills} : get all the mentorSkills.
     *

     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of mentorSkills in body.
     */
    @GetMapping("/mentor-skills")
    public List<MentorSkill> getAllMentorSkills() {
        log.debug("REST request to get all MentorSkills");
        return mentorSkillService.findAll();
    }

    /**
     * {@code GET  /mentor-skills/:id} : get the "id" mentorSkill.
     *
     * @param id the id of the mentorSkill to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the mentorSkill, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/mentor-skills/{id}")
    public ResponseEntity<MentorSkill> getMentorSkill(@PathVariable Long id) {
        log.debug("REST request to get MentorSkill : {}", id);
        Optional<MentorSkill> mentorSkill = mentorSkillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mentorSkill);
    }

    /**
     * {@code DELETE  /mentor-skills/:id} : delete the "id" mentorSkill.
     *
     * @param id the id of the mentorSkill to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/mentor-skills/{id}")
    public ResponseEntity<Void> deleteMentorSkill(@PathVariable Long id) {
        log.debug("REST request to delete MentorSkill : {}", id);
        mentorSkillService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
