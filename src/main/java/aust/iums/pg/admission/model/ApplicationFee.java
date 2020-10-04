package aust.iums.pg.admission.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="application_fee")
@Getter @Setter @NoArgsConstructor @ToString
public class ApplicationFee extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    private Long id;

    private BigDecimal amount;

    @ManyToOne(optional = false)
    @JoinColumn(insertable = false, updatable = false)
    private Semester semester;

}
