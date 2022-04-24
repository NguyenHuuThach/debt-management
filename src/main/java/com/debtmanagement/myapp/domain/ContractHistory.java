package com.debtmanagement.myapp.domain;

import com.debtmanagement.myapp.domain.enumeration.StatusContract;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * A ContractHistory.
 */
@Entity
@Table(name = "contract_history")
public class ContractHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date_start", nullable = false)
    private Instant dateStart;

    @Column(name = "settlement_date")
    private Instant settlementDate;

    @NotNull
    @Column(name = "total_loan_amount", nullable = false)
    private Double totalLoanAmount;

    @NotNull
    @Column(name = "interest_payment_period", nullable = false)
    private Integer interestPaymentPeriod;

    @NotNull
    @Column(name = "interest_rate", nullable = false)
    private Integer interestRate;

    @NotNull
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @NotNull
    @Column(name = "customer_address", nullable = false)
    private String customerAddress;

    @NotNull
    @Column(name = "customer_phone_number", nullable = false)
    private String customerPhoneNumber;

    @NotNull
    @Column(name = "customer_identity_card", nullable = false)
    private String customerIdentityCard;

    @NotNull
    @Column(name = "product_name", nullable = false)
    private String productName;

    @Column(name = "imei")
    private String imei;

    @Column(name = "icloud")
    private String icloud;

    @Column(name = "user_create")
    private String userCreate;

    @Column(name = "note")
    private String note;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusContract status;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ContractHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateStart() {
        return this.dateStart;
    }

    public ContractHistory dateStart(Instant dateStart) {
        this.setDateStart(dateStart);
        return this;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Instant getSettlementDate() {
        return this.settlementDate;
    }

    public ContractHistory settlementDate(Instant settlementDate) {
        this.setSettlementDate(settlementDate);
        return this;
    }

    public void setSettlementDate(Instant settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Double getTotalLoanAmount() {
        return this.totalLoanAmount;
    }

    public ContractHistory totalLoanAmount(Double totalLoanAmount) {
        this.setTotalLoanAmount(totalLoanAmount);
        return this;
    }

    public void setTotalLoanAmount(Double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Integer getInterestPaymentPeriod() {
        return this.interestPaymentPeriod;
    }

    public ContractHistory interestPaymentPeriod(Integer interestPaymentPeriod) {
        this.setInterestPaymentPeriod(interestPaymentPeriod);
        return this;
    }

    public void setInterestPaymentPeriod(Integer interestPaymentPeriod) {
        this.interestPaymentPeriod = interestPaymentPeriod;
    }

    public Integer getInterestRate() {
        return this.interestRate;
    }

    public ContractHistory interestRate(Integer interestRate) {
        this.setInterestRate(interestRate);
        return this;
    }

    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public ContractHistory customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public ContractHistory customerAddress(String customerAddress) {
        this.setCustomerAddress(customerAddress);
        return this;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return this.customerPhoneNumber;
    }

    public ContractHistory customerPhoneNumber(String customerPhoneNumber) {
        this.setCustomerPhoneNumber(customerPhoneNumber);
        return this;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerIdentityCard() {
        return this.customerIdentityCard;
    }

    public ContractHistory customerIdentityCard(String customerIdentityCard) {
        this.setCustomerIdentityCard(customerIdentityCard);
        return this;
    }

    public void setCustomerIdentityCard(String customerIdentityCard) {
        this.customerIdentityCard = customerIdentityCard;
    }

    public String getProductName() {
        return this.productName;
    }

    public ContractHistory productName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImei() {
        return this.imei;
    }

    public ContractHistory imei(String imei) {
        this.setImei(imei);
        return this;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIcloud() {
        return this.icloud;
    }

    public ContractHistory icloud(String icloud) {
        this.setIcloud(icloud);
        return this;
    }

    public void setIcloud(String icloud) {
        this.icloud = icloud;
    }

    public String getUserCreate() {
        return this.userCreate;
    }

    public ContractHistory userCreate(String userCreate) {
        this.setUserCreate(userCreate);
        return this;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getNote() {
        return this.note;
    }

    public ContractHistory note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StatusContract getStatus() {
        return this.status;
    }

    public ContractHistory status(StatusContract status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(StatusContract status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ContractHistory)) {
            return false;
        }
        return id != null && id.equals(((ContractHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ContractHistory{" +
            "id=" + getId() +
            ", dateStart='" + getDateStart() + "'" +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", totalLoanAmount=" + getTotalLoanAmount() +
            ", interestPaymentPeriod=" + getInterestPaymentPeriod() +
            ", interestRate=" + getInterestRate() +
            ", customerName='" + getCustomerName() + "'" +
            ", customerAddress='" + getCustomerAddress() + "'" +
            ", customerPhoneNumber='" + getCustomerPhoneNumber() + "'" +
            ", customerIdentityCard='" + getCustomerIdentityCard() + "'" +
            ", productName='" + getProductName() + "'" +
            ", imei='" + getImei() + "'" +
            ", icloud='" + getIcloud() + "'" +
            ", userCreate='" + getUserCreate() + "'" +
            ", note='" + getNote() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
