package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.MentorService;
import com.laidongs.sba.gateway.domain.Mentor;
import com.laidongs.sba.gateway.repository.MentorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Mentor}.
 */
@Service
@Transactional
public class MentorServiceImpl implements MentorService {

    private final Logger log = LoggerFactory.getLogger(MentorServiceImpl.class);

    private final MentorRepository mentorRepository;

    public MentorServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    /**
     * Save a mentor.
     *
     * @param mentor the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Mentor save(Mentor mentor) {
        log.debug("Request to save Mentor : {}", mentor);
        return mentorRepository.save(mentor);
    }

    /**
     * Get all the mentors.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Mentor> findAll() {
        log.debug("Request to get all Mentors");
        return mentorRepository.findAll();
    }


    /**
     * Get one mentor by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Mentor> findOne(Long id) {
        log.debug("Request to get Mentor : {}", id);
        return mentorRepository.findById(id);
    }

    /**
     * Delete the mentor by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mentor : {}", id);
        mentorRepository.deleteById(id);
    }
}
