package com.investformula.InvestFormula.infra.invest;

import com.investformula.InvestFormula.application.command.InvestContentCommand;
import com.investformula.InvestFormula.domain.util.PreConditions;

public record GetAllRequest(String sector, String limit, String type) {

    public static GetAllRequest from(String sector, String limit, String type) {
        return new GetAllRequest(sector, limit, type);
    }

    public InvestContentCommand toCommand() {
        return new InvestContentCommand(
                PreConditions.nonNull(sector) ? sector : "",
                PreConditions.nonNull(limit) ? limit : "",
                PreConditions.nonNull(type) ? type : ""
        );
    }
}
