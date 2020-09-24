package aust.iums.pg.admission.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * Created by Rumi on 9/16/2020.
 */
public class WorkExperienceList {
  private String organizationName;
  private String designation;
  private String jobResponsibility;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String fromDate;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String toDate;
  private MultipartFile experienceFile;

  private String experienceFileError;

  public String getExperienceFileError() {
    return experienceFileError;
  }

  public void setExperienceFileError(String experienceFileError) {
    this.experienceFileError = experienceFileError;
  }

  public MultipartFile getExperienceFile() {
    return experienceFile;
  }

  public void setExperienceFile(MultipartFile experienceFile) {
    this.experienceFile = experienceFile;
  }


  public WorkExperienceList(String pOrganizationName, String pDesignation, String pJobResponsibility, String pFromDate, String pToDate) {
    organizationName = pOrganizationName;
    designation = pDesignation;
    jobResponsibility = pJobResponsibility;
    fromDate = pFromDate;
    toDate = pToDate;
  }

  public WorkExperienceList() {
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

  public String getJobResponsibility() {
    return jobResponsibility;
  }

  public void setJobResponsibility(String pJobResponsibility) {
    jobResponsibility = pJobResponsibility;
  }

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String pFromDate) {
    fromDate = pFromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String pToDate) {
    toDate = pToDate;
  }

  @Override
  public String toString() {
    return "WorkExperienceList{" +
        "organizationName='" + organizationName + '\'' +
        ", designation='" + designation + '\'' +
        ", jobResponsibility='" + jobResponsibility + '\'' +
        ", fromDate='" + fromDate + '\'' +
        ", toDate='" + toDate + '\'' +
        '}';
  }
}
