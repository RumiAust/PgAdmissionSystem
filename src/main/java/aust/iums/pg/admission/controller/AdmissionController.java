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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

  @ModelAttribute("applicant")
  public ApplicationForm applicantModel(){
    ApplicationForm applicationForm = new ApplicationForm();
    applicationForm.setWorkExperienceList(new ArrayList<>());
    applicationForm.setWorkExperienceDivId("");
    return applicationForm;
  }
  @ModelAttribute("semester")
  public Semester semesterModel(){
    Semester semester =mHelper.getActiveSemester();
    return semester;
  }
  @ModelAttribute("programList")
  public List<Program> programListModel(){
    List<Program> programs=mHelper.getAllPrograms();
    return programs;
  }
  @ModelAttribute("divisionList")
  public List<Division> divisionListModel(){
    List<Division> divisions=mHelper.getAllDivisions();
    return divisions;
  }
  @ModelAttribute("districtList")
  public List<District> districtListModel(){
    List<District> districts=mHelper.getAllDistricts();
    return districts;
  }
  @ModelAttribute("thanaList")
  public List<Thana> thanaListModel(){
    List<Thana> thanas=mHelper.getAllThanas();
    return thanas;
  }

  @GetMapping("/applicationForm")
  public String applicationForm(Model model) {

    /*ApplicationForm applicationForm = new ApplicationForm();
    applicationForm.setWorkExperienceList(new ArrayList<>());
    model.addAttribute("applicant",applicationForm);*/
    /*List<Program> programs=mHelper.getAllPrograms();
    Semester semester =mHelper.getActiveSemester();
    List<Division> divisions=mHelper.getAllDivisions();
    List<District> districts=mHelper.getAllDistricts();
    List<Thana> thanas=mHelper.getAllThanas();
    model.addAttribute("semester",semester);
    model.addAttribute("programList",programs);
    model.addAttribute("divisionList",divisions);
    model.addAttribute("districtList",districts);
    model.addAttribute("thanaList",thanas);*/
    return "application-form";
  }

  @PostMapping(value = "/apply", params = {"save"})
  public String greetingSubmit(@ModelAttribute ApplicationForm applicant, Model model, RedirectAttributes redirectAttributes) {
    /*model.addAttribute("applicant", applicant);
    String name = applicant.getFullName();
    String program=applicant.getProgramId();
    String programInfo[]=applicant.getProgramId().split("-");
    applicant.setProgramId(programInfo[0]);
    applicant.setProgramName(programInfo[1]);
    log.info(" [{}]: Applicant Infos ",applicant.toString());*/

    try {
      fileStorageService.saveFile(applicant.getPhoto(),"photo");
      fileStorageService.saveFile(applicant.getSignature(),"signature");
      applicant.setWorkExperienceDivId("");
      redirectAttributes.addFlashAttribute("successmessage","Files are saved successfully");
      return "redirect:/applicationForm";
    }catch (Exception e){
      redirectAttributes.addFlashAttribute("errormessage","Files are not saved successfully because "+e.getMessage());
      applicant.setWorkExperienceDivId("");
      return "application-form";
    }

    /*int id=  greeting.getId();
    String name=greeting.getContent();*/
  }


  @PostMapping(value="/apply", params={"addRow"})
  public String addRow(@ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult,Model model) {
    applicationForm.getWorkExperienceList().add(new WorkExperienceList());
    applicationForm.setWorkExperienceDivId("workExperienceDivId");
    return "application-form";
  }

  @PostMapping(value="/apply", params={"removeRow"})
  public String removeRow(
          @ModelAttribute("applicant") ApplicationForm applicationForm, final BindingResult bindingResult,
          final HttpServletRequest req) {
    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
    applicationForm.getWorkExperienceList().remove(rowId.intValue());
    applicationForm.setWorkExperienceDivId("workExperienceDivId");
    return "application-form";
  }


  @GetMapping("/statusCheck")
  public String statusCheck(Model model) {
    return "status-check";
  }

}
