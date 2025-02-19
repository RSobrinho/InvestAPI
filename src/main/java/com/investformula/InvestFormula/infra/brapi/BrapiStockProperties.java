package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.StockProperties;

public record BrapiStockProperties(String currency, String shortName, String longName, String regularMarketChange,
                                   String regularMarketChangePercent, String regularMarketTime, String regularMarketPrice,
                                   String regularMarketDayHigh, String regularMarketDayRange, String regularMarketDayLow,
                                   String regularMarketVolume, String regularMarketPreviousClose, String regularMarketOpen,
                                   String fiftyTwoWeekRange, String fiftyTwoWeekLow, String fiftyTwoWeekHigh, String symbol,
                                   String priceEarnings, String earningsPerShare, String logourl) implements StockProperties {

    @Override
    public String ticker() {
        return this.symbol;
    }
}
