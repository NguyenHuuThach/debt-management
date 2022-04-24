package com.debtmanagement.myapp.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PayDownPrincipal.
 */
@Entity
@Table(name = "pay_down_principal")
public class PayDownPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @NotNull
    @Column(name = "pay_down_principal_date", nullable = false)
    private Instant payDownPrincipalDate;

    @NotNull
    @Column(name = "pay_down_principal_amount", nullable = false)
    private Double payDownPrincipalAmount;

    @Column(name = "payer_name")
    private String payerName;

    @Column(name = "user_create")
    private String userCreate;

    @Column(name = "note")
    private String note;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PayDownPrincipal id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return this.contractId;
    }

    public PayDownPrincipal contractId(Long contractId) {
        this.setContractId(contractId);
        return this;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Instant getPayDownPrincipalDate() {
        return this.payDownPrincipalDate;
    }

    public PayDownPrincipal payDownPrincipalDate(Instant payDownPrincipalDate) {
        this.setPayDownPrincipalDate(payDownPrincipalDate);
        return this;
    }

    public void setPayDownPrincipalDate(Instant payDownPrincipalDate) {
        this.payDownPrincipalDate = payDownPrincipalDate;
    }

    public Double getPayDownPrincipalAmount() {
        return this.payDownPrincipalAmount;
    }

    public PayDownPrincipal payDownPrincipalAmount(Double payDownPrincipalAmount) {
        this.setPayDownPrincipalAmount(payDownPrincipalAmount);
        return this;
    }

    public void setPayDownPrincipalAmount(Double payDownPrincipalAmount) {
        this.payDownPrincipalAmount = payDownPrincipalAmount;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public PayDownPrincipal payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getUserCreate() {
        return this.userCreate;
    }

    public PayDownPrincipal userCreate(String userCreate) {
        this.setUserCreate(userCreate);
        return this;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getNote() {
        return this.note;
    }

    public PayDownPrincipal note(String note) {
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
        if (!(o instanceof PayDownPrincipal)) {
            return false;
        }
        return id != null && id.equals(((PayDownPrincipal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayDownPrincipal{" +
            "id=" + getId() +
            ", contractId=" + getContractId() +
            ", payDownPrincipalDate='" + getPayDownPrincipalDate() + "'" +
            ", payDownPrincipalAmount=" + getPayDownPrincipalAmount() +
            ", payerName='" + getPayerName() + "'" +
            ", userCreate='" + getUserCreate() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
