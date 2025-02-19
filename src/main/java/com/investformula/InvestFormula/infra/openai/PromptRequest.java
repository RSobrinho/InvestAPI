package com.investformula.InvestFormula.infra.openai;

import java.util.List;

public record PromptRequest(String model, List<OpenAIMessage> messages) {
}
