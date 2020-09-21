package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.enums.AddressTypeEnum;
import aust.iums.pg.admission.enums.AdmissionEnum;
import aust.iums.pg.admission.enums.ExamTypeEnum;
import aust.iums.pg.admission.enums.SemesterEnum;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.*;
import aust.iums.pg.admission.utils.PgUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Monjur-E-Morshed on 9/17/2020.
 */

@Service
public class AdmissionService {

  @Autowired
  SemesterRepository mSemesterRepository;

  @Autowired
  ProgramRepository mProgramRepository;

  @Autowired
  DivisionRepository mDivisionRepository;

  @Autowired
  DistrictRepository mDistrictRepository;

  @Autowired
  ThanaRepository mThanaRepository;

  @Autowired
  ApplicantRepository mApplicantRepository;

  @Autowired
  ApplicantPersonalInfoRepository mApplicantPersonalInfoRepository;

  @Autowired
  ApplicantEducationalInfoRepository mApplicantEducationalInfoRepository;

  @Autowired
  ApplicantAddressRepository mApplicantAddressRepository;

  @Autowired
  JobExperienceRepository mJobExperienceRepository;

  @Autowired
  FileStorageService fileStorageService;

  public Semester getSemesters(Integer pStatus){
    Semester semester=mSemesterRepository.findAllByIsActive(pStatus.intValue());
    return semester;
  }

  public List<Program> getPrograms(){
    List<Program> programList= (List<Program>) mProgramRepository.findAll();
    return programList;
  }

  public List<Division> getDivisions(){
    List<Division> divisions = (List<Division>) mDivisionRepository.findAll();
    return divisions;
  }

  public List<District> getDistricts(){
    List<District> districts= (List<District>) mDistrictRepository.findAll();
    return districts;
  }

  public List<Thana> getThanas(){
    List<Thana> thanas= (List<Thana>) mThanaRepository.findAll();
    return thanas;
  }

  public void save(ApplicationForm pApp) throws ParseException, IOException {
   String applicantSerialNo= mApplicantRepository.getApplicantSerialNo().toString();
   Applicant applicant= new Applicant();
   applicant.setSemesterId(pApp.getSemesterId());
   applicant.setProgramId(pApp.getProgramId());
   applicant.setApplicationSn(applicantSerialNo);
   applicant.setStatus(AdmissionEnum.APPLIED.getLabel());
   applicant.setCreatedOn(Instant.now());
   applicant.setAppliedOn(Instant.now());
   applicant.setSelectedRejectedOn(Instant.now());
   applicant.setApplicationFeePaidOn(Instant.now());

    ApplicantPersonaIInfo app= new ApplicantPersonaIInfo();
    app.setFirstName(pApp.getFatherName());
    app.setMiddleName(pApp.getFullName());
    app.setLastName(pApp.getFullName());
    app.setFatherName(pApp.getFatherName());
    app.setMotherName(pApp.getMotherName());
    app.setApplicationSn(applicantSerialNo);
    app.setGender(pApp.getGender());
    app.setMaritalStatus(pApp.getMaritalStatus());
    app.setReligion(pApp.getReligion());
    app.setNationality(pApp.getNationality());
    app.setDateOfBirth(PgUtils.formateDate(pApp.getDateOfBirth()));
    app.setPlaceOfBirth("BD");
    app.setMobileNumber("1003");
    app.setEmailAddress(pApp.getEmail());
    app.setCreatedOn(Instant.now());


    List<ApplicantAddress> addressList= new ArrayList<>();
    ApplicantAddress address = new ApplicantAddress();
    address.setAddressType(AddressTypeEnum.PRESENT.getLabel());
    address.setApplicationSn(applicantSerialNo);
    address.setDivisionId(Integer.parseInt(pApp.getPresentDivisionId()));
    address.setDistrictId(Integer.parseInt(pApp.getPresentDistrictId()));
    address.setThanaId(Integer.parseInt(pApp.getPresentThanaId()));
    address.setLine1(pApp.getPresentAddress());
    addressList.add(address);
    address = new ApplicantAddress();
    address.setAddressType(AddressTypeEnum.PERMANENT.getLabel());
    address.setApplicationSn(applicantSerialNo);
    address.setDivisionId(Integer.parseInt(pApp.getPermanentDivisionId()));
    address.setDistrictId(Integer.parseInt(pApp.getPermanentDistrictId()));
    address.setThanaId(Integer.parseInt(pApp.getPermanentThanaId()));
    address.setLine1(pApp.getPermanentAddress());
    addressList.add(address);


    List<JobExperience> workExperienceLists= new ArrayList<>();
    for(WorkExperienceList data: pApp.getWorkExperienceList()){
      JobExperience obj = new JobExperience();
      fileStorageService.saveFile(data.getExperienceFile(),"document");
      obj.setApplicationSn(applicantSerialNo);
      obj.setOrganizationName(data.getOrganizationName());
      obj.setDesignation(data.getDesignation());
      obj.setJobResponsibilities(data.getJobResponsibility());
      obj.setFromDate(PgUtils.formateDate(data.getFromDate()));
      obj.setToDate(PgUtils.formateDate(data.getToDate()));
      workExperienceLists.add(obj);
    }


    List<ApplicantEducationalInfo> educationalInfoList = new ArrayList<>();
    ApplicantEducationalInfo eduInfo = new ApplicantEducationalInfo();
    eduInfo.setExamType(ExamTypeEnum.SSC_EQU.name());
    eduInfo.setApplicationSn(applicantSerialNo);
    eduInfo.setInstituteName(pApp.getSscInstituteName());
    eduInfo.setBoard(pApp.getSscBoardName());
    eduInfo.setTotalMarks(pApp.getSscMarks());
    eduInfo.setDivisionClassGrade(pApp.getSscGrade());
    eduInfo.setPassingYear(pApp.getSscPassingYear());
    educationalInfoList.add(eduInfo);
    eduInfo = new ApplicantEducationalInfo();
    eduInfo.setExamType(ExamTypeEnum.HSC_EQU.name());
    eduInfo.setApplicationSn(applicantSerialNo);
    eduInfo.setInstituteName(pApp.getHscInstituteName());
    eduInfo.setBoard(pApp.getHscBoardName());
    eduInfo.setTotalMarks(pApp.getHscMarks());
    eduInfo.setDivisionClassGrade(pApp.getHscGrade());
    eduInfo.setPassingYear(pApp.getHscPassingYear());
    educationalInfoList.add(eduInfo);
    eduInfo = new ApplicantEducationalInfo();
    eduInfo.setExamType(ExamTypeEnum.BBA_BCOM_BSC_BA_EQU.name());
    eduInfo.setApplicationSn(applicantSerialNo);
    eduInfo.setInstituteName(pApp.getBscInstituteName());
    eduInfo.setBoard(pApp.getBscBoardName());
    eduInfo.setTotalMarks(pApp.getBscMarks());
    eduInfo.setDivisionClassGrade(pApp.getBscGrade());
    eduInfo.setPassingYear(pApp.getBscPassingYear());
    educationalInfoList.add(eduInfo);
    eduInfo = new ApplicantEducationalInfo();
    eduInfo.setExamType(ExamTypeEnum.MCOM_MA_MBA_MSC_EQU.name());
    eduInfo.setApplicationSn(applicantSerialNo);
    eduInfo.setInstituteName(pApp.getMscInstituteName());
    eduInfo.setBoard(pApp.getMscBoardName());
    eduInfo.setTotalMarks(pApp.getMscMarks());
    eduInfo.setDivisionClassGrade(pApp.getMscGrade());
    eduInfo.setPassingYear(pApp.getMscPassingYear());
    educationalInfoList.add(eduInfo);


    List<MultipartFile> eduFile=new ArrayList<>();
    eduFile.add(pApp.getSscFile());
    eduFile.add(pApp.getHscFile());
    eduFile.add(pApp.getBscFile());
    eduFile.add(pApp.getMscFile());

    applicant = mApplicantRepository.save(applicant);
    //mApplicantRepository.saveAndFlush(applicant);
    app.setApplicant(applicant);
    mApplicantPersonalInfoRepository.save(app);
    mApplicantEducationalInfoRepository.saveAll(educationalInfoList);
    mJobExperienceRepository.saveAll(workExperienceLists);
    mApplicantAddressRepository.saveAll(addressList);


    for(MultipartFile file:eduFile){
      if (!file.isEmpty()){
        fileStorageService.saveFile(file,"document");
      }
    }
    fileStorageService.saveFile(pApp.getPhoto(),"photo");
    fileStorageService.saveFile(pApp.getSignature(),"signature");

  }

}
