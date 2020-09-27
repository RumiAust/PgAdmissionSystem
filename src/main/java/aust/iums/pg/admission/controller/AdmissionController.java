package aust.iums.pg.admission.controller;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.StatusCheckDto;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.enums.FileTypeEnum;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.service.FileStorageService;
import aust.iums.pg.admission.utils.PgUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Monjur-E-Morshed on 9/16/2020.
 */

@Controller
public class AdmissionController {
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    AdmissionHelper mHelper;

    private final Logger log = LoggerFactory.getLogger(AdmissionController.class);
    Semester semester;

    @ModelAttribute("applicant")
    public ApplicationForm applicantModel() {
        ApplicationForm applicationForm = new ApplicationForm();
        applicationForm.setWorkExperienceList(new ArrayList<>());
        applicationForm.setWorkExperienceDivId("");
        return applicationForm;
    }

    @ModelAttribute("semester")
    public Semester semesterModel() {
        semester = mHelper.getActiveSemester();
        return semester;
    }

    @ModelAttribute("programList")
    public List<Program> programListModel() {
        List<Program> programs = mHelper.getAllPrograms();
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
    public String greetingSubmit(@Valid @ModelAttribute("applicant") ApplicationForm applicant, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws IOException, ParseException {


        boolean otherErrors = isOtherErrors(applicant);


        if (bindingResult.hasErrors() || otherErrors) {
            log.error("errors: " + bindingResult.toString());
            return "application-form";
        }


        model.addAttribute("applicant", applicant);
        addressMap(applicant);
        log.info(" [{}]: Applicant Infos ", applicant.toString());
        mHelper.saveInfo(applicant);
        applicant.setWorkExperienceDivId("");

        return "form-view";
   /*}catch (Exception e){
      redirectAttributes.addFlashAttribute("errormessage","Files are not saved successfully because "+e.getMessage());
      applicant.setWorkExperienceDivId("");
      return ""+e.getMessage();
    }*/
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
                log.error(e.getMessage());
                applicant.setBscFileError(e.getMessage());
            }

        }
        if (!applicant.getMscFile().isEmpty()) {
            try {
                fileStorageService.validate(applicant.getMscFile(), FileTypeEnum.MSC);
            } catch (Exception e) {
                log.error(e.getMessage());
                applicant.setMscFileError(e.getMessage());
            }
        }

        if (applicant.getWorkExperienceList() != null) {
            for (WorkExperienceList data : applicant.getWorkExperienceList()) {
                if (!data.getExperienceFile().isEmpty()) {
                    try {
                        fileStorageService.validate(data.getExperienceFile(), FileTypeEnum.EXPERIENCE);
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        data.setExperienceFileError(e.getMessage());
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
        String pThana[] = applicant.getPresentThanaId().split("-");
        applicant.setPresentThanaId(pThana[0]);
        applicant.setPresentThana(pThana[1]);

        String perDivision[] = applicant.getPermanentDivisionId().split("-");
        applicant.setPermanentDivisionId(perDivision[0]);
        applicant.setPermanentDivision(perDivision[1]);
        String perDistrict[] = applicant.getPermanentDistrictId().split("-");
        applicant.setPermanentDistrictId(perDistrict[0]);
        applicant.setPermanentDistrict(perDistrict[1]);
        String perThana[] = applicant.getPermanentThanaId().split("-");
        applicant.setPermanentThanaId(perThana[0]);
        applicant.setPermanentThana(perThana[1]);
    }


  @GetMapping("/statusCheck")
  public String statusCheck(Model model) {
    StatusCheckDto app= new StatusCheckDto();
    model.addAttribute("applicantInfo",app);
    return "status-check";
  }


    @PostMapping("/result")
    public String getResult(@ModelAttribute StatusCheckDto pStatusCheckDto, Model model) throws ParseException {
        try {
          if(pStatusCheckDto.getApplicationSerialNo() !=null && pStatusCheckDto.getDateOfBirth() !=null) {
            Date dateOfBirth=PgUtils.formateDate(pStatusCheckDto.getDateOfBirth());
            Optional<Applicant> applicant =mHelper.getApplicantBy(pStatusCheckDto.getApplicationSerialNo(), dateOfBirth);
            if (applicant.isPresent()){
              model.addAttribute("valid",true);
              ApplicantPersonaIInfo applicantPersonaIInfo=applicant.get().getApplicantPersonaIInfo();
             List<JobExperience> jobExperience= applicant.get().getJobExperience();
             List<ApplicantEducationalInfo> educationalInfoList=applicant.get().getApplicantEducationalInfo();
             List<ApplicantAddress> addressList=applicant.get().getApplicantAddresses();
             model.addAttribute("applicantDetails",applicant);
            }else{
              model.addAttribute("notFound",true);
              model.addAttribute("msg","No records found with Serial No: "+pStatusCheckDto.getApplicationSerialNo()+" and " +
                  "Date of Birth : "+pStatusCheckDto.getDateOfBirth());
            }
            model.addAttribute("hideText","yes");
          }else {
          //  model.addAttribute("invalid","You must enter Application Serial No and Date of Birth");
            return "error";
          }

          return "status-check";
      }catch (Exception e){
        log.error("Error :: "+e.getMessage());
          return "error";
      }

  }

}
