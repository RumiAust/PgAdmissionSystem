package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

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
  @Column(name = "DISTRICT_NAME")
  private String districtName;

  @NotNull
  @Column(name = "CODE")
  private String code;


  public District() {

  }

  public String getCode() {
    return code;
  }

  public void setCode(String pCode) {
    code = pCode;
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
        ", districtName='" + districtName + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof District)) return false;
    District district = (District) pO;
    return Objects.equals(getId(), district.getId()) &&
        Objects.equals(getDivisionId(), district.getDivisionId()) &&
        Objects.equals(getDistrictName(), district.getDistrictName()) &&
        Objects.equals(getCode(), district.getCode());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getDivisionId(), getDistrictName(), getCode());
  }
}
