package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

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

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "APPLICANTION_SN",referencedColumnName = "APPLICANTION_SN",insertable=false, updatable=false)
  Applicant mApplicant;

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
  private Integer totalMarks;

  @NotNull
  @Column(name = "DIVISION_CLASS_GRADE")
  private String divisionClassGrade;

  @NotNull
  @Column(name = "PASSING_YEAR")
  private Integer passingYear;

  public ApplicantEducationalInfo() {

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

  public Integer getTotalMarks() {
    return totalMarks;
  }

  public void setTotalMarks(Integer pTotalMarks) {
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
