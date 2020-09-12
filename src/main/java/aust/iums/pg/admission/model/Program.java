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
@Table(name = "MST_PROGRAM")
public class Program {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;
  
  @NotNull
  @Column(name = "PROGRAM_ID")
  private String programId;
  
  @NotNull
  @Column(name = "PROGRAM_NAME_SHORT")
  private String  programShortName;
  
  @NotNull
  @Column(name = "PROGRAM_NAM_LONG")
  private String programLongName;
  
  @Column(name = "CREATED_ON")
  @CreatedDate
  private Instant createdOn;
  
  @Column(name = "UPDATED_ON")
  @LastModifiedDate
  private Instant updatedOn;

  public Program() {
    
  }

  public Program(Long pId, String pProgramId, String pProgramShortName, String pProgramLongName, Instant pCreatedOn, Instant pUpdatedOn) {
    id = pId;
    programId = pProgramId;
    programShortName = pProgramShortName;
    programLongName = pProgramLongName;
    createdOn = pCreatedOn;
    updatedOn = pUpdatedOn;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public String getProgramId() {
    return programId;
  }

  public void setProgramId(String pProgramId) {
    programId = pProgramId;
  }

  public String getProgramShortName() {
    return programShortName;
  }

  public void setProgramShortName(String pProgramShortName) {
    programShortName = pProgramShortName;
  }

  public String getProgramLongName() {
    return programLongName;
  }

  public void setProgramLongName(String pProgramLongName) {
    programLongName = pProgramLongName;
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
    return "Program{" +
        "id=" + id +
        ", programId='" + programId + '\'' +
        ", programShortName='" + programShortName + '\'' +
        ", programLongName='" + programLongName + '\'' +
        ", createdOn=" + createdOn +
        ", updatedOn=" + updatedOn +
        '}';
  }
}
