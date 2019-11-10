package com.laidongs.sba.gateway.service.impl;

import com.laidongs.sba.gateway.service.MyCalendarService;
import com.laidongs.sba.gateway.domain.MyCalendar;
import com.laidongs.sba.gateway.repository.MyCalendarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link MyCalendar}.
 */
@Service
@Transactional
public class MyCalendarServiceImpl implements MyCalendarService {

    private final Logger log = LoggerFactory.getLogger(MyCalendarServiceImpl.class);

    private final MyCalendarRepository myCalendarRepository;

    public MyCalendarServiceImpl(MyCalendarRepository myCalendarRepository) {
        this.myCalendarRepository = myCalendarRepository;
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
        return myCalendarRepository.save(myCalendar);
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
    }
}
