package aust.iums.pg.admission.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Monjur-E-Morshed on 9/16/2020.
 */

@Controller
public class AdmissionController {

  @GetMapping("/apply")
  public String ifElseForm(Model model) {
    return "application-form";
  }
}
