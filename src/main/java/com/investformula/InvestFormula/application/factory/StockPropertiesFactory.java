package com.investformula.InvestFormula.application.factory;

import com.investformula.InvestFormula.domain.interfaces.DefaultFactory;
import com.investformula.InvestFormula.domain.stock.StockProperties;
import com.investformula.InvestFormula.domain.interfaces.StockPropertiesRequest;

public class StockPropertiesFactory implements DefaultFactory<StockProperties, StockPropertiesRequest> {
    public StockProperties create(StockPropertiesRequest stockPropertiesRequest) {
        return new StockProperties(
                stockPropertiesRequest.stock(),
                stockPropertiesRequest.regularMarketPrice(),
                stockPropertiesRequest.earningsPerShare(),
                stockPropertiesRequest.priceEarnings(),
                stockPropertiesRequest.fiftyTwoWeekHigh(),
                stockPropertiesRequest.fiftyTwoWeekLow(),
                stockPropertiesRequest.regularMarketDayHigh(),
                stockPropertiesRequest.regularMarketDayLow(),
                stockPropertiesRequest.regularMarketChange(),
                stockPropertiesRequest.regularMarketPreviousClose(),
                stockPropertiesRequest.regularMarketChangePercent());
    }
}
