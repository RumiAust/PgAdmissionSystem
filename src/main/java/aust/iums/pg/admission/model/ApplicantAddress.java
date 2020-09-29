package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

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

  @ManyToOne(optional = false)
  @JoinColumn(insertable = false, updatable = false)
  private Division division;

  @ManyToOne(optional = false)
  @JoinColumn(insertable = false, updatable = false)
  private District district;

  @ManyToOne(optional = false)
  @JoinColumn(insertable = false, updatable = false)
  private Thana thana;


  /* @ManyToOne
  @JoinColumn(name = "APPLICANT_ID", referencedColumnName = "ID",updatable=false)
  Applicant applicant;*/
 @ManyToOne
 private Applicant applicant;


  public ApplicantAddress() {

  }

  public Applicant getApplicant() {
    return applicant;
  }

  public Division getDivision() {
    return division;
  }

  public void setDivision(Division pDivision) {
    division = pDivision;
  }

  public District getDistrict() {
    return district;
  }

  public void setDistrict(District pDistrict) {
    district = pDistrict;
  }

  public Thana getThana() {
    return thana;
  }

  public void setThana(Thana pThana) {
    thana = pThana;
  }

  public void setApplicant(Applicant pApplicant) {
    applicant = pApplicant;
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
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof ApplicantAddress)) return false;
    ApplicantAddress address = (ApplicantAddress) pO;
    return Objects.equals(getId(), address.getId()) &&
        Objects.equals(getApplicationSn(), address.getApplicationSn()) &&
        Objects.equals(getAddressType(), address.getAddressType()) &&
        Objects.equals(getDivisionId(), address.getDivisionId()) &&
        Objects.equals(getDistrictId(), address.getDistrictId()) &&
        Objects.equals(getThanaId(), address.getThanaId()) &&
        Objects.equals(getThanaOther(), address.getThanaOther()) &&
        Objects.equals(getLine1(), address.getLine1()) &&
        Objects.equals(getLine2(), address.getLine2()) &&
        Objects.equals(getDivision(), address.getDivision()) &&
        Objects.equals(getDistrict(), address.getDistrict()) &&
        Objects.equals(getThana(), address.getThana()) &&
        Objects.equals(getApplicant(), address.getApplicant());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getApplicationSn(), getAddressType(), getDivisionId(), getDistrictId(), getThanaId(), getThanaOther(), getLine1(), getLine2(), getDivision(), getDistrict(), getThana(), getApplicant());
  }

}
