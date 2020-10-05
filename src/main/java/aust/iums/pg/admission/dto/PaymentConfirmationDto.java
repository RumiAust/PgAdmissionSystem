package aust.iums.pg.admission.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PaymentConfirmationDto {
    private String transactionId;
    private String apiKey;
}
