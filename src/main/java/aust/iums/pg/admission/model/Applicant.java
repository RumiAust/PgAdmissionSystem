package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */

@Entity
@Table(name = "MST_APPLICANT")
public class Applicant {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "SEMESTER_ID")
  private String semesterId;

  @NotNull
  @Column(name = "PROGRAM_ID")
  private String programId;

  @NotNull
  @Column(name = "APPLICANTION_SN")
  private String applicationSn;

  @NotNull
  @Column(name = "STATUS")
  String status;

  @NotNull
  @Column(name = "APPLIED_ON", nullable = false)
  private Instant appliedOn;

  @NotNull
  @Column(name = "APPLICATION_FEE_PAID_ON", nullable = false)
  private Instant applicationFeePaidOn;

  @NotNull
  @Column(name = "SELECTED_REJECTED_ON", nullable = false)
  private Instant selectedRejectedOn;


  @Column(name = "CREATED_ON")
  @CreatedDate
  private Instant createdOn;

  @Column(name = "UPDATED_ON")
  @LastModifiedDate
  private Instant updatedOn;

  public Applicant() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public String getSemesterId() {
    return semesterId;
  }

  public void setSemesterId(String pSemesterId) {
    semesterId = pSemesterId;
  }

  public String getProgramId() {
    return programId;
  }

  public void setProgramId(String pProgramId) {
    programId = pProgramId;
  }

  public String getApplicationSn() {
    return applicationSn;
  }

  public void setApplicationSn(String pApplicationSn) {
    applicationSn = pApplicationSn;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String pStatus) {
    status = pStatus;
  }

  public Instant getAppliedOn() {
    return appliedOn;
  }

  public void setAppliedOn(Instant pAppliedOn) {
    appliedOn = pAppliedOn;
  }

  public Instant getApplicationFeePaidOn() {
    return applicationFeePaidOn;
  }

  public void setApplicationFeePaidOn(Instant pApplicationFeePaidOn) {
    applicationFeePaidOn = pApplicationFeePaidOn;
  }

  public Instant getSelectedRejectedOn() {
    return selectedRejectedOn;
  }

  public void setSelectedRejectedOn(Instant pSelectedRejectedOn) {
    selectedRejectedOn = pSelectedRejectedOn;
  }

  public Instant getCreatedOn() {
    return createdOn;
  }

  public void setCreatedOn(Instant pCreatedOn) {
    createdOn = pCreatedOn;
  }

  public Instant getUpdatedOn() {
    return updatedOn;
  }

  public void setUpdatedOn(Instant pUpdatedOn) {
    updatedOn = pUpdatedOn;
  }

  @Override
  public String toString() {
    return "Applicant{" +
        "id=" + id +
        ", semesterId='" + semesterId + '\'' +
        ", programId='" + programId + '\'' +
        ", applicationSn='" + applicationSn + '\'' +
        ", status='" + status + '\'' +
        ", appliedOn=" + appliedOn +
        ", applicationFeePaidOn=" + applicationFeePaidOn +
        ", selectedRejectedOn=" + selectedRejectedOn +
        ", createdOn=" + createdOn +
        ", updatedOn=" + updatedOn +
        '}';
  }
}
