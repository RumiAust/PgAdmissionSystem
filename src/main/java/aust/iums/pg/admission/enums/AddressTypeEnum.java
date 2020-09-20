package aust.iums.pg.admission.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Monjur-E-Morshed on 9/20/2020.
 */
public enum AddressTypeEnum {
  PRESENT(0, "PRESENT ADDRESS"),
  PERMANENT(1, "PERMANENT ADDRESS");

  private static final Map<Integer, AddressTypeEnum> Lookup = new HashMap<>();
  private static final Map<String, AddressTypeEnum> lookUpByLabel = new HashMap<>();

  static {
    for(AddressTypeEnum c : EnumSet.allOf(AddressTypeEnum.class)) {
      Lookup.put(c.typeCode, c);
      lookUpByLabel.put(c.getLabel(), c);
    }
  }

  private Integer typeCode;
  private String label;

  private AddressTypeEnum(Integer pTypeCode, String pLabel) {
    this.typeCode = pTypeCode;
    this.label = pLabel;
  }

  public static AddressTypeEnum get(final Integer pTypeCode) {
    return Lookup.get(pTypeCode);
  }

  public static AddressTypeEnum getByLabel(final String pLabel) {
    return lookUpByLabel.get(pLabel);
  }

  public Integer getValue() {
    return this.typeCode;
  }

  public String getLabel() {
    return this.label;
  }
}
