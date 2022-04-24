package com.debtmanagement.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A PayDownPrincipalHistory.
 */
@Entity
@Table(name = "pay_down_principal_history")
public class PayDownPrincipalHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @NotNull
    @Column(name = "pay_down_principal_amount", nullable = false)
    private Double payDownPrincipalAmount;

    @Column(name = "payer_name")
    private String payerName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PayDownPrincipalHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return this.contractId;
    }

    public PayDownPrincipalHistory contractId(Long contractId) {
        this.setContractId(contractId);
        return this;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public Double getPayDownPrincipalAmount() {
        return this.payDownPrincipalAmount;
    }

    public PayDownPrincipalHistory payDownPrincipalAmount(Double payDownPrincipalAmount) {
        this.setPayDownPrincipalAmount(payDownPrincipalAmount);
        return this;
    }

    public void setPayDownPrincipalAmount(Double payDownPrincipalAmount) {
        this.payDownPrincipalAmount = payDownPrincipalAmount;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public PayDownPrincipalHistory payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PayDownPrincipalHistory)) {
            return false;
        }
        return id != null && id.equals(((PayDownPrincipalHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PayDownPrincipalHistory{" +
            "id=" + getId() +
            ", contractId=" + getContractId() +
            ", payDownPrincipalAmount=" + getPayDownPrincipalAmount() +
            ", payerName='" + getPayerName() + "'" +
            "}";
    }
}
