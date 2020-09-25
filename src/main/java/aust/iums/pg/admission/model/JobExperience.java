package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "JOB_EXPERIENCE")
public class JobExperience {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "APPLICANTION_SN")
  private String applicationSn;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "APPLICANT_ID",referencedColumnName = "ID",updatable=false)
  Applicant applicant;

  @NotNull
  @Column(name = "ORGANIZATION_NAME")
  private String organizationName;

  @NotNull
  @Column(name = "DESIGNATION")
  private String designation;

  @NotNull
  @Column(name = "JOB_RESPONSIBILITY")
  private String jobResponsibilities;

  @NotNull
  @Column(name = "FROM_DATE", nullable = false)
  private Date fromDate;

  @NotNull
  @Column(name = "TO_DATE", nullable = false)
  private Date toDate;

  public JobExperience() {

  }

  public Applicant getApplicant() {
    return applicant;
  }

  public void setApplicant(Applicant pApplicant) {
    applicant = pApplicant;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public String getApplicationSn() {
    return applicationSn;
  }

  public void setApplicationSn(String pApplicationSn) {
    applicationSn = pApplicationSn;
  }

  public String getOrganizationName() {
    return organizationName;
  }

  public void setOrganizationName(String pOrganizationName) {
    organizationName = pOrganizationName;
  }

  public String getDesignation() {
    return designation;
  }

  public void setDesignation(String pDesignation) {
    designation = pDesignation;
  }

  public String getJobResponsibilities() {
    return jobResponsibilities;
  }

  public void setJobResponsibilities(String pJobResponsibilities) {
    jobResponsibilities = pJobResponsibilities;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date pFromDate) {
    fromDate = pFromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date pToDate) {
    toDate = pToDate;
  }

  @Override
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof JobExperience)) return false;
    JobExperience that = (JobExperience) pO;
    return Objects.equals(getId(), that.getId()) &&
        Objects.equals(getApplicationSn(), that.getApplicationSn()) &&
        Objects.equals(getApplicant(), that.getApplicant()) &&
        Objects.equals(getOrganizationName(), that.getOrganizationName()) &&
        Objects.equals(getDesignation(), that.getDesignation()) &&
        Objects.equals(getJobResponsibilities(), that.getJobResponsibilities()) &&
        Objects.equals(getFromDate(), that.getFromDate()) &&
        Objects.equals(getToDate(), that.getToDate());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getApplicationSn(), getApplicant(), getOrganizationName(), getDesignation(), getJobResponsibilities(), getFromDate(), getToDate());
  }

  @Override
  public String toString() {
    return "JobExperience{" +
        "id=" + id +
        ", applicationSn='" + applicationSn + '\'' +
        ", organizationName='" + organizationName + '\'' +
        ", designation='" + designation + '\'' +
        ", jobResponsibilities='" + jobResponsibilities + '\'' +
        ", fromDate=" + fromDate +
        ", toDate=" + toDate +
        '}';
  }

}
