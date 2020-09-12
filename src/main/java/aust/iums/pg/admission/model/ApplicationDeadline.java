package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */

// semester--> oneToMany

@Entity
@Table(name = "APPLICATION_DEADLINES")
public class ApplicationDeadline {

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
  @Column(name = "FROM_DATE", nullable = false)
  private Instant fromDate;

  @NotNull
  @Column(name = "TO_DATE", nullable = false)
  private Instant toDate;

  @Column(name = "CREATED_ON")
  @CreatedDate
  private Instant createdOn;

  @Column(name = "UPDATED_ON")
  @LastModifiedDate
  private Instant updatedOn;




  public ApplicationDeadline() {

  }

  public ApplicationDeadline(Long pId, String pSemesterId, String pProgramId, Instant pFromDate, Instant pToDate, Instant pCreatedOn, Instant pUpdatedOn) {
    id = pId;
    semesterId = pSemesterId;
    programId = pProgramId;
    fromDate = pFromDate;
    toDate = pToDate;
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

  public String getProgramId() {
    return programId;
  }

  public void setProgramId(String pProgramId) {
    programId = pProgramId;
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
    return "ApplicationDeadline{" +
        "id=" + id +
        ", semesterId='" + semesterId + '\'' +
        ", programId='" + programId + '\'' +
        ", fromDate=" + fromDate +
        ", toDate=" + toDate +
        ", createdOn=" + createdOn +
        ", updatedOn=" + updatedOn +
        '}';
  }

}
