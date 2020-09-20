package aust.iums.pg.admission.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rumi on 9/20/2020.
 */
public enum SemesterEnum {
  SSC_BANGLA(0, "INACTIVE"),
  SSC_ENGLISH(1, "ACTIVE");

  private static final Map<Integer, SemesterEnum> Lookup = new HashMap<>();
  private static final Map<String, SemesterEnum> lookUpByLabel = new HashMap<>();

  static {
    for(SemesterEnum c : EnumSet.allOf(SemesterEnum.class)) {
      Lookup.put(c.typeCode, c);
      lookUpByLabel.put(c.getLabel(), c);
    }
  }

  private Integer typeCode;
  private String label;

  private SemesterEnum(Integer pTypeCode, String pLabel) {
    this.typeCode = pTypeCode;
    this.label = pLabel;
  }

  public static SemesterEnum get(final Integer pTypeCode) {
    return Lookup.get(pTypeCode);
  }

  public static SemesterEnum getByLabel(final String pLabel) {
    return lookUpByLabel.get(pLabel);
  }

  public Integer getValue() {
    return this.typeCode;
  }

  public String getLabel() {
    return this.label;
  }
}
