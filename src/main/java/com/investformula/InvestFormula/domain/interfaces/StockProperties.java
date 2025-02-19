package com.investformula.InvestFormula.domain.interfaces;


public interface StockProperties {
    String ticker();
    String regularMarketPrice();
    String earningsPerShare();
    String priceEarnings();
    String fiftyTwoWeekHigh();
    String fiftyTwoWeekLow();
    String regularMarketDayHigh();
    String regularMarketDayLow();
    String regularMarketChange();
    String regularMarketPreviousClose();
    String regularMarketChangePercent();
}
