package com.investformula.InvestFormula.infra.invest.request;

import com.investformula.InvestFormula.domain.Stock;

import java.util.List;
import java.util.stream.Collectors;

public record InvestStocksResponse(List<InvestStockResponse> stocks) {
    public static InvestStocksResponse from(List<Stock> domainStocks) {
        List<InvestStockResponse> investStockResponseList = domainStocks.stream()
                .map(InvestStockResponse::from)
                .collect(Collectors.toList());
        return new InvestStocksResponse(investStockResponseList);
    }
}
