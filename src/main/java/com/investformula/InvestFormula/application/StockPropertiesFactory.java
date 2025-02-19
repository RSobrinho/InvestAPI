package com.investformula.InvestFormula.application;

import com.investformula.InvestFormula.domain.StockProperties;
import com.investformula.InvestFormula.domain.interfaces.ExternalStockProperties;

public class StockPropertiesFactory {
    public StockProperties create(ExternalStockProperties externalStockProperties) {
        return new StockProperties(
                externalStockProperties.ticker(),
                externalStockProperties.regularMarketPrice(),
                externalStockProperties.earningsPerShare(),
                externalStockProperties.priceEarnings(),
                externalStockProperties.fiftyTwoWeekHigh(),
                externalStockProperties.fiftyTwoWeekLow(),
                externalStockProperties.regularMarketDayHigh(),
                externalStockProperties.regularMarketDayLow(),
                externalStockProperties.regularMarketChange(),
                externalStockProperties.regularMarketPreviousClose(),
                externalStockProperties.regularMarketChangePercent());
    }
}
