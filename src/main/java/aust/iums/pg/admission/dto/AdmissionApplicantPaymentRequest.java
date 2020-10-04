package aust.iums.pg.admission.dto;

import aust.iums.pg.admission.enums.FacultyType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Getter @Setter @NoArgsConstructor @ToString(includeFieldNames = true)
public class AdmissionApplicantPaymentRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String transactionId;
    private Date transactionValidTill;
    private BigDecimal amount;
    private String serialNo;
    private Integer semesterId;
    private FacultyType facultyType;
}
