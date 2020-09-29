package aust.iums.pg.admission.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
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
  private Long semesterId;

  @NotNull
  @Column(name = "PROGRAM_ID")
  private Long programId;

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


  @Column(name = "SELECTED_REJECTED_ON", nullable = false)
  private Instant selectedRejectedOn;


  @Column(name = "CREATED_ON")
  @CreatedDate
  private Instant createdOn;

  @Column(name = "UPDATED_ON")
  @LastModifiedDate
  private Instant updatedOn;




  /*@OneToMany
  @JoinColumn(name = "ADDRESS_ID", referencedColumnName = "ID",updatable=false)
  List<ApplicantAddress> applicantAddress;*/

  @OneToMany(mappedBy = "applicant")
  private List<ApplicantAddress> applicantAddresses = new ArrayList<>();

  @OneToMany(mappedBy = "applicant")
  List<ApplicantEducationalInfo> applicantEducationalInfo = new ArrayList<>();

  @OneToOne(mappedBy = "applicant")
  ApplicantPersonaIInfo applicantPersonaIInfo;

  @OneToMany(mappedBy = "applicant")
  List<JobExperience> jobExperience = new ArrayList<>();

  @ManyToOne(optional = false)
  @JoinColumn(insertable = false, updatable = false)
  private Semester semester;

  @ManyToOne(optional = false)
  @JoinColumn(insertable = false, updatable = false)
  private Program program;


  public Applicant() {

  }

  public Semester getSemester() {
    return semester;
  }

  public void setSemester(Semester pSemester) {
    semester = pSemester;
  }

  public Program getProgram() {
    return program;
  }

  public void setProgram(Program pProgram) {
    program = pProgram;
  }

  public ApplicantPersonaIInfo getApplicantPersonaIInfo() {
    return applicantPersonaIInfo;
  }

  public void setApplicantPersonaIInfo(ApplicantPersonaIInfo pApplicantPersonaIInfo) {
    applicantPersonaIInfo = pApplicantPersonaIInfo;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public Long getSemesterId() {
    return semesterId;
  }

  public void setSemesterId(Long pSemesterId) {
    semesterId = pSemesterId;
  }

  public Long getProgramId() {
    return programId;
  }

  public void setProgramId(Long pProgramId) {
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

  public List<ApplicantEducationalInfo> getApplicantEducationalInfo() {
    return applicantEducationalInfo;
  }

  public void setApplicantEducationalInfo(List<ApplicantEducationalInfo> pApplicantEducationalInfo) {
    applicantEducationalInfo = pApplicantEducationalInfo;
  }

  public List<JobExperience> getJobExperience() {
    return jobExperience;
  }

  public void setJobExperience(List<JobExperience> pJobExperience) {
    jobExperience = pJobExperience;
  }


  public List<ApplicantAddress> getApplicantAddresses() {
    return applicantAddresses;
  }

  public void setApplicantAddresses(List<ApplicantAddress> pApplicantAddresses) {
    applicantAddresses = pApplicantAddresses;
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
        Objects.equals(getApplicantAddresses(), applicant.getApplicantAddresses()) &&
        Objects.equals(getApplicantEducationalInfo(), applicant.getApplicantEducationalInfo()) &&
        Objects.equals(getApplicantPersonaIInfo(), applicant.getApplicantPersonaIInfo()) &&
        Objects.equals(getJobExperience(), applicant.getJobExperience()) &&
        Objects.equals(getSemester(), applicant.getSemester()) &&
        Objects.equals(getProgram(), applicant.getProgram());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getSemesterId(), getProgramId(), getApplicationSn(), getStatus(), getAppliedOn(), getApplicationFeePaidOn(), getSelectedRejectedOn(), getCreatedOn(), getUpdatedOn(), getApplicantAddresses(), getApplicantEducationalInfo(), getApplicantPersonaIInfo(), getJobExperience(), getSemester(), getProgram());
  }



}
