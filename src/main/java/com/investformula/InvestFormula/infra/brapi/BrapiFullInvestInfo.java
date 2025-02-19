package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.FullInvestInfo;
import com.investformula.InvestFormula.domain.interfaces.ExternalStockProperties;

import java.util.List;

public record BrapiFullInvestInfo(List<BrapiExternalStockProperties> results, String requestedAt, String took) implements FullInvestInfo {
    @Override
    public List<? extends ExternalStockProperties> stockProperties() {
        return results;
    }

    @Override
    public ExternalStockProperties findByTicker(String ticker) {
        return stockProperties().stream()
                .filter(stock -> stock.ticker().equalsIgnoreCase(ticker))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
