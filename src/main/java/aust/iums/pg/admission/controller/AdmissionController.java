package aust.iums.pg.admission.controller;

import aust.iums.pg.admission.pojo.ApplicationForm;
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

  @GetMapping("/apply")
  public String applicationForm(Model model) {
    ApplicationForm applicationForm = new ApplicationForm();
    /*List<String> professions= Arrays.asList("Software Engineer","Lecturer","System Engineer","Business Analyst");
    model.addAttribute("professionList",professions);*/
    model.addAttribute("applicant",applicationForm );
    return "application-form";
  }

  @PostMapping("/apply")
  public String greetingSubmit(@ModelAttribute ApplicationForm applicant, Model model) {
    model.addAttribute("applicant", applicant);
    String name = applicant.getFullName();
    /*int id=  greeting.getId();
    String name=greeting.getContent();*/
    return "form-view";
  }
}
