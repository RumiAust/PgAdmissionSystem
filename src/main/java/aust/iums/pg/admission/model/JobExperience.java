package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.Instant;

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
  private Instant fromDate;

  @NotNull
  @Column(name = "TO_DATE", nullable = false)
  private Instant toDate;

  public JobExperience() {

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

  public Instant getFromDate() {
    return fromDate;
  }

  public void setFromDate(Instant pFromDate) {
    fromDate = pFromDate;
  }

  public Instant getToDate() {
    return toDate;
  }

  public void setToDate(Instant pToDate) {
    toDate = pToDate;
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
