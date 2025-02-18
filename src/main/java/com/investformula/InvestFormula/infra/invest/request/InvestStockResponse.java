package com.investformula.InvestFormula.infra.invest.request;

import com.investformula.InvestFormula.domain.Stock;

public record InvestStockResponse(String stockName, String formulaXValue) {
    public static InvestStockResponse from(Stock stock) {
        return new InvestStockResponse(stock.name(), stock.formulaX());
    }
}
