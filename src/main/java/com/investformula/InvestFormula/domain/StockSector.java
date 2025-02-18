package com.investformula.InvestFormula.domain;

public enum StockSector {
    RETAIL_TRADE("retail_trade"),
    ENERGY("energy"),
    HEALTH("health"),
    UTILITIES("utilities"),
    FINANCE("finance"),
    CONSUMER_SERVICES("consumer_services"),
    NON_DURABLES("non_durables"),
    NON_ENERGY_MINERALS("non_energy_minerals"),
    COMMERCIAL_SERVICES("commercial_services"),
    DISTRIBUTION("distribution"),
    TRANSPORTATION("transportation"),
    TECHNOLOGY("technology"),
    PROCESS_INDUSTRIES("process_industries"),
    COMMUNICATIONS("communications"),
    PRODUCER_MANUFACTURING("producer_manufacturing"),
    NULL("null"),
    MISCELLANEOUS("miscellaneous"),
    ELECTRONIC_TECHNOLOGY("electronic_technology"),
    INDUSTRIAL_SERVICES("industrial_services"),
    HEALTH_TECHNOLOGY("health_technology"),
    CONSUMER_DURABLES("consumer_durables");

    private final String value;

    StockSector(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static StockSector from(String value) {
        for (StockSector sector : StockSector.values()) {
            if(sector.getValue().equalsIgnoreCase(value)) {
                return sector;
            }
        }
        throw new IllegalArgumentException("No enum found for value: " + value);
    }
}
