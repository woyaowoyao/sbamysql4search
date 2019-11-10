package com.laidongs.sba.gateway.domain;
import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.time.Instant;

import com.laidongs.sba.gateway.domain.enumeration.PayTraType;

/**
 * PaymentRecord entity.\n@author full stack laidongshi.
 */
@ApiModel(description = "PaymentRecord entity.\n@author full stack laidongshi.")
@Entity
@Table(name = "payment_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "paymentrecord")
public class PaymentRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "txn_type", nullable = false)
    private PayTraType txnType;

    @NotNull
    @Column(name = "amount", nullable = false)
    private Float amount;

    @NotNull
    @Column(name = "total_amount_to_mentor", nullable = false)
    private Float totalAmountToMentor;

    @NotNull
    @Column(name = "issued_time", nullable = false)
    private Instant issuedTime;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "train_record_id")
    private Long trainRecordId;

    @Column(name = "remarks")
    private String remarks;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PayTraType getTxnType() {
        return txnType;
    }

    public PaymentRecord txnType(PayTraType txnType) {
        this.txnType = txnType;
        return this;
    }

    public void setTxnType(PayTraType txnType) {
        this.txnType = txnType;
    }

    public Float getAmount() {
        return amount;
    }

    public PaymentRecord amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getTotalAmountToMentor() {
        return totalAmountToMentor;
    }

    public PaymentRecord totalAmountToMentor(Float totalAmountToMentor) {
        this.totalAmountToMentor = totalAmountToMentor;
        return this;
    }

    public void setTotalAmountToMentor(Float totalAmountToMentor) {
        this.totalAmountToMentor = totalAmountToMentor;
    }

    public Instant getIssuedTime() {
        return issuedTime;
    }

    public PaymentRecord issuedTime(Instant issuedTime) {
        this.issuedTime = issuedTime;
        return this;
    }

    public void setIssuedTime(Instant issuedTime) {
        this.issuedTime = issuedTime;
    }

    public Long getUserId() {
        return userId;
    }

    public PaymentRecord userId(Long userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTrainRecordId() {
        return trainRecordId;
    }

    public PaymentRecord trainRecordId(Long trainRecordId) {
        this.trainRecordId = trainRecordId;
        return this;
    }

    public void setTrainRecordId(Long trainRecordId) {
        this.trainRecordId = trainRecordId;
    }

    public String getRemarks() {
        return remarks;
    }

    public PaymentRecord remarks(String remarks) {
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
        if (!(o instanceof PaymentRecord)) {
            return false;
        }
        return id != null && id.equals(((PaymentRecord) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "PaymentRecord{" +
            "id=" + getId() +
            ", txnType='" + getTxnType() + "'" +
            ", amount=" + getAmount() +
            ", totalAmountToMentor=" + getTotalAmountToMentor() +
            ", issuedTime='" + getIssuedTime() + "'" +
            ", userId=" + getUserId() +
            ", trainRecordId=" + getTrainRecordId() +
            ", remarks='" + getRemarks() + "'" +
            "}";
    }
}
