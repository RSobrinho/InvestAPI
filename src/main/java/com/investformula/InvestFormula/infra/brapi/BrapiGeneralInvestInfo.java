package com.investformula.InvestFormula.infra.brapi;

import com.investformula.InvestFormula.domain.interfaces.GeneralInvestInfo;

import java.util.List;

public record BrapiGeneralInvestInfo(List<BrapiIndexes> indexes, List<BrapiStock> stocks,
                                     List<String> availableSectors,
                                     List<String> availableStockTypes) implements GeneralInvestInfo {
}
