package aust.iums.pg.admission.dto;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Rumi on 9/16/2020.
 */
public class ApplicationForm {
  //program info
   private String programId;
   private String programName;
   //personal info
   private String fullName;
   private String fatherName;
   private String motherName;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private String dateOfBirth;
  private String nationality;
  private String religion;
  private String email;
  private String gender;
  private String maritalStatus;
  //educational info
  private String sscInstituteName;
  private String sscBoardName;
  private String sscMarks;
  private String sscGrade;
  private Integer sscPassingYear;
  private MultipartFile sscFile;
  private String hscInstituteName;
  private String hscBoardName;
  private String hscMarks;
  private String hscGrade;
  private Integer hscPassingYear;
  private MultipartFile hscFile;
  private String bscInstituteName;
  private String bscBoardName;
  private String bscMarks;
  private String bscGrade;
  private Integer bscPassingYear;
  private MultipartFile bscFile;
  private String mscInstituteName;
  private String mscBoardName;
  private String mscMarks;
  private String mscGrade;
  private Integer mscPassingYear;
  private MultipartFile mscFile;
  //work experience
  private MultipartFile workExperienceFile;
  List<WorkExperienceList> experienceListList;
  //contact info
  private String presentDivision;
  private String presentDistrict;
  private String presentThana;
  private String presentAddress;
  private String permanentDivision;
  private String permanentDistrict;
  private String permanentThana;
  private String permanentAddress;
  // photo and signature
  private MultipartFile photo;
  private MultipartFile signature;
  private boolean declaration;

  public String getProgramId() {
    return programId;
  }

  public void setProgramId(String pProgramId) {
    programId = pProgramId;
  }

  public String getProgramName() {
    return programName;
  }

  public void setProgramName(String pProgramName) {
    programName = pProgramName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String pFullName) {
    fullName = pFullName;
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

  public String getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(String pDateOfBirth) {
    dateOfBirth = pDateOfBirth;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String pNationality) {
    nationality = pNationality;
  }

  public String getReligion() {
    return religion;
  }

  public void setReligion(String pReligion) {
    religion = pReligion;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String pEmail) {
    email = pEmail;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String pGender) {
    gender = pGender;
  }

  public String getMaritalStatus() {
    return maritalStatus;
  }

  public void setMaritalStatus(String pMaritalStatus) {
    maritalStatus = pMaritalStatus;
  }

  public String getSscInstituteName() {
    return sscInstituteName;
  }

  public void setSscInstituteName(String pSscInstituteName) {
    sscInstituteName = pSscInstituteName;
  }

  public String getSscBoardName() {
    return sscBoardName;
  }

  public void setSscBoardName(String pSscBoardName) {
    sscBoardName = pSscBoardName;
  }

  public String getSscMarks() {
    return sscMarks;
  }

  public void setSscMarks(String pSscMarks) {
    sscMarks = pSscMarks;
  }

  public String getSscGrade() {
    return sscGrade;
  }

  public void setSscGrade(String pSscGrade) {
    sscGrade = pSscGrade;
  }

  public Integer getSscPassingYear() {
    return sscPassingYear;
  }

  public void setSscPassingYear(Integer pSscPassingYear) {
    sscPassingYear = pSscPassingYear;
  }

  public MultipartFile getSscFile() {
    return sscFile;
  }

  public void setSscFile(MultipartFile pSscFile) {
    sscFile = pSscFile;
  }

  public String getHscInstituteName() {
    return hscInstituteName;
  }

  public void setHscInstituteName(String pHscInstituteName) {
    hscInstituteName = pHscInstituteName;
  }

  public String getHscBoardName() {
    return hscBoardName;
  }

  public void setHscBoardName(String pHscBoardName) {
    hscBoardName = pHscBoardName;
  }

  public String getHscMarks() {
    return hscMarks;
  }

  public void setHscMarks(String pHscMarks) {
    hscMarks = pHscMarks;
  }

  public String getHscGrade() {
    return hscGrade;
  }

  public void setHscGrade(String pHscGrade) {
    hscGrade = pHscGrade;
  }

  public Integer getHscPassingYear() {
    return hscPassingYear;
  }

  public void setHscPassingYear(Integer pHscPassingYear) {
    hscPassingYear = pHscPassingYear;
  }

  public MultipartFile getHscFile() {
    return hscFile;
  }

  public void setHscFile(MultipartFile pHscFile) {
    hscFile = pHscFile;
  }

  public String getBscInstituteName() {
    return bscInstituteName;
  }

  public void setBscInstituteName(String pBscInstituteName) {
    bscInstituteName = pBscInstituteName;
  }

  public String getBscBoardName() {
    return bscBoardName;
  }

  public void setBscBoardName(String pBscBoardName) {
    bscBoardName = pBscBoardName;
  }

  public String getBscMarks() {
    return bscMarks;
  }

  public void setBscMarks(String pBscMarks) {
    bscMarks = pBscMarks;
  }

  public String getBscGrade() {
    return bscGrade;
  }

  public void setBscGrade(String pBscGrade) {
    bscGrade = pBscGrade;
  }

  public Integer getBscPassingYear() {
    return bscPassingYear;
  }

  public void setBscPassingYear(Integer pBscPassingYear) {
    bscPassingYear = pBscPassingYear;
  }

  public MultipartFile getBscFile() {
    return bscFile;
  }

  public void setBscFile(MultipartFile pBscFile) {
    bscFile = pBscFile;
  }

  public String getMscInstituteName() {
    return mscInstituteName;
  }

  public void setMscInstituteName(String pMscInstituteName) {
    mscInstituteName = pMscInstituteName;
  }

  public String getMscBoardName() {
    return mscBoardName;
  }

  public void setMscBoardName(String pMscBoardName) {
    mscBoardName = pMscBoardName;
  }

  public String getMscMarks() {
    return mscMarks;
  }

  public void setMscMarks(String pMscMarks) {
    mscMarks = pMscMarks;
  }

  public String getMscGrade() {
    return mscGrade;
  }

  public void setMscGrade(String pMscGrade) {
    mscGrade = pMscGrade;
  }

  public Integer getMscPassingYear() {
    return mscPassingYear;
  }

  public void setMscPassingYear(Integer pMscPassingYear) {
    mscPassingYear = pMscPassingYear;
  }

  public MultipartFile getMscFile() {
    return mscFile;
  }

  public void setMscFile(MultipartFile pMscFile) {
    mscFile = pMscFile;
  }

  public MultipartFile getWorkExperienceFile() {
    return workExperienceFile;
  }

  public void setWorkExperienceFile(MultipartFile pWorkExperienceFile) {
    workExperienceFile = pWorkExperienceFile;
  }

  public String getPresentDivision() {
    return presentDivision;
  }

  public void setPresentDivision(String pPresentDivision) {
    presentDivision = pPresentDivision;
  }

  public String getPresentDistrict() {
    return presentDistrict;
  }

  public void setPresentDistrict(String pPresentDistrict) {
    presentDistrict = pPresentDistrict;
  }

  public String getPresentThana() {
    return presentThana;
  }

  public void setPresentThana(String pPresentThana) {
    presentThana = pPresentThana;
  }

  public String getPresentAddress() {
    return presentAddress;
  }

  public void setPresentAddress(String pPresentAddress) {
    presentAddress = pPresentAddress;
  }

  public String getPermanentDivision() {
    return permanentDivision;
  }

  public void setPermanentDivision(String pPermanentDivision) {
    permanentDivision = pPermanentDivision;
  }

  public String getPermanentDistrict() {
    return permanentDistrict;
  }

  public void setPermanentDistrict(String pPermanentDistrict) {
    permanentDistrict = pPermanentDistrict;
  }

  public String getPermanentThana() {
    return permanentThana;
  }

  public void setPermanentThana(String pPermanentThana) {
    permanentThana = pPermanentThana;
  }

  public String getPermanentAddress() {
    return permanentAddress;
  }

  public void setPermanentAddress(String pPermanentAddress) {
    permanentAddress = pPermanentAddress;
  }

  public MultipartFile getPhoto() {
    return photo;
  }

  public void setPhoto(MultipartFile pPhoto) {
    photo = pPhoto;
  }

  public MultipartFile getSignature() {
    return signature;
  }

  public void setSignature(MultipartFile pSignature) {
    signature = pSignature;
  }

  public boolean isDeclaration() {
    return declaration;
  }

  public void setDeclaration(boolean pDeclaration) {
    declaration = pDeclaration;
  }

  public List<WorkExperienceList> getExperienceListList() {
    return experienceListList;
  }

  public void setExperienceListList(List<WorkExperienceList> pExperienceListList) {
    experienceListList = pExperienceListList;
  }
}
