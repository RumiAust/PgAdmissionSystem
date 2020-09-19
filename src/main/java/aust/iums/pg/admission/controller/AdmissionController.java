package aust.iums.pg.admission.controller;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.SemesterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Monjur-E-Morshed on 9/16/2020.
 */

@Controller
public class AdmissionController {
  @Autowired
  AdmissionHelper mHelper;

  private final Logger log = LoggerFactory.getLogger(AdmissionController.class);


  @GetMapping("/applicationForm")
  public String applicationForm(Model model) {
    ApplicationForm applicationForm = new ApplicationForm();
    List<Program> programs=mHelper.getPrograms();
    Semester semester =mHelper.getSemesters();
    List<Division> divisions=mHelper.getDivisions();
    List<District> districts=mHelper.getDistricts();
    List<Thana> thanas=mHelper.getThanas();
    model.addAttribute("semester",semester);
    model.addAttribute("programList",programs);
    model.addAttribute("divisionList",divisions);
    model.addAttribute("districtList",districts);
    model.addAttribute("thanaList",thanas);
    model.addAttribute("applicant",applicationForm);
    return "application-form";
  }

  @PostMapping("/apply")
  public String greetingSubmit(@ModelAttribute ApplicationForm applicant, Model model) {
    model.addAttribute("applicant", applicant);
    System.out.println(applicant);
    String name = applicant.getFullName();
    String program=applicant.getProgramId();
    String programInfo[]=applicant.getProgramId().split("-");
    applicant.setProgramId(programInfo[0]);
    applicant.setProgramName(programInfo[1]);
    log.info(" [{}]: Applicant Infos ",applicant.toString());
    /*int id=  greeting.getId();
    String name=greeting.getContent();*/
    return "form-view";
  }

  @GetMapping("/statusCheck")
  public String statusCheck(Model model) {
    return "status-check";
  }

}
