package aust.iums.pg.admission.controller;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.dto.WorkExperienceList;
import aust.iums.pg.admission.helper.AdmissionHelper;
import aust.iums.pg.admission.model.Program;
import aust.iums.pg.admission.model.Semester;
import aust.iums.pg.admission.repository.SemesterRepository;
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


  @GetMapping("/applicationForm")
  public String applicationForm(Model model) {
    ApplicationForm applicationForm = new ApplicationForm();
    List<Program> programs=mHelper.getPrograms();
    Semester semester =mHelper.getSemesters();
    model.addAttribute("semester",semester);
    model.addAttribute("programList",programs);
    model.addAttribute("applicant",applicationForm );
    return "application-form";
  }

  @PostMapping("/apply")
  public String greetingSubmit(@ModelAttribute ApplicationForm applicant, Model model) {
    model.addAttribute("applicant", applicant);
    String name = applicant.getFullName();
    String program=applicant.getProgramId();
    String programInfo[]=applicant.getProgramId().split("-");
    applicant.setProgramId(programInfo[0]);
    applicant.setProgramName(programInfo[1]);
    /*int id=  greeting.getId();
    String name=greeting.getContent();*/
    return "form-view";
  }

  @GetMapping("/statusCheck")
  public String statusCheck(Model model) {
    return "status-check";
  }

}
