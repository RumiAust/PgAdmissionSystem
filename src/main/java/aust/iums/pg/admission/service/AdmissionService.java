package aust.iums.pg.admission.service;

import aust.iums.pg.admission.model.*;
import aust.iums.pg.admission.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Monjur-E-Morshed on 9/17/2020.
 */

@Service
public class AdmissionService {
  @Autowired
  SemesterRepository mSemesterRepository;

  @Autowired
  ProgramRepository mProgramRepository;

  @Autowired
  DivisionRepository mDivisionRepository;

  @Autowired
  DistrictRepository mDistrictRepository;

  @Autowired
  ThanaRepository mThanaRepository;

  public Semester getSemesters(Integer pStatus){
    Semester semester=mSemesterRepository.findAllByIsActive(pStatus.intValue());
    return semester;
  }


  public List<Program> getPrograms(){
    List<Program> programList= (List<Program>) mProgramRepository.findAll();
    return programList;
  }

  public List<Division> getDivisions(){
    List<Division> divisions = (List<Division>) mDivisionRepository.findAll();
    return divisions;
  }
  public List<District> getDistricts(){
    List<District> districts= (List<District>) mDistrictRepository.findAll();
    return districts;
  }
  public List<Thana> getThanas(){
    List<Thana> thanas= (List<Thana>) mThanaRepository.findAll();
    return thanas;
  }
}
