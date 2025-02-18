package com.investformula.InvestFormula.domain;

public enum Type {

    STOCK("stock"),
    FUND("fund"),
    BDR("bdr");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Type from(String value) {
        for (Type type : Type.values()) {
            if(type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum found for value: " + value);
    }
}
