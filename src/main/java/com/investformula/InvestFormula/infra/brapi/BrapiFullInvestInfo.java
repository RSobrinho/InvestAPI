package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.FullInvestInfo;
import com.investformula.InvestFormula.domain.interfaces.StockProperties;

import java.util.List;

public record BrapiFullInvestInfo(List<BrapiStockProperties> results, String requestedAt, String took) implements FullInvestInfo {
    @Override
    public List<? extends StockProperties> stockProperties() {
        return results;
    }

    @Override
    public StockProperties findByTicker(String ticker) {
        return stockProperties().stream()
                .filter(stock -> stock.ticker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
