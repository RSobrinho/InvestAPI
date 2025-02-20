package com.investformula.InvestFormula.infra;

import com.investformula.InvestFormula.domain.StockFormulas;

import java.util.Map;

public record StockFormulasResponse(Map<String, String> rawFormulas) {
    public static StockFormulasResponse from(StockFormulas stockFormulas) {
        return new StockFormulasResponse(stockFormulas.rawFormulas());
    }
}
