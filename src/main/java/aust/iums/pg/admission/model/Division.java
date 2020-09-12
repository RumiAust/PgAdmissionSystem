package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "MST_DIVISION")
public class Division {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Integer id;
  
  @NotNull
  @Column(name = "DIVISION_NAME")
  private String divisionName;

  public Division() {
    
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer pId) {
    id = pId;
  }

  public String getDivisionName() {
    return divisionName;
  }

  public void setDivisionName(String pDivisionName) {
    divisionName = pDivisionName;
  }

  @Override
  public String toString() {
    return "Division{" +
        "id=" + id +
        ", divisionName='" + divisionName + '\'' +
        '}';
  }
}
