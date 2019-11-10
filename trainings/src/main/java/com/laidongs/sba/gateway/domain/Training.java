package com.laidongs.sba.gateway.domain;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.Instant;

import com.laidongs.sba.gateway.domain.enumeration.TrainStatus;

/**
 * Training entity.\n@author full stack trainning laidongshi.
 */
@ApiModel(description = "Training entity.\n@author full stack trainning laidongshi.")
@Entity
@Table(name = "training")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Training implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TrainStatus status;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "commission_amount", nullable = false)
    private Float commissionAmount;

    @Column(name = "avg_rating")
    private Float avgRating;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "remarks")
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TrainStatus getStatus() {
        return status;
    }

    public Training status(TrainStatus status) {
        this.status = status;
        return this;
    }

    public void setStatus(TrainStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public Training name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCommissionAmount() {
        return commissionAmount;
    }

    public Training commissionAmount(Float commissionAmount) {
        this.commissionAmount = commissionAmount;
        return this;
    }

    public void setCommissionAmount(Float commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public Float getAvgRating() {
        return avgRating;
    }

    public Training avgRating(Float avgRating) {
        this.avgRating = avgRating;
        return this;
    }

    public void setAvgRating(Float avgRating) {
        this.avgRating = avgRating;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public Training startDate(Instant startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public Training endDate(Instant endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public Training remarks(String remarks) {
        this.remarks = remarks;
        return this;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Training)) {
            return false;
        }
        return id != null && id.equals(((Training) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Training{" +
            "id=" + getId() +
            ", status='" + getStatus() + "'" +
            ", name='" + getName() + "'" +
            ", commissionAmount=" + getCommissionAmount() +
            ", avgRating=" + getAvgRating() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
