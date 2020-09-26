package aust.iums.pg.admission.model;

import com.sun.istack.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Monjur-E-Morshed on 9/11/2020.
 */
@Entity
@Table(name = "APPLICANT_PERSONAL_INFO")
public class ApplicantPersonaIInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
  @SequenceGenerator(name = "sequenceGenerator")
  private Long id;

  @NotNull
  @Column(name = "APPLICANTION_SN")
  private String applicationSn;



  @NotNull
  @Column(name = "FIRST_NAME")
  private String firstName;

  @NotNull
  @Column(name = "MIDDLE_NAME")
  private String middleName;

  @NotNull
  @Column(name = "LAST_NAME")
  private String lastName;

  @NotNull
  @Column(name = "FATHER_NAME")
  private String fatherName;

  @NotNull
  @Column(name = "MOTHER_NAME")
  private String motherName;

  @NotNull
  @Column(name = "GENDER")
  private String gender;

  @NotNull
  @Column(name = "RELIGION")
  private String religion;

  @NotNull
  @Column(name = "NATIONALITY")
  private String nationality;

  @NotNull
  @Column(name = "DATE_OF_BIRTH")
  private Date dateOfBirth;

  @NotNull
  @Column(name = "PLACE_OF_BIRTH")
  private String placeOfBirth;

  @NotNull
  @Column(name = "MOBILE_NUMBER")
  private String mobileNumber;

  @NotNull
  @Column(name = "EMAIL_ADDRESS")
  private String emailAddress;

  @NotNull
  @Column(name = "MARITAL_STATUS")
  private String maritalStatus;

  @Column(name = "CREATED_ON")
  @CreatedDate
  private Instant createdOn;

  @Column(name = "UPDATED_ON")
  @LastModifiedDate
  private Instant updatedOn;

  @OneToOne
  @JoinColumn(unique = true)
  private Applicant applicant;

  public ApplicantPersonaIInfo() {

  }

  public Applicant getApplicant() {
    return applicant;
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

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String pFirstName) {
    firstName = pFirstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String pMiddleName) {
    middleName = pMiddleName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String pLastName) {
    lastName = pLastName;
  }

  public String getFatherName() {
    return fatherName;
  }

  public void setFatherName(String pFatherName) {
    fatherName = pFatherName;
  }

  public String getMotherName() {
    return motherName;
  }

  public void setMotherName(String pMotherName) {
    motherName = pMotherName;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String pGender) {
    gender = pGender;
  }

  public String getReligion() {
    return religion;
  }

  public void setReligion(String pReligion) {
    religion = pReligion;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String pNationality) {
    nationality = pNationality;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date pDateOfBirth) {
    dateOfBirth = pDateOfBirth;
  }

  public String getPlaceOfBirth() {
    return placeOfBirth;
  }

  public void setPlaceOfBirth(String pPlaceOfBirth) {
    placeOfBirth = pPlaceOfBirth;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String pMobileNumber) {
    mobileNumber = pMobileNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String pEmailAddress) {
    emailAddress = pEmailAddress;
  }

  public String getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(String pMaritalStatus) {
    maritalStatus = pMaritalStatus;
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
  public boolean equals(Object pO) {
    if (this == pO) return true;
    if (!(pO instanceof ApplicantPersonaIInfo)) return false;
    ApplicantPersonaIInfo that = (ApplicantPersonaIInfo) pO;
    return Objects.equals(getId(), that.getId()) &&
        Objects.equals(getApplicationSn(), that.getApplicationSn()) &&
        Objects.equals(getFirstName(), that.getFirstName()) &&
        Objects.equals(getMiddleName(), that.getMiddleName()) &&
        Objects.equals(getLastName(), that.getLastName()) &&
        Objects.equals(getFatherName(), that.getFatherName()) &&
        Objects.equals(getMotherName(), that.getMotherName()) &&
        Objects.equals(getGender(), that.getGender()) &&
        Objects.equals(getReligion(), that.getReligion()) &&
        Objects.equals(getNationality(), that.getNationality()) &&
        Objects.equals(getDateOfBirth(), that.getDateOfBirth()) &&
        Objects.equals(getPlaceOfBirth(), that.getPlaceOfBirth()) &&
        Objects.equals(getMobileNumber(), that.getMobileNumber()) &&
        Objects.equals(getEmailAddress(), that.getEmailAddress()) &&
        Objects.equals(getMaritalStatus(), that.getMaritalStatus()) &&
        Objects.equals(getCreatedOn(), that.getCreatedOn()) &&
        Objects.equals(getUpdatedOn(), that.getUpdatedOn()) &&
        Objects.equals(getApplicant(), that.getApplicant());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getApplicationSn(), getFirstName(), getMiddleName(), getLastName(), getFatherName(), getMotherName(), getGender(), getReligion(), getNationality(), getDateOfBirth(), getPlaceOfBirth(), getMobileNumber(), getEmailAddress(), getMaritalStatus(), getCreatedOn(), getUpdatedOn(), getApplicant());
  }

  @Override
  public String toString() {
    return "ApplicantPersonaIInfo{" +
        "id=" + id +
        ", applicationSn='" + applicationSn + '\'' +
        ", firstName='" + firstName + '\'' +
        ", middleName='" + middleName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", fatherName='" + fatherName + '\'' +
        ", motherName='" + motherName + '\'' +
        ", gender='" + gender + '\'' +
        ", religion='" + religion + '\'' +
        ", nationality='" + nationality + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", placeOfBirth='" + placeOfBirth + '\'' +
        ", mobileNumber='" + mobileNumber + '\'' +
        ", emailAddress='" + emailAddress + '\'' +
        ", maritalStatus='" + maritalStatus + '\'' +
        ", createdOn=" + createdOn +
        ", updatedOn=" + updatedOn +
        '}';
  }
}
