package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.ApplicantEducationalInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Monjur-E-Morshed on 9/13/2020.
 */
public interface ApplicantEducationalInfoRepository extends JpaRepository<ApplicantEducationalInfo,Long> {
}
