package aust.iums.pg.admission.dto;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * Created by RUmi on 9/24/2020.
 */
public class StatusCheckDto {
  private String applicationSerialNo;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String dateOfBirth;

  private String fullName;
  private String programName;
  private String semesterName;
  private String applicationStatus;

  public StatusCheckDto() {
  }

  public String getApplicationSerialNo() {
    return applicationSerialNo;
  }

  public void setApplicationSerialNo(String pApplicationSerialNo) {
    applicationSerialNo = pApplicationSerialNo;
  }

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String pDateOfBirth) {
    dateOfBirth = pDateOfBirth;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String pFullName) {
    fullName = pFullName;
  }

  public String getProgramName() {
    return programName;
  }

  public void setProgramName(String pProgramName) {
    programName = pProgramName;
  }

  public String getSemesterName() {
    return semesterName;
  }

  public void setSemesterName(String pSemesterName) {
    semesterName = pSemesterName;
  }

  public String getApplicationStatus() {
    return applicationStatus;
  }

  public void setApplicationStatus(String pApplicationStatus) {
    applicationStatus = pApplicationStatus;
  }

  @Override
  public String toString() {
    return "StatusCheckDto{" +
        "applicationSerialNo='" + applicationSerialNo + '\'' +
        ", dateOfBirth='" + dateOfBirth + '\'' +
        ", fullName='" + fullName + '\'' +
        ", programName='" + programName + '\'' +
        ", semesterName='" + semesterName + '\'' +
        ", applicationStatus='" + applicationStatus + '\'' +
        '}';
  }
}
