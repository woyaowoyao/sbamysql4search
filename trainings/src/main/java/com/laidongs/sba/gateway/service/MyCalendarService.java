package com.laidongs.sba.gateway.service;

import com.laidongs.sba.gateway.domain.MyCalendar;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link MyCalendar}.
 */
public interface MyCalendarService {

    /**
     * Save a myCalendar.
     *
     * @param myCalendar the entity to save.
     * @return the persisted entity.
     */
    MyCalendar save(MyCalendar myCalendar);

    /**
     * Get all the myCalendars.
     *
     * @return the list of entities.
     */
    List<MyCalendar> findAll();


    /**
     * Get the "id" myCalendar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MyCalendar> findOne(Long id);

    /**
     * Delete the "id" myCalendar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
