package aust.iums.pg.admission.service;

import aust.iums.pg.admission.dto.AdmissionApplicantPaymentRequest;
import aust.iums.pg.admission.enums.PaymentCategory;
import aust.iums.pg.admission.enums.PaymentStatus;
import aust.iums.pg.admission.model.Applicant;
import aust.iums.pg.admission.model.ApplicationDeadline;
import aust.iums.pg.admission.model.ApplicationFee;
import aust.iums.pg.admission.model.Payment;
import aust.iums.pg.admission.repository.ApplicantRepository;
import aust.iums.pg.admission.repository.ApplicationDeadlineRepository;
import aust.iums.pg.admission.repository.ApplicationFeeRepository;
import aust.iums.pg.admission.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

@Service
@Transactional
public class PaymentProcessingService {
    @Value("${pgAdmission.iums.url}")
    private String iumsApi;

    private final ApplicantRepository applicantRepository;
    private final PaymentRepository paymentRepository;
    private final ApplicationDeadlineRepository applicationDeadlineRepository;
    private final ApplicationFeeRepository applicationFeeRepository;

    public PaymentProcessingService(ApplicantRepository applicantRepository, PaymentRepository paymentRepository, ApplicationDeadlineRepository applicationDeadlineRepository, ApplicationFeeRepository applicationFeeRepository) {
        this.applicantRepository = applicantRepository;
        this.paymentRepository = paymentRepository;
        this.applicationDeadlineRepository = applicationDeadlineRepository;
        this.applicationFeeRepository = applicationFeeRepository;
    }

    public void sendApplicantPaymentInformationToIUMS(final Applicant applicant){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("APIKEY", "PG_ADMISSION");

    }

    private AdmissionApplicantPaymentRequest prepareApplicationApplicationPaymentRequestData(final Applicant applicant){
        Payment applicationPayment = prepareApplicationPayment(applicant);
        AdmissionApplicantPaymentRequest paymentRequest = new AdmissionApplicantPaymentRequest();

        return paymentRequest;
    }

    private Payment prepareApplicationPayment(final Applicant applicant){
        Payment payment = new Payment();
        payment.setTransactionId(applicant.getApplicationSn());

        ApplicationDeadline applicationDeadline = applicationDeadlineRepository.findBySemesterIdAndProgramId(applicant.getSemester().getId(), applicant.getProgram().getId());
        payment.setTransactionValidTill(applicationDeadline.getToDate());
        payment.setApplicant(applicant);
        payment.setPaymentCategory(PaymentCategory.ADMISSION_APPLICATION);

        ApplicationFee applicationFee = applicationFeeRepository.getBySemester(applicant.getSemester());
        payment.setAmount(applicationFee.getAmount());
        payment.setStatus(PaymentStatus.NOT_PAID);
        return payment;
    }

}
