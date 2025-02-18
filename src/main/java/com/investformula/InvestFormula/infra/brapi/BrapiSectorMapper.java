package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.StockSector;

public class BrapiSectorMapper {
    public static String mapToBrapiSector(StockSector sector) {
        return switch (sector) {
            case RETAIL_TRADE -> "Retail Trade";
            case ENERGY -> "Energy Minerals";
            case HEALTH -> "Health Services";
            case UTILITIES -> "Utilities";
            case FINANCE -> "Finance";
            case CONSUMER_SERVICES -> "Consumer Services";
            case NON_DURABLES -> "Consumer Non-Durables";
            case NON_ENERGY_MINERALS -> "Non-Energy Minerals";
            case COMMERCIAL_SERVICES -> "Commercial Services";
            case DISTRIBUTION -> "Distribution Services";
            case TRANSPORTATION -> "Transportation";
            case TECHNOLOGY -> "Technology Services";
            case PROCESS_INDUSTRIES -> "Process Industries";
            case COMMUNICATIONS -> "Communications";
            case PRODUCER_MANUFACTURING -> "Producer Manufacturing";
            case NULL -> "null";
            case MISCELLANEOUS -> "Miscellaneous";
            case ELECTRONIC_TECHNOLOGY -> "Electronic Technology";
            case INDUSTRIAL_SERVICES -> "Industrial Services";
            case HEALTH_TECHNOLOGY -> "Health Technology";
            case CONSUMER_DURABLES -> "Consumer Durables";
        };
    }
}
