package com.investformula.InvestFormula.domain;

import com.investformula.InvestFormula.domain.interfaces.StockProperties;
import com.investformula.InvestFormula.infra.PreConditions;

public class Stock {
    private final StockFormulaResolver resolver;
    private final String stock;
    private final String sector;
    private final String volume;
    private String fullName;
    private String close;
    private String market_cap;
    private String logo;
    private StockProperties properties;
    private StockFormulas formulas;

    public Stock(String stock, String sector, String volume, StockFormulaResolver resolver) {
        this.stock = stock;
        this.sector = sector;
        this.volume = volume;
        this.resolver = resolver;
    }

    public String name() {
        return stock;
    }

    public void withFullProperties(StockProperties properties) {
        this.properties = properties;
    }

    public void createFormulas() {
        if(PreConditions.nonNull(properties)) {
            this.formulas = resolver.getFormulasBy(properties);
            return;
        }
        throw new IllegalArgumentException("full properties is null");
    }

    public StockFormulas getFormulas() {
        return this.formulas;
    }
}
