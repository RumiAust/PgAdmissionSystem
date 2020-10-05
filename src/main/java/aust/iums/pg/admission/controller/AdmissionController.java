package aust.iums.pg.admission.controller;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.StatusCheckDto;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.enums.FileTypeEnum;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.service.AdmissionService;
import aust.iums.pg.admission.service.FileStorageService;
import aust.iums.pg.admission.service.PgAdmissionMailService;
import aust.iums.pg.admission.utils.PgUtils;
import com.itextpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.Instant;
import java.util.*;

/**
 * Created by Monjur-E-Morshed on 9/16/2020.
 */

@Controller
public class AdmissionController {
    private final FileStorageService fileStorageService;
    private final AdmissionHelper mHelper;
    private final PgAdmissionMailService mPgAdmissionMailService;
    private final AdmissionService admissionService;



    private final Logger log = LoggerFactory.getLogger(AdmissionController.class);
    Semester semester;
  List<Program> programsCheck;


    public AdmissionController(FileStorageService fileStorageService, AdmissionHelper mHelper, PgAdmissionMailService mPgAdmissionMailService, AdmissionService admissionService) {
        this.fileStorageService = fileStorageService;
        this.mHelper = mHelper;
        this.mPgAdmissionMailService = mPgAdmissionMailService;
        this.admissionService = admissionService;
    }

    @ModelAttribute("applicant")
    public ApplicationForm applicantModel() {
      // programsCheck.size()>0 "Form":"Not in Admission Slot";
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setWorkExperienceList(new ArrayList<>());
        applicationForm.setSerialList(new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
        applicationForm.setPointer(-1);
        for (int i = 0; i < 10; i++) {
            WorkExperienceList w = new WorkExperienceList();
            w.setVisbility(0);
            applicationForm.getWorkExperienceList().add(w);

        }
        applicationForm.setWorkExperienceDivId("");


        return applicationForm;
    }

    @ModelAttribute("semester")
    public Semester semesterModel() {
        semester = mHelper.getActiveSemester();
        return semester;
    }

    @ModelAttribute("religionList")
    public List<String> religionModel() {
        List<String> religions = Arrays.asList("BUDDHISM", "CHRISTIANITY", "HINDUISM", "ISLAM", "JAINISM", "JUDAISM", "OTHERS");
        return religions;
    }

    @ModelAttribute("passingYearList")
    public List<Integer> passingYearModel() {
        List<Integer> passingYearList = new ArrayList<>();
        for (Integer i = 2000; i < 2031; i++)
            passingYearList.add(i);
        return passingYearList;
    }

    @ModelAttribute("programList")
    public List<Program> programListModel() {
        List<Program> programs = mHelper.getAllPrograms();
         programsCheck= new ArrayList<>();
        //loop --programId,SemesterId--deadline validKina
        return programs;
    }

    @ModelAttribute("divisionList")
    public List<Division> divisionListModel() {
        List<Division> divisions = mHelper.getAllDivisions();
        return divisions;
    }

    @ModelAttribute("districtList")
    public List<District> districtListModel() {
        List<District> districts = mHelper.getAllDistricts();
        return districts;
    }

    @ModelAttribute("thanaList")
    public List<Thana> thanaListModel() {
        List<Thana> thanas = mHelper.getAllThanas();
        return thanas;
    }

    @GetMapping("/applicationForm")
    public String applicationForm(Model model) {
        return "application-form";
    }

    @PostMapping(value = "/apply", params = {"save"})
    public String greetingSubmit(@Valid @ModelAttribute("applicant") ApplicationForm applicant, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception {
        boolean otherErrors = isOtherErrors(applicant);
        if (bindingResult.hasErrors() || otherErrors) {
            log.error("errors: " + bindingResult.toString());
            applicant.setDeclaration(false);
            Boolean hasError = true;
            model.addAttribute("hasError",hasError);
            return "application-form";
        } else {
            addressMap(applicant);
            ApplicationDeadline deadline = mHelper.getDeadlineBy(Long.parseLong(applicant.getSemesterId()), Long.parseLong(applicant.getProgramId()));
            log.info(" [{}]: Applicant Infos ", applicant.toString());
            String serialNo = mHelper.saveInfo(applicant);
            applicant.setWorkExperienceDivId("");
            model.addAttribute("serialNo", serialNo);
            String toDate = PgUtils.instantFormatter(deadline.getToDate());
            model.addAttribute("deadline", toDate);

            String dob=applicant.getDateOfBirth();
            model.addAttribute("dateOfBirth",dob);
          //  mHelper.sendApplicantFormToApplicant(applicant, serialNo, dob);
            return "success-page";
        }

    }



    @GetMapping("/statusCheck")
    public String statusCheck(Model model) {
        StatusCheckDto app = new StatusCheckDto();
        model.addAttribute("applicantInfo", app);
        return "status-check";
    }

    @RequestMapping(value = "/downloadAppForm/{applicationSn}/{dateOfBirth}", method = RequestMethod.GET, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadAppForm(HttpServletResponse response, @PathVariable(name = "applicationSn") String applicationSn, @PathVariable(name = "dateOfBirth") String dateOfBirth) throws IOException, DocumentException, ParseException {
        ByteArrayInputStream bis = mHelper.getApplicationFormPdf(applicationSn, dateOfBirth);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=applicant_form.pdf");
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

         /*new StreamingOutput() {
            @Override
            public void write(OutputStream pOutputStream) throws WebApplicationException {

            }
        };*/
    }

  @RequestMapping(value = "/sendEmail/applicantNo/{applicationSn}/dateOfBirth/{dob}", method = RequestMethod.GET)
  public void sendEmail(@PathVariable(name = "applicationSn") String applicationSn, @PathVariable(name = "dob") String dateOfBirth) throws ParseException {
    System.out.println("hello world");
    Optional<ApplicantPersonaIInfo> personalInfo=mHelper.getInfoBy(applicationSn);
    if(personalInfo.isPresent()){
     /* if(personalInfo.get().getDateOfBirth().equals(PgUtils.formateDate(dateOfBirth))){

      }*/
      log.info("Sending emial.......");
    }

  }


    @PostMapping("/result")
    public String getResult(@ModelAttribute StatusCheckDto pStatusCheckDto, Model model) throws ParseException {
        try {
            if (pStatusCheckDto.getApplicationSerialNo() != null && pStatusCheckDto.getDateOfBirth() != null) {
                Date dateOfBirth = PgUtils.formateDate(pStatusCheckDto.getDateOfBirth());
                Optional<Applicant> applicant = mHelper.getApplicantBy(pStatusCheckDto.getApplicationSerialNo(), dateOfBirth);
                if (applicant.isPresent()) {
                    model.addAttribute("valid", true);
                    ApplicantPersonaIInfo applicantPersonaIInfo = applicant.get().getApplicantPersonaIInfo();
                    applicantPersonaIInfo.setMiddleName(applicantPersonaIInfo.getMiddleName() == null ? " " : applicantPersonaIInfo.getMiddleName());
                    applicantPersonaIInfo.setLastName(applicantPersonaIInfo.getLastName() == null ? " " : applicantPersonaIInfo.getLastName());
                    List<JobExperience> jobExperience = applicant.get().getJobExperience();
                    List<ApplicantEducationalInfo> educationalInfoList = applicant.get().getApplicantEducationalInfo();
                    List<ApplicantAddress> addressList = applicant.get().getApplicantAddresses();
                    model.addAttribute("applicantDetails", applicant);
                } else {
                    model.addAttribute("notFound", true);
                    model.addAttribute("msg", "No records found with Serial No: " + pStatusCheckDto.getApplicationSerialNo() + " and " +
                            "Date of Birth : " + pStatusCheckDto.getDateOfBirth());
                }
                model.addAttribute("hideText", "yes");
            } else {
                //  model.addAttribute("invalid","You must enter Application Serial No and Date of Birth");
                return "error";
            }

            return "status-check";
        } catch (Exception e) {
            log.error("Error :: " + e.getMessage());
            return "error";
        }

    }


    @PostMapping(value = "/getDistrict/{divId}", produces = "application/json")
    public @ResponseBody
    List<District>
    getDistrict(@PathVariable(name = "divId") String divId) {
        String pDivision[] = divId.split("-");
        int id = Integer.parseInt(pDivision[0]);
        List<District> districts = mHelper.getAllDistrictsByDivId(id);
        return districts;
    }

    @GetMapping(value = "/getThana/{disId}", produces = "application/json")
    public @ResponseBody
    List<Thana>
    getThana(@PathVariable(name = "disId") String disId) {
        String pDis[] = disId.split("-");
        int id = Integer.parseInt(pDis[0]);
        List<Thana> thanas = mHelper.getAllThanasByDisId(id);
        Optional<Thana> others = mHelper.getThanaById(Long.parseLong("9999"));
        thanas.add(others.get());
        return thanas;
    }


    private boolean isOtherErrors(ApplicationForm applicant) {
        boolean otherErrors = false;
        if (applicant.getPhoto().isEmpty()) {
            otherErrors = true;
            applicant.setPhotoError("Please select photo.");
        } else {
            try {
                fileStorageService.validate(applicant.getPhoto(), FileTypeEnum.PHOTO);
            } catch (Exception e) {
                otherErrors = true;
                log.error(e.getMessage());
                applicant.setPhotoError(e.getMessage());
            }

        }
        if (applicant.getSignature().isEmpty()) {
            otherErrors = true;
            applicant.setSignatureError("Please select signature.");
        } else {
            try {
                fileStorageService.validate(applicant.getSignature(), FileTypeEnum.SIGNATURE);
            } catch (Exception e) {
                otherErrors = true;
                log.error(e.getMessage());
                applicant.setSignatureError(e.getMessage());
            }

        }
        if (applicant.getSscFile().isEmpty()) {
            otherErrors = true;
            applicant.setSscFileError("Please select file.");
        } else {
            try {
                fileStorageService.validate(applicant.getSscFile(), FileTypeEnum.SSC);
            } catch (Exception e) {
                otherErrors = true;
                log.error(e.getMessage());
                applicant.setSscFileError(e.getMessage());
            }

        }
        if (applicant.getHscFile().isEmpty()) {
            otherErrors = true;
            applicant.setHscFileError("Please select file.");
        } else {
            try {
                fileStorageService.validate(applicant.getHscFile(), FileTypeEnum.HSC);
            } catch (Exception e) {
                otherErrors = true;
                log.error(e.getMessage());
                applicant.setHscFileError(e.getMessage());
            }

        }
        if (applicant.getBscFile().isEmpty()) {
            otherErrors = true;
            applicant.setBscFileError("Please select file.");
        } else {
            try {
                fileStorageService.validate(applicant.getBscFile(), FileTypeEnum.BSC);
            } catch (Exception e) {
                otherErrors = true;
                log.error(e.getMessage());
                applicant.setBscFileError(e.getMessage());
            }

        }
        if (!applicant.getMscFile().isEmpty()) {
            try {
                fileStorageService.validate(applicant.getMscFile(), FileTypeEnum.MSC);
            } catch (Exception e) {
                otherErrors = true;
                log.error(e.getMessage());
                applicant.setMscFileError(e.getMessage());
            }
        }

        if (applicant.getWorkExperienceList() != null) {
            for (WorkExperienceList data : applicant.getWorkExperienceList()) {
                if (data.getVisbility() == 1) {
                    if (data.getOrganizationName().isEmpty()) {
                        otherErrors = true;
                        data.setOrganizationNameError("Please select organization name.");
                    }
                    if (data.getDesignation().isEmpty()) {
                        otherErrors = true;
                        data.setDesignationError("Please select designation.");
                    }
                    if (data.getJobResponsibility().isEmpty()) {
                        otherErrors = true;
                        data.setJobResponsibilityError("Please select job responsibility.");
                    }
                    if (data.getFromDate().isEmpty()) {
                        otherErrors = true;
                        data.setFromDateError("Please select from date.");
                    }
                    if (data.getToDate().isEmpty() && !data.getCurrentlyWorking()) {
                        otherErrors = true;
                        data.setToDateError("Please select to date.");
                    }
                    if (!data.getExperienceFile().isEmpty()) {
                        try {
                            fileStorageService.validate(data.getExperienceFile(), FileTypeEnum.EXPERIENCE);
                        } catch (Exception e) {
                            otherErrors = true;
                            log.error(e.getMessage());
                            data.setExperienceFileError(e.getMessage());
                        }
                    } else {
                        otherErrors = true;
                        data.setExperienceFileError("Please select file.");
                    }
                }
            }
        }
        return otherErrors;
    }

    private void addressMap(ApplicationForm applicant) {
        String programInfo[] = applicant.getProgramId().split("-");
        applicant.setProgramId(programInfo[0]);
        applicant.setProgramName(programInfo[1]);
        applicant.setSemesterId(semester.getId().toString());

        String pDivision[] = applicant.getPresentDivisionId().split("-");
        applicant.setPresentDivisionId(pDivision[0]);
        applicant.setPresentDivision(pDivision[1]);
        String pDistrict[] = applicant.getPresentDistrictId().split("-");
        applicant.setPresentDistrictId(pDistrict[0]);
        applicant.setPresentDistrict(pDistrict[1]);
        if (applicant.getPresentOtherThana() == null) {
            String pThana[] = applicant.getPresentThanaId().split("-");
            applicant.setPresentThanaId(pThana[0]);
            applicant.setPresentThana(pThana[1]);
        } else {
            applicant.setPresentThanaId("9999");
            applicant.setPresentThana(applicant.getPresentOtherThana());
        }

        String perDivision[] = applicant.getPermanentDivisionId().split("-");
        applicant.setPermanentDivisionId(perDivision[0]);
        applicant.setPermanentDivision(perDivision[1]);
        String perDistrict[] = applicant.getPermanentDistrictId().split("-");
        applicant.setPermanentDistrictId(perDistrict[0]);
        applicant.setPermanentDistrict(perDistrict[1]);

        if (applicant.getPermanentOtherThana() == null) {
            String perThana[] = applicant.getPermanentThanaId().split("-");
            applicant.setPermanentThanaId(perThana[0]);
            applicant.setPermanentThana(perThana[1]);
        } else {
            applicant.setPresentThanaId("9999");
            applicant.setPresentThana(applicant.getPermanentOtherThana());
        }

      if(applicant.getPermanentOtherThana() ==null) {
        String perThana[] = applicant.getPermanentThanaId().split("-");
        applicant.setPermanentThanaId(perThana[0]);
        applicant.setPermanentThana(perThana[1]);
      }else {
        applicant.setPermanentThanaId("9999");
        applicant.setPermanentThana(applicant.getPermanentOtherThana());
      }

    }

    @PostMapping(value = "/apply", params = {"addRow"})
    public String addRow(@ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult, Model model) {
        applicationForm.getWorkExperienceList().add(new WorkExperienceList());
        applicationForm.setWorkExperienceDivId("workExperienceDivId");
        //addressMap(applicationForm);
        return "application-form";
    }

    @PostMapping(value = "/apply", params = {"removeRow"})
    public String removeRow(
            @ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        applicationForm.getWorkExperienceList().remove(rowId.intValue());
        applicationForm.setWorkExperienceDivId("workExperienceDivId");
        //  addressMap(applicationForm);
        return "application-form";
    }

    @PostMapping(value = "/apply", params = {"modal"})
    public String showModal(
            @ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult,
            final HttpServletRequest req) {
        applicationForm.setModalId("myModal");
        addressMap(applicationForm);
        return "application-form";
    }

}
