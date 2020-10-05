package aust.iums.pg.admission.controller.rest;

import aust.iums.pg.admission.dto.PaymentConfirmationDto;
import aust.iums.pg.admission.service.PaymentProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentResource {

    private final PaymentProcessingService paymentProcessingService;

    public PaymentResource(PaymentProcessingService paymentProcessingService) {
        this.paymentProcessingService = paymentProcessingService;
    }

    @PostMapping("/payment/payment-confirmation")
    public ResponseEntity<Void> confirmAdmissionApplicationPayment(@RequestBody PaymentConfirmationDto paymentConfirmationDto) throws Exception{
        paymentProcessingService.confirmAdmissionPayment(paymentConfirmationDto);
        return ResponseEntity.noContent().build();
    }
}
