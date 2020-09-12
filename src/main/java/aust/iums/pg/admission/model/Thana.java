package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

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
  @Column(name = "THANA_ID")
  private Integer  thanaId;

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

  public Integer getThanaId() {
    return thanaId;
  }

  public void setThanaId(Integer pThanaId) {
    thanaId = pThanaId;
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
        ", thanaId=" + thanaId +
        ", thanaName='" + thanaName + '\'' +
        '}';
  }
}
