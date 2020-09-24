package aust.iums.pg.admission.enums;

import java.awt.*;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum PaymentCategory {
    ADMISSION_APPLICATION(1, "ADMISSION APPLICATION"),
    ADMISSION(2,"ADMISSION");

    private static final Map<Integer, PaymentCategory> lookup = new HashMap<>();
    private static final Map<String, PaymentCategory> lookUpByLabel = new HashMap<>();


    static{
        for(PaymentCategory c: EnumSet.allOf(PaymentCategory.class)){
            lookup.put(c.typeCode, c);
            lookUpByLabel.put(c.getLabel(), c);
        }
    }

    private Integer typeCode;
    private String label;

    PaymentCategory(Integer typeCode, String label) {
        this.typeCode = typeCode;
        this.label = label;
    }

    public static PaymentCategory get(final Integer typeCode){
        return lookup.get(typeCode);
    }

    public static PaymentCategory getByLabel(final String label){
        return lookUpByLabel.get(label);
    }

    public Integer getValue(){
        return this.typeCode;
    }

    public String getLabel(){
        return this.label;
    }
}
