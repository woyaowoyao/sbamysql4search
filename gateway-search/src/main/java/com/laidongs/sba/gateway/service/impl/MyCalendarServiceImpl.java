package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.MyCalendarService;
import com.laidongs.sba.gateway.domain.MyCalendar;
import com.laidongs.sba.gateway.repository.MyCalendarRepository;
import com.laidongs.sba.gateway.repository.search.MyCalendarSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link MyCalendar}.
 */
@Service
@Transactional
public class MyCalendarServiceImpl implements MyCalendarService {

    private final Logger log = LoggerFactory.getLogger(MyCalendarServiceImpl.class);

    private final MyCalendarRepository myCalendarRepository;

    private final MyCalendarSearchRepository myCalendarSearchRepository;

    public MyCalendarServiceImpl(MyCalendarRepository myCalendarRepository, MyCalendarSearchRepository myCalendarSearchRepository) {
        this.myCalendarRepository = myCalendarRepository;
        this.myCalendarSearchRepository = myCalendarSearchRepository;
    }

    /**
     * Save a myCalendar.
     *
     * @param myCalendar the entity to save.
     * @return the persisted entity.
     */
    @Override
    public MyCalendar save(MyCalendar myCalendar) {
        log.debug("Request to save MyCalendar : {}", myCalendar);
        MyCalendar result = myCalendarRepository.save(myCalendar);
        myCalendarSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the myCalendars.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MyCalendar> findAll() {
        log.debug("Request to get all MyCalendars");
        return myCalendarRepository.findAll();
    }


    /**
     * Get one myCalendar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MyCalendar> findOne(Long id) {
        log.debug("Request to get MyCalendar : {}", id);
        return myCalendarRepository.findById(id);
    }

    /**
     * Delete the myCalendar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MyCalendar : {}", id);
        myCalendarRepository.deleteById(id);
        myCalendarSearchRepository.deleteById(id);
    }

    /**
     * Search for the myCalendar corresponding to the query.
     *
     * @param query the query of the search.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<MyCalendar> search(String query) {
        log.debug("Request to search MyCalendars for query {}", query);
        return StreamSupport
            .stream(myCalendarSearchRepository.search(queryStringQuery(query)).spliterator(), false)
            .collect(Collectors.toList());
    }
}
