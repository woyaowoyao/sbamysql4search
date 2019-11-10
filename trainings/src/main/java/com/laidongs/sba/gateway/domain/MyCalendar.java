package com.laidongs.sba.gateway.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.Instant;

import com.laidongs.sba.gateway.domain.enumeration.DurationType;

/**
 * A MyCalendar.
 */
@Entity
@Table(name = "my_calendar")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MyCalendar implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cal_date")
    private Instant calDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "duration")
    private DurationType duration;

    @Column(name = "remarks")
    private String remarks;

    @OneToOne
    @JoinColumn(unique = true)
    private Training training;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCalDate() {
        return calDate;
    }

    public MyCalendar calDate(Instant calDate) {
        this.calDate = calDate;
        return this;
    }

    public void setCalDate(Instant calDate) {
        this.calDate = calDate;
    }

    public DurationType getDuration() {
        return duration;
    }

    public MyCalendar duration(DurationType duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(DurationType duration) {
        this.duration = duration;
    }

    public String getRemarks() {
        return remarks;
    }

    public MyCalendar remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Training getTraining() {
        return training;
    }

    public MyCalendar training(Training training) {
        this.training = training;
        return this;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyCalendar)) {
            return false;
        }
        return id != null && id.equals(((MyCalendar) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MyCalendar{" +
            "id=" + getId() +
            ", calDate='" + getCalDate() + "'" +
            ", duration='" + getDuration() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
