package aust.iums.pg.admission.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monjur-E-Morshed on 10/6/2020.
 */
public enum ProgramEnum {
  MBA(100100, "Master's_in_MBA"),
  EMBA(100200, "Master's_in_EMBA"),
  MATH(100300, "Master's_in_Math"),
  CE(100400, "Master's in CE"),
  EEE(100500, "Master's in EEE");
  private static final Map<Integer, ProgramEnum> Lookup = new HashMap<>();
  private static final Map<String, ProgramEnum> lookUpByLabel = new HashMap<>();

  static {
    for(ProgramEnum c : EnumSet.allOf(ProgramEnum.class)) {
      Lookup.put(c.typeCode, c);
      lookUpByLabel.put(c.getLabel(), c);
    }
  }

  private Integer typeCode;
  private String label;

  private ProgramEnum(Integer pTypeCode, String pLabel) {
    this.typeCode = pTypeCode;
    this.label = pLabel;
  }

  public static ProgramEnum get(final Integer pTypeCode) {
    return Lookup.get(pTypeCode);
  }

  public static ProgramEnum getByLabel(final String pLabel) {
    return lookUpByLabel.get(pLabel);
  }

  public Integer getValue() {
    return this.typeCode;
  }

  public String getLabel() {
    return this.label;
  }
}
