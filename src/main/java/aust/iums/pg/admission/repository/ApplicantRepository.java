package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Optional;

/**
 * Created by Monjur-E-Morshed on 9/13/2020.
 */
public interface ApplicantRepository extends JpaRepository<Applicant,Long> {

  @Query(value = "SELECT applicant_sn.nextval FROM dual", nativeQuery = true)
  Integer getApplicantSerialNo();

  @Query(value = "SELECT applicant_sn_bba.nextval FROM dual", nativeQuery = true)
  Integer getApplicantSerialNoForSoB();

  Optional<Applicant> findByApplicationSn(String serialNo);

  Optional<Applicant> findByApplicationSnAndApplicantPersonaIInfo_DateOfBirth(String pSerialNo, Date pDateOfBirth);
  Optional<Applicant> findByApplicationSnAndApplicantPersonaIInfo_ApplicationSn(String pSerialNo, String e);

}
