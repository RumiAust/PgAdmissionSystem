package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

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

  @ManyToOne
  @JoinColumn(name = "DIVISION_ID", referencedColumnName = "ID",updatable=false)
  Division mDivision;

  @ManyToOne
  @JoinColumn(name = "DISTRCIT_ID", referencedColumnName = "ID",updatable=false)
  District mDistrict;

  @ManyToOne
  @JoinColumn(name = "THANA_ID", referencedColumnName = "ID",updatable=false)
  Thana mThana;

  @OneToOne
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID",updatable=false)
  ApplicantAddress mApplicantAddress;

  @OneToOne
  @JoinColumn(name = "EDUCATION_INFO_ID", referencedColumnName = "ID",updatable=false)
  ApplicantEducationalInfo mApplicantEducationalInfo;

  @OneToOne
  @JoinColumn(name = "PERSONAL_INFO_ID", referencedColumnName = "ID",updatable=false)
  ApplicantPersonaIInfo mApplicantPersonaIInfo;

  @OneToOne
  @JoinColumn(name = "JOB_EXPERIENCE_ID", referencedColumnName = "ID",updatable=false)
  JobExperience mJobExperience;



  public Applicant() {

  }

  public Division getDivision() {
    return mDivision;
  }

  public void setDivision(Division pDivision) {
    mDivision = pDivision;
  }

  public District getDistrict() {
    return mDistrict;
  }

  public void setDistrict(District pDistrict) {
    mDistrict = pDistrict;
  }

  public Thana getThana() {
    return mThana;
  }

  public void setThana(Thana pThana) {
    mThana = pThana;
  }

  public ApplicantAddress getApplicantAddress() {
    return mApplicantAddress;
  }

  public void setApplicantAddress(ApplicantAddress pApplicantAddress) {
    mApplicantAddress = pApplicantAddress;
  }

  public ApplicantEducationalInfo getApplicantEducationalInfo() {
    return mApplicantEducationalInfo;
  }

  public void setApplicantEducationalInfo(ApplicantEducationalInfo pApplicantEducationalInfo) {
    mApplicantEducationalInfo = pApplicantEducationalInfo;
  }

  public ApplicantPersonaIInfo getApplicantPersonaIInfo() {
    return mApplicantPersonaIInfo;
  }

  public void setApplicantPersonaIInfo(ApplicantPersonaIInfo pApplicantPersonaIInfo) {
    mApplicantPersonaIInfo = pApplicantPersonaIInfo;
  }

  public JobExperience getJobExperience() {
    return mJobExperience;
  }

  public void setJobExperience(JobExperience pJobExperience) {
    mJobExperience = pJobExperience;
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
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof Applicant)) return false;
    Applicant applicant = (Applicant) pO;
    return Objects.equals(getId(), applicant.getId()) &&
        Objects.equals(getSemesterId(), applicant.getSemesterId()) &&
        Objects.equals(getProgramId(), applicant.getProgramId()) &&
        Objects.equals(getApplicationSn(), applicant.getApplicationSn()) &&
        Objects.equals(getStatus(), applicant.getStatus()) &&
        Objects.equals(getAppliedOn(), applicant.getAppliedOn()) &&
        Objects.equals(getApplicationFeePaidOn(), applicant.getApplicationFeePaidOn()) &&
        Objects.equals(getSelectedRejectedOn(), applicant.getSelectedRejectedOn()) &&
        Objects.equals(getCreatedOn(), applicant.getCreatedOn()) &&
        Objects.equals(getUpdatedOn(), applicant.getUpdatedOn()) &&
        Objects.equals(getDivision(), applicant.getDivision()) &&
        Objects.equals(getDistrict(), applicant.getDistrict()) &&
        Objects.equals(getThana(), applicant.getThana()) &&
        Objects.equals(getApplicantAddress(), applicant.getApplicantAddress()) &&
        Objects.equals(getApplicantEducationalInfo(), applicant.getApplicantEducationalInfo()) &&
        Objects.equals(getApplicantPersonaIInfo(), applicant.getApplicantPersonaIInfo()) &&
        Objects.equals(getJobExperience(), applicant.getJobExperience());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getSemesterId(), getProgramId(), getApplicationSn(), getStatus(), getAppliedOn(), getApplicationFeePaidOn(), getSelectedRejectedOn(), getCreatedOn(), getUpdatedOn(), getDivision(), getDistrict(), getThana(), getApplicantAddress(), getApplicantEducationalInfo(), getApplicantPersonaIInfo(), getJobExperience());
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
