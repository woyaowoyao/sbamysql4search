package com.laidongs.sba.gateway.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.laidongs.sba.gateway.domain.enumeration.TrainStatus;

/**
 * A Mentor.
 */
@Entity
@Table(name = "mentor")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mentor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "reg_datetime")
    private Instant regDatetime;

    @Column(name = "reg_code")
    private String regCode;

    @Column(name = "experience")
    private Float experience;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TrainStatus status;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public Mentor username(String username) {
        this.username = username;
        return this;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public Mentor linkedin(String linkedin) {
        this.linkedin = linkedin;
        return this;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }

    public Instant getRegDatetime() {
        return regDatetime;
    }

    public Mentor regDatetime(Instant regDatetime) {
        this.regDatetime = regDatetime;
        return this;
    }

    public void setRegDatetime(Instant regDatetime) {
        this.regDatetime = regDatetime;
    }

    public String getRegCode() {
        return regCode;
    }

    public Mentor regCode(String regCode) {
        this.regCode = regCode;
        return this;
    }

    public void setRegCode(String regCode) {
        this.regCode = regCode;
    }

    public Float getExperience() {
        return experience;
    }

    public Mentor experience(Float experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Float experience) {
        this.experience = experience;
    }

    public TrainStatus getStatus() {
        return status;
    }

    public Mentor status(TrainStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(TrainStatus status) {
        this.status = status;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Mentor)) {
            return false;
        }
        return id != null && id.equals(((Mentor) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Mentor{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", linkedin='" + getLinkedin() + "'" +
            ", regDatetime='" + getRegDatetime() + "'" +
            ", regCode='" + getRegCode() + "'" +
            ", experience=" + getExperience() +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
