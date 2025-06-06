package com.investformula.InvestFormula.domain.interfaces;

import java.util.List;

public interface FullInvestInfo {
    List<? extends StockPropertiesRequest> stockProperties();
    StockPropertiesRequest findByTicker(String ticker);
}
