package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "MST_EXAM_TYPE")
public class ExamType {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "EXAM_TYPE_CODE")
  private String examTypeCode;

  @NotNull
  @Column(name = "EXAM_TYPE_NAME")
  private String examTypeName;

  public ExamType() {

  }

  public ExamType(Long pId, String pExamTypeCode, String pExamTypeName) {
    id = pId;
    examTypeCode = pExamTypeCode;
    examTypeName = pExamTypeName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public String getExamTypeCode() {
    return examTypeCode;
  }

  public void setExamTypeCode(String pExamTypeCode) {
    examTypeCode = pExamTypeCode;
  }

  public String getExamTypeName() {
    return examTypeName;
  }

  public void setExamTypeName(String pExamTypeName) {
    examTypeName = pExamTypeName;
  }

  @Override
  public String toString() {
    return "ExamType{" +
        "id=" + id +
        ", examTypeCode='" + examTypeCode + '\'' +
        ", examTypeName='" + examTypeName + '\'' +
        '}';
  }
}
