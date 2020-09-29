package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "MST_THANA")
public class Thana {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "DISTRICT_ID")
  private Integer  districtId;


  @NotNull
  @Column(name = "THANA_NAME")
  private String  thanaName;

  public Thana() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer pDistrictId) {
    districtId = pDistrictId;
  }


  public String getThanaName() {
    return thanaName;
  }

  public void setThanaName(String pThanaName) {
    thanaName = pThanaName;
  }

  @Override
  public String toString() {
    return "Thana{" +
        "id=" + id +
        ", districtId=" + districtId +
        ", thanaName='" + thanaName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof Thana)) return false;
    Thana thana = (Thana) pO;
    return Objects.equals(getId(), thana.getId()) &&
        Objects.equals(getDistrictId(), thana.getDistrictId()) &&
        Objects.equals(getThanaName(), thana.getThanaName());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getDistrictId(), getThanaName());
  }
}
