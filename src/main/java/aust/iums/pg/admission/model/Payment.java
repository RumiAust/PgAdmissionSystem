package aust.iums.pg.admission.model;

import aust.iums.pg.admission.enums.PaymentCategory;
import aust.iums.pg.admission.enums.PaymentStatus;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name="payment")
public class Payment extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name="sequenceGenerator")
    private Long id;

    private String transactionId;

    private Instant transactionValidTill;

    @ManyToOne
    private Applicant applicant;

    private PaymentCategory paymentCategory;

    private BigDecimal amount;

    private BigDecimal waivePercentage;

    private PaymentStatus status;

    private Instant paidOn;


    public Payment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Instant getTransactionValidTill() {
        return transactionValidTill;
    }

    public void setTransactionValidTill(Instant transactionValidTill) {
        this.transactionValidTill = transactionValidTill;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public PaymentCategory getPaymentCategory() {
        return paymentCategory;
    }

    public void setPaymentCategory(PaymentCategory paymentCategory) {
        this.paymentCategory = paymentCategory;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getWaivePercentage() {
        return waivePercentage;
    }

    public void setWaivePercentage(BigDecimal waivePercentage) {
        this.waivePercentage = waivePercentage;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public Instant getPaidOn() {
        return paidOn;
    }

    public void setPaidOn(Instant paidOn) {
        this.paidOn = paidOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Payment)) return false;
        Payment payment = (Payment) o;
        return Objects.equals(getId(), payment.getId()) &&
                Objects.equals(getTransactionId(), payment.getTransactionId()) &&
                Objects.equals(getTransactionValidTill(), payment.getTransactionValidTill()) &&
                Objects.equals(getApplicant(), payment.getApplicant()) &&
                getPaymentCategory() == payment.getPaymentCategory() &&
                Objects.equals(getAmount(), payment.getAmount()) &&
                Objects.equals(getWaivePercentage(), payment.getWaivePercentage()) &&
                getStatus() == payment.getStatus() &&
                Objects.equals(getPaidOn(), payment.getPaidOn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTransactionId(), getTransactionValidTill(), getApplicant(), getPaymentCategory(), getAmount(), getWaivePercentage(), getStatus(), getPaidOn());
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", transactionId='" + transactionId + '\'' +
                ", transactionValidTill=" + transactionValidTill +
                ", applicant=" + applicant +
                ", paymentCategory=" + paymentCategory +
                ", amount=" + amount +
                ", waivePercentage=" + waivePercentage +
                ", status=" + status +
                ", paidOn=" + paidOn +
                '}';
    }
}
