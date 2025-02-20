package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.FullInvestInfo;
import com.investformula.InvestFormula.domain.interfaces.StockPropertiesRequest;

import java.util.List;

public record BrapiFullInvestInfo(List<BrapiStockPropertiesRequest> results, String requestedAt, String took) implements FullInvestInfo {
    @Override
    public List<? extends StockPropertiesRequest> stockProperties() {
        return results;
    }

    @Override
    public StockPropertiesRequest findByTicker(String ticker) {
        return stockProperties().stream()
                .filter(stock -> stock.stock().equalsIgnoreCase(ticker))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
