package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.StockRequest;

public record BrapiStock(String stock, String name, String close, String volume,
                         String market_cap, String logo, String sector, String type) implements StockRequest {


}
