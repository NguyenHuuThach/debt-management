package com.debtmanagement.myapp.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A InterestPay.
 */
@Entity
@Table(name = "interest_pay")
public class InterestPay implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @NotNull
    @Column(name = "interest_pay_date", nullable = false)
    private Instant interestPayDate;

    @NotNull
    @Column(name = "interest_pay_amount", nullable = false)
    private Double interestPayAmount;

    @Column(name = "payer_name")
    private String payerName;

    @Column(name = "note")
    private String note;

    @Column(name = "status")
    private Boolean status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public InterestPay id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return this.contractId;
    }

    public InterestPay contractId(Long contractId) {
        this.setContractId(contractId);
        return this;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Instant getInterestPayDate() {
        return this.interestPayDate;
    }

    public InterestPay interestPayDate(Instant interestPayDate) {
        this.setInterestPayDate(interestPayDate);
        return this;
    }

    public void setInterestPayDate(Instant interestPayDate) {
        this.interestPayDate = interestPayDate;
    }

    public Double getInterestPayAmount() {
        return this.interestPayAmount;
    }

    public InterestPay interestPayAmount(Double interestPayAmount) {
        this.setInterestPayAmount(interestPayAmount);
        return this;
    }

    public void setInterestPayAmount(Double interestPayAmount) {
        this.interestPayAmount = interestPayAmount;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public InterestPay payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getNote() {
        return this.note;
    }

    public InterestPay note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Boolean getStatus() {
        return this.status;
    }

    public InterestPay status(Boolean status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InterestPay)) {
            return false;
        }
        return id != null && id.equals(((InterestPay) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "InterestPay{" +
            "id=" + getId() +
            ", contractId=" + getContractId() +
            ", interestPayDate='" + getInterestPayDate() + "'" +
            ", interestPayAmount=" + getInterestPayAmount() +
            ", payerName='" + getPayerName() + "'" +
            ", note='" + getNote() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
