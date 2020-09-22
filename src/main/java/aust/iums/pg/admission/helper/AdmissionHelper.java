package aust.iums.pg.admission.helper;

import aust.iums.pg.admission.dto.ApplicationForm;
import aust.iums.pg.admission.enums.SemesterEnum;
import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.*;
import aust.iums.pg.admission.service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

/**
 * Created by Rumi on 9/17/2020.
 */
@Component
public class AdmissionHelper {

  @Autowired
  AdmissionService mAdmissionService;

  public AdmissionHelper(AdmissionService pAdmissionService) {
    this.mAdmissionService = pAdmissionService;
  }

  public Semester getActiveSemester(){
    Semester semester=mAdmissionService.getSemesters(SemesterEnum.ACTIVE.getValue());
    return semester;
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

  public List<Thana> getAllThanasByDisId(int disId){
    List<Thana> thanas= mAdmissionService.getThanaByDisId(disId);
    return thanas;
  }

  public void saveInfo(ApplicationForm pApplicationForm) throws ParseException, IOException {
    mAdmissionService.save(pApplicationForm);
  }

}
