package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.JobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Monjur-E-Morshed on 9/13/2020.
 */
public interface JobExperienceRepository extends JpaRepository<JobExperience,Long> {
}
