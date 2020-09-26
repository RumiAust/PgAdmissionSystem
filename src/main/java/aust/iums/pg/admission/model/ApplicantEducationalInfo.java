package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "APPLICANT_EDUCATIONAL_INFO")
public class ApplicantEducationalInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "APPLICANTION_SN")
  private String applicationSn;


  @NotNull
  @Column(name = "Exam_Type")
  private String examType;

  @NotNull
  @Column(name = "INSTITUTE_NAME")
  private String instituteName;

  @NotNull
  @Column(name = "BOARD")
  private String board;

  @NotNull
  @Column(name = "TOTAL_MARKS_GRADE")
  private String totalMarks;

  @NotNull
  @Column(name = "DIVISION_CLASS_GRADE")
  private String divisionClassGrade;

  @NotNull
  @Column(name = "PASSING_YEAR")
  private Integer passingYear;

  @ManyToOne
  Applicant applicant;

  public ApplicantEducationalInfo() {

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

  public String getExamType() {
    return examType;
  }

  public void setExamType(String pExamType) {
    examType = pExamType;
  }

  public String getInstituteName() {
    return instituteName;
  }

  public void setInstituteName(String pInstituteName) {
    instituteName = pInstituteName;
  }

  public String getBoard() {
    return board;
  }

  public void setBoard(String pBoard) {
    board = pBoard;
  }

  public String getTotalMarks() {
    return totalMarks;
  }

  public void setTotalMarks(String pTotalMarks) {
    totalMarks = pTotalMarks;
  }

  public String getDivisionClassGrade() {
    return divisionClassGrade;
  }

  public void setDivisionClassGrade(String pDivisionClassGrade) {
    divisionClassGrade = pDivisionClassGrade;
  }

  public Integer getPassingYear() {
    return passingYear;
  }

  public void setPassingYear(Integer pPassingYear) {
    passingYear = pPassingYear;
  }

  @Override
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof ApplicantEducationalInfo)) return false;
    ApplicantEducationalInfo that = (ApplicantEducationalInfo) pO;
    return Objects.equals(getId(), that.getId()) &&
        Objects.equals(getApplicationSn(), that.getApplicationSn()) &&
        Objects.equals(getExamType(), that.getExamType()) &&
        Objects.equals(getInstituteName(), that.getInstituteName()) &&
        Objects.equals(getBoard(), that.getBoard()) &&
        Objects.equals(getTotalMarks(), that.getTotalMarks()) &&
        Objects.equals(getDivisionClassGrade(), that.getDivisionClassGrade()) &&
        Objects.equals(getPassingYear(), that.getPassingYear()) &&
        Objects.equals(getApplicant(), that.getApplicant());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getApplicationSn(), getExamType(), getInstituteName(), getBoard(), getTotalMarks(), getDivisionClassGrade(), getPassingYear(), getApplicant());
  }

  @Override
  public String toString() {
    return "ApplicantEducationalInfo{" +
        "id=" + id +
        ", applicationSn='" + applicationSn + '\'' +
        ", examType='" + examType + '\'' +
        ", instituteName='" + instituteName + '\'' +
        ", board='" + board + '\'' +
        ", totalMarks=" + totalMarks +
        ", divisionClassGrade='" + divisionClassGrade + '\'' +
        ", passingYear=" + passingYear +
        '}';
  }
}
