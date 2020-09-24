package aust.iums.pg.admission.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class AdmissionApplicantPaymentRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String transactionId;
    private Date transactionValidTill;
    private BigDecimal amount;
    private String serialNo;
    private Integer semesterId;
    private Integer programId;

    public AdmissionApplicantPaymentRequest() {
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

    public Date getTransactionValidTill() {
        return transactionValidTill;
    }

    public void setTransactionValidTill(Date transactionValidTill) {
        this.transactionValidTill = transactionValidTill;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public Integer getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(Integer semesterId) {
        this.semesterId = semesterId;
    }

    public Integer getProgramId() {
        return programId;
    }

    public void setProgramId(Integer programId) {
        this.programId = programId;
    }
}
