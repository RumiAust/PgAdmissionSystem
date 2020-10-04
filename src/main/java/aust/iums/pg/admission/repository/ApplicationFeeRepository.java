package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.ApplicationFee;
import aust.iums.pg.admission.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationFeeRepository extends JpaRepository<ApplicationFee, Long> {
    ApplicationFee getBySemester(Semester semester);
}
