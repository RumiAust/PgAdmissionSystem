package aust.iums.pg.admission.controller.rest;

import aust.iums.pg.admission.dto.PaymentConfirmationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PaymentResource {

    @PostMapping("/payment/payment-confirmation")
    public ResponseEntity<Void> confirmAdmissionApplicationPayment(@RequestBody PaymentConfirmationDto paymentConfirmationDto) throws Exception{

        return ResponseEntity.noContent().build();
    }
}
