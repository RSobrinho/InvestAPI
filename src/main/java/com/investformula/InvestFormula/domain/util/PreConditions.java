package com.investformula.InvestFormula.domain.util;

public class PreConditions {

    public static boolean nonNull(Object value) {
        return value != null;
    }

    public static String blankWhenNull(String value) {
        if(value == null) {
            return "";
        }
        return value;
    }

}
