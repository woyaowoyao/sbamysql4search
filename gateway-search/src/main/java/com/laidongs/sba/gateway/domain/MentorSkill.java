package com.laidongs.sba.gateway.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;

/**
 * A MentorSkill.
 */
@Entity
@Table(name = "mentor_skill")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "mentorskill")
public class MentorSkill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "self_rate")
    private String selfRate;

    @Column(name = "experience")
    private Float experience;

    @ManyToOne
    @JsonIgnoreProperties("mentorSkills")
    private Mentor mentor;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelfRate() {
        return selfRate;
    }

    public MentorSkill selfRate(String selfRate) {
        this.selfRate = selfRate;
        return this;
    }

    public void setSelfRate(String selfRate) {
        this.selfRate = selfRate;
    }

    public Float getExperience() {
        return experience;
    }

    public MentorSkill experience(Float experience) {
        this.experience = experience;
        return this;
    }

    public void setExperience(Float experience) {
        this.experience = experience;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public MentorSkill mentor(Mentor mentor) {
        this.mentor = mentor;
        return this;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MentorSkill)) {
            return false;
        }
        return id != null && id.equals(((MentorSkill) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MentorSkill{" +
            "id=" + getId() +
            ", selfRate='" + getSelfRate() + "'" +
            ", experience=" + getExperience() +
            "}";
    }
}
