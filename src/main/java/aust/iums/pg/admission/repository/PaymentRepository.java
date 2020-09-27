package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
