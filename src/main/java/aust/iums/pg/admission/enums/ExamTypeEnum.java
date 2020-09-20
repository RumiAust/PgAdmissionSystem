package aust.iums.pg.admission.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monjur-E-Morshed on 9/20/2020.
 */
public enum ExamTypeEnum {
  SSC_EQU(100, "SSC or Equivalent"),
  HSC_EQU(101, "HSC or Equivalent"),
  BBA_BCOM_BSC_BA_EQU(102, "BBA/B.Com/B.Sc./B.A./ Equivalent"),
  MCOM_MA_MBA_MSC_EQU(103, "M.Com/M.A./MBA/M.Sc.");


  private static final Map<Integer, ExamTypeEnum> Lookup = new HashMap<>();
  private static final Map<String, ExamTypeEnum> lookUpByLabel = new HashMap<>();

  static {
    for(ExamTypeEnum c : EnumSet.allOf(ExamTypeEnum.class)) {
      Lookup.put(c.typeCode, c);
      lookUpByLabel.put(c.getLabel(), c);
    }
  }

  private Integer typeCode;
  private String label;

  private ExamTypeEnum(Integer pTypeCode, String pLabel) {
    this.typeCode = pTypeCode;
    this.label = pLabel;
  }

  public static ExamTypeEnum get(final Integer pTypeCode) {
    return Lookup.get(pTypeCode);
  }

  public static ExamTypeEnum getByLabel(final String pLabel) {
    return lookUpByLabel.get(pLabel);
  }

  public Integer getValue() {
    return this.typeCode;
  }

  public String getLabel() {
    return this.label;
  }
}

