package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "MST_DISTRICT")
public class District {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "DIVISION_ID")
  private Integer divisionId;

  @NotNull
  @Column(name = "DISTRICT_ID")
  private Integer  districtId;

  @NotNull
  @Column(name = "DISTRICT_NAME")
  private String districtName;

  public District() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long pId) {
    id = pId;
  }

  public Integer getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(Integer pDivisionId) {
    divisionId = pDivisionId;
  }

  public Integer getDistrictId() {
    return districtId;
  }

  public void setDistrictId(Integer pDistrictId) {
    districtId = pDistrictId;
  }

  public String getDistrictName() {
    return districtName;
  }

  public void setDistrictName(String pDistrictName) {
    districtName = pDistrictName;
  }

  @Override
  public String toString() {
    return "District{" +
        "id=" + id +
        ", divisionId=" + divisionId +
        ", districtId=" + districtId +
        ", districtName='" + districtName + '\'' +
        '}';
  }
}
