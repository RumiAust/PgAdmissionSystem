package aust.iums.pg.admission.helper;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.enums.SemesterEnum;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.*;
import aust.iums.pg.admission.service.AdmissionService;
import aust.iums.pg.admission.service.ApplicationFormPdfGenerator;
import aust.iums.pg.admission.service.PgAdmissionMailService;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.PublicKey;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Rumi on 9/17/2020.
 */
@Component
public class AdmissionHelper {

  private final AdmissionService mAdmissionService;
  private final ApplicationFormPdfGenerator pApplicationFormPdfGenerator;
  private final ApplicationDeadlineRepository mApplicationDeadlineRepository;
  private final PgAdmissionMailService pgAdmissionMailService;

  public AdmissionHelper(AdmissionService mAdmissionService, @Lazy ApplicationFormPdfGenerator pApplicationFormPdfGenerator, ApplicationDeadlineRepository mApplicationDeadlineRepository, PgAdmissionMailService pgAdmissionMailService) {
    this.mAdmissionService = mAdmissionService;
    this.pApplicationFormPdfGenerator = pApplicationFormPdfGenerator;
    this.mApplicationDeadlineRepository = mApplicationDeadlineRepository;
    this.pgAdmissionMailService = pgAdmissionMailService;
  }



  public Semester getActiveSemester(){
    Semester semester=mAdmissionService.getSemesters(SemesterEnum.ACTIVE.getValue());
    return semester;
  }
  public Optional<Semester> getSemesterById(Long pSemesterId){
    Optional<Semester> semester=mAdmissionService.getSemesterById(pSemesterId);
    return semester;
  }
  public Optional<Program> getProgramById(Long pProgramId){
    Optional<Program> program=mAdmissionService.getProgramById(pProgramId);
    return program;
  }

  public ApplicationDeadline getDeadlineBy(Long pSemesterId,Long pProgramId){
    return mApplicationDeadlineRepository.findBySemesterIdAndProgramId(pSemesterId,pProgramId);
  }

  public List<Program> getAllPrograms(){
    List<Program> programList=mAdmissionService.getPrograms();
    return programList;
  }

  public List<Division> getAllDivisions(){
    List<Division> divisions = mAdmissionService.getDivisions();
    return divisions;
  }
  public List<District> getAllDistricts(){
    List<District> districts= mAdmissionService.getDistricts();
    return districts;
  }
  public List<District> getAllDistrictsByDivId(int divId){
    List<District> districts= mAdmissionService.getDistrictByDivId(divId);
    return districts;
  }

  public List<Thana> getAllThanas(){
    List<Thana> thanas= mAdmissionService.getThanas();
    return thanas;
  }
  public Optional<Thana> getThanaById(Long i) {
    Optional<Thana> thana = mAdmissionService.getThanaById(i);
    return thana;
  }

  public List<Thana> getAllThanasByDisId(int disId){
    List<Thana> thanas= mAdmissionService.getThanaByDisId(disId);
    return thanas;
  }

  public String saveInfo(ApplicationForm pApplicationForm) throws ParseException, IOException, Exception {
   String serialNo= mAdmissionService.save(pApplicationForm);
   return serialNo;
  }
  public Optional<Applicant> getApplicantBy(String pSerialNo, Date pDateOfBirth){
   return mAdmissionService.getDetailsBy(pSerialNo, pDateOfBirth);
  }

   public ByteArrayInputStream getApplicationFormPdf(String applicationSn, String dateOfBirth) throws IOException, DocumentException, ParseException {
    return pApplicationFormPdfGenerator.createApplicationForm(applicationSn, dateOfBirth);
   }

  @Async
  public void sendApplicantFormToApplicant(ApplicationForm applicant, String serialNo, String dateOfBirth) throws Exception{
    //Thread.sleep(10*1000);
    ByteArrayInputStream bis = getApplicationFormPdf(serialNo, dateOfBirth);
    pgAdmissionMailService.sendEmailFromTemplate(applicant,"Aust","Admission",bis);
  }

  public Optional<ApplicantPersonaIInfo> getInfoBy(String pSerialNo){
   return mAdmissionService.getPersonalInfoBy(pSerialNo);
  }
}
