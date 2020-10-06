package aust.iums.pg.admission.model;

import aust.iums.pg.admission.enums.PaymentCategory;
import aust.iums.pg.admission.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name="payment")
@Getter @Setter @NoArgsConstructor
public class Payment extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name="sequenceGenerator")
    private Long id;

    private String transactionId;

    private Instant transactionValidTill;

    @ManyToOne
    private Applicant applicant;

    @Enumerated(EnumType.ORDINAL)
    private PaymentCategory paymentCategory;

    private BigDecimal amount;

    private BigDecimal waivePercentage;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus status;

    private Instant paidOn;

}
