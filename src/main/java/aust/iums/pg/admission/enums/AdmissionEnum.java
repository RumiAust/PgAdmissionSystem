package aust.iums.pg.admission.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monjur-E-Morshed on 9/20/2020.
 */
public enum AdmissionEnum {

  APPLIED(1, "APPLIED"),
  PAYMENT_COMPLETED(2, "PAYMENT COMPLETED"),
  ADMISSION_OFFERED(3, "ADMISSION_OFFERED");

  private static final Map<Integer, AdmissionEnum> Lookup = new HashMap<>();
  private static final Map<String, AdmissionEnum> lookUpByLabel = new HashMap<>();

  static {
    for(AdmissionEnum c : EnumSet.allOf(AdmissionEnum.class)) {
      Lookup.put(c.typeCode, c);
      lookUpByLabel.put(c.getLabel(), c);
    }
  }

  private Integer typeCode;
  private String label;

  private AdmissionEnum(Integer pTypeCode, String pLabel) {
    this.typeCode = pTypeCode;
    this.label = pLabel;
  }

  public static AdmissionEnum get(final Integer pTypeCode) {
    return Lookup.get(pTypeCode);
  }

  public static AdmissionEnum getByLabel(final String pLabel) {
    return lookUpByLabel.get(pLabel);
  }

  public Integer getValue() {
    return this.typeCode;
  }

  public String getLabel() {
    return this.label;
  }
}
