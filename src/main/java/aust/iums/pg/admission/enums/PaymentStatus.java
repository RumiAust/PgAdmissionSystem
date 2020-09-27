package aust.iums.pg.admission.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum  PaymentStatus {
    NOT_PAID(1, "NOT PAID"),
    PAID(2,"PAID"),
    EXPIRED(3,"EXPIRED"),
    CANCELLED(4,"CANCELLED");


    private static final Map<Integer, PaymentStatus> lookup = new HashMap<>();
    private static final Map<String, PaymentStatus> lookUpByLabel = new HashMap<>();


    static{
        for(PaymentStatus c: EnumSet.allOf(PaymentStatus.class)){
            lookup.put(c.typeCode, c);
            lookUpByLabel.put(c.getLabel(), c);
        }
    }

    private Integer typeCode;
    private String label;

    private  PaymentStatus(Integer typeCode, String label) {
        this.typeCode = typeCode;
        this.label = label;
    }

    public static PaymentStatus get(final Integer typeCode){
        return lookup.get(typeCode);
    }

    public static PaymentStatus getByLabel(final String label){
        return lookUpByLabel.get(label);
    }

    public Integer getValue(){
        return this.typeCode;
    }

    public String getLabel(){
        return this.label;
    }
}
