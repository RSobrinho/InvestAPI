package com.investformula.InvestFormula.domain;

public class Stock {
    private final String stock;
    private final String fullName;
    private final String close;
    private final String volume;
    private final String market_cap;
    private final String logo;
    private final String sector;

    public Stock(String stock, String fullName, String close, String volume, String marketCap, String logo, String sector) {
        this.stock = stock;
        this.fullName = fullName;
        this.close = close;
        this.volume = volume;
        market_cap = marketCap;
        this.logo = logo;
        this.sector = sector;
    }

    public String name() {
        return stock;
    }

    public String formulaX() {
        return "valorDaformula";
    }
}
