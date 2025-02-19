package com.investformula.InvestFormula.domain.interfaces;

import java.util.List;

public interface FullInvestInfo {
    List<? extends ExternalStockProperties> stockProperties();
    ExternalStockProperties findByTicker(String ticker);
}
