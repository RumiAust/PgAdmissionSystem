package aust.iums.pg.admission.service;

import aust.iums.pg.admission.model.Applicant;
import aust.iums.pg.admission.repository.ApplicantRepository;
import aust.iums.pg.admission.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentProcessingService {
    private final ApplicantRepository applicantRepository;
    private final PaymentRepository paymentRepository;

    public PaymentProcessingService(ApplicantRepository applicantRepository, PaymentRepository paymentRepository) {
        this.applicantRepository = applicantRepository;
        this.paymentRepository = paymentRepository;
    }

    public void sendApplicantPaymentInformationToIUMS(Applicant applicant){

    }

}
