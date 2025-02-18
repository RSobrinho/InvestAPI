package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.IndexRequest;

public record BrapiIndexes(String stock, String name) implements IndexRequest {
}
