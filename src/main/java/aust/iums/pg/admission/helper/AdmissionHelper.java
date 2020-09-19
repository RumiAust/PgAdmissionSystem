package aust.iums.pg.admission.helper;

import aust.iums.pg.admission.model.Program;
import aust.iums.pg.admission.model.Semester;
import aust.iums.pg.admission.repository.ProgramRepository;
import aust.iums.pg.admission.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Rumi on 9/17/2020.
 */
@Component
public class AdmissionHelper {

  @Autowired
  SemesterRepository mSemesterRepository;

  @Autowired
  ProgramRepository mProgramRepository;

  public List<Semester> getSemesters(){
     /* Semester s= new Semester();
    s.setIsActive(0);
    s.setSemesterId("110300");
    s.setSemesterName("Fall 18");
    mSemesterRepository.save(s);*/
    List<Semester> semesters= (List<Semester>) mSemesterRepository.findAll();
    Semester data=mSemesterRepository.findAllByIsActive(1);
    return semesters;
  }


  public List<Program> getPrograms(){
    List<Program> programList= (List<Program>) mProgramRepository.findAll();
    return programList;
  }

}
