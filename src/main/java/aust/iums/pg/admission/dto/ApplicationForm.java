package aust.iums.pg.admission.dto;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by Rumi on 9/16/2020.
 */
public class ApplicationForm {
    //application serial number
    private String applicationSerialNumber;

    //program info
    @NotNull(message = "Please select program.")
    @Size(min = 1, message = "Please select program.")
    private String programId;
    private String programName;
    private String semesterId;
    private String semesterName;
    //personal info
    private String fullName;

    @NotNull(message = "Please enter first name.")
    @Size(min = 1, message = "Please enter first name.")
    private String firstName;
    private String middleName;
    private String lastName;
    @NotNull(message = "Please enter father name.")
    @Size(min = 1, message = "Please enter father name.")
    private String fatherName;
    @NotNull
    @Size(min = 1, message = "Please enter mother name.")
    private String motherName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    @Size(min = 1, message = "Please select date of birth.")
    private String dateOfBirth;
    @NotNull
    @Size(min = 1, message = "Please enter place of birth.")
    private String placeOfBirth;
    @NotNull
    @Size(min = 1, message = "Please enter mobile number.")
    private String mobileNumber;
    @NotNull
    @Size(min = 1, message = "Please enter nationality.")
    private String nationality;
    @NotNull
    @Size(min = 1, message = "Please enter religion.")
    private String religion;
    @Size(min = 1,message = "Please enter email.")
    @Email
    private String email;
    @NotNull(message = "Please select gender.")
    @Size(min = 1, message = "Please select gender.")
    private String gender;
    @NotNull(message = "Please select marital status.")
    @Size(min = 1, message = "Please select marital status.")
    private String maritalStatus;

    //educational info
    @NotNull
    @Size(min = 1, message = "Please enter Institution name.")
    private String sscInstituteName;
    @NotNull
    @Size(min = 1, message = "Please enter board name.")
    private String sscBoardName;
    @NotNull
    @Size(min = 1, message = "Please enter total marks/grade.")
    private String sscMarks;
    @NotNull
    @Size(min = 1, message = "Please enter division/class/grade.")
    private String sscGrade;
    @NotNull(message = "Please select passing year.")
    private Integer sscPassingYear;
    @NotNull(message = "Please select file.")
    private MultipartFile sscFile;
    @NotNull
    @Size(min = 1, message = "Please enter Institution name.")
    private String hscInstituteName;
    @NotNull
    @Size(min = 1, message = "Please enter board name.")
    private String hscBoardName;
    @NotNull
    @Size(min = 1, message = "Please enter total marks/grade.")
    private String hscMarks;
    @NotNull
    @Size(min = 1, message = "Please enter division/class/grade.")
    private String hscGrade;
    @NotNull(message = "Please select passing year.")
    private Integer hscPassingYear;
    @NotNull(message = "Please select file.")
    private MultipartFile hscFile;
    @NotNull
    @Size(min = 1, message = "Please enter Institution name.")
    private String bscInstituteName;
    @NotNull
    @Size(min = 1, message = "Please enter board name.")
    private String bscBoardName;
    @NotNull
    @Size(min = 1, message = "Please enter total marks/grade.")
    private String bscMarks;
    @NotNull
    @Size(min = 1, message = "Please enter division/class/grade.")
    private String bscGrade;
    @NotNull(message = "Please select passing year.")
    private Integer bscPassingYear;
    @NotNull(message = "Please select file.")
    private MultipartFile bscFile;
    private String mscInstituteName;
    private String mscBoardName;
    private String mscMarks;
    private String mscGrade;
    private Integer mscPassingYear;
    private MultipartFile mscFile;

    //work experience
    List<WorkExperienceList> workExperienceList;
    private String workExperienceDivId;
    private List<Number> serialList;
    private int pointer;


    //contact info
    @NotNull(message = "Please select division.")
    @Size(min = 1, message = "Please select division.")
    private String presentDivisionId;
    private String presentDivision;
    @NotNull(message = "Please select district.")
    @Size(min = 1, message = "Please select district.")
    private String presentDistrictId;
    private String presentDistrict;
    @NotNull(message = "Please select thana.")
    @Size(min = 1, message = "Please select thana.")
    private String presentThanaId;
    private String presentThana;
    private String presentOtherThana;
    @NotNull
    @Size(max=254,message = "Maximum character length is 254 characters.")
    @Size(min = 1, message = "Please enter present address.")
    private String presentAddress;
    @NotNull
    @Size(min = 1, message = "Please select division.")
    private String permanentDivisionId;
    private String permanentDivision;
    @NotNull(message = "Please select district.")
    @Size(min = 1, message = "Please select district.")
    private String permanentDistrictId;
    private String permanentDistrict;
    @NotNull(message = "Please select thana.")
    @Size(min = 1, message = "Please select thana.")
    private String permanentThanaId;
    private String permanentThana;
    private String permanentOtherThana;
    @NotNull
    @Size(max=254,message = "Maximum character length is 254 characters.")
    @Size(min = 1, message = "Please enter permanent address.")
    private String permanentAddress;
    // photo and signature
    @NotNull(message = "Please select photo.")
    private MultipartFile photo;
    @NotNull(message = "Please select signature.")
    private MultipartFile signature;
    @NotNull
    private boolean declaration;

    //error-check-and-show-message
    private String photoError;
    private String signatureError;
    private String sscFileError;
    private String hscFileError;
    private String bscFileError;
    private String mscFileError;
    private String programDeadlineOverError;

    // modal Id
    private String modalId;



    public String getProgramDeadlineOverError() {
        return programDeadlineOverError;
    }

    public void setProgramDeadlineOverError(String programDeadlineOverError) {
        this.programDeadlineOverError = programDeadlineOverError;
    }

    public int getPointer() {
        return pointer;
    }

    public void setPointer(int pointer) {
        this.pointer = pointer;
    }

    public List<Number> getSerialList() {
        return serialList;
    }

    public void setSerialList(List<Number> serialList) {
        this.serialList = serialList;
    }

    public String getPresentOtherThana() {
        return presentOtherThana;
    }

    public void setPresentOtherThana(String presentOtherThana) {
        this.presentOtherThana = presentOtherThana;
    }

    public String getPermanentOtherThana() {
        return permanentOtherThana;
    }

    public void setPermanentOtherThana(String permanentOtherThana) {
        this.permanentOtherThana = permanentOtherThana;
    }

    @Override
    public String toString() {
        return "ApplicationForm{" +
                "applicationSerialNumber='" + applicationSerialNumber + '\'' +
                ", programId='" + programId + '\'' +
                ", programName='" + programName + '\'' +
                ", semesterId='" + semesterId + '\'' +
                ", semesterName='" + semesterName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", motherName='" + motherName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", placeOfBirth='" + placeOfBirth + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", nationality='" + nationality + '\'' +
                ", religion='" + religion + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", sscInstituteName='" + sscInstituteName + '\'' +
                ", sscBoardName='" + sscBoardName + '\'' +
                ", sscMarks='" + sscMarks + '\'' +
                ", sscGrade='" + sscGrade + '\'' +
                ", sscPassingYear=" + sscPassingYear +
                ", sscFile=" + sscFile +
                ", hscInstituteName='" + hscInstituteName + '\'' +
                ", hscBoardName='" + hscBoardName + '\'' +
                ", hscMarks='" + hscMarks + '\'' +
                ", hscGrade='" + hscGrade + '\'' +
                ", hscPassingYear=" + hscPassingYear +
                ", hscFile=" + hscFile +
                ", bscInstituteName='" + bscInstituteName + '\'' +
                ", bscBoardName='" + bscBoardName + '\'' +
                ", bscMarks='" + bscMarks + '\'' +
                ", bscGrade='" + bscGrade + '\'' +
                ", bscPassingYear=" + bscPassingYear +
                ", bscFile=" + bscFile +
                ", mscInstituteName='" + mscInstituteName + '\'' +
                ", mscBoardName='" + mscBoardName + '\'' +
                ", mscMarks='" + mscMarks + '\'' +
                ", mscGrade='" + mscGrade + '\'' +
                ", mscPassingYear=" + mscPassingYear +
                ", mscFile=" + mscFile +
                ", workExperienceList=" + workExperienceList +
                ", workExperienceDivId='" + workExperienceDivId + '\'' +
                ", presentDivisionId='" + presentDivisionId + '\'' +
                ", presentDivision='" + presentDivision + '\'' +
                ", presentDistrictId='" + presentDistrictId + '\'' +
                ", presentDistrict='" + presentDistrict + '\'' +
                ", presentThanaId='" + presentThanaId + '\'' +
                ", presentThana='" + presentThana + '\'' +
                ", presentOtherThana='" + presentOtherThana + '\'' +
                ", presentAddress='" + presentAddress + '\'' +
                ", permanentDivisionId='" + permanentDivisionId + '\'' +
                ", permanentDivision='" + permanentDivision + '\'' +
                ", permanentDistrictId='" + permanentDistrictId + '\'' +
                ", permanentDistrict='" + permanentDistrict + '\'' +
                ", permanentThanaId='" + permanentThanaId + '\'' +
                ", permanentThana='" + permanentThana + '\'' +
                ", permanentOtherThana='" + permanentOtherThana + '\'' +
                ", permanentAddress='" + permanentAddress + '\'' +
                ", photo=" + photo +
                ", signature=" + signature +
                ", declaration=" + declaration +
                ", photoError='" + photoError + '\'' +
                ", signatureError='" + signatureError + '\'' +
                ", sscFileError='" + sscFileError + '\'' +
                ", hscFileError='" + hscFileError + '\'' +
                ", bscFileError='" + bscFileError + '\'' +
                ", mscFileError='" + mscFileError + '\'' +
                ", modalId='" + modalId + '\'' +
                '}';
    }

    public String getMscFileError() {
        return mscFileError;
    }

    public void setMscFileError(String mscFileError) {
        this.mscFileError = mscFileError;
    }

    public String getPhotoError() {
        return photoError;
    }

    public void setPhotoError(String photoError) {
        this.photoError = photoError;
    }

    public String getSignatureError() {
        return signatureError;
    }

    public void setSignatureError(String signatureError) {
        this.signatureError = signatureError;
    }

    public String getSscFileError() {
        return sscFileError;
    }

    public void setSscFileError(String sscFileError) {
        this.sscFileError = sscFileError;
    }

    public String getHscFileError() {
        return hscFileError;
    }

    public void setHscFileError(String hscFileError) {
        this.hscFileError = hscFileError;
    }

    public String getBscFileError() {
        return bscFileError;
    }

    public void setBscFileError(String bscFileError) {
        this.bscFileError = bscFileError;
    }


    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String pSemesterName) {
        semesterName = pSemesterName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getModalId() {
        return modalId;
    }

    public void setModalId(String modalId) {
        this.modalId = modalId;
    }

    public String getApplicationSerialNumber() {
        return applicationSerialNumber;
    }

    public void setApplicationSerialNumber(String applicationSerialNumber) {
        this.applicationSerialNumber = applicationSerialNumber;
    }

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

    public String getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(String pSemesterId) {
        semesterId = pSemesterId;
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

    public String getWorkExperienceDivId() {
        return workExperienceDivId;
    }

    public void setWorkExperienceDivId(String workExperienceDivId) {
        this.workExperienceDivId = workExperienceDivId;
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


    public String getPresentDivisionId() {
        return presentDivisionId;
    }

    public void setPresentDivisionId(String pPresentDivisionId) {
        presentDivisionId = pPresentDivisionId;
    }

    public String getPresentDistrictId() {
        return presentDistrictId;
    }

    public void setPresentDistrictId(String pPresentDistrictId) {
        presentDistrictId = pPresentDistrictId;
    }

    public String getPresentThanaId() {
        return presentThanaId;
    }

    public void setPresentThanaId(String pPresentThanaId) {
        presentThanaId = pPresentThanaId;
    }

    public String getPermanentDivisionId() {
        return permanentDivisionId;
    }

    public void setPermanentDivisionId(String pPermanentDivisionId) {
        permanentDivisionId = pPermanentDivisionId;
    }

    public String getPermanentDistrictId() {
        return permanentDistrictId;
    }

    public void setPermanentDistrictId(String pPermanentDistrictId) {
        permanentDistrictId = pPermanentDistrictId;
    }

    public String getPermanentThanaId() {
        return permanentThanaId;
    }

    public void setPermanentThanaId(String pPermanentThanaId) {
        permanentThanaId = pPermanentThanaId;
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

    public List<WorkExperienceList> getWorkExperienceList() {
        return workExperienceList;
    }

    public void setWorkExperienceList(List<WorkExperienceList> pWorkExperienceList) {
        workExperienceList = pWorkExperienceList;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

}
