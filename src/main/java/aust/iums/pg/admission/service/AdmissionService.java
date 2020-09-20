package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.enums.AdmissionEnum;
import aust.iums.pg.admission.enums.SemesterEnum;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    mApplicantRepository.save(applicant);
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
    DateFormat formatter;
    Date date;
    formatter = new SimpleDateFormat("yyyy-MM-dd");
    date = formatter.parse(pApp.getDateOfBirth());
    app.setDateOfBirth(date);
    app.setPlaceOfBirth("BD");
    app.setMobileNumber("1003");
    app.setEmailAddress(pApp.getEmail());
    app.setCreatedOn(Instant.now());

    mApplicantPersonalInfoRepository.save(app);

    List<ApplicantAddress> addressList= new ArrayList<>();
    ApplicantAddress address = new ApplicantAddress();
    address.setAddressType("PRESENT");
    address.setApplicationSn(applicantSerialNo);
    address.setDivisionId(Integer.parseInt(pApp.getPresentDivisionId()));
    address.setDistrictId(Integer.parseInt(pApp.getPresentDistrictId()));
    address.setThanaId(Integer.parseInt(pApp.getPresentThanaId()));
    address.setLine1(pApp.getPresentAddress());
    addressList.add(address);
    address = new ApplicantAddress();
    address.setAddressType("PERMANENT");
    address.setApplicationSn(applicantSerialNo);
    address.setDivisionId(Integer.parseInt(pApp.getPermanentDivisionId()));
    address.setDistrictId(Integer.parseInt(pApp.getPermanentDistrictId()));
    address.setThanaId(Integer.parseInt(pApp.getPermanentThanaId()));
    address.setLine1(pApp.getPermanentAddress());
    addressList.add(address);
    mApplicantAddressRepository.saveAll(addressList);

    List<JobExperience> workExperienceLists= new ArrayList<>();
    for(WorkExperienceList data: pApp.getWorkExperienceList()){
      JobExperience obj = new JobExperience();
      fileStorageService.saveFile(data.getExperienceFile(),"document");
      obj.setApplicationSn(applicantSerialNo);
      obj.setOrganizationName(data.getOrganizationName());
      obj.setDesignation(data.getDesignation());
      obj.setJobResponsibilities(data.getJobResponsibility());
      obj.setFromDate(formatter.parse(data.getFromDate()));
      obj.setToDate(formatter.parse(data.getToDate()));
      workExperienceLists.add(obj);
    }

     mJobExperienceRepository.saveAll(workExperienceLists);
    List<ApplicantEducationalInfo> educationalInfoList = new ArrayList<>();
    ApplicantEducationalInfo eduInfo = new ApplicantEducationalInfo();
    eduInfo.setExamType("SSC");



    //method create
   /* mApplicantRepository.save(applicant);

    mApplicantRepository.flush();*/
    /*applicant = mApplicantRepository.save(applicant);
    mApplicantRepository.flush();
    return applicant;*/
  }

}
