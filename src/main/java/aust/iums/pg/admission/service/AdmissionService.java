package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.enums.*;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.*;
import aust.iums.pg.admission.utils.PgUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

/**
 * Created by Monjur-E-Morshed on 9/17/2020.
 */

@Service
@Transactional
public class AdmissionService {

    private final SemesterRepository mSemesterRepository;
    private final ProgramRepository mProgramRepository;
    private final DivisionRepository mDivisionRepository;
    private final DistrictRepository mDistrictRepository;
    private final ThanaRepository mThanaRepository;
    private final ApplicantRepository mApplicantRepository;
    private final ApplicantPersonalInfoRepository mApplicantPersonalInfoRepository;
    private final ApplicantEducationalInfoRepository mApplicantEducationalInfoRepository;
    private final ApplicantAddressRepository mApplicantAddressRepository;
    private final JobExperienceRepository mJobExperienceRepository;
    private final FileStorageService fileStorageService;
    private final EntityManager entityManager;
    private final FileStorageRepository mFileStorageRepository;
    private final PaymentProcessingService paymentProcessingService;
    private final Environment environment;

    public AdmissionService(SemesterRepository mSemesterRepository, ProgramRepository mProgramRepository, DivisionRepository mDivisionRepository, DistrictRepository mDistrictRepository, ThanaRepository mThanaRepository, ApplicantRepository mApplicantRepository, ApplicantPersonalInfoRepository mApplicantPersonalInfoRepository, ApplicantEducationalInfoRepository mApplicantEducationalInfoRepository, ApplicantAddressRepository mApplicantAddressRepository, JobExperienceRepository mJobExperienceRepository, FileStorageService fileStorageService, EntityManager entityManager, FileStorageRepository mFileStorageRepository, PaymentProcessingService paymentProcessingService, Environment environment) {
        this.mSemesterRepository = mSemesterRepository;
        this.mProgramRepository = mProgramRepository;
        this.mDivisionRepository = mDivisionRepository;
        this.mDistrictRepository = mDistrictRepository;
        this.mThanaRepository = mThanaRepository;
        this.mApplicantRepository = mApplicantRepository;
        this.mApplicantPersonalInfoRepository = mApplicantPersonalInfoRepository;
        this.mApplicantEducationalInfoRepository = mApplicantEducationalInfoRepository;
        this.mApplicantAddressRepository = mApplicantAddressRepository;
        this.mJobExperienceRepository = mJobExperienceRepository;
        this.fileStorageService = fileStorageService;
        this.entityManager = entityManager;
        this.mFileStorageRepository = mFileStorageRepository;
        this.paymentProcessingService = paymentProcessingService;
        this.environment = environment;
    }

    public Semester getSemesters(Integer pStatus) {
        Semester semester = mSemesterRepository.findAllByIsActive(pStatus.intValue());
        return semester;
    }

    public Optional<Semester> getSemesterById(Long pId) {
        Optional<Semester> semester = mSemesterRepository.findById(pId);
        return semester;
    }

    public Optional<Program> getProgramById(Long pId) {
        Optional<Program> program = mProgramRepository.findById(pId);
        return program;
    }

    public List<Program> getPrograms() {
        List<Program> programList = (List<Program>) mProgramRepository.findAll();
        return programList;
    }

    public List<Division> getDivisions() {
        List<Division> divisions = (List<Division>) mDivisionRepository.findAll();
        return divisions;
    }

    public List<District> getDistricts() {
        List<District> districts = (List<District>) mDistrictRepository.findAll();
        return districts;
    }

    public List<Thana> getThanas() {
        List<Thana> thanas = (List<Thana>) mThanaRepository.findAll();
        return thanas;
    }

    public Optional<Thana> getThanaById(Long i) {
        Optional<Thana> thana = mThanaRepository.findById(i);
        return thana;
    }

    public List<District> getDistrictByDivId(int divId) {
        List<District> districts = mDistrictRepository.findByDivisionId(divId);
        return districts;
    }

    public List<Thana> getThanaByDisId(int disId) {
        List<Thana> thanas = mThanaRepository.findByDistrictId(disId);
        return thanas;
    }

    public Optional<Applicant> getDetailsBy(String pSerialNo, Date pDateOfBirth) {
        Optional<Applicant> info1 = mApplicantRepository.findByApplicationSn(pSerialNo);
        /* Optional<Applicant> infos=mApplicantRepository.findByApplicationSnAndApplicantPersonaIInfo_DateOfBirthAAndApplicantAddress(pSerialNo, pDateOfBirth,"1064");*/
        Optional<Applicant> info = mApplicantRepository.findByApplicationSnAndApplicantPersonaIInfo_DateOfBirth(pSerialNo, pDateOfBirth);
//      Optional<Applicant> info=mApplicantRepository.findByApplicationSnAndApplicantPersonaIInfo_ApplicationSn(pSerialNo,pSerialNo);
        //  ApplicantPersonaIInfo personaIInfo = info.get().getApplicantPersonaIInfo();
        return info;
    }

  public Optional<ApplicantPersonaIInfo> getPersonalInfoBy(String pSerialNo){
     return  mApplicantPersonalInfoRepository.findByApplicationSn(pSerialNo);
  }

    public String save(ApplicationForm pApp) throws Exception {
        String applicantSerialNo = mApplicantRepository.getApplicantSerialNo().toString();
        pApp.setApplicationSerialNumber(applicantSerialNo);
        Applicant applicant = new Applicant();
        applicant.setSemesterId(Long.parseLong(pApp.getSemesterId()));
        applicant.setProgramId(Long.parseLong(pApp.getProgramId()));
        applicant.setApplicationSn(applicantSerialNo);
        applicant.setStatus(AdmissionEnum.APPLIED.getLabel());
        applicant.setCreatedOn(Instant.now());
        applicant.setAppliedOn(Instant.now());
        applicant.setSelectedRejectedOn(Instant.now());
        applicant.setApplicationFeePaidOn(Instant.now());
        applicant = mApplicantRepository.saveAndFlush(applicant);
        entityManager.refresh(applicant);

        ApplicantPersonaIInfo app = new ApplicantPersonaIInfo();
        app.setApplicant(applicant);
        app.setFirstName(pApp.getFirstName());
        app.setMiddleName(pApp.getMiddleName());
        app.setLastName(pApp.getLastName());
        app.setFatherName(pApp.getFatherName());
        app.setMotherName(pApp.getMotherName());
        app.setApplicationSn(applicantSerialNo);
        app.setGender(pApp.getGender());
        app.setMaritalStatus(pApp.getMaritalStatus());
        app.setReligion(pApp.getReligion());
        app.setNationality(pApp.getNationality());
        app.setDateOfBirth(PgUtils.formateDate(pApp.getDateOfBirth()));
        app.setPlaceOfBirth(pApp.getPlaceOfBirth());
        app.setMobileNumber(pApp.getMobileNumber());
        app.setEmailAddress(pApp.getEmail());
        app.setCreatedOn(Instant.now());


        List<ApplicantAddress> addressList = new ArrayList<>();
        ApplicantAddress address = new ApplicantAddress();
        address.setApplicant(applicant);
        address.setAddressType(AddressTypeEnum.PRESENT.getLabel());
        address.setApplicationSn(applicantSerialNo);
        address.setDivisionId(Integer.parseInt(pApp.getPresentDivisionId()));
        address.setDistrictId(Integer.parseInt(pApp.getPresentDistrictId()));
        address.setThanaId(Integer.parseInt(pApp.getPresentThanaId()));
        address.setThanaOther(pApp.getPresentOtherThana());
        address.setLine1(pApp.getPresentAddress());
        addressList.add(address);
        address = new ApplicantAddress();
        address.setApplicant(applicant);
        address.setAddressType(AddressTypeEnum.PERMANENT.getLabel());
        address.setApplicationSn(applicantSerialNo);
        address.setDivisionId(Integer.parseInt(pApp.getPermanentDivisionId()));
        address.setDistrictId(Integer.parseInt(pApp.getPermanentDistrictId()));
        address.setThanaId(Integer.parseInt(pApp.getPermanentThanaId()));
        address.setThanaOther(pApp.getPermanentOtherThana());
        address.setLine1(pApp.getPermanentAddress());
        addressList.add(address);


        List<JobExperience> workExperienceLists = new ArrayList<>();
        int size = pApp.getWorkExperienceList().size();

        if (pApp.getWorkExperienceList().size() > 0) {
            for (WorkExperienceList data : pApp.getWorkExperienceList()) {
                if (data.getVisbility() == 1) {
                    JobExperience obj = new JobExperience();
                    //fileStorageService.saveFile(data.getExperienceFile(), "document", pApp, "exp");
                    obj.setApplicationSn(applicantSerialNo);
                    obj.setOrganizationName(data.getOrganizationName());
                    obj.setDesignation(data.getDesignation());
                    obj.setJobResponsibilities(data.getJobResponsibility());
                    obj.setFromDate(PgUtils.formateDate(data.getFromDate()));
                    if (!data.getCurrentlyWorking())
                        obj.setToDate(PgUtils.formateDate(data.getToDate()));
                    else
                        obj.setToDate(PgUtils.formateDate("0001-01-01"));
                    obj.setApplicant(applicant);
                    workExperienceLists.add(obj);
                }
            }
        }


        List<ApplicantEducationalInfo> educationalInfoList = new ArrayList<>();
        ApplicantEducationalInfo eduInfo = new ApplicantEducationalInfo();
        eduInfo.setApplicant(applicant);
        eduInfo.setExamType(ExamTypeEnum.SSC_EQU.name());
        eduInfo.setApplicationSn(applicantSerialNo);
        eduInfo.setInstituteName(pApp.getSscInstituteName());
        eduInfo.setBoard(pApp.getSscBoardName());
        eduInfo.setTotalMarks(pApp.getSscMarks());
        eduInfo.setDivisionClassGrade(pApp.getSscGrade());
        eduInfo.setPassingYear(pApp.getSscPassingYear());
        educationalInfoList.add(eduInfo);
        eduInfo = new ApplicantEducationalInfo();
        eduInfo.setApplicant(applicant);
        eduInfo.setExamType(ExamTypeEnum.HSC_EQU.name());
        eduInfo.setApplicationSn(applicantSerialNo);
        eduInfo.setInstituteName(pApp.getHscInstituteName());
        eduInfo.setBoard(pApp.getHscBoardName());
        eduInfo.setTotalMarks(pApp.getHscMarks());
        eduInfo.setDivisionClassGrade(pApp.getHscGrade());
        eduInfo.setPassingYear(pApp.getHscPassingYear());
        educationalInfoList.add(eduInfo);
        eduInfo = new ApplicantEducationalInfo();
        eduInfo.setApplicant(applicant);
        eduInfo.setExamType(ExamTypeEnum.BBA_BCOM_BSC_BA_EQU.name());
        eduInfo.setApplicationSn(applicantSerialNo);
        eduInfo.setInstituteName(pApp.getBscInstituteName());
        eduInfo.setBoard(pApp.getBscBoardName());
        eduInfo.setTotalMarks(pApp.getBscMarks());
        eduInfo.setDivisionClassGrade(pApp.getBscGrade());
        eduInfo.setPassingYear(pApp.getBscPassingYear());
        educationalInfoList.add(eduInfo);
        eduInfo = new ApplicantEducationalInfo();
        eduInfo.setApplicant(applicant);
        eduInfo.setExamType(ExamTypeEnum.MCOM_MA_MBA_MSC_EQU.name());
        eduInfo.setApplicationSn(applicantSerialNo);
        eduInfo.setInstituteName(pApp.getMscInstituteName());
        eduInfo.setBoard(pApp.getMscBoardName());
        eduInfo.setTotalMarks(pApp.getMscMarks());
        eduInfo.setDivisionClassGrade(pApp.getMscGrade());
        eduInfo.setPassingYear(pApp.getMscPassingYear());
        educationalInfoList.add(eduInfo);


        mApplicantPersonalInfoRepository.saveAndFlush(app);
        entityManager.refresh(app);

      /*  mApplicantEducationalInfoRepository.saveAll(educationalInfoList);
        mApplicantEducationalInfoRepository.flush();
        educationalInfoList.forEach(entityManager::refresh);*/

        String path = "";

        //Save Educational Info
        ApplicantEducationalInfo applicantEducationalInfo = mApplicantEducationalInfoRepository.saveAndFlush(educationalInfoList.get(0));
        entityManager.refresh(educationalInfoList.get(0));
        if (!pApp.getSscFile().isEmpty()) {
            path = fileStorageService.saveFile(pApp.getSscFile(), -1, pApp, FileTypeEnum.SSC);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setApplicationSn(applicantSerialNo);
            fileStorage.setFileType(FileTypeEnum.SSC.toString());
            if (path.length() > 251)
                fileStorage.setFilePath(path.substring(0, 250));
            else fileStorage.setFilePath(path);
            fileStorage.setRefId(applicantEducationalInfo.getId());
            fileStorage.setCreatedOn(Instant.now());
            mFileStorageRepository.saveAndFlush(fileStorage);
            entityManager.refresh(fileStorage);

            if(Arrays.stream(environment.getActiveProfiles()).anyMatch(env-> env.equalsIgnoreCase("prod")))
                paymentProcessingService.sendApplicantPaymentInformationToIUMS(applicant);
        }


        applicantEducationalInfo = mApplicantEducationalInfoRepository.saveAndFlush(educationalInfoList.get(1));
        entityManager.refresh(educationalInfoList.get(1));
        if (!pApp.getHscFile().isEmpty()) {
            path = fileStorageService.saveFile(pApp.getHscFile(), -1, pApp, FileTypeEnum.HSC);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setApplicationSn(applicantSerialNo);
            fileStorage.setFileType(FileTypeEnum.HSC.toString());
            if (path.length() > 251)
                fileStorage.setFilePath(path.substring(0, 250));
            else fileStorage.setFilePath(path);
            fileStorage.setRefId(applicantEducationalInfo.getId());
            fileStorage.setCreatedOn(Instant.now());
            mFileStorageRepository.saveAndFlush(fileStorage);
            entityManager.refresh(fileStorage);
        }


        applicantEducationalInfo = mApplicantEducationalInfoRepository.saveAndFlush(educationalInfoList.get(2));
        entityManager.refresh(educationalInfoList.get(2));
        if (!pApp.getBscFile().isEmpty()) {
            path = fileStorageService.saveFile(pApp.getBscFile(), -1, pApp, FileTypeEnum.BSC);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setApplicationSn(applicantSerialNo);
            fileStorage.setFileType(FileTypeEnum.BSC.toString());
            if (path.length() > 251)
                fileStorage.setFilePath(path.substring(0, 250));
            else fileStorage.setFilePath(path);
            fileStorage.setRefId(applicantEducationalInfo.getId());
            fileStorage.setCreatedOn(Instant.now());
            mFileStorageRepository.saveAndFlush(fileStorage);
            entityManager.refresh(fileStorage);
        }

        applicantEducationalInfo = mApplicantEducationalInfoRepository.saveAndFlush(educationalInfoList.get(3));
        entityManager.refresh(educationalInfoList.get(3));
        if (!pApp.getMscFile().isEmpty()) {
            path=fileStorageService.saveFile(pApp.getMscFile(), -1, pApp, FileTypeEnum.MSC);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setApplicationSn(applicantSerialNo);
            fileStorage.setFileType(FileTypeEnum.MSC.toString());
            if (path.length() > 251)
                fileStorage.setFilePath(path.substring(0, 250));
            else fileStorage.setFilePath(path);
            fileStorage.setRefId(applicantEducationalInfo.getId());
            fileStorage.setCreatedOn(Instant.now());
            mFileStorageRepository.saveAndFlush(fileStorage);
            entityManager.refresh(fileStorage);
        }




        /*if (workExperienceLists.size() > 0) {
            mJobExperienceRepository.saveAll(workExperienceLists);
            mJobExperienceRepository.flush();
            workExperienceLists.forEach(entityManager::refresh);
        }*/

        //Save Job Experience
        if (pApp.getWorkExperienceList() != null) {
            int index = 0;
            for (WorkExperienceList data : pApp.getWorkExperienceList()) {
                if (!data.getExperienceFile().isEmpty() && data.getVisbility() == 1) {
                    JobExperience jobExperience = mJobExperienceRepository.saveAndFlush(workExperienceLists.get(index));
                    entityManager.refresh(workExperienceLists.get(index));

                    path = fileStorageService.saveFile(data.getExperienceFile(), index, pApp, FileTypeEnum.EXPERIENCE);
                    FileStorage fileStorage = new FileStorage();
                    fileStorage.setApplicationSn(applicantSerialNo);
                    fileStorage.setFileType(FileTypeEnum.EXPERIENCE.toString());
                    if (path.length() > 251)
                        fileStorage.setFilePath(path.substring(0, 250));
                    else fileStorage.setFilePath(path);

                    fileStorage.setRefId(jobExperience.getId());
                    fileStorage.setCreatedOn(Instant.now());
                    mFileStorageRepository.saveAndFlush(fileStorage);
                    entityManager.refresh(fileStorage);

                }
                index++;
            }
        }

        mApplicantAddressRepository.saveAll(addressList);
        mApplicantAddressRepository.flush();
        addressList.forEach(entityManager::refresh);

        //Save Photo
        if (!pApp.getPhoto().isEmpty()) {
            path=fileStorageService.saveFile(pApp.getPhoto(), -1, pApp, FileTypeEnum.PHOTO);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setApplicationSn(applicantSerialNo);
            fileStorage.setFileType(FileTypeEnum.PHOTO.toString());
            if (path.length() > 251)
                fileStorage.setFilePath(path.substring(0, 250));
            else fileStorage.setFilePath(path);
            fileStorage.setCreatedOn(Instant.now());
            mFileStorageRepository.saveAndFlush(fileStorage);
            entityManager.refresh(fileStorage);
        }
        //Save signature
        if (!pApp.getSignature().isEmpty()) {
            path=fileStorageService.saveFile(pApp.getSignature(), -1, pApp, FileTypeEnum.SIGNATURE);
            FileStorage fileStorage = new FileStorage();
            fileStorage.setApplicationSn(applicantSerialNo);
            fileStorage.setFileType(FileTypeEnum.SIGNATURE.toString());
            if (path.length() > 251)
                fileStorage.setFilePath(path.substring(0, 250));
            else fileStorage.setFilePath(path);
            fileStorage.setCreatedOn(Instant.now());
            mFileStorageRepository.saveAndFlush(fileStorage);
            entityManager.refresh(fileStorage);
        }
        return applicantSerialNo;
    }


}
