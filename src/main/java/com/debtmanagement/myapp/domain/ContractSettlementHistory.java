package com.debtmanagement.myapp.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ContractSettlementHistory.
 */
@Entity
@Table(name = "contract_settlement_history")
public class ContractSettlementHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "contract_id", nullable = false)
    private Long contractId;

    @Column(name = "settler_name")
    private String settlerName;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContractSettlementHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContractId() {
        return this.contractId;
    }

    public ContractSettlementHistory contractId(Long contractId) {
        this.setContractId(contractId);
        return this;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getSettlerName() {
        return this.settlerName;
    }

    public ContractSettlementHistory settlerName(String settlerName) {
        this.setSettlerName(settlerName);
        return this;
    }

    public void setSettlerName(String settlerName) {
        this.settlerName = settlerName;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractSettlementHistory)) {
            return false;
        }
        return id != null && id.equals(((ContractSettlementHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractSettlementHistory{" +
            "id=" + getId() +
            ", contractId=" + getContractId() +
            ", settlerName='" + getSettlerName() + "'" +
            "}";
    }
}
