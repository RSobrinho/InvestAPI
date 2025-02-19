package com.investformula.InvestFormula.infra.openai;

import java.util.List;

public record PromptResponse(List<Choice> choices) {
}
