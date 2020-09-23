package aust.iums.pg.admission.controller;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.SemesterRepository;
import aust.iums.pg.admission.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @PostMapping(value = "/apply")
    public String greetingSubmit(@ModelAttribute ApplicationForm applicant, Model model, RedirectAttributes redirectAttributes) throws IOException, ParseException {
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

    private void addressMap(ApplicationForm applicant) {
        String programInfo[] = applicant.getProgramId().split("-");
        applicant.setProgramId(programInfo[0]);
        applicant.setProgramName(programInfo[1]);
        applicant.setSemesterId(semester.getSemesterId());

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


    @PostMapping(value = "/apply", params = {"addRow"})
    public String addRow(@ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult, Model model) {
        applicationForm.getWorkExperienceList().add(new WorkExperienceList());
        applicationForm.setWorkExperienceDivId("workExperienceDivId");
        addressMap(applicationForm);
        return "application-form";
    }

    @PostMapping(value = "/apply", params = {"removeRow"})
    public String removeRow(
            @ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult,
            final HttpServletRequest req) {
        final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
        applicationForm.getWorkExperienceList().remove(rowId.intValue());
        applicationForm.setWorkExperienceDivId("workExperienceDivId");
        addressMap(applicationForm);
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
    public @ResponseBody List<District>
    getDistrict(@PathVariable(name = "divId") String divId) {
        String pDivision[] = divId.split("-");
        int id = Integer.parseInt(pDivision[0]);
        List<District> districts= mHelper.getAllDistrictsByDivId(id);
        return districts;
    }

    @GetMapping(value = "/getThana/{disId}", produces = "application/json")
    public @ResponseBody List<Thana>
    getThana(@PathVariable(name = "disId") String disId) {
        String pDis[] = disId.split("-");
        int id = Integer.parseInt(pDis[0]);
        List<Thana> thanas= mHelper.getAllThanasByDisId(id);
        return thanas;
    }


    @GetMapping("/statusCheck")
    public String statusCheck(Model model) {
      return "status-check";
    }

    @PostMapping("/result")
    public String getResult(@ModelAttribute ApplicationForm applicant, Model model){
    model.addAttribute("search","demo");
    model.addAttribute("valid",1);
      return "status-check";
  }
}
