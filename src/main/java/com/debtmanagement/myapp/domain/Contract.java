package com.debtmanagement.myapp.domain;

import com.debtmanagement.myapp.domain.enumeration.StatusContract;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * not an ignored comment
 */
@Schema(description = "not an ignored comment")
@Entity
@Table(name = "contract")
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "date_start", nullable = false)
    private Instant dateStart;

    @NotNull
    @Column(name = "number_interest_payments", nullable = false)
    private Integer numberInterestPayments;

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

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Contract id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDateStart() {
        return this.dateStart;
    }

    public Contract dateStart(Instant dateStart) {
        this.setDateStart(dateStart);
        return this;
    }

    public void setDateStart(Instant dateStart) {
        this.dateStart = dateStart;
    }

    public Integer getNumberInterestPayments() {
        return this.numberInterestPayments;
    }

    public Contract numberInterestPayments(Integer numberInterestPayments) {
        this.setNumberInterestPayments(numberInterestPayments);
        return this;
    }

    public void setNumberInterestPayments(Integer numberInterestPayments) {
        this.numberInterestPayments = numberInterestPayments;
    }

    public Double getTotalLoanAmount() {
        return this.totalLoanAmount;
    }

    public Contract totalLoanAmount(Double totalLoanAmount) {
        this.setTotalLoanAmount(totalLoanAmount);
        return this;
    }

    public void setTotalLoanAmount(Double totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Integer getInterestPaymentPeriod() {
        return this.interestPaymentPeriod;
    }

    public Contract interestPaymentPeriod(Integer interestPaymentPeriod) {
        this.setInterestPaymentPeriod(interestPaymentPeriod);
        return this;
    }

    public void setInterestPaymentPeriod(Integer interestPaymentPeriod) {
        this.interestPaymentPeriod = interestPaymentPeriod;
    }

    public Integer getInterestRate() {
        return this.interestRate;
    }

    public Contract interestRate(Integer interestRate) {
        this.setInterestRate(interestRate);
        return this;
    }

    public void setInterestRate(Integer interestRate) {
        this.interestRate = interestRate;
    }

    public String getCustomerName() {
        return this.customerName;
    }

    public Contract customerName(String customerName) {
        this.setCustomerName(customerName);
        return this;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return this.customerAddress;
    }

    public Contract customerAddress(String customerAddress) {
        this.setCustomerAddress(customerAddress);
        return this;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return this.customerPhoneNumber;
    }

    public Contract customerPhoneNumber(String customerPhoneNumber) {
        this.setCustomerPhoneNumber(customerPhoneNumber);
        return this;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerIdentityCard() {
        return this.customerIdentityCard;
    }

    public Contract customerIdentityCard(String customerIdentityCard) {
        this.setCustomerIdentityCard(customerIdentityCard);
        return this;
    }

    public void setCustomerIdentityCard(String customerIdentityCard) {
        this.customerIdentityCard = customerIdentityCard;
    }

    public String getProductName() {
        return this.productName;
    }

    public Contract productName(String productName) {
        this.setProductName(productName);
        return this;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImei() {
        return this.imei;
    }

    public Contract imei(String imei) {
        this.setImei(imei);
        return this;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIcloud() {
        return this.icloud;
    }

    public Contract icloud(String icloud) {
        this.setIcloud(icloud);
        return this;
    }

    public void setIcloud(String icloud) {
        this.icloud = icloud;
    }

    public String getUserCreate() {
        return this.userCreate;
    }

    public Contract userCreate(String userCreate) {
        this.setUserCreate(userCreate);
        return this;
    }

    public void setUserCreate(String userCreate) {
        this.userCreate = userCreate;
    }

    public String getNote() {
        return this.note;
    }

    public Contract note(String note) {
        this.setNote(note);
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public StatusContract getStatus() {
        return this.status;
    }

    public Contract status(StatusContract status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(StatusContract status) {
        this.status = status;
    }

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public Contract isDeleted(Boolean isDeleted) {
        this.setIsDeleted(isDeleted);
        return this;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contract)) {
            return false;
        }
        return id != null && id.equals(((Contract) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Contract{" +
            "id=" + getId() +
            ", dateStart='" + getDateStart() + "'" +
            ", numberInterestPayments=" + getNumberInterestPayments() +
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
            ", isDeleted='" + getIsDeleted() + "'" +
            "}";
    }
}
