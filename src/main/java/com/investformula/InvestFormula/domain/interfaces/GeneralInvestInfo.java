package com.investformula.InvestFormula.domain.interfaces;

import java.util.List;

public interface GeneralInvestInfo {
    List<? extends StockRequest> stocks();
    List<? extends IndexRequest> indexes();
}
