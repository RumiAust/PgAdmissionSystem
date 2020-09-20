package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.Applicant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Monjur-E-Morshed on 9/13/2020.
 */
public interface ApplicantRepository extends CrudRepository<Applicant,Long> {

  @Query(value = "SELECT applicant_sn.nextval FROM dual", nativeQuery = true)
  Integer getApplicantSerialNo();
}
