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
@Table(name = "MST_SEMESTER")
public class Semester {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "SEMESTER_ID")
  private String semesterId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "SEMESTER_ID",referencedColumnName = "SEMESTER_ID",insertable=false, updatable=false)
  Applicant mApplicant;

  @NotNull
  @Column(name = "SEMESTER_NAME")
  private String semesterName;

  @NotNull
  @Column(name = "IS_ACTIVE")
  private Integer isActive;

  @Column(name = "CREATED_ON")
  @CreatedDate
  private Instant createdOn;

  @Column(name = "UPDATED_ON")
  @LastModifiedDate
  private Instant updatedOn;

  public Semester() {

  }

  public Semester(Long pId, String pSemesterId, String pSemesterName, Integer pIsActive, Instant pCreatedOn, Instant pUpdatedOn) {
    id = pId;
    semesterId = pSemesterId;
    semesterName = pSemesterName;
    isActive = pIsActive;
    createdOn = pCreatedOn;
    updatedOn = pUpdatedOn;
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

  public String getSemesterName() {
    return semesterName;
  }

  public void setSemesterName(String pSemesterName) {
    semesterName = pSemesterName;
  }

  public Integer getIsActive() {
    return isActive;
  }

  public void setIsActive(Integer pIsActive) {
    isActive = pIsActive;
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
    return "Semester{" +
        "id=" + id +
        ", semesterId='" + semesterId + '\'' +
        ", semesterName='" + semesterName + '\'' +
        ", isActive=" + isActive +
        ", createdOn=" + createdOn +
        ", UpdatedOn=" + updatedOn +
        '}';
  }
}
