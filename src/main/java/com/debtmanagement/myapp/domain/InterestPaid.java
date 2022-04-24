package com.debtmanagement.myapp.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A InterestPaid.
 */
@Entity
@Table(name = "interest_paid")
public class InterestPaid implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @NotNull
    @Column(name = "interest_paid_date", nullable = false)
    private Instant interestPaidDate;

    @Column(name = "payer_name")
    private String payerName;

    @NotNull
    @Column(name = "paid_amount", nullable = false)
    private Double paidAmount;

    @Column(name = "note")
    private String note;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InterestPaid id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return this.contractId;
    }

    public InterestPaid contractId(Long contractId) {
        this.setContractId(contractId);
        return this;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Instant getInterestPaidDate() {
        return this.interestPaidDate;
    }

    public InterestPaid interestPaidDate(Instant interestPaidDate) {
        this.setInterestPaidDate(interestPaidDate);
        return this;
    }

    public void setInterestPaidDate(Instant interestPaidDate) {
        this.interestPaidDate = interestPaidDate;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public InterestPaid payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public Double getPaidAmount() {
        return this.paidAmount;
    }

    public InterestPaid paidAmount(Double paidAmount) {
        this.setPaidAmount(paidAmount);
        return this;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getNote() {
        return this.note;
    }

    public InterestPaid note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterestPaid)) {
            return false;
        }
        return id != null && id.equals(((InterestPaid) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InterestPaid{" +
            "id=" + getId() +
            ", contractId=" + getContractId() +
            ", interestPaidDate='" + getInterestPaidDate() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", paidAmount=" + getPaidAmount() +
            ", note='" + getNote() + "'" +
            "}";
    }
}
