package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "APPLICANT_ADDRESS")
public class ApplicantAddress {
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
  @Column(name = "ADDRESS_TYPE")
  private String addressType;

  @NotNull
  @Column(name = "DIVISION_ID")
  private Integer divisionId;

  @NotNull
  @Column(name = "DISTRICT_ID")
  private Integer districtId;

  @NotNull
  @Column(name = "THANA_ID")
  private Integer thanaId;

  @NotNull
  @Column(name = "THANA_OTHER")
  private String thanaOther;

  @NotNull
  @Column(name = "LINE1")
  private String line1;

  @NotNull
  @Column(name = "LINE2")
  private String line2;

  public ApplicantAddress() {

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

  public String getAddressType() {
    return addressType;
  }

  public void setAddressType(String pAddressType) {
    addressType = pAddressType;
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

  public Integer getThanaId() {
    return thanaId;
  }

  public void setThanaId(Integer pThanaId) {
    thanaId = pThanaId;
  }

  public String getThanaOther() {
    return thanaOther;
  }

  public void setThanaOther(String pThanaOther) {
    thanaOther = pThanaOther;
  }

  public String getLine1() {
    return line1;
  }

  public void setLine1(String pLine1) {
    line1 = pLine1;
  }

  public String getLine2() {
    return line2;
  }

  public void setLine2(String pLine2) {
    line2 = pLine2;
  }

  @Override
  public String toString() {
    return "ApplicantAddress{" +
        "id=" + id +
        ", applicationSn='" + applicationSn + '\'' +
        ", addressType='" + addressType + '\'' +
        ", divisionId=" + divisionId +
        ", districtId=" + districtId +
        ", thanaId=" + thanaId +
        ", thanaOther='" + thanaOther + '\'' +
        ", line1='" + line1 + '\'' +
        ", line2='" + line2 + '\'' +
        '}';
  }
}
